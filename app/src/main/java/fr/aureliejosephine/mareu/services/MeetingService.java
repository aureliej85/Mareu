package fr.aureliejosephine.mareu.services;

import java.util.List;

import fr.aureliejosephine.mareu.modele.Meeting;

public interface MeetingService {

    List<Meeting> getMeeting();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);


}
