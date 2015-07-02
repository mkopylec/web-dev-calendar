package com.github.mkopylec.webdevcalendar.api;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class MeetingConstraints {

    @Min(value = 1, message = "Meeting length must be at least 1 minute")
    private int meetingLengthInMinutes;

    @Min(value = 1, message = "Number of meetings to find must be at least 1")
    private int meetingsToFind;

    @NotNull(message = "Empty meeting earliest starting date")
    @TimeSlotBoundary(message = "Invalid meeting earliest starting date")
    private LocalDateTime earliestStartingDate;

    @NotNull(message = "Empty meeting latest ending date")
    @TimeSlotBoundary(message = "Invalid meeting latest ending date")
    private LocalDateTime latestEndingDate;

    @NotEmpty(message = "Empty meeting attendees list")
    private List<Attendee> attendees;

    public int getMeetingLengthInMinutes() {
        return meetingLengthInMinutes;
    }

    public int getMeetingsToFind() {
        return meetingsToFind;
    }

    public LocalDateTime getEarliestStartingDate() {
        return earliestStartingDate;
    }

    public LocalDateTime getLatestEndingDate() {
        return latestEndingDate;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }
}
