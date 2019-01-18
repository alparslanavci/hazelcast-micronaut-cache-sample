package com.hazelcast.integration.micronaut.sample;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CacheInvalidate;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.cache.annotation.Cacheable;

import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@CacheConfig(cacheNames = {"counter"})
public class CounterService {
    private Map<String, Integer> counters = new ConcurrentHashMap<>();

    public int incrementWithoutCaching(String name) {
        int value = counters.computeIfAbsent(name, s -> 0);
        counters.put(name, ++value);
        return value;
    }

    @CachePut
    public int increment(String name) {
        int value = getValue(name);
        counters.put(name, ++value);
        return value;
    }

    @Cacheable
    public int getValue(String name) {
        return counters.computeIfAbsent(name, s -> 0);
    }

    @CacheInvalidate(all = true)
    public void reset() {
        counters.clear();
    }

    @CacheInvalidate()
    public void reset(String name) {
        counters.remove(name);
    }

    @CacheInvalidate
    public void set(String name, int val) {
        counters.put(name, val);
    }
}
