package fr.aureliejosephine.mareu.services;

import java.util.List;

import fr.aureliejosephine.mareu.modele.Meeting;

public class MeetingServiceClass implements MeetingService {

    private List<Meeting> meetings = MeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

}
