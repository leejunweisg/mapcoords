package me.overtide.mapcoords.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static me.overtide.mapcoords.utils.CoordsHUD.toggle;

public class SubCommandHUD {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        // Just toggle for player
        boolean enabled = toggle(sender.getName());

        if (enabled) {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                    ChatColor.WHITE + "The HUD has been enabled!");
        } else {
            sender.sendMessage(ChatColor.BLUE + "[MapCoords] " +
                    ChatColor.WHITE + "The HUD has been disabled!");
        }
    }
}
