package fr.aureliejosephine.mareu.DI;

import fr.aureliejosephine.mareu.services.MeetingService;
import fr.aureliejosephine.mareu.services.MeetingServiceClass;

public class DI {

        private static MeetingService service = new MeetingServiceClass();

        public static MeetingService getMeetingService() {
            return service;
        }

        public static MeetingService getNewInstanceService() {
            return new MeetingServiceClass();
        }

}
