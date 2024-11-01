package AppClasses;

import AppClasses.ResourceClasses.ResourceEnum;
import AppClasses.ResourceClasses.ResourcesManager;

import java.io.*;

public class App {
    private static final PrintStream CONSOLE = System.out;
    private static final String OUTPUT_FILE = "Log.txt";
    private static State appState;

    public enum State {
        /** User has run the program, and the app is loading (default phase)*/
        LOADING,
        /** App has finished loading and should be showing on screen. */
        FOREGROUND,
        /** App has finished loading and should be running off-screen */
        BACKGROUND,
        /** App is programmatically being closed. */
        CLOSING
    }

    /**
     * Start a new app session.
     */
    public App() {
        appState = State.LOADING;
    }

    /**
     * Set the output stream to a file in the root directory.
     */
    private static void setOutputToFile() {
        File logFile = new File(OUTPUT_FILE);
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            CONSOLE.println("Could not create output file \"" + OUTPUT_FILE + "\". Will continue printing to console.");
            throw new RuntimeException(e);
        }

        try {
            PrintStream out = new PrintStream(new FileOutputStream(logFile));
            System.setOut(out);
        } catch (FileNotFoundException e) {
            CONSOLE.println("Could not set output stream to \"" + OUTPUT_FILE + "\". Will continue printing to console.");
            System.setOut(CONSOLE);
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            CONSOLE.println("Insufficient permission to set output stream to \"" + OUTPUT_FILE + "\". Will continue printing to console.");
            System.setOut(CONSOLE);
            throw new RuntimeException(e);
        }

        CONSOLE.println("Outputs will be printed into \"" + OUTPUT_FILE + "\" instead of the console from here on.");
    }

    /**
     * Preload all the byte-data for all the enums.
     */
    private static void preloadResourceFiles() {
        for (ResourceEnum e : ResourceEnum.values()) {
            File file = ResourcesManager.enumToFile(e);
            try {
                ResourcesManager.loadFile(file);
            } catch (IOException er) {
                System.out.println("Could not pre-load resource file " + file.getName() + ": " + er.getMessage());
                throw new RuntimeException(er);
            }
        }
    }

    /**
     * Start a session.
     * @param logToFile {@code true} if outputs should be streamed to a log file, {@code false} if outputs should be streamed to the console (as usual).
     */
    public static void startApp(boolean logToFile) {
        if (logToFile) {
            setOutputToFile();
        }
        System.out.println("Starting Cababas Windows Edition");
        preloadResourceFiles();
    }

    public static void main(String[] args) {
        startApp(false);
    }
}