package com.hazelcast.integration.micronaut.sample;

import io.micronaut.http.annotation.*;

@Controller("/api")
public class CounterController {

    private CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @Get("/incrementByCaching{?counterName}") int incrementByCaching(String counterName) {
        return counterService.increment(counterName);
    }

    @Get("/incrementWithoutCaching{?counterName}") int incrementWithoutCaching(String counterName) {
        return counterService.incrementWithoutCaching(counterName);
    }

    @Get("/get{?counterName}") int get(String counterName) {
        return counterService.getValue(counterName);
    }

    @Delete("/reset") void reset() {
        counterService.reset();
    }
}