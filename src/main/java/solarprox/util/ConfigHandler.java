/*
 * Class: ConfigHandler
 * Author: Jacob Sanford
 * Date: 1/20/2021
 * Description: Allows reading and writing of a a config file called "Config.properties".
 *              The Config is intended to be initialized once and then be accessible via static access.
 */
package solarprox.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private static final Properties PROPERTIES = new Properties();
    private static final File FILE = new File("config.properties");
    private static final double VERSIONNUMBER = 1.1;
    public ConfigHandler() {
        try {
            //Upon creating an empty file, write
            if (FILE.createNewFile()) {
                createNewConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadConfig();
    }

    private void createNewConfig() {
        try {
            FileWriter writer = new FileWriter(FILE);
            writer.write("version= " + VERSIONNUMBER);
            //these are default config settings - Please keep all values lowercase or camel case.
            //when defining values, please use default credentials. Do *NOT* store any personal credentials related
            //to the lab enviroment or testing lab. as these will be visible to the web but the config file will not.
            writer.write("username= " + "root");
            writer.write("password= " + "password");
            writer.write("realm= " + "pam");
            //Insert any other values here for properties using the format
            // writer.write(<key>= <default Value>);
            //Make sure to have the values go after this comment.
            writer.close();
            System.exit(0); // This is called as the config is filled out and will most likely crash.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            PROPERTIES.load(new FileInputStream(FILE));
            if(Double.parseDouble(PROPERTIES.getProperty("version"))  != VERSIONNUMBER){
                System.out.println();
                createNewConfig();
            }
        } catch (IOException e) {
            System.err.println("The config file could not be read. Please relaunch the program or delete your current config.");
            e.printStackTrace();

            System.exit(-1); //crash value
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}