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
import solarprox.util.FileLogger;
import solarprox.util.UtilityHandler;
import solarprox.web.TomcatHandler;

import static solarprox.util.FileLogger.*;

public class SolarProx {
    private UtilityHandler  utilityHandler;
    private DirectoryHandler directoryHandler;
    private ProxmoxHandler proxmoxHandler;
    private TomcatHandler tomcatHandler;

    public SolarProx(){
        //Called at starting the program.
        utilityHandler = new UtilityHandler();
        directoryHandler = new DirectoryHandler();
        proxmoxHandler = new ProxmoxHandler();
        tomcatHandler = new TomcatHandler();
        Runtime.getRuntime().addShutdownHook(onShutdown()); //calls shutdown thread on attempting to exit
    }

    //This is called when the program shutdown. Every module should have a class that handles shutting down the module to make it easier to read the calls.
    private Thread onShutdown(){
        return new Thread(){
            public void run(){
                utilityHandler.onShutdown();
                directoryHandler.onShutdown();
                proxmoxHandler.onShutdown();
                tomcatHandler.onShutdown();
                System.out.println("Shutting Down");
            }
        };
    }
    public static void main(String[] args){
        new SolarProx();
    }
}
