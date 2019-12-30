package fr.aureliejosephine.mareu.modele;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import fr.aureliejosephine.mareu.R;

public class Reunion {

    private String lieu;
    private String sujet;
    private String email;
    private String date;
    private String heure;
    private int avatar;


    public Reunion(String lieu, String sujet, String email, String date, String heure) {
        this.lieu = lieu;
        this.sujet = sujet;
        this.email = email;
        this.date = date;
        this.heure = heure;
    }

    public Reunion(String lieu, String sujet, String email, String date, String heure, int avatar) {
        this.lieu = lieu;
        this.sujet = sujet;
        this.email = email;
        this.date = date;
        this.heure = heure;
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
