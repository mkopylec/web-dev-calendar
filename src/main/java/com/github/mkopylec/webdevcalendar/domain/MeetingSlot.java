package com.github.mkopylec.webdevcalendar.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

import static com.github.mkopylec.webdevcalendar.domain.MeetingSlotsSchedule.TIME_SLOT_MINUTE_STEP;

public class MeetingSlot implements Comparable<MeetingSlot> {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private int possibleParticipants = 0;

    MeetingSlot(LocalDateTime startDate) {
        this.startDate = startDate;
        endDate = startDate.plusMinutes(TIME_SLOT_MINUTE_STEP);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    boolean hasStartDate(LocalDateTime date) {
        return this.startDate.equals(date);
    }

    boolean isNotBetween(LocalDateTime start, LocalDateTime end) {
        return startDate.isBefore(start) || endDate.isAfter(end);
    }

    boolean isNotNextAfter(MeetingSlot meetingSlot) {
        return !meetingSlot.endDate.equals(startDate);
    }

    int getNumberOfPossibleParticipants() {
        return possibleParticipants;
    }

    void addPossibleParticipant() {
        possibleParticipants++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MeetingSlot)) return false;

        MeetingSlot that = (MeetingSlot) o;

        return new EqualsBuilder()
                .append(startDate, that.startDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(startDate)
                .toHashCode();
    }

    @Override
    public int compareTo(MeetingSlot meetingSlot) {
        return startDate.compareTo(meetingSlot.startDate);
    }
}
