# Sample Application of Hazelcast Cache Integration for Micronaut

This repository contains a sample counter application for demonstrating Hazelcast Cache Integration for [Micronaut](http://micronaut.io/).
Please see the [Hazelcast Cache Integration for Micronaut](https://github.com/alparslanavci/hazelcast-micronaut-cache) repository for details.

# Requirements

- Maven

# Usage

- Run `com.hazelcast.integration.micronaut.sample.Application` class to start web server. You can configure the port by providing it as a program argument.
- Navigate to your web server address with the following API paths:
    - `[SERVER_ADDRESS:PORT]/api/incrementByCaching?counterName=[your_counter_name]`: To increment the counter by caching
    - `[SERVER_ADDRESS:PORT]/api/incrementWithoutCaching?counterName=[your_counter_name]`: To increment the counter without caching
    - `[SERVER_ADDRESS:PORT]/api/get?counterName=[your_counter_name]`: To get the latest value of your counter

You can multiple applications at the same time to build a cluster. Please note that you need to configure `application.yml`, `hazelcast.xml`, and `hazelcast-client.xml` properly to build the cluster. For more details, please visit [Hazelcast Cache Integration for Micronaut](https://github.com/alparslanavci/hazelcast-micronaut-cache) repository.