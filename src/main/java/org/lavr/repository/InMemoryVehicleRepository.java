package org.lavr.repository;

import org.lavr.api.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryVehicleRepository implements VehicleRepository {

    private ConcurrentMap<String, Vehicle> vehicles = new ConcurrentHashMap<>();

    @Override
    public List<Vehicle> getAll() {
        return new ArrayList<>(vehicles.values());
    }

    @Override
    public Vehicle getByVin(String vin) {
        return vehicles.get(vin);
    }

    @Override
    public void insert(Vehicle vehicle) {
        vehicles.putIfAbsent(vehicle.getVin(), vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicles.put(vehicle.getVin(), vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicles.remove(vehicle.getVin());
    }
}
