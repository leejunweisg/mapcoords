package me.overtide.mapcoords.subcommands;

import me.overtide.mapcoords.models.Coord;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static me.overtide.mapcoords.utils.CoordsStorage.findCoord;

public class SubCommandShow {

    public static void run(CommandSender sender, Command command, String label, String[] args) {

        if (args.length >= 2) {
            // A name was provided
            // Get name
            String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            // Find the coord object
            Coord coord = findCoord(name, sender.getName());

            if (coord == null) {
                sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                        ChatColor.YELLOW + "Coordinates with that name could not be found!");
                return;
            }

            sender.sendMessage(String.format(ChatColor.BLUE + "[MapCoords] " +
                            ChatColor.GOLD + "%s " +
                            ChatColor.RESET + "is at " +
                            ChatColor.LIGHT_PURPLE + "[%d, %d, %d] " +
                            ChatColor.RESET + "of " +
                            ChatColor.GREEN + "%s" +
                            ChatColor.RESET + ".",
                    name, coord.getX(), coord.getY(), coord.getZ(), coord.getWorld()));

        } else {
            // No name provided, display the player's current location
            // Get the player's current location
            Location loc = ((Player) sender).getLocation();
            int x = (int) loc.getX();
            int y = (int) loc.getY();
            int z = (int) loc.getZ();
            String world = loc.getWorld().getName();

            sender.sendMessage(String.format(ChatColor.BLUE + "[MapCoords] " +
                            ChatColor.WHITE + "You are at " +
                            ChatColor.LIGHT_PURPLE + "[%d, %d, %d] " +
                            ChatColor.RESET + "of " +
                            ChatColor.GREEN + "%s" +
                            ChatColor.RESET + ".",
                    x, y, z, world));
        }
    }

}
