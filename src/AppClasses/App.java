package AppClasses;

import java.io.*;

public class App {
    private static final PrintStream CONSOLE = System.out;
    private static final String OUTPUT_FILE = "Log.txt";

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
     * Start a session.
     * @param logToFile {@code true} if outputs should be streamed to a log file, {@code false} if outputs should be streamed to the console (as usual).
     */
    public static void startApp(boolean logToFile) {
        if (logToFile) {
            setOutputToFile();
        }
        System.out.println("Starting Cababas Windows Edition");
    }

    public static void main(String[] args) {
        startApp(false);
    }
}
