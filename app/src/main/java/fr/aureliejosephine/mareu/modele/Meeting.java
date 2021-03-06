package fr.aureliejosephine.mareu.modele;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Meeting implements Comparable<Meeting>{

    private String room;
    private String subject;
    private String date;
    private String hour;
    private int avatar;
    private List<String> emailList;


    //CONSTRUCTORS
    public Meeting(String room, String date, String hour) {
        this.room = room;
        this.date = date;
        this.hour = hour;

    }

    public Meeting(String room, String subject, String date, String hour, int avatar, List<String> emailList){
        this.room = room;
        this.subject = subject;
        this.date = date;
        this.hour = hour;
        this.avatar = avatar;
        this.emailList = emailList;
    }


    //GETTERS & SETTERS
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }


    @Override
    public int compareTo(Meeting o) {
        if (getDate() == null || o.getDate() == null) {
            return 0;
        }

        return getDate().compareTo(o.getDate());
    }
}
