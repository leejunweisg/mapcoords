package me.overtide.mapcoords.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.util.ArrayList;

public class CoordsHUD {
    private static ArrayList<String> enabledPlayers = new ArrayList<>();

    public static void run(final Plugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                // Only draw for enabled players
                if (enabledPlayers.contains(p.getName())) {
                    // Get the player's current location
                    Location loc = p.getLocation();
                    int x = (int) loc.getX();
                    int y = (int) loc.getY();
                    int z = (int) loc.getZ();
                    String facing = getDirection(loc.getYaw());
                    String biome = loc.getWorld().getBiome(loc).name();

                    // Display HUD as Action Bar
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (
                            new ComponentBuilder("XYZ: ").color(ChatColor.GOLD)
                                    .append(String.format("%d %d %d", x, y, z)).color(ChatColor.LIGHT_PURPLE)
                                    .append("  Facing: ").color(ChatColor.GOLD)
                                    .append(facing).color(ChatColor.LIGHT_PURPLE)
                                    .append("  Biome: ").color(ChatColor.GOLD)
                                    .append(biome).color(ChatColor.LIGHT_PURPLE)
                                    .create()
                    ));

                }
            }
        }, 0, 10);
    }

    public static boolean toggle(String player) {
        if (enabledPlayers.contains(player)) {
            enabledPlayers.remove(player);
            return false;
        } else {
            enabledPlayers.add(player);
            return true;
        }
    }

    private static String getDirection(float yaw) {
        double rotation = yaw - 180;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "North";
        }
        if (22.5 <= rotation && rotation < 67.5) {
            return "North East";
        }
        if (67.5 <= rotation && rotation < 112.5) {
            return "East";
        }
        if (112.5 <= rotation && rotation < 157.5) {
            return "South East";
        }
        if (157.5 <= rotation && rotation < 202.5) {
            return "South";
        }
        if (202.5 <= rotation && rotation < 247.5) {
            return "South West";
        }
        if (247.5 <= rotation && rotation < 292.5) {
            return "West";
        }
        if (292.5 <= rotation && rotation < 337.5) {
            return "North West";
        }
        if (337.5 <= rotation && rotation <= 360) {
            return "North";
        }
        return null;
    }
}
