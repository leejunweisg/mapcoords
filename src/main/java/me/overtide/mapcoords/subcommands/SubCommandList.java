package me.overtide.mapcoords.subcommands;

import me.overtide.mapcoords.models.Coord;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

import static me.overtide.mapcoords.utils.CoordsStorage.getCoords;

public class SubCommandList {
    public static void run(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<Coord> coords = getCoords();

        // if there are no saved coordinates return early
        if (coords.isEmpty()) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                    ChatColor.RESET + "You have no saved coordinates!");
            return;
        }

        StringBuilder results = new StringBuilder(ChatColor.BLUE + "[MapCoords] " + ChatColor.RESET + "Saved locations:\n");
        for (Coord c : coords) {
            results.append(String.format("  " +
                            ChatColor.GRAY + "[%s] - " +
                            ChatColor.GOLD + "%s " +
                            ChatColor.LIGHT_PURPLE + " [%d, %d, %d]\n",
                    c.getWorld(), c.getName(), c.getX(), c.getY(), c.getZ()));
        }

        sender.sendMessage(results.toString());
    }
}
