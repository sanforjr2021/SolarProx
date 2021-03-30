package solarprox.data;

import org.json.simple.JSONObject;
import solarprox.util.ConfigHandler;

import java.io.File;
import java.io.IOException;

public class ProxmoxHandler {
    private static String username, password, realm, address;

    public ProxmoxHandler(String username, String password, String realm, String address) {
        ProxmoxHandler.username = username;
        ProxmoxHandler.password = password;
        ProxmoxHandler.realm = realm;
        ProxmoxHandler.address = address;
    }

    /**
     * Connects to Proxmox. Returns true if it is successful and false if it is not.
     *
     * @return
     */
    public static boolean connectToProxmox() {
        return false;
    }

    ///////////////////////
    //  Utility Methods  //
    ///////////////////////

    /**
     * Disconnects to Proxmox. Returns true if it is successful and false if it is not.
     *
     * @return
     */
    public static boolean disconnectFromProxmox() {
        return false;
    }

    /**
     * Returns default snapshot
     * @param machineID
     * @return
     * @throws IOException
     */
    public static void rollback(String machineID) throws IOException {
        File f = new File("scripts/rollback.sh");
        System.out.println(f.isFile());
        System.out.println(f.getAbsolutePath());
        Process p = new ProcessBuilder(f.getAbsolutePath(), machineID).start();
        System.out.println("Rolling back machine " + machineID);
    }

    ///////////////////////////
    //  Snapshot Management  //
    ///////////////////////////

    /**
     * Rolls back to a certain snapshot
     * @param machineID
     * @param stateName
     * @return
     * @throws IOException
     */
    public static int rollback(String machineID, String stateName) throws IOException {
        Process p = new ProcessBuilder("scripts/stop.sh", machineID, stateName).start();
        System.out.println("Called rollback on " + machineID + " with statname of " + stateName);
        return 1;
    }

    /**
     * Returns a list of snapshots.
     *
     * @return
     */
    public static JSONObject getMachineSnapshots(String machineID) {
        return null;
    }

    /**
     * Take a snapshot of the device
     *
     * @return
     */
    public static JSONObject captureSnapshot(String machineID) {
        return null;
    }

    /**
     * Retrieves a list of available machines on the network.
     *
     * @return
     */
    public static JSONObject getListOfMachines() {
        return null;
    }

    /////////////////////////
    //  Machine Management //
    /////////////////////////

    /**
     * Shuts down all machines
     *
     * @return
     */
    public static JSONObject powerOffAll() {
        return null;
    }


    /**
     * Turn on all machines
     *
     * @return
     */
    public static JSONObject powerOnAll() {
        return null;
    }

    /**
     * Turns on a certain machine
     * @param machineID
     * @return
     * @throws IOException
     */
    public static int powerOn(String machineID) throws IOException {
        File f = new File("./scripts/start.sh");
        System.out.println(f.isFile());
        Process p = new ProcessBuilder(f.getAbsolutePath(), machineID).start();
        System.out.println("Powering on machine " + machineID);
        return 0;
    }

    /**
     * Runs when the program is shutting down.
     */
    public void onShutdown() {
       disconnectFromProxmox();

    }

    /**
     * Turns off the machine
     * @param machineID
     * @return
     * @throws IOException
     */
    public static int powerOff(String machineID) throws IOException {
        Process p = new ProcessBuilder("scripts/stop.sh", machineID).start();
        System.out.println("Stopping machine " + machineID);
        return 0;
    }

    /**
     * Retrieves status information about a machine
     *
     * @return
     */
    public static JSONObject getStatus(String machineID) {
        return null;
    }
    public static String getTestAttempt(){
        return ConfigHandler.getProperty(password);
    }
}