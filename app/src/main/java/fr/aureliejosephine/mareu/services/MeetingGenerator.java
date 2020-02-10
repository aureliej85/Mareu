package fr.aureliejosephine.mareu.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.aureliejosephine.mareu.R;
import fr.aureliejosephine.mareu.modele.Meeting;

public abstract class MeetingGenerator {

    public static List<String> fakeEmails = Arrays.asList("lauryn@gmail.fr", "lylo@gmail.com");
    public static List<String> fakeEmails2 = Arrays.asList("noemie@gmail.fr", "nono@gmail.com");
    public static List<String> fakeEmails3 = Arrays.asList("sylvere@gmail.fr", "laurent@gmail.com");

    //List of dummy meetings
    public static  List<Meeting> listMeeting = Arrays.asList(
            new Meeting("Réunion A", "Sujet 1","5/3/2020", "7h00", R.drawable.rubis_lens_24dp, fakeEmails),
            new Meeting("Réunion J", "Sujet 2","29/7/2020", "11h00", R.drawable.corail_lens_4dp, fakeEmails2),
            new Meeting("Réunion H", "Sujet 3","24/9/2020", "14h00", R.drawable.grege_lens_24dp, fakeEmails3)
    );


     static List<Meeting> generateMeetings(){
        return new ArrayList<>(listMeeting);
    }



}



