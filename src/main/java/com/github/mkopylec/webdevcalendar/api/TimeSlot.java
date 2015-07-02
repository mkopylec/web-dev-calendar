package com.github.mkopylec.webdevcalendar.api;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TimeSlot {

    @NotNull(message = "Empty time slot starting date")
    @TimeSlotBoundary(message = "Invalid time slot starting date")
    private LocalDateTime startingDate;

    private boolean free;

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public boolean isFree() {
        return free;
    }
}
