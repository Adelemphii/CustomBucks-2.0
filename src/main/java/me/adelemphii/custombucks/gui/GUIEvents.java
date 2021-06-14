package me.adelemphii.custombucks.gui;

import me.adelemphii.custombucks.CustomBucks;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GUIEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // This is a horrible way of doing this
        // but it's not going to be used in prod so its ok vvv
        if(player.getInventory().getBoots() != null) {
            checkHermes(player);
            checkSlime(player);
        } else if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED);
        } else if(player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }

    public void checkHermes(Player player) {
        ItemStack item = player.getInventory().getBoots();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(CustomBucks.getPlugin(CustomBucks.class), "HermesBoots");

        if (container.has(key, PersistentDataType.STRING)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1));
        }
    }

    public void checkSlime(Player player) {
        ItemStack item = player.getInventory().getBoots();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(CustomBucks.getPlugin(CustomBucks.class), "SlimeBoots");

        if (container.has(key, PersistentDataType.STRING)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 5));
        }
    }

}
