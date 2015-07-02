package com.github.mkopylec.webdevcalendar.application;

import com.github.mkopylec.webdevcalendar.api.Meeting;
import com.github.mkopylec.webdevcalendar.domain.MeetingSlot;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.Iterators.getLast;

@Component
public class MeetingFactory {

    public Meeting createMeetingFromSlots(List<MeetingSlot> meetingSlots) {
        LocalDateTime start = meetingSlots.get(0).getStartDate();
        LocalDateTime end = getLast(meetingSlots.iterator()).getEndDate();
        return new Meeting(start, end);
    }
}
