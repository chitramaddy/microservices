package com.example.motoinventoryservice.controller;

import com.example.motoinventoryservice.dao.MotoInventoryDao;
import com.example.motoinventoryservice.model.Motorcycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RefreshScope
public class MotoInventoryController {

    @Autowired
    private MotoInventoryDao motoInventoryDao;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${randomVinLookupName}")
    private String randomVinLookupName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    @RequestMapping(value = "/motorcycles", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Motorcycle createMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {

        motoInventoryDao.addMotorcycle(motorcycle);
        return motorcycle;
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Motorcycle getMotorcycle(@PathVariable int motoId) {
        if (motoId < 1) {
            throw new IllegalArgumentException("MotoId must be greater than 0.");
        }
        Motorcycle moto = motoInventoryDao.getMotorcycle(motoId);

        return moto;
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMotorcycle(@PathVariable("motoId") int motoId) {
        // do nothing here - in a real application we would delete the entry from
        // the backing data store.

        motoInventoryDao.deleteMotorcycle(motoId);
    }

    @RequestMapping(value = "/motorcycles/{motoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMotorcycle(@RequestBody @Valid Motorcycle motorcycle, @PathVariable int motoId) {
        // make sure the motoId on the path matches the id of the motorcycle object
        if (motoId != motorcycle.getId()) {
            throw new IllegalArgumentException("Motorcycle ID on path must match the ID in the Motorcycle object.");
        }

        // do nothing here - in a real application we would update the entry in the backing data store
        motoInventoryDao.updateMotorcycle(motorcycle);
    }

    @RequestMapping(value = "/vehicle/{vin}")
    public Map<String,String> getMotorcycleWithVin(@PathVariable String vin){
        List<ServiceInstance> instances = discoveryClient.getInstances(randomVinLookupName);
        System.out.println(instances.get(0).getHost());
        System.out.println(instances.get(0).getPort());

        String randomVinLookupUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath+"/"+vin;
        Motorcycle moto = restTemplate.getForObject(randomVinLookupUri, Motorcycle.class);

        Map<String, String> vehicle = new HashMap<>();

        vehicle.put("Vehicle Type", "Motorcycle");
        vehicle.put("Vehicle Make", moto.getMake());
        vehicle.put("Vehicle Model", moto.getModel());
        vehicle.put("Vehicle Year", moto.getYear());
        vehicle.put("Vehicle Color", moto.getColor());

        return vehicle;
    }


}

