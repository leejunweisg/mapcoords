package me.overtide.mapcoords.commands;

import me.overtide.mapcoords.models.Coord;
import me.overtide.mapcoords.utils.CoordsStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabCompleteCoords implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> result = new ArrayList<String>();

        if (args.length == 1) {  // subcommands level
            List<String> subcommands = Arrays.asList("delete", "help", "hud", "list", "save", "show", "tp");

            for (String s : subcommands) {
                if (s.startsWith(args[0].toLowerCase())) {
                    result.add(s);
                }
            }

        } else if (args.length >= 2) {  // second argument level
            // Get the subcommand
            String subcommand = args[0].toLowerCase();

            // Only provide suggestion if subcommands are "show", "delete" or "tp"
            if (subcommand.equals("show") || subcommand.equals("delete") || subcommand.equals("tp")) {
                String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length)).toLowerCase();
                for (Coord c : CoordsStorage.getCoords()) {
                    if (c.getName().startsWith(name)) {
                        result.add(c.getName());
                    }
                }
            }
        }

        Collections.sort(result);

        return result;
    }
}
