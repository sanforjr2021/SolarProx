package solarprox.data;

import solarprox.util.ConfigHandler;
import solarprox.util.UtilityHandler;

public class ProxmoxHandler {
    private static final String username = ConfigHandler.getProperty("username");
    private static final String password = ConfigHandler.getProperty("password");

    public ProxmoxHandler(){

    }

    public void onShutdown(){

    }
}
