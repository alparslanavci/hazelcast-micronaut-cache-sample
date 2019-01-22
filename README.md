# Sample Application of Hazelcast Cache Integration for Micronaut

This repository contains a sample counter application for demonstrating Hazelcast Cache Integration for [Micronaut](http://micronaut.io/).
Please see the [Hazelcast Cache Integration for Micronaut](https://github.com/alparslanavci/hazelcast-micronaut-cache) repository for details.

# Requirements

- Maven
- `hazelcast-micronaut-cache` Module
- `curl` or any other HTTP client

# Usage

- Install `hazelcast-micronaut-cache` module to your local Maven repository
- Run `com.hazelcast.integration.micronaut.sample.Application` class to start web server. You can configure the port by providing it as a program argument. For instance;
```
mvn exec:java -Dexec.args="8090"
```
- Increase the counter by updating the cache using the following HTTP request:

```
curl -X GET http://localhost:8090/api/incrementByCaching?counterName=myCounter
```

- Get the latest value of the counter from cache by using the following HTTP request. You will see that the value is the same as the one returned in previous request:

```
curl -X GET http://localhost:8090/api/get?counterName=myCounter
```

- Now, increase the counter **without** updating the cache using the following HTTP request. The increased value will be returned:

```
curl -X GET http://localhost:8090/api/incrementWithoutCaching?counterName=myCounter
```

- Again, get the latest value of the counter from cache by using the following HTTP request. Now, you will see that the returned value is the one from the first increase request, since the value is get from the cache.

```
curl -X GET http://localhost:8090/api/get?counterName=myCounter
```

- To play with the sample, please find the available endpoints below:
    - `[SERVER_ADDRESS:PORT]/api/incrementByCaching?counterName=[your_counter_name]`: (*Request Type: GET*) To increment the counter by caching
    - `[SERVER_ADDRESS:PORT]/api/incrementWithoutCaching?counterName=[your_counter_name]`: (*Request Type: GET*) To increment the counter without caching
    - `[SERVER_ADDRESS:PORT]/api/get?counterName=[your_counter_name]`: (*Request Type: GET*) To get the latest value of your counter from cache
    - `[SERVER_ADDRESS:PORT]/api/reset?counterName=[your_counter_name]`: (*Request Type: DELETE*) To reset your counter

You can multiple applications at the same time to build a cluster. Please note that you need to configure `application.yml`, `hazelcast.xml`, and `hazelcast-client.xml` properly to build the cluster. For more details, please visit [Hazelcast Cache Integration for Micronaut](https://github.com/alparslanavci/hazelcast-micronaut-cache) repository.