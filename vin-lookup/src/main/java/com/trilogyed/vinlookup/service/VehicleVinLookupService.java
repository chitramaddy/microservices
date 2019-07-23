package com.trilogyed.vinlookup.service;

import com.trilogyed.vinlookup.dao.VehicleDao;
import com.trilogyed.vinlookup.model.Vehicle;
import com.trilogyed.vinlookup.viewmodel.VehicleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleVinLookupService {

    private VehicleDao vehicleDao;

    @Autowired

    public VehicleVinLookupService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public VehicleViewModel findVehicleByVin(String vin) {
        Vehicle vehicle = vehicleDao.getVehicle(vin);
        if (vehicle == null)
            return null;
        else
            return buildVehicleViewModel(vehicle);
    }

    private VehicleViewModel buildVehicleViewModel(Vehicle vehicle) {

        VehicleViewModel vehicleViewModel = new VehicleViewModel();
        vehicleViewModel.setVin(vehicle.getVin());
        vehicleViewModel.setType(vehicle.getType());
        vehicleViewModel.setMake(vehicle.getMake());
        vehicleViewModel.setModel(vehicle.getModel());
        vehicleViewModel.setYear(vehicle.getYear());
        vehicleViewModel.setColor(vehicle.getColor());

        return vehicleViewModel;
    }


}
