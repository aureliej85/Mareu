package fr.aureliejosephine.mareu.services;

import java.util.Calendar;
import java.util.List;

import fr.aureliejosephine.mareu.modele.Meeting;

public interface MeetingService {

    enum DateFilter {
        NONE,
        BEFORE,
        MATCH,
        AFTER
    }

    List<Meeting> getMeeting();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> getMeetingsFilteredByDate(Calendar date, DateFilter filterType);


}
