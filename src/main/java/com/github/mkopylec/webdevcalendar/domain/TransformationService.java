package com.github.mkopylec.webdevcalendar.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransformationService {

    public void sortSlotsChronologically(MeetingSlotsSchedule schedule) {
        MeetingSlotsTransformer chronologicalSorter = new ChronologicalSorter();
        schedule.applyTransformer(chronologicalSorter);
    }

    public void filterSlotsByTimeFrame(MeetingSlotsSchedule schedule, LocalDateTime start, LocalDateTime end) {
        MeetingSlotsTransformer timeFrameFilter = new TimeFrameFilter(start, end);
        schedule.applyTransformer(timeFrameFilter);
    }

    public void filterSlotsByNumberOfParticipants(MeetingSlotsSchedule schedule, int numberOfParticipants) {
        MeetingSlotsTransformer participantsQuantityFilter = new ParticipantsQuantityFilter(numberOfParticipants);
        schedule.applyTransformer(participantsQuantityFilter);
    }
}
