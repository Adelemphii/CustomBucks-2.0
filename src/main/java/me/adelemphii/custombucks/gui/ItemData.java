package me.adelemphii.custombucks.gui;

import me.adelemphii.custombucks.CustomBucks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemData {

    public ItemStack getHermesStore() {
        ItemStack hermes = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta hermesMeta = hermes.getItemMeta();
        NamespacedKey key = new NamespacedKey(CustomBucks.getPlugin(CustomBucks.class), "HermesBoots");
        PersistentDataContainer container = hermesMeta.getPersistentDataContainer();
        List<String> lore = new ArrayList<>();

        hermesMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.ITALIC + "Sandals of Hermes");
        lore.add("");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "Ancient sandals which were possessed");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "by the ancient god, Hermes.");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "They grant the wearer great speed.");
        lore.add("");
        lore.add(ChatColor.GREEN + "Click here to buy!");
        lore.add(ChatColor.RED + "Price: 2 tokens.");
        hermesMeta.setLore(lore);
        hermesMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        container.set(key, PersistentDataType.STRING, "HermesBoots");
        hermesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS );
        hermesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES );
        hermes.setItemMeta(hermesMeta);
        return hermes;
    }

    public ItemStack getHermes() {
        ItemStack hermes = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta hermesMeta = hermes.getItemMeta();
        NamespacedKey key = new NamespacedKey(CustomBucks.getPlugin(CustomBucks.class), "HermesBoots");
        PersistentDataContainer container = hermesMeta.getPersistentDataContainer();
        List<String> lore = new ArrayList<>();

        hermesMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.ITALIC + "Sandals of Hermes");
        lore.add("");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "Ancient sandals which were possessed");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "by the ancient god, Hermes.");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "They grant the wearer great speed.");
        hermesMeta.setLore(lore);
        hermesMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        container.set(key, PersistentDataType.STRING, "HermesBoots");
        hermesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS );
        hermesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES );
        hermes.setItemMeta(hermesMeta);
        return hermes;
    }

    public ItemStack getSlimeBootsStore() {
        ItemStack slime = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta slimeMeta = slime.getItemMeta();
        NamespacedKey key = new NamespacedKey(CustomBucks.getPlugin(CustomBucks.class), "SlimeBoots");
        PersistentDataContainer container = slimeMeta.getPersistentDataContainer();
        List<String> lore = new ArrayList<>();

        slimeMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.ITALIC + "Sneakers of Slime");
        lore.add("");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "Ancient sneakers which were possessed");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "by the ancient god, Kobe Bryant.");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "They grant the wearer great jump height.");
        lore.add("");
        lore.add(ChatColor.GREEN + "Click here to buy!");
        lore.add(ChatColor.RED + "Price: 2 tokens.");
        slimeMeta.setLore(lore);
        slimeMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        container.set(key, PersistentDataType.STRING, "SlimeBoots");
        slimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS );
        slimeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES );
        slime.setItemMeta(slimeMeta);
        return slime;
    }

    public ItemStack getSlimeBoots() {
        ItemStack slime = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta slimeMeta = slime.getItemMeta();
        NamespacedKey key = new NamespacedKey(CustomBucks.getPlugin(CustomBucks.class), "SlimeBoots");
        PersistentDataContainer container = slimeMeta.getPersistentDataContainer();
        List<String> lore = new ArrayList<>();

        slimeMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.ITALIC + "Sneakers of Slime");
        lore.add("");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "Ancient sneakers which were possessed");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "by the ancient god, Kobe Bryant.");
        lore.add(ChatColor.ITALIC + "" + ChatColor.RED + "They grant the wearer great jump height.");
        slimeMeta.setLore(lore);
        slimeMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        slimeMeta.addEnchant(Enchantment.PROTECTION_FALL, 100, true);
        container.set(key, PersistentDataType.STRING, "SlimeBoots");
        slimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS );
        slimeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES );
        slime.setItemMeta(slimeMeta);
        return slime;
    }


}
