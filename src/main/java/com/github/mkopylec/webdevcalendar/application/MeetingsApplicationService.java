package com.github.mkopylec.webdevcalendar.application;

import com.github.mkopylec.webdevcalendar.api.Attendee;
import com.github.mkopylec.webdevcalendar.api.Meeting;
import com.github.mkopylec.webdevcalendar.api.MeetingConstraints;
import com.github.mkopylec.webdevcalendar.api.MeetingsEndpoint;
import com.github.mkopylec.webdevcalendar.api.TimeSlot;
import com.github.mkopylec.webdevcalendar.domain.MeetingSlot;
import com.github.mkopylec.webdevcalendar.domain.MeetingSlotsSchedule;
import com.github.mkopylec.webdevcalendar.domain.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingsApplicationService implements MeetingsEndpoint {

    private final TransformationService transformationService;
    private final MeetingFactory meetingFactory;

    @Autowired
    public MeetingsApplicationService(TransformationService transformationService, MeetingFactory meetingFactory) {
        this.transformationService = transformationService;
        this.meetingFactory = meetingFactory;
    }

    @Override
    public List<Meeting> findPossibleMeetings(MeetingConstraints constraints) {
        MeetingSlotsSchedule schedule = new MeetingSlotsSchedule(constraints.getMeetingLengthInMinutes());
        fillInSchedule(constraints, schedule);
        filterSchedule(constraints, schedule);
        List<Meeting> meetings = getAllPossibleMeetings(schedule);
        if (meetings.isEmpty()) {
            throw new NoMeetingsFoundException("Cannot find any possible meetings for the given constraints");
        }
        return getRestrictedNumberOfMeetings(constraints, meetings);
    }

    private void fillInSchedule(MeetingConstraints constraints, MeetingSlotsSchedule schedule) {
        for (Attendee attendee : constraints.getAttendees()) {
            for (TimeSlot timeSlot : attendee.getWorkingHours()) {
                schedule.addMeetingSlotIfNotExist(timeSlot.getStartingDate(), timeSlot.isFree());
            }
        }
    }

    private void filterSchedule(MeetingConstraints constraints, MeetingSlotsSchedule schedule) {
        transformationService.sortSlotsChronologically(schedule);
        transformationService.filterSlotsByTimeFrame(schedule, constraints.getEarliestStartingDate(), constraints.getLatestEndingDate());
        transformationService.filterSlotsByNumberOfParticipants(schedule, constraints.getAttendees().size());
    }

    private List<Meeting> getAllPossibleMeetings(MeetingSlotsSchedule schedule) {
        List<Meeting> meetings = new ArrayList<>();
        for (List<MeetingSlot> meetingSlots : schedule) {
            Meeting meeting = meetingFactory.createMeetingFromSlots(meetingSlots);
            meetings.add(meeting);
        }
        return meetings;
    }

    private List<Meeting> getRestrictedNumberOfMeetings(MeetingConstraints constraints, List<Meeting> meetings) {
        int meetingsToFind = constraints.getMeetingsToFind();
        if (meetings.size() < meetingsToFind) {
            throw new NoMeetingsFoundException("Cannot find " + meetingsToFind + " possible meetings for the given constraints");
        }
        return meetings.subList(0, meetingsToFind);
    }
}
