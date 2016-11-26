package org.lavr.repository;

import org.lavr.api.Vehicle;
import org.testng.annotations.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class InMemoryVehicleRepositoryTest {
    @Test
    public void testGetByVin() throws Exception {
        // Given
        InMemoryVehicleRepository repository = new InMemoryVehicleRepository();
        ConcurrentMap<String, Vehicle> vehicles = new ConcurrentHashMap<>();

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("123");
        vehicle.setMake("Bmw");
        vehicle.setModel("X5");
        vehicle.setYear(2005);

        vehicles.put(vehicle.getVin(), vehicle);
        repository.setVehicles(vehicles);

        // When
        Vehicle vehicleActual = repository.getByVin(vehicle.getVin());

        // Then
        assertEquals(vehicleActual, vehicle);
    }

    @Test
    public void testInsert() throws Exception {
        // Given
        InMemoryVehicleRepository repository = new InMemoryVehicleRepository();
        System.out.println("test test");
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("123");
        vehicle.setMake("Bmw");
        vehicle.setModel("X5");
        vehicle.setYear(2005);

        // When
        repository.insert(vehicle);

        // Then
        Vehicle vehicleActual = repository.getVehicles().get(vehicle.getVin());
        assertNotNull(vehicleActual);
        assertEquals(vehicleActual, vehicle);
    }

}