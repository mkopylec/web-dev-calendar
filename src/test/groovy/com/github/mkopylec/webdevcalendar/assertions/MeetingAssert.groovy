package com.github.mkopylec.webdevcalendar.assertions

import com.github.mkopylec.webdevcalendar.api.Meeting
import groovy.transform.PackageScope

import static java.time.LocalDateTime.parse

@PackageScope
class MeetingAssert {

    private final Meeting actual
    private final ResponseAssert responseAssert

    protected MeetingAssert(Meeting actual, ResponseAssert responseAssert) {
        this.responseAssert = responseAssert
        this.actual = actual
    }

    MeetingAssert startsAt(String start) {
        assertNotNull()
        assert actual.start == parse(start)
        return this
    }

    MeetingAssert endsAt(String end) {
        assertNotNull()
        assert actual.end == parse(end)
        return this
    }

    ResponseAssert and() {
        assertNotNull()
        return responseAssert
    }

    private void assertNotNull() {
        assert actual != null
    }
}
