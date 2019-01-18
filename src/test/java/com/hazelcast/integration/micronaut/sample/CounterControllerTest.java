package com.hazelcast.integration.micronaut.sample;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CounterControllerTest {

    private static EmbeddedServer server1;
    private static EmbeddedServer server2;
    private static HttpClient client1;
    private static HttpClient client2;

    @BeforeClass
    public static void setupServer() {
        server1 = ApplicationContext.run(EmbeddedServer.class);
        server2 = ApplicationContext.run(EmbeddedServer.class);
        client1 = server1.getApplicationContext() .createBean(HttpClient.class, server1.getURL());
        client2 = server2.getApplicationContext() .createBean(HttpClient.class, server2.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (server1 != null) {
            server1.stop();
        }
        if (server2 != null) {
            server2.stop();
        }
        if (client1 != null) {
            client1.stop();
        }
        if (client2 != null) {
            client2.stop();
        }
    }

    @After
    public void after(){
        client1.toBlocking().exchange(HttpRequest.DELETE("/api/reset"));
    }

    @Test
    public void testIncrement() {
        MutableHttpRequest<Object> incrementRequest = HttpRequest.GET("/api/incrementByCaching?counterName=test");
        MutableHttpRequest<Object> getRequest = HttpRequest.GET("/api/get?counterName=test");
        Argument<Integer> integerArgument = Argument.of(int.class);
        Integer retrieve = client1.toBlocking().retrieve(incrementRequest, integerArgument);
        assertEquals(1, retrieve);
        retrieve = client2.toBlocking().retrieve(getRequest, integerArgument);
        assertEquals(1, retrieve);
        retrieve = client2.toBlocking().retrieve(incrementRequest, integerArgument);
        assertEquals(2, retrieve);
        retrieve = client1.toBlocking().retrieve(getRequest, integerArgument);
        assertEquals(2, retrieve);
    }

    @Test
    public void testIncrementWithoutCache() {
        MutableHttpRequest<Object> incrementWithCacheRequest = HttpRequest.GET("/api/incrementByCaching?counterName=test");
        MutableHttpRequest<Object> incrementWithoutCacheRequest = HttpRequest.GET("/api/incrementWithoutCaching?counterName=test");
        MutableHttpRequest<Object> getRequest = HttpRequest.GET("/api/get?counterName=test");
        Argument<Integer> integerArgument = Argument.of(int.class);
        Integer retrieve = client1.toBlocking().retrieve(incrementWithCacheRequest, integerArgument);
        assertEquals(1, retrieve);
        retrieve = client1.toBlocking().retrieve(getRequest, integerArgument);
        assertEquals(1, retrieve);
        retrieve = client1.toBlocking().retrieve(incrementWithoutCacheRequest, integerArgument);
        assertEquals(2, retrieve);
        retrieve = client1.toBlocking().retrieve(getRequest, integerArgument);
        assertEquals(1, retrieve);
    }

}
