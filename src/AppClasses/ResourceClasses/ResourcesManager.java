package AppClasses.ResourceClasses;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/** Gathers and loads all the resource files (such as images). */
public class ResourcesManager {
    private static final String RESOURCE_DIR = "Resources";
    /** Hashmap that stores loaded files (absolute path, byte data). */
    private static final HashMap<String, byte[]> LOADED_FILES = new HashMap<>();

    /**
     * Loads a file to a hashmap if it isn't already loaded.
     * @param file File to read.
     * @throws IOException If an I/O exception occurs when reading the file's byte data
     */
    private static void loadFile(File file) throws IOException {
        byte[] tempData = new byte[0]; // Temporary var to store byte data

        // Check if file is valid, is readable, and isn't already loaded.
        if (
                !fileIsLoaded(file) &&
                file.exists() &&
                file.isFile() &&
                file.canRead()
        ) {
            // Try to get byte data
            tempData = Files.readAllBytes(file.toPath());

            // Add the data to the hashmap.
            LOADED_FILES.put(fileToKey(file), tempData);
        }
    }

    /**
     * Creates a valid key of a file for the hashmap
     * @param file Requested file
     * @return String
     */
    private static String fileToKey(File file) {
        return file.getName();
    }

    /**
     * Check if the file is already loaded.
     * @param file File to check
     * @return {@code true} if loaded, {@code false} if not loaded.
     */
    private static boolean fileIsLoaded(File file) {
        return LOADED_FILES.containsKey(fileToKey(file));
    }

    /**
     * Creates a file object using one of the enums.
     * @param e Enum corresponding to a resource file.
     * @return A file object
     */
    private static File enumToFile(ResourceEnum e) {
        return new File(switch (e) {
            case Cababas_PNG -> RESOURCE_DIR + "\\Cababas.png";
        });
    }

    /**
     * Loads file data
     * @param e Enum corresponding to a resource file.
     * @return {@code byte[]}
     */
    public static byte[] getAsByteData(ResourceEnum e) {
        File tempFile = enumToFile(e);

        // Try to load the temp file. Throw error if it fails.
        try {
            loadFile(tempFile);
        } catch (IOException er) {
            throw new RuntimeException(er);
        }

        // If the file was successfully loaded, return the loaded byte data
        if (fileIsLoaded(tempFile)) {
            return LOADED_FILES.get(fileToKey(tempFile));
        }

        // Return an empty byte array by default
        return new byte[0];
    }

    public static BufferedImage getAsBufferedImage(ResourceEnum e) {
        byte[] bytes = getAsByteData(e);

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        try {
            image = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException ex) {
            RuntimeException exception = new RuntimeException(ex);
            System.out.println("Could not open image.");
            throw exception;
        }

        return image;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Testing resource manager by loading Cababas byte data. Input anything to continue...");
        console.next();
        console.close();
        System.out.println(Arrays.toString(getAsByteData(ResourceEnum.Cababas_PNG)));
    }
}
