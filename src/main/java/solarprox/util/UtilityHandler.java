
package solarprox.util;


public class UtilityHandler {
    private static FileLogger fileLogger;
    private static ConfigHandler configHandler;
    public UtilityHandler(){
        fileLogger = new FileLogger();
        configHandler = new ConfigHandler();
    }
    //called upon shutdown. Any Utility file that needs shutdown may be called here.
    public void onShutdown(){
        fileLogger.onExit();
    }
}
