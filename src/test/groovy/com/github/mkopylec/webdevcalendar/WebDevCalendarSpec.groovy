package com.github.mkopylec.webdevcalendar

import static com.github.mkopylec.webdevcalendar.assertions.CustomAssertions.assertThat
import static com.github.mkopylec.webdevcalendar.utils.FileLoader.loadJsonFile
import static javax.ws.rs.client.Entity.json

class WebDevCalendarSpec extends BasicSpec {

    private static final String MEETINGS_ENDPOINT_PATH = 'meetings'

    def "Should find 3 possible meetings that all attendees can participate"() {
        given:
        def meetingConstraints = loadJsonFile('exact_meetings_found_input.json')

        when:
        def response = getWebTarget(MEETINGS_ENDPOINT_PATH)
                .request()
                .post(json(meetingConstraints));

        then:
        assertThat(response)
                .hasOkStatus()
                .containsMeetings(3)
                .whereMeeting(1).startsAt('2015-07-01T10:00').endsAt('2015-07-01T11:00')
                .and()
                .whereMeeting(2).startsAt('2015-07-01T10:30').endsAt('2015-07-01T11:30')
                .and()
                .whereMeeting(3).startsAt('2015-07-02T10:00').endsAt('2015-07-02T11:00')
    }

    def "Should fail to find 4 possible meetings when at least six meetings cannot be arranged"() {
        given:
        def meetingConstraints = loadJsonFile('not_enough_meetings_found_input.json')

        when:
        def response = getWebTarget(MEETINGS_ENDPOINT_PATH)
                .request()
                .post(json(meetingConstraints));

        then:
        assertThat(response)
                .hasNotFoundStatus()
                .containsBody('Cannot find 4 possible meetings for the given constraints')
    }

    def "Should fail to find 3 possible meetings when at no meeting can be arranged"() {
        given:
        def meetingConstraints = loadJsonFile('no_meetings_found_input.json')

        when:
        def response = getWebTarget(MEETINGS_ENDPOINT_PATH)
                .request()
                .post(json(meetingConstraints));

        then:
        assertThat(response)
                .hasNotFoundStatus()
                .containsBody('Cannot find any possible meetings for the given constraints')
    }
}