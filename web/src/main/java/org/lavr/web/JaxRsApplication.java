package org.lavr.web;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.lavr.web.resource.JaxRsResource;
import org.lavr.web.resource.VehicleResource;

public class JaxRsApplication extends ResourceConfig {

    public JaxRsApplication() {
        // App resources
        registerClasses(JaxRsResource.class, VehicleResource.class);
        // Swagger resources
        registerClasses(ApiListingResource.class, SwaggerSerializers.class);
    }

}