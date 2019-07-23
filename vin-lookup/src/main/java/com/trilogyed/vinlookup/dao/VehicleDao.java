package com.trilogyed.vinlookup.dao;

import com.trilogyed.vinlookup.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    Vehicle getVehicle(String vin);

    List<Vehicle> getAllVehicles();

    Vehicle addVehicle(Vehicle vehicle);

    void updateVehicle(Vehicle vehicle);

    void deleteVehicle(String vin);
}
