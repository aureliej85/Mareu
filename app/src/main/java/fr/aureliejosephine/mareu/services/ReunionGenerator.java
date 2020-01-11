package fr.aureliejosephine.mareu.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.aureliejosephine.mareu.R;
import fr.aureliejosephine.mareu.modele.Reunion;

public abstract class ReunionGenerator {


    public static List<Reunion> LIST_REUNION = Arrays.asList(
            new Reunion("Rubis", "Sujet 1","01/01/2020", "15:00", R.drawable.rubis_lens_24dp ),
            new Reunion("Corail", "Sujet 2", "05/03/2020", "11:00", R.drawable.corail_lens_4dp)
    );

    static List<Reunion> generateReunions(){
        return new ArrayList<>(LIST_REUNION);
    }
}
