package fr.aureliejosephine.mareu;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;
import fr.aureliejosephine.mareu.services.MeetingGenerator;
import fr.aureliejosephine.mareu.services.MeetingService;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MeetingListTest {
    private MeetingService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeeting();
        List<Meeting> expectedMeetings = MeetingGenerator.listMeeting;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void addMeetingWithSuccess(){
        Meeting newMeeting = new Meeting("Réunion A", "12/12/2010", "11h00");
        service.addMeeting(newMeeting);
        assertTrue(service.getMeeting().contains(newMeeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void filterMeetingsWithSuccess(){
        List<Meeting> fullList = service.getMeeting();
        List<Meeting> filteredList = fullList.stream()
                .filter(x -> "24/9/2020".equals(x.getDate()))
                .collect(Collectors.toList());
        assertThat(filteredList, hasSize(1));
    }
}
