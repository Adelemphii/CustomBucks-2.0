package me.adelemphii.custombucks.events;

import me.adelemphii.custombucks.CustomBucks;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class RedeemEvent implements Listener {
    Integer items;

    CustomBucks plugin;

    public RedeemEvent(CustomBucks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand() != null) {
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "owowhatsthis");
                if (container.has(key, PersistentDataType.STRING)) {
                    int amount = 0;
                    if (this.plugin.data.getConfig().contains("players." + player.getUniqueId().toString() + ".total"))
                        amount = this.plugin.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".total");
                    this.plugin.data.getConfig().set("players." + player.getUniqueId().toString() + ".total", Integer.valueOf(amount + item.getAmount()));
                    this.plugin.data.saveConfig();
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                    player.sendMessage(ChatColor.GREEN + "You have redeemed " + item.getAmount() + " " + item.getItemMeta().getDisplayName() + "(s)!");
                }
            }
            player.getInventory().getItemInMainHand();
        }
    }
}
