package server;

import database.Database;
import fileio.Input;

public class Server {

    private static Server instance = null;

    public Server() { }

    /**
     * returns the program's server - singleton
     * @return server's instance
     */
    public static Server getServer() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    /**
     * the program entry point
     * @param input input
     */
    public void entryPoint(final Input input) {
        Database.getDatabase().loadData(input);
        Simulation.startSimulation();
    }
}
