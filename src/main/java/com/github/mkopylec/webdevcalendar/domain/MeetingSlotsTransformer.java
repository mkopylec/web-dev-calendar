package com.github.mkopylec.webdevcalendar.domain;

import java.util.List;

interface MeetingSlotsTransformer {

    void transform(List<MeetingSlot> meetingSlots);
}
