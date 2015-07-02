package com.github.mkopylec.webdevcalendar.domain;

import java.util.List;

class ParticipantsQuantityFilter implements MeetingSlotsTransformer {

    private final int numberOfParticipants;

    public ParticipantsQuantityFilter(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public void transform(List<MeetingSlot> meetingSlots) {
        meetingSlots.removeIf(meetingSlot -> meetingSlot.getNumberOfPossibleParticipants() != numberOfParticipants);
    }
}
