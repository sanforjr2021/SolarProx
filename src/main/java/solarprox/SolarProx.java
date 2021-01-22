/**
 * Class: SolarProx
 * Author: Jacob Sanford
 * Date: 1/21/2021
 * Description: This is the main file for launching and using solar prox, as well as connecting all modules of the
 *              the project. There should be 3 methods in here at once. SolarProx for on startup including event listeners,
 *              a shutdown thread, and the main method to launch the program.
 */
package solarprox;

import solarprox.util.FileLogger;

import static solarprox.util.FileLogger.*;

public class SolarProx {
    private FileLogger fileLogger;
    public SolarProx(){
        fileLogger = new FileLogger();
        logInfo("SolarProx is Initialized");

        Runtime.getRuntime().addShutdownHook(onShutdown()); //calls shutdown thread on attempting to exit
    }

    private Thread onShutdown(){
        return new Thread(){
            public void run(){
                fileLogger.onExit();

            }
        };
    }
    public static void main(String[] args){
        new SolarProx();
    }
}
