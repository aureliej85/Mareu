package fr.aureliejosephine.mareu.DI;

import fr.aureliejosephine.mareu.services.ReunionService;
import fr.aureliejosephine.mareu.services.ReunionServiceClass;

public class DI {

        private static ReunionService service = new ReunionServiceClass();

        public static ReunionService getReunionService() {
            return service;
        }

        public static ReunionService getNewInstanceService() {
            return new ReunionServiceClass();
        }

}
