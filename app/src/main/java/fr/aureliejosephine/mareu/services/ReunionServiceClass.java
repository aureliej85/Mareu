package fr.aureliejosephine.mareu.services;

import java.util.List;

import fr.aureliejosephine.mareu.modele.Reunion;

public class ReunionServiceClass implements ReunionService {

    private List<Reunion> reunions = ReunionGenerator.generateReunions();

    @Override
    public List<Reunion> getReunion() {
        return reunions;
    }

    @Override
    public void addReunion(Reunion reunion) {
        reunions.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }
}
