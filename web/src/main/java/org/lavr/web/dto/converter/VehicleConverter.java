package org.lavr.web.dto.converter;

import org.lavr.api.Vehicle;
import org.lavr.web.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleConverter {
    public VehicleDto convert(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        if (vehicle == null) {
            return null;
        }
        vehicleDto.setVin(vehicle.getVin());
        vehicleDto.setMake(vehicle.getMake());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setYear(vehicle.getYear());

        return vehicleDto;
    }

    public Vehicle convert(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        if (vehicleDto == null) {
            return null;
        }
        vehicle.setVin(vehicleDto.getVin());
        vehicle.setMake(vehicleDto.getMake());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setYear(vehicleDto.getYear());

        return vehicle;
    }
}
