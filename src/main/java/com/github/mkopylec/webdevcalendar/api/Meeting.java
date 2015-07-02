package com.github.mkopylec.webdevcalendar.api;

import java.time.LocalDateTime;

public class Meeting {

    private LocalDateTime start;
    private LocalDateTime end;

    public Meeting(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    private Meeting() {
    }
}
