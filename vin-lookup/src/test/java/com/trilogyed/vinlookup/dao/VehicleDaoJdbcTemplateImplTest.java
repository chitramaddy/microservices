package com.trilogyed.vinlookup.dao;

import com.trilogyed.vinlookup.model.Vehicle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VehicleDaoJdbcTemplateImplTest {

    @Autowired
    protected VehicleDao vehicleDao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Vehicle> vList = vehicleDao.getAllVehicles();

        vList.stream()
                .forEach(vehicle -> vehicleDao.deleteVehicle(vehicle.getVin()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteVehicle() {

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("12345");
        vehicle.setType("Motorcycle");
        vehicle.setMake("Honda");
        vehicle.setModel("Africa Twin");
        vehicle.setYear("2019");
        vehicle.setColor("black");

        vehicle = vehicleDao.addVehicle(vehicle);

        Vehicle vehicle1 = vehicleDao.getVehicle(vehicle.getVin());

        assertEquals(vehicle, vehicle1);

        vehicleDao.deleteVehicle(vehicle.getVin());

        vehicle1 = vehicleDao.getVehicle(vehicle.getVin());
        assertNull(vehicle1);

    }

    @Test
    public void getAllVehicles() {

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("22222");
        vehicle.setType("Motorcycle");
        vehicle.setMake("Honda");
        vehicle.setModel("CB300");
        vehicle.setYear("2019");
        vehicle.setColor("red");

        vehicleDao.addVehicle(vehicle);

        vehicle = new Vehicle();

        vehicle.setVin("33333");
        vehicle.setType("Motorcycle");
        vehicle.setMake("Suzuki");
        vehicle.setModel("DR650");
        vehicle.setYear("2012");
        vehicle.setColor("gray");

        vehicleDao.addVehicle(vehicle);

        List<Vehicle> vList = vehicleDao.getAllVehicles();

        assertEquals(vList.size(), 2);
    }
}