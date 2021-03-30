/*
 * Class: SolarProx
 * Author: Jacob Sanford
 * Date: 1/21/2021
 * Description: This is the main file for launching and using solar prox, as well as connecting all modules of the
 *              the project. There should be 3 methods in here at once. SolarProx for on startup including event listeners,
 *              a shutdown thread, and the main method to launch the program.
 */
package solarprox;

import solarprox.data.DirectoryHandler;
import solarprox.data.ProxmoxHandler;
import solarprox.util.ConfigHandler;
import solarprox.util.UtilityHandler;
import solarprox.web.TomcatHandler;


public class SolarProx {
    public static UtilityHandler  utilityHandler = new UtilityHandler();
    public static DirectoryHandler directoryHandler = new DirectoryHandler();
    public static ProxmoxHandler proxmoxHandler = new ProxmoxHandler();
    public static TomcatHandler tomcatHandler = new TomcatHandler();

    public SolarProx(){
        Runtime.getRuntime().addShutdownHook(onShutdown()); //calls shutdown thread on attempting to exit
    }

    //This is called when the program shutdown. Every module should have a class that handles shutting down the module to make it easier to read the calls.
    private Thread onShutdown(){
        return new Thread(){
            public void run(){
                tomcatHandler.onShutdown();
                proxmoxHandler.onShutdown();
                directoryHandler.onShutdown();
                utilityHandler.onShutdown();
                System.out.println("Shutting Down");
            }
        };
    }
    public static void main(String[] args){
        new SolarProx();
    }
}
