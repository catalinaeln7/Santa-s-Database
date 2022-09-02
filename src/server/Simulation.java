package server;

import database.Database;
import database.DatabaseUpdate;

public final class Simulation {
    private Simulation() { }

    /**
     * starts the simulation for year 0 and year 1 - numberOfYears
     */
    public static void startSimulation() {
        int initialYear = -1;
        int numberOfYears = Database.getDatabase().getNumberOfYears();

        Year.yearFlow(initialYear);
        for (int yr = 0; yr < numberOfYears; ++yr) {
            DatabaseUpdate.addChangesToDatabase(yr);
            Year.yearFlow(yr);
        }
    }
}
