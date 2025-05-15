package fr.Infuseting.map;

import fr.Infuseting.util.JSONObject;
import fr.Infuseting.util.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public class WorldIO {

    public static void saveWorld(World w, File f) {
        Path PATH = createAdventureAndMonsterFolder();
        if (f == null) {
            throw new NullPointerException("File is null");
        }
        if (w == null) {
            throw new NullPointerException("World is null");
        }
        File worldFolder = PATH.toAbsolutePath().resolve("world").toFile();
        if (!worldFolder.exists()) {
            boolean created = worldFolder.mkdirs();
            if (!created) {
                System.err.println("Failed to create the world folder.");
            }
        }

        File file = worldFolder.toPath().resolve(f.getName()).toFile();
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new FileNotFoundException("File already exists");
                }
            } catch (Exception e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
        }
        try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
            writer.write(w.toJson());
        } catch (Exception e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }




    }

    public static World loadWorld(InputStream f) {
        if (f == null) {
            throw new NullPointerException("File is null");
        }

        StringBuilder json = new StringBuilder();
        try {
            int ch;
            while ((ch = f.read()) != -1) {
                json.append((char) ch);
            }
        } catch (Exception e) {
            System.err.println("Error loading the world: " + e.getMessage());
        }
        JSONObject jsonObject = new JSONParser(json.toString()).parse();
        World w = World.loadJson(jsonObject);
        System.out.println("World loaded: " + w.getString());
        return w;
    }

    private static Path createAdventureAndMonsterFolder() {
        String localAppData;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            localAppData = System.getenv("LOCALAPPDATA");
        } else if (os.contains("mac")) {
            localAppData = System.getProperty("user.home") + "/Library/Application Support";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            localAppData = System.getProperty("user.home") + "/.local/share";
        } else {
            localAppData = null;
        }

        if (localAppData != null) {
            File folder = new File(localAppData, "Adventure&Monster");
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                if (!created) {
                    System.err.println("Failed to create the Adventure&Monster folder.");
                }
            }
        } else {
            System.err.println("Could not determine a suitable directory for Adventure&Monster.");
        }
        return Path.of(localAppData, "Adventure&Monster");
    }
    public static List<File> getMaps() {
        Path PATH = createAdventureAndMonsterFolder();
        File worldFolder = PATH.toAbsolutePath().resolve("world").toFile();
        if (!worldFolder.exists()) {
            System.err.println("World folder does not exist.");
            return List.of();
        }
        File[] files = worldFolder.listFiles();
        if (files != null) {
            return List.of(files);
        } else {
            System.err.println("No files found in the world folder.");
            return List.of();
        }
    }

    public static File getFolder() {
        Path PATH = createAdventureAndMonsterFolder();
        File worldFolder = PATH.toAbsolutePath().resolve("world").toFile();
        if (!worldFolder.exists()) {
            System.err.println("World folder does not exist.");
            return null;
        }
        return worldFolder;
    }
}
