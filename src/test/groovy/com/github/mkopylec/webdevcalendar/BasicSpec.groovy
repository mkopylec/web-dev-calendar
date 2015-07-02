package com.github.mkopylec.webdevcalendar

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import org.glassfish.jersey.client.ClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.WebTarget

import static javax.ws.rs.client.ClientBuilder.newClient

@WebIntegrationTest
@ActiveProfiles('test')
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = WebDevCalendar)
abstract class BasicSpec extends Specification {

    @Shared
    private ObjectMapper mapper = new ObjectMapper()
    @Shared
    private Client client

    @Value('${server.port}')
    private int serverPort;

    void setupSpec() {
        mapper.registerModule(new JavaTimeModule())
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider()
        jacksonProvider.mapper = mapper
        client = newClient(new ClientConfig(jacksonProvider))
    }

    protected WebTarget getWebTarget(String path) {
        return client.target("http://localhost:$serverPort").path(path)
    }

    void cleanupSpec() {
        client.close()
    }
}
