package me.overtide.mapcoords.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static me.overtide.mapcoords.utils.CoordsStorage.deleteCoord;

public class SubCommandDelete {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        // Ensure a name is given
        if (args.length < 2) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "That's not a valid usage for this subcommand.");
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "Usage: " + ChatColor.GREEN + " /coords delete <name>");
        }

        // Get name
        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        // Delete and get result
        int result = deleteCoord(name, sender.getName());

        switch (result) {
            case 1:
                sender.sendMessage(String.format(ChatColor.BLUE + "[MapCoords] " +
                                ChatColor.RESET + "Successfully deleted " +
                                ChatColor.GOLD + "%s" +
                                ChatColor.RESET + ".",
                        name));
                break;

            case -1:
                sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                        ChatColor.YELLOW + "Coordinates with that name could not be found!");
                break;

            case -2:
                sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                        ChatColor.RED + "An error has occurred while saving the changes. Please contact the server administrator.");
                break;
        }
    }
}
