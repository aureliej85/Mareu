package fr.aureliejosephine.mareu.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Reunion {

    private String lieu;
    private String sujet;
    private String date;
    private String heure;
    private int avatar;
    private ArrayList<String> addEmail;


    public Reunion(){

    }

    public Reunion(String lieu, String sujet, String date, String heure) {
        this.lieu = lieu;
        this.sujet = sujet;
        this.date = date;
        this.heure = heure;
    }

    public Reunion(String lieu, String sujet, String date, String heure, int avatar) {
        this.lieu = lieu;
        this.sujet = sujet;
        this.date = date;
        this.heure = heure;
        this.avatar = avatar;
        addEmail = new ArrayList<>();
    }


    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
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
