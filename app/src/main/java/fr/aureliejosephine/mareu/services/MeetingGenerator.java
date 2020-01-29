package fr.aureliejosephine.mareu.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.aureliejosephine.mareu.R;
import fr.aureliejosephine.mareu.modele.Meeting;

public abstract class MeetingGenerator {


    public static List<Meeting> listMeeting = Arrays.asList(
            new Meeting("Réunion A", "Sujet 1","5/3/2020", "7h00", R.drawable.rubis_lens_24dp),
            new Meeting("Réunion J", "Sujet 2","29/7/2020", "11h00", R.drawable.corail_lens_4dp),
            new Meeting("Réunion H", "Sujet 3","24/9/2020", "14h00", R.drawable.grege_lens_24dp)
    );



    static List<Meeting> generateMeetings(){
        return new ArrayList<>(listMeeting);
    }




}
