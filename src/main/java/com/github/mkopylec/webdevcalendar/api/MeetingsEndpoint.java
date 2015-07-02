package com.github.mkopylec.webdevcalendar.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("meetings")
@Produces(APPLICATION_JSON)
public interface MeetingsEndpoint {

    @POST
    @Consumes(APPLICATION_JSON)
    List<Meeting> findPossibleMeetings(
            @NotNull(message = "Empty meeting constraints") @Valid MeetingConstraints constraints
    );
}
