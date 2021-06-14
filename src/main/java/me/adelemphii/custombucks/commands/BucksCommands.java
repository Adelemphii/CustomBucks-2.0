package me.adelemphii.custombucks.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import me.adelemphii.custombucks.CustomBucks;
import me.adelemphii.custombucks.gui.GUI;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BucksCommands implements CommandExecutor {
    CustomBucks plugin;

    public BucksCommands(CustomBucks plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("cb") || label.equalsIgnoreCase("custombucks")) {
            if (args.length <= 0)
                return false;
            if (sender.hasPermission("custombucks.admin")) {
                if (args[0].equalsIgnoreCase("rename")) {
                    if (args.length <= 1) {
                        sender.sendMessage(ChatColor.RED + "Usage: /cb rename <lore> <name>");
                        return true;
                    }
                    int arg = args.length - 1;
                    this.plugin.bucksName = args[arg];
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length - 1; i++)
                        message.append(String.valueOf(args[i]) + " ");
                    this.plugin.bucksLore = message.toString();
                    sender.sendMessage(ChatColor.GREEN + "Action complete!");
                    this.plugin.data.getConfig().set("bucks-name", this.plugin.bucksName);
                    sender.sendMessage(ChatColor.GREEN + "Bucks renamed to " + ChatColor.translateAlternateColorCodes('&', this.plugin.data.getConfig().getString("bucks-name")) + "!");
                    this.plugin.data.getConfig().set("bucks-lore", this.plugin.bucksLore);
                    this.plugin.data.saveConfig();
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that!");
            }
            if (args[0].equalsIgnoreCase("bucks")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Players only!");
                    return false;
                }
                Player player = (Player)sender;
                int amount = 0;
                if (this.plugin.data.getConfig().contains("players." + player.getUniqueId().toString() + ".total"))
                    amount = this.plugin.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".total");
                if (this.plugin.bucksName == null && this.plugin.bucksLore == null) {
                    player.sendMessage(ChatColor.RED + "Uh oh! Something went wrong, tell the admin/dev to do '/cb rename <lore> <name>' to fix this!");
                    return true;
                }
                player.sendMessage(ChatColor.GREEN + "You have " + amount + " " + ChatColor.translateAlternateColorCodes('&', this.plugin.bucksName) + "(s)!");
                return true;
            }
            if (sender.hasPermission("custombucks.admin")) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("Players only!");
                        return false;
                    }
                    Player player = (Player)sender;
                    Location loc = player.getLocation();
                    World world = loc.getWorld();
                    if (this.plugin.data.getConfig().contains("bucks-name") && this.plugin.data.getConfig().contains("bucks-lore")) {
                        this.plugin.bucksLore = this.plugin.data.getConfig().getString("bucks-lore");
                        this.plugin.bucksName = this.plugin.data.getConfig().getString("bucks-name");
                        world.dropItem(loc, spawnBucks(this.plugin.bucksName, this.plugin.bucksLore));
                    } else if (this.plugin.bucksName == null && this.plugin.bucksLore == null) {
                        player.sendMessage(ChatColor.RED + "The name and lore for this item is null! Please use '/cb rename <lore> <name>' first!");
                        return true;
                    }
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that!");
            }
            if (args[0].equalsIgnoreCase("leaderboard")) {
                arrangeStats(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("redeem")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Players only!");
                    return false;
                }
                Player player = (Player) sender;
                GUI menu = new GUI(plugin);
                menu.openShop(player);
                return true;
            }
            if (sender.hasPermission("custombucks.developer")) {
                if (args[0].equalsIgnoreCase("cleardatabase")) {
                    this.plugin.data.deleteConfig();
                    sender.sendMessage(ChatColor.DARK_RED + "I really, really hope you know what you are doing. (Deleted Database!)");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that!");
            }
        }
        return false;
    }

    private ItemStack spawnBucks(String bn, String bl) {
        ItemStack item = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta itemMeta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "owowhatsthis");
        List<String> lore = new ArrayList<>();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', bn));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', bl));
        itemMeta.setLore(lore);
        container.set(key, PersistentDataType.STRING, "ayayaYAYAYYAOWO");
        item.setItemMeta(itemMeta);
        return item;
    }

    private void arrangeStats(CommandSender sender) {
        List<Integer> tokens = new ArrayList<>();
        ConfigurationSection sec = this.plugin.data.getConfig().getConfigurationSection("players");
        if (sec == null) {
            sender.sendMessage(ChatColor.RED + "There is currently nobody on the leaderboard!");
        } else {
            for (String key : sec.getKeys(false)) {
                Integer total = Integer.valueOf(this.plugin.data.getConfig().getInt("players." + key + ".total"));
                tokens.add(total);
            }
        }
        int position = 1;
        Collections.sort(tokens, Collections.reverseOrder());
        for (Iterator<Integer> iterator = tokens.iterator(); iterator.hasNext(); ) {
            int i = ((Integer)iterator.next()).intValue();
            for (String key : sec.getKeys(false)) {
                if (this.plugin.data.getConfig().getInt("players." + key + ".total") == i) {
                    UUID id = UUID.fromString(key);
                    try {
                        Player player = Bukkit.getPlayer(id);
                        sender.sendMessage(String.valueOf(position++) + ". " + player.getDisplayName() + " has a total of " + i + " tokens.");
                    } catch(Exception e) {
                        OfflinePlayer player = Bukkit.getOfflinePlayer(id);
                        sender.sendMessage(String.valueOf(position++) + ". " + player.getName() + " has a total of " + i + " tokens.");
                    }
                }
            }
        }
    }
}
