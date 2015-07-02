package com.github.mkopylec.webdevcalendar.domain;

import java.time.LocalDateTime;
import java.util.List;

class TimeFrameFilter implements MeetingSlotsTransformer {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeFrameFilter(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void transform(List<MeetingSlot> meetingSlots) {
        meetingSlots.removeIf(meetingSlot -> meetingSlot.isNotBetween(start, end));
    }
}
