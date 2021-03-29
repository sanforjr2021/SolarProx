package solarprox.data;



import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

public class DirectoryHandler {
    public DirectoryHandler() {

    }

    /**
     * Adds a user to the active directory
     *
     * @param user
     * @return
     */
    public static boolean addUser(UserContainer user) {
        return false;
    }

    //////////////////////////
    // utility sub methods  //
    //////////////////////////

    // This could be any method that often needs to be called multiple times but may or may
    // not be required for public access. An example would be logging into active directory

    ////////////////////////
    //   User Management  //
    ////////////////////////

    /**
     * Deletes a user from active directory
     *
     * @param user
     * @return
     */
    public static boolean removeUser(UserContainer user) {
        return false;
    }

    /**
     * Updates values of a user in active directory
     *
     * @param user
     * @return
     */
    public static boolean updateUser(UserContainer user) {
        return false;
    }

    /**
     * Retrieves a list of all users in active directory
     *
     * @return
     */
    public static ArrayList<UserContainer> getAllUsers() {  //Maybe be easier and faster as a list of strings instead
        return null;
    }

    /**
     * Retrieves a user by their username
     *
     * @param username
     * @return
     */
    public static UserContainer getUserByUsername(String username) {
        return null;
    }

    /**
     * Adds a Group to the active directory
     *
     * @param group
     * @return
     */
    public static boolean addGroup(GroupContainer group) {
        return false;
    }

    //////////////////////////
    //   Group Management //
    //////////////////////////

    /**
     * Deletes a Group from active directory
     *
     * @param group
     * @return
     */
    public static boolean removeGroup(UserContainer group) {
       return false;
    }

    /**
     * Updates values of a Group in active directory
     *
     * @param group
     * @return
     */
    public static boolean updateGroup(GroupContainer group) {

        return false;
    }

    /**
     * Retrieves a list of all Groups in active directory
     *
     * @return
     */
    public static ArrayList<GroupContainer> getAllGroups() {  //Maybe be easier and faster as a list of strings instead
        return null;
    }

    /**
     * Adds a machine to the active directory
     *
     * @param machine
     * @return
     */
    public static boolean addMachine(MachineContainer machine) {
        return false;
    }

    /**
     * Deletes a machine from active directory
     *
     * @param machine
     * @return
     */
    public static boolean removeMachine(MachineContainer machine) {
        return false;
    }

    //////////////////////////
    //   Machine Management //
    //////////////////////////

    /**
     * Updates values of a machine in active directory
     *
     * @param machine
     * @return
     */
    public static boolean updateMachine(MachineContainer machine) {
        return false;
    }

    /**
     * Retrieves a list of all machines in active directory
     *
     * @return
     */
    public static ArrayList<MachineContainer> getAllMachines() {
        //Maybe be easier and faster as a list of strings instead
        return null;
    }

    /**
     * Called upon shutting down the software.
     */
    public void onShutdown() {
    }

    /**
     * Retrieves a group by their name
     *
     * @param name
     * @return
     */
    public GroupContainer getGroupByName(String name) {
        return null;
    }

    /**
     * Retrieves a machine by their name
     *
     * @param name
     * @return
     */
    public GroupContainer getMachineByName(String name) {
        return null;
    }

}
