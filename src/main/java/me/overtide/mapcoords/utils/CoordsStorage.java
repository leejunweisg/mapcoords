package me.overtide.mapcoords.utils;

import com.google.gson.Gson;
import me.overtide.mapcoords.Mapcoords;
import me.overtide.mapcoords.models.Coord;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CoordsStorage {
    // ArrayList to store coords
    private static ArrayList<Coord> coords = new ArrayList<Coord>();


    public static ArrayList<Coord> getCoords() {
        return coords;
    }

    /**
     * @param x
     * @param y
     * @param z
     * @param name
     * @param world
     * @param owner
     * @param isPublic
     * @return 1 if location saved successfully, -1 if name already exists, -2 if failed to save to file.
     */
    public static int saveCoord(int x, int y, int z, String name, String world, String owner, boolean isPublic) {

        // Create Coord object
        Coord newCoord = new Coord(x, y, z, name, world, owner, isPublic);

        // Check if name already exists
        for (Coord coord : coords) {
            if (coord.getName().equalsIgnoreCase(name)) {
                return -1;
            }
        }

        // Add to arraylist
        coords.add(newCoord);

        // Save to file
        try {
            saveToFile();
        } catch (IOException ex) {
            Mapcoords.getPlugin().getLogger().severe("Failed to save to coords to coords.json");
            Mapcoords.getPlugin().getLogger().severe(ex.toString());
            return -2;
        }

        return 1;
    }

    /**
     * @param name   name of the saved coord object
     * @param caller the player who called the command
     * @return Coord object if found, null if not.
     */
    public static Coord findCoord(String name, String caller) {
        // Find the Coord
        Coord coord = null;
        for (Coord c : coords) {
            if (c.getName().equalsIgnoreCase(name)) {
                // Coord is found, check permissions
                if (c.isPublic() || c.getOwner().equalsIgnoreCase(caller))
                    coord = c;
            }
        }
        return coord;
    }

    /**
     * @param name
     * @param caller
     * @return 1 if successfully deleted, -1 if not found, -2 if failed to save to file.
     */
    public static int deleteCoord(String name, String caller) {
        // Find the Coord to remove
        Coord toRemove = findCoord(name, caller);

        // Coord with specified name was not found
        if (toRemove == null) {
            return -1;
        }

        // Remove it from the arraylist
        coords.remove(toRemove);

        // Save to file
        try {
            saveToFile();
        } catch (IOException ex) {
            Mapcoords.getPlugin().getLogger().severe("Failed to save to coords to coords.json");
            Mapcoords.getPlugin().getLogger().severe(ex.toString());
            return -2;
        }

        return 1;
    }

    public static void saveToFile() throws IOException {
        Gson gson = new Gson();
        File file = new File(Mapcoords.getPlugin().getDataFolder().getAbsolutePath() + "/coords.json");

        // Create directory and file if it does not exist
        if (file.getParentFile().mkdir()) {
            Mapcoords.getPlugin().getLogger().info("Plugin folder for Mapcoords not found, new one is created.");
        }
        if (file.createNewFile()) {
            Mapcoords.getPlugin().getLogger().info("No coords.json found, new one is created.");
        }

        Writer writer = new FileWriter(file);

        gson.toJson(coords, writer);

        writer.flush();
        writer.close();

        Mapcoords.getPlugin().getLogger().info("Coords successfully saved to coords.json!");
    }

    public static boolean loadFromFile() {
        Gson gson = new Gson();
        File file = new File(Mapcoords.getPlugin().getDataFolder().getAbsolutePath() + "/coords.json");

        try {
            Reader reader = new FileReader(file);
            Coord[] c = gson.fromJson(reader, Coord[].class);
            coords = new ArrayList<>(Arrays.asList(c));
            Mapcoords.getPlugin().getLogger().info("Coords successfully loaded from coords.json!");
            return true;
        } catch (FileNotFoundException ex) {
            Mapcoords.getPlugin().getLogger().info("coords.json was not found, no coordinates loaded.");
            return false;
        }
    }
}
