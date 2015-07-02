package com.github.mkopylec.webdevcalendar.application;

import javax.ws.rs.WebApplicationException;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class NoMeetingsFoundException extends WebApplicationException {

    public NoMeetingsFoundException(String message) {
        super(message, NOT_FOUND);
    }
}
