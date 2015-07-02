package com.github.mkopylec.webdevcalendar.api;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class Attendee {

    @NotBlank(message = "Empty attendee name")
    private String name;

    @NotEmpty(message = "Empty attendee working hours")
    private List<TimeSlot> workingHours;

    public String getName() {
        return name;
    }

    public List<TimeSlot> getWorkingHours() {
        return workingHours;
    }
}
