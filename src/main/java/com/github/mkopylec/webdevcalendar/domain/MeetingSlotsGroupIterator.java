package com.github.mkopylec.webdevcalendar.domain;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterators.getLast;

public class MeetingSlotsGroupIterator implements Iterator<List<MeetingSlot>> {

    private final List<MeetingSlot> meetingSlots;
    private final int numberOfSlotsInMeeting;
    private int currentStartingIndex = 0;

    MeetingSlotsGroupIterator(List<MeetingSlot> meetingSlots, int numberOfSlotsInMeeting) {
        checkArgument(Ordering.natural().isOrdered(meetingSlots), "Meetings slots not ordered");
        this.meetingSlots = meetingSlots;
        this.numberOfSlotsInMeeting = numberOfSlotsInMeeting;
    }

    @Override
    public boolean hasNext() {
        return getMeetingSlots(false) != null;
    }

    @Override
    public List<MeetingSlot> next() {
        return getMeetingSlots(true);
    }

    private List<MeetingSlot> getMeetingSlots(boolean increaseStaringIndex) {
        List<MeetingSlot> group = new ArrayList<>();
        for (int i = currentStartingIndex; i < meetingSlots.size(); i++) {
            MeetingSlot slot = meetingSlots.get(i);
            if (group.isEmpty()) {
                group.add(slot);
            } else {
                MeetingSlot lastGroupSlot = getLast(group.iterator());
                if (slot.isNotNextAfter(lastGroupSlot)) {
                    group.clear();
                }
                group.add(slot);
            }
            if (group.size() == numberOfSlotsInMeeting) {
                if (increaseStaringIndex) {
                    currentStartingIndex = i - numberOfSlotsInMeeting + 2;
                }
                return group;
            }
        }
        return null;
    }
}
