package com.trilogyed.vinlookup.service;

import com.trilogyed.vinlookup.dao.VehicleDao;
import com.trilogyed.vinlookup.dao.VehicleDaoJdbcTemplateImpl;
import com.trilogyed.vinlookup.model.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;

public class VehicleVinLookupServiceTest {

    VehicleDao vehicleDao;
    VehicleVinLookupService vehicleVinLookupService;

    @Before
    public void setUp() throws Exception {

        vehicleVinLookupService = new VehicleVinLookupService(vehicleDao);
    }

    private void setUpVehicleDaoMock(){

        vehicleDao = mock(VehicleDaoJdbcTemplateImpl.class);

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("12345");
        vehicle.setType("Motorcycle");
        vehicle.setMake("Honda");
        vehicle.setModel("XL");
        vehicle.setYear("2009");
        vehicle.setColor("red");

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVin("12345");
        vehicle1.setType("Motorcycle");
        vehicle1.setMake("Honda");
        vehicle1.setModel("XL");
        vehicle1.setYear("2009");
        vehicle1.setColor("red");

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);

        doReturn(vehicle).when(vehicleDao).getVehicle("12345");
    }


    @Test
    public void findVehicleByVin() {


    }
}