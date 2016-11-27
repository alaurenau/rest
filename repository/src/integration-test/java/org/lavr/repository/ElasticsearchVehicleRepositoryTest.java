package org.lavr.repository;

import org.lavr.api.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ElasticsearchVehicleRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    @Qualifier("elasticsearchVehicleRepository")
    private VehicleRepository repository;

    @Test
    public void testGetByVin() throws Exception {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("123");
        vehicle.setMake("Bmw");
        vehicle.setModel("X5");
        vehicle.setYear(2005);

        repository.insert(vehicle);

        // When
        Vehicle vehicleActual = repository.getByVin(vehicle.getVin());

        // Then
        assertEquals(vehicleActual, vehicle);
    }

    @Test
    public void testInsert() throws Exception {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1243");
        vehicle.setMake("Bmw");
        vehicle.setModel("X5");
        vehicle.setYear(2005);

        // When
        repository.insert(vehicle);

        // Then
        Vehicle vehicleActual = repository.getByVin(vehicle.getVin());
        assertNotNull(vehicleActual);
        assertEquals(vehicleActual, vehicle);
    }

}