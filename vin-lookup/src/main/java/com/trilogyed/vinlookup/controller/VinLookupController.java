package com.trilogyed.vinlookup.controller;

import com.trilogyed.vinlookup.dao.VehicleDao;
import com.trilogyed.vinlookup.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Random;

@RestController
@RefreshScope
public class VinLookupController {
    @Autowired
    private VehicleDao vehicleDao;

    @RequestMapping (value = "/vehicle/{vin}", method = RequestMethod.GET)
    public Vehicle lookUpVehicle(@PathVariable String vin) {

        return vehicleDao.getVehicle(vin);

    }
}
