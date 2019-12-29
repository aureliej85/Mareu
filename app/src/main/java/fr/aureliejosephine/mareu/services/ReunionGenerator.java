package fr.aureliejosephine.mareu.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.aureliejosephine.mareu.modele.Reunion;

public abstract class ReunionGenerator {

    public static List<Reunion> LIST_REUNION = Arrays.asList(
            new Reunion("salle 1", "Sujet 1", "aurelie@wanadoo.fr , mamadou@caramail.net", "01/01/2020", "15:00"),
            new Reunion("salle 2", "Sujet 2", "petitpoucet@monchemin.com", "05/03/2020", "11:00")
    );

    static List<Reunion> generateReunions(){
        return new ArrayList<>(LIST_REUNION);
    }
}
