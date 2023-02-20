package me.overtide.mapcoords;

import me.overtide.mapcoords.commands.CommandCoords;
import me.overtide.mapcoords.commands.TabCompleteCoords;
import me.overtide.mapcoords.utils.CoordsHUD;
import me.overtide.mapcoords.utils.CoordsStorage;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mapcoords extends JavaPlugin {

    private static Mapcoords plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Plugin startup logic
        getLogger().info("onEnable is called!");

        // Load coords.json
        CoordsStorage.loadFromFile();

        // Enable HUD Timer
        CoordsHUD.run(this);

        // Register the 'coords' command
        this.getCommand("coords").setExecutor(new CommandCoords());
        this.getCommand("coords").setTabCompleter(new TabCompleteCoords());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("onDisable is called!");
    }

    public static Mapcoords getPlugin() {
        return plugin;
    }
}
