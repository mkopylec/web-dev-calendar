package com.github.mkopylec.webdevcalendar.application;

import com.github.mkopylec.webdevcalendar.infrastructure.ObjectMapperCustomizer;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(MeetingsApplicationService.class);
        register(ObjectMapperCustomizer.class);
        register(WebApplicationExceptionMapper.class);
    }
}
