package me.adelemphii.custombucks.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.adelemphii.custombucks.CustomBucks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {
    private static ChestGui GUI;

    CustomBucks plugin;
    public GUI(CustomBucks plugin) {
        this.plugin = plugin;
    }

    ItemData itemData = new ItemData();

    // https://github.com/stefvanschie/IF/wiki/Static-Pane
    public ChestGui registerShop() {
        ChestGui gui = new ChestGui(5, "Shop!");

        StaticPane pane = new StaticPane(0, 0, 9, 5);

        gui.setOnGlobalClick(inventoryClickEvent -> inventoryClickEvent.setCancelled(true));

        pane.addItem(new GuiItem(new ItemStack(itemData.getHermesStore()), inventoryClickEvent -> {
            inventoryClickEvent.getWhoClicked().closeInventory();
            if(isInventoryFull((Player)inventoryClickEvent.getWhoClicked())) {
                inventoryClickEvent.getWhoClicked().sendMessage("Your inventory is full! Please clear it before trying to buy something!");
            }
            if(buyItem(inventoryClickEvent.getWhoClicked(), itemData.getSlimeBoots().getItemMeta().getDisplayName(), 2)) {
                inventoryClickEvent.getWhoClicked().getInventory().addItem(itemData.getHermes());
            }


        }), 3, 2);
        pane.addItem(new GuiItem(new ItemStack(Material.STICK), inventoryClickEvent -> {

            GuiItem stick = new GuiItem(new ItemStack(Material.STICK));
            List<String> lore = new ArrayList<>();
            ItemMeta meta = stick.getItem().getItemMeta();

            stick.setAction(inventoryClickEvent1 -> {
                inventoryClickEvent.getWhoClicked().sendMessage("Hehe, you clicked the stick.");
            });
            meta.addEnchant(Enchantment.KNOCKBACK, 100, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.setDisplayName("Stick!");
            lore.add("It's just a stick, what did you expect?");
            meta.setLore(lore);
            stick.getItem().setItemMeta(meta);


            inventoryClickEvent.getWhoClicked().closeInventory();
            if(isInventoryFull((Player)inventoryClickEvent.getWhoClicked())) {
                inventoryClickEvent.getWhoClicked().sendMessage("Your inventory is full! Please clear it before trying to buy something!");
            }
            if(buyItem(inventoryClickEvent.getWhoClicked(), itemData.getSlimeBoots().getItemMeta().getDisplayName(), 2)) {
                inventoryClickEvent.getWhoClicked().getInventory().addItem();
            }
        }), 4, 2);
        pane.addItem(new GuiItem(new ItemStack(itemData.getSlimeBootsStore()), inventoryClickEvent -> {
            inventoryClickEvent.getWhoClicked().closeInventory();
            if(isInventoryFull((Player)inventoryClickEvent.getWhoClicked())) {
                inventoryClickEvent.getWhoClicked().sendMessage("Your inventory is full! Please clear it before trying to buy something!");
            }
            if(buyItem(inventoryClickEvent.getWhoClicked(), itemData.getSlimeBoots().getItemMeta().getDisplayName(), 2)) {
                inventoryClickEvent.getWhoClicked().getInventory().addItem(itemData.getSlimeBoots());
            }
        }), 5, 2);
        pane.fillWith(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        gui.addPane(pane);
        GUI = gui;
        return gui;
    }

    public void openShop(Player player) {
        GUI.show(player);
    }

    public boolean isInventoryFull(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }

    public boolean buyItem(HumanEntity player, String item, int amount) {
        if(plugin.data.getConfig().contains("players." + player.getUniqueId())) {
            if (plugin.data.getConfig().getInt("players." + player.getUniqueId() + ".total") >= 2) {
                int total = plugin.data.getConfig().getInt("players." + player.getUniqueId() + ".total");
                plugin.data.getConfig().set("players." + player.getUniqueId() + ".total", total - amount);
                int newTotal = plugin.data.getConfig().getInt("players." + player.getUniqueId() + ".total");
                plugin.data.saveConfig();
                player.sendMessage("You have redeemed " + item + ChatColor.RESET + ", you have paid " + amount + " tokens! You have " + newTotal + " tokens left!");
                return true;
            } else {
                player.sendMessage("You do not have enough tokens!");
                return false;
            }
        }
        player.sendMessage("You do not have enough tokens!");
        return false;
    }
}
