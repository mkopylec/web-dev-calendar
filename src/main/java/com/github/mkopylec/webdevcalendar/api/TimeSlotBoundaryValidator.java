package com.github.mkopylec.webdevcalendar.api;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

import static com.github.mkopylec.webdevcalendar.domain.MeetingSlotsSchedule.TIME_SLOT_MINUTE_STEP;

public class TimeSlotBoundaryValidator implements ConstraintValidator<TimeSlotBoundary, LocalDateTime> {

    @Override
    public void initialize(TimeSlotBoundary constraint) {
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return isFullOrHalfAnHour(value) && isFullMinute(value);
    }

    private boolean isFullMinute(LocalDateTime value) {
        return value.getSecond() == 0
                && value.getNano() == 0;
    }

    private boolean isFullOrHalfAnHour(LocalDateTime value) {
        return value.getMinute() == 0 || value.getMinute() == TIME_SLOT_MINUTE_STEP;
    }
}
