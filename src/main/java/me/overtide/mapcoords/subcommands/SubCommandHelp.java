package me.overtide.mapcoords.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SubCommandHelp {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                ChatColor.RESET + "Available subcommands:\n" +
                ChatColor.GRAY +
                "  /coords delete <name>\n" +
                "  /coords help\n" +
                "  /coords list\n" +
                "  /coords save <name>\n" +
                "  /coords show [name]\n" +
                "  /coords tp <name>\n"
        );
    }
}
