package com.github.mkopylec.webdevcalendar.domain;

import java.util.List;

import static java.util.Collections.sort;

class ChronologicalSorter implements MeetingSlotsTransformer {

    @Override
    public void transform(List<MeetingSlot> meetingSlots) {
        sort(meetingSlots);
    }
}
