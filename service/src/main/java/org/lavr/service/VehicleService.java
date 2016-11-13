package org.lavr.service;

import org.lavr.api.Vehicle;
import org.lavr.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle getVehicle(String vin) {
        return vehicleRepository.getByVin(vin);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.update(vehicle);
    }
}
