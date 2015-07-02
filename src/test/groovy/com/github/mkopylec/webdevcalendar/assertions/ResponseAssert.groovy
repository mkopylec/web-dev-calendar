package com.github.mkopylec.webdevcalendar.assertions

import com.github.mkopylec.webdevcalendar.api.Meeting

import javax.ws.rs.core.GenericType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

import static javax.ws.rs.core.Response.Status.NOT_FOUND
import static javax.ws.rs.core.Response.Status.OK

class ResponseAssert {

    private final Response actual
    private List<Meeting> meetings

    protected ResponseAssert(Response actual) {
        this.actual = actual
    }

    ResponseAssert hasOkStatus() {
        return hasStatus(OK)
    }

    ResponseAssert hasNotFoundStatus() {
        return hasStatus(NOT_FOUND)
    }

    ResponseAssert containsBody(String body) {
        assertNotNull()
        assert actual.readEntity(String) == body
        return this
    }

    ResponseAssert containsMeetings(int numberOfMeetings) {
        assertNotNull()
        meetings = actual.readEntity(new GenericType<List<Meeting>>() {})
        assertNotNull()
        assert meetings.size() == numberOfMeetings
        return this
    }

    MeetingAssert whereMeeting(int meetingIndex) {
        assertNotNull()
        assertNotNullMeetings()
        return new MeetingAssert(meetings[meetingIndex - 1], this)
    }

    private ResponseAssert hasStatus(Status status) {
        assertNotNull()
        assert actual.statusInfo.statusCode == status.statusCode
        return this
    }

    private void assertNotNull() {
        assert actual != null
    }

    private void assertNotNullMeetings() {
        assert meetings != null
    }
}
