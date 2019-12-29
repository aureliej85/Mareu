package fr.aureliejosephine.mareu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Reunion;
import fr.aureliejosephine.mareu.services.ReunionService;
import fr.aureliejosephine.mareu.services.ReunionServiceClass;

import static fr.aureliejosephine.mareu.services.ReunionGenerator.LIST_REUNION;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;



@RunWith(JUnit4.class)
public class ListReunionTest {

    private ListReunionActivity ListReunion;

    @Before
    public void setup() {
        ListReunion = DI.getReunionService();
    }

    @Test
    public void getUsersWithSuccess() {
        List<Reunion> usersActual = ReunionServiceClass.getReunion;
        List<Reunion> usersExpected = LIST_REUNION;
        assertThat(usersActual, containsInAnyOrder(usersExpected.toArray()));
    }