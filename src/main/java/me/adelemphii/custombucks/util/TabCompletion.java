package me.adelemphii.custombucks.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompletion implements TabCompleter {
    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (this.arguments.isEmpty()) {
            this.arguments.add("give");
            this.arguments.add("bucks");
            this.arguments.add("leaderboard");
            this.arguments.add("rename");
            this.arguments.add("redeem");
        }
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : this.arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }
}
