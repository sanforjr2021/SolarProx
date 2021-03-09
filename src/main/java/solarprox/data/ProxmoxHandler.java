package solarprox.data;

import org.json.simple.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    /**
     * Rolls back a device to a certain snapshot.
     *
     * @return
     */
    public static JSONObject rollback(String machineID, String snapshotID) {
        throw new NotImplementedException();
    }

    ///////////////////////////
    //  Snapshot Management  //
    ///////////////////////////

    /**
     * Rolls back a device to the latest snapshot.
     *
     * @return
     */
    public static JSONObject rollback(String machineID) {
        throw new NotImplementedException();
    }

    /**
     * Returns a list of snapshots.
     *
     * @return
     */
    public static JSONObject getMachineSnapshots(String machineID) {
        throw new NotImplementedException();
    }

    /**
     * Take a snapshot of the device
     *
     * @return
     */
    public static JSONObject captureSnapshot(String machineID) {
        throw new NotImplementedException();
    }

    /**
     * Retrieves a list of available machines on the network.
     *
     * @return
     */
    public static JSONObject getListOfMachines() {
        throw new NotImplementedException();
    }

    /////////////////////////
    //  Machine Management //
    /////////////////////////

    /**
     * Shuts down all machines
     *
     * @return
     */
    public static JSONObject powerOff() {
        throw new NotImplementedException();
    }

    /**
     * Turns off a specific machine
     *
     * @return
     */
    public static JSONObject powerOff(String machineID) {
        throw new NotImplementedException();
    }

    /**
     * Turns on a all machines
     *
     * @return
     */
    public static JSONObject powerOn() {
        throw new NotImplementedException();
    }

    /**
     * Runs when the program is shutting down.
     */
    public void onShutdown() {
        //disconnectFromProxmox  <- Commented out till not throwing exception
        System.out.println("Shutdown from ProxmoxHandler");
    }

    /**
     * Turns on a specific machine
     *
     * @return
     */
    public JSONObject powerOn(String machineID) {
        throw new NotImplementedException();
    }

    /**
     * Retrieves status information about a machine
     *
     * @return
     */
    public JSONObject getStatus(String machineID) {
        throw new NotImplementedException();
    }
}