package me.overtide.mapcoords.subcommands;

import me.overtide.mapcoords.models.Coord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static me.overtide.mapcoords.utils.CoordsStorage.findCoord;

public class SubCommandTp {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        // Ensure a name is given
        if (args.length < 2) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "That's not a valid usage for this subcommand.");
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "Usage: " + ChatColor.GREEN + " /coords tp <name>");
        }

        // Get name
        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        // Find the coord object
        Coord coord = findCoord(name, sender.getName());

        if (coord == null) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                    ChatColor.YELLOW + "Coordinates with that name could not be found!");
            return;
        }

        // Create location object
        Location loc = new Location(Bukkit.getWorld(coord.getWorld()), coord.getX(), coord.getY(), coord.getZ(), 0, 0);
        ((Player) sender).teleport(loc);

        sender.sendMessage(String.format(ChatColor.BLUE + "[MapCoords] " + ChatColor.RESET + "Teleporting to " + ChatColor.GOLD + "%s" + ChatColor.RESET + "...", coord.getName()));
    }
}
