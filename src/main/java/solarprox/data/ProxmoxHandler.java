package solarprox.data;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ProxmoxHandler {

    public ProxmoxHandler() {

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
        Process p = new ProcessBuilder("scripts/rollback.sh", machineID).start();
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
        Process p = new ProcessBuilder("scripts/stop.sh", machineID).start();
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
}