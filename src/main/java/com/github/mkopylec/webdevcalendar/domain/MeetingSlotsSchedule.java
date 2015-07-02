package com.github.mkopylec.webdevcalendar.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.find;

public class MeetingSlotsSchedule implements Iterable<List<MeetingSlot>> {

    public static final int TIME_SLOT_MINUTE_STEP = 30;

    private final int meetingLengthInMinutes;
    private final List<MeetingSlot> meetingSlots = new ArrayList<>();

    public MeetingSlotsSchedule(int meetingLengthInMinutes) {
        this.meetingLengthInMinutes = meetingLengthInMinutes;
    }

    public void addMeetingSlotIfNotExist(LocalDateTime meetingSlotDate, boolean isFree) {
        MeetingSlot meetingSlot = findMeetingSlot(meetingSlotDate);
        if (meetingSlot == null) {
            meetingSlot = new MeetingSlot(meetingSlotDate);
            meetingSlots.add(meetingSlot);
        }
        if (isFree) {
            meetingSlot.addPossibleParticipant();
        }
    }

    public MeetingSlotsSchedule applyTransformer(MeetingSlotsTransformer transformer) {
        transformer.transform(meetingSlots);
        return this;
    }

    @Override
    public Iterator<List<MeetingSlot>> iterator() {
        int numberOfSlotsInMeeting = meetingLengthInMinutes / TIME_SLOT_MINUTE_STEP + 1;
        return new MeetingSlotsGroupIterator(meetingSlots, numberOfSlotsInMeeting);
    }

    private MeetingSlot findMeetingSlot(LocalDateTime meetingSlotDate) {
        return find(meetingSlots, slot -> slot.hasStartDate(meetingSlotDate));
    }
}
