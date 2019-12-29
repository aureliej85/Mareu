package fr.aureliejosephine.mareu.services;

import java.util.List;

import fr.aureliejosephine.mareu.modele.Reunion;

public interface ReunionService {

    List<Reunion> getReunion();

    void addReunion(Reunion reunion);

    void deleteReunion(Reunion reunion);
}
