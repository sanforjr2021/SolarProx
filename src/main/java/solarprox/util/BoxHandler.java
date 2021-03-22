package solarprox.util;

import java.io.IOException;

public class BoxHandler {
    private String boxID;

    public BoxHandler(String ID) {
        boxID = ID;
    }

    public int startBox() throws IOException {
        Process p = new ProcessBuilder("./start.sh", boxID).start();
        return 0;
    }

    public int stopBox() throws IOException {
        Process p = new ProcessBuilder("./stop.sh", boxID).start();
        return 0;
    }


    public int rollback(String stateName) throws IOException {
        Process p = new ProcessBuilder("./stop.sh", boxID, stateName).start();
        return 0;
    }

    public int rollback() throws IOException {
        Process p = new ProcessBuilder("./stop.sh", boxID).start();
        return 0;
    }
}