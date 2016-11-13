package org.lavr.web;

import org.lavr.web.resource.JaxRsResource;
import org.lavr.web.resource.VehicleResource;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JaxRsApplication extends Application {
    private final Set<Class<?>> classes;

    public JaxRsApplication() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(JaxRsResource.class);
        c.add(VehicleResource.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}