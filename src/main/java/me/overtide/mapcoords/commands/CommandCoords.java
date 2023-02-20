package me.overtide.mapcoords.commands;

import me.overtide.mapcoords.Mapcoords;
import me.overtide.mapcoords.subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCoords implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Ensure command sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("[MapCoords] Error: This is a player-only command!");
            return true;
        }

        // Ensure at least one argument was provided
        if (args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "That's not a valid subcommand!");
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "Use " + ChatColor.GREEN + "/coords help" + ChatColor.YELLOW + " to view correct usage.");
            return true;
        }

        // Get subcommand
        String subcommand = args[0].toLowerCase();
        switch (subcommand) {

            // Delete a previously saved coordinates: /coords delete <name>
            case "delete":
                SubCommandDelete.run(sender, command, label, args);
                break;

            // Display the help message: /coords help
            case "help":
                SubCommandHelp.run(sender, command, label, args);
                break;

            // Toggle the coordinates HUD: /coords hud
            case "hud":
                SubCommandHUD.run(sender, command, label, args);
                break;

            // List all saved coordinates: /coords list
            case "list":
                SubCommandList.run(sender, command, label, args);
                break;

            // Save the player's current location: /coords save <name>
            case "save":
                SubCommandSave.run(sender, command, label, args);
                break;

            // Show the player's current location or saved location: /coords show [name]
            case "show":
                SubCommandShow.run(sender, command, label, args);
                break;

            // Teleport the player to a saved location: /coords tp <name>
            case "tp":
                SubCommandTp.run(sender, command, label, args);
                break;

            default:
                sender.sendMessage(ChatColor.BLUE + "[MapCoords] " + ChatColor.YELLOW + "That's not a valid subcommand! Use " + ChatColor.GREEN + "/coords help" + ChatColor.YELLOW + " to view correct usage.");
        }

        return true;
    }

}
