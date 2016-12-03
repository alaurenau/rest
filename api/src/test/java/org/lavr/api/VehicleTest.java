package org.lavr.api;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VehicleTest {
    @Test
    public void testSetVin() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("vin");

        assertEquals(vehicle.getVin(), "vin");
    }

}