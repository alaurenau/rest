package org.lavr.web.resource;

import org.lavr.api.Vehicle;
import org.lavr.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Resource
@Path("api/v1/")
public class VehicleResource {

    @Autowired
    private VehicleService vehicleService;


    /**
     * Method returns vehicle by its vin
     *
     * @return vehicle
     */
    @GET
    @Path("vehicle/{vin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vehicle getVehicle(@PathParam("vin") String vin) {
        return vehicleService.getVehicle(vin);
    }

    /**
     * Method creates vehicle
     *
     * @return status
     */
    @PUT
    @Path("vehicle/{vin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertVehicle(Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return Response.status(Response.Status.CREATED).build();
    }
}
