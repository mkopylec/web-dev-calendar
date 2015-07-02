package com.github.mkopylec.webdevcalendar.assertions

import javax.ws.rs.core.Response

class CustomAssertions {

    static ResponseAssert assertThat(Response actual) {
        return new ResponseAssert(actual)
    }
}
