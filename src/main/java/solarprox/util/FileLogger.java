/**
 * Class: FileLogger
 * Author: Jacob Sanford
 * Date: 1/21/2021
 * Description: Allows for appending and creating new log files in the "Logs" folder.
 *          Note: FileLogger must be initialized once to set the filepath and must be closed after final usage,
 *          otherwise, all logging commands can be referenced statically.
 */
package solarprox.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger{
    private static PrintWriter printWriter;
    private static BufferedWriter bufferedWriter;

    public FileLogger(){
        String logPath = "logs/Log_" + getDateString() + ".log";
        new File("logs").mkdir();
        try{
            new File(logPath).createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(logPath, true));
            printWriter = new PrintWriter(bufferedWriter);


        } catch (IOException e) {
            System.err.println("The system logging did not load, closing program.");
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void onExit(){
        logInfo("SolarProx is shutting down");
        printWriter.close();

    }

    public static void logInfo(String info){
        String infoMessage = "[INFO] " + getCompleteDateString() + " " + info;
        System.out.println(infoMessage);
        printWriter.append(infoMessage + "\n");
    }

    public static void logWarning(String warning){
        String warningMessage = "[WARNING] " + getCompleteDateString() + " " + warning;
        System.out.println(warningMessage);
        printWriter.append(warningMessage + "\n");
    }

    public static void logError(String error){
        String errorMessage = "[ERROR] " + getCompleteDateString() + " " + error;
        System.err.println(errorMessage);
        printWriter.append(errorMessage + "\n");
    }

    private static String getDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy");
        return dateFormat.format(new Date());
    }

    private static String getCompleteDateString(){
       SimpleDateFormat completeTimeFormat = new SimpleDateFormat("[MM/dd/yyyy HH:mm:ss]");
        return completeTimeFormat.format(new Date());
    }
}
