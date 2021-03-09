package solarprox.data;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    /**
     * Updates values of a user in active directory
     *
     * @param user
     * @return
     */
    public static boolean updateUser(UserContainer user) {
        throw new NotImplementedException();
    }

    /**
     * Retrieves a list of all users in active directory
     *
     * @return
     */
    public static ArrayList<UserContainer> getAllUsers() {  //Maybe be easier and faster as a list of strings instead
        throw new NotImplementedException();
    }

    /**
     * Retrieves a user by their username
     *
     * @param username
     * @return
     */
    public static UserContainer getUserByUsername(String username) {
        throw new NotImplementedException();
    }

    /**
     * Adds a Group to the active directory
     *
     * @param group
     * @return
     */
    public static boolean addGroup(GroupContainer group) {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    /**
     * Updates values of a Group in active directory
     *
     * @param group
     * @return
     */
    public static boolean updateGroup(GroupContainer group) {
        throw new NotImplementedException();
    }

    /**
     * Retrieves a list of all Groups in active directory
     *
     * @return
     */
    public static ArrayList<GroupContainer> getAllGroups() {  //Maybe be easier and faster as a list of strings instead
        throw new NotImplementedException();
    }

    /**
     * Adds a machine to the active directory
     *
     * @param machine
     * @return
     */
    public static boolean addMachine(MachineContainer machine) {
        throw new NotImplementedException();
    }

    /**
     * Deletes a machine from active directory
     *
     * @param machine
     * @return
     */
    public static boolean removeMachine(MachineContainer machine) {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    /**
     * Retrieves a list of all machines in active directory
     *
     * @return
     */
    public static ArrayList<MachineContainer> getAllMachines() {
        //Maybe be easier and faster as a list of strings instead
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    /**
     * Retrieves a machine by their name
     *
     * @param name
     * @return
     */
    public GroupContainer getMachineByName(String name) {
        throw new NotImplementedException();
    }

}
