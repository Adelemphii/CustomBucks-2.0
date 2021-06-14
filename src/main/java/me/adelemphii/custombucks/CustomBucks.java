package me.adelemphii.custombucks;

import me.adelemphii.custombucks.commands.BucksCommands;
import me.adelemphii.custombucks.events.RedeemEvent;
import me.adelemphii.custombucks.gui.GUI;
import me.adelemphii.custombucks.gui.GUIEvents;
import me.adelemphii.custombucks.gui.ItemData;
import me.adelemphii.custombucks.util.DataManager;
import me.adelemphii.custombucks.util.TabCompletion;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomBucks extends JavaPlugin {
    public DataManager data;
    public String bucksName;
    public String bucksLore;

    GUI menu;
    ItemData itemData;

    public void onEnable() {
        data = new DataManager(this);

        GUI menu = new GUI(this);
        ItemData itemData = new ItemData();
        menu.registerShop();

        registerCE();



    }

    public void onDisable() {

    }

    public void registerCE() {
        getCommand("cb").setExecutor(new BucksCommands(this));
        getCommand("cb").setTabCompleter(new TabCompletion());
        getServer().getPluginManager().registerEvents(new RedeemEvent(this), this);
        getServer().getPluginManager().registerEvents(new GUIEvents(), this);
    }
}
