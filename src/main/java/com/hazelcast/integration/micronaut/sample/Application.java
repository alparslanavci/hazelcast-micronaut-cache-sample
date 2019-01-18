package com.hazelcast.integration.micronaut.sample;

import io.micronaut.runtime.Micronaut;

/**
 * This is a sample application for demonstration of Hazelcast Cache for Micronaut Framework. It is a simple
 * web application with endpoints for a cached counter.
 */
public class Application {

    public static void main(String[] args) {
        String port = "8080";
        if (args != null && args.length > 0){
            port = args[0];
        }
        Micronaut.run(Application.class, "-Dmicronaut.server.port=" + port);
    }
}