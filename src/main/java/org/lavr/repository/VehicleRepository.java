package org.lavr.repository;

import org.lavr.api.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> getAll();

    Vehicle getByVin(String vin);

    void insert(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Vehicle vehicle);
}
