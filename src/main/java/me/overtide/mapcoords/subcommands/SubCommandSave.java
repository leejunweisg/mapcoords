package me.overtide.mapcoords.subcommands;

import me.overtide.mapcoords.models.Coord;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static me.overtide.mapcoords.utils.CoordsStorage.findCoord;
import static me.overtide.mapcoords.utils.CoordsStorage.saveCoord;

public class SubCommandSave {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        // Get the player's current location
        Location loc = ((Player) sender).getLocation();
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        String world = loc.getWorld().getName();

        // Ensure a name is given
        if (args.length < 2) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                    ChatColor.YELLOW + "That's not a valid usage for this subcommand.");

            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                    ChatColor.YELLOW + "Usage: " +
                    ChatColor.GREEN + " /coords save <name>");
        }

        // Get name
        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length)).toLowerCase();

        int result = saveCoord(x, y, z, name, world, sender.getName(), true);

        switch (result) {
            case 1:
                sender.sendMessage(String.format(ChatColor.BLUE + "[MapCoords] " +
                                ChatColor.RESET + "Successfully saved " +
                                ChatColor.GOLD + "%s " +
                                ChatColor.RESET + "at " +
                                ChatColor.LIGHT_PURPLE + "[%d, %d, %d] " +
                                ChatColor.RESET + "in " +
                                ChatColor.GREEN + "%s" +
                                ChatColor.RESET + ".",
                        name, x, y, z, world));
                break;

            case -1:
                sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                        ChatColor.YELLOW + "A location with that name already exists! Please select another name.");

                Coord existing = findCoord(name, sender.getName());

                sender.sendMessage(String.format(ChatColor.BLUE + "[MapCoords] " +
                                ChatColor.GOLD + "%s " +
                                ChatColor.RESET + "is at " +
                                ChatColor.LIGHT_PURPLE + "[%d, %d, %d] " +
                                ChatColor.RESET + "of " +
                                ChatColor.GREEN + "%s" +
                                ChatColor.RESET + ".",
                        existing.getName(), existing.getX(), existing.getY(), existing.getZ(), existing.getWorld()));
                break;

            case -2:
                sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                        ChatColor.RED + "An error has occurred while saving the changes. Please contact the server administrator.");
                break;
        }
    }
}
