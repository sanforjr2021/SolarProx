/**
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
    private static final Properties prop = new Properties();
    private static final File file = new File("config.properties");
    public ConfigHandler() {
        try {
            //Upon creating an empty file, write
            if (file.createNewFile()) {
                createNewConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadConfig();
    }

    private void createNewConfig() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("version= " + 1.0);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            prop.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}