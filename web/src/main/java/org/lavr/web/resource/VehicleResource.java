package org.lavr.web.resource;

import io.swagger.annotations.*;
import org.lavr.service.VehicleService;
import org.lavr.web.dto.VehicleDto;
import org.lavr.web.dto.converter.VehicleConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Resource
@Api(value = "Vehicle Api", description = "Provides ability to store and get vehicles.")
@Path("api/v1/")
public class VehicleResource {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleConverter vehicleConverter;

    /**
     * Method returns vehicle by its vin.
     *
     * @return vehicle
     */
    @GET
    @ApiOperation(value = "Find by vin method.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of vehicle", response = VehicleDto.class),
            @ApiResponse(code = 404, message = "Vehicle with given vin does not exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    @Path("vehicle/{vin}")
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleDto getVehicle(@ApiParam(name = "vin", value = "Vehicle vin", required = true)
                              @PathParam("vin") String vin) {
        VehicleDto vehicleDto = vehicleConverter.convert(vehicleService.getVehicle(vin));
        if (vehicleDto == null) {
            throw new NotFoundException("Vehicle with vin: " + vin + " not found.");
        }
        return vehicleDto;
    }

    /**
     * Method creates vehicle.
     *
     * @return status
     */
    @PUT
    @ApiOperation(value = "Save vehicle method.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vehicle successfully saved of vehicle"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    @Path("vehicle/{vin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertVehicle(@ApiParam(name = "vehicle", value = "Vehicle object", required = true)
                                          VehicleDto vehicle) {
        vehicleService.addVehicle(vehicleConverter.convert(vehicle));
        return Response.status(Response.Status.CREATED).build();
    }
}
