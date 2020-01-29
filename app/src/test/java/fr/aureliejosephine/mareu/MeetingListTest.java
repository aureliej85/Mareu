package fr.aureliejosephine.mareu;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;
import fr.aureliejosephine.mareu.services.MeetingGenerator;
import fr.aureliejosephine.mareu.services.MeetingService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class MeetingListTest {
    private MeetingService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceService();
    }

    @Test
    public void getReunionsWithSuccess() {
        List<Meeting> meetings = service.getMeeting();
        List<Meeting> expectedNeighbours = MeetingGenerator.listMeeting;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }
}
