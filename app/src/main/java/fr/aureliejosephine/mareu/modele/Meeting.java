package fr.aureliejosephine.mareu.modele;



import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Meeting {

    private String room;
    private String subject;
    private String date;
    private String hour;
    private int avatar;
    private ArrayList<String> addEmail;
    private String fakeEmail;



    public Meeting(){
        addEmail = new ArrayList<>();
    }

    public Meeting(String room, String date, String hour) {
        this.room = room;
        this.date = date;
        this.hour = hour;
    }

    public Meeting(String room, String subject, String date, String hour, int avatar){
        this.room = room;
        this.subject = subject;
        this.date = date;
        this.hour = hour;
        this.avatar = avatar;
        addEmail = new ArrayList<>();
    }

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

    public ArrayList<String> getAddEmail() {
        return addEmail;
    }

    public void setAddEmail(ArrayList<String> emailList) {
        this.addEmail = emailList;
    }

    public void addEmails(String string) {
        this.addEmail.add(string);
    }


}
