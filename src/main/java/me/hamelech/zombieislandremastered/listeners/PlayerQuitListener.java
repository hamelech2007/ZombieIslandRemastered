package me.hamelech.zombieislandremastered.listeners;

import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final PluginManager pluginManager;

    public PlayerQuitListener(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        pluginManager.getPlayerDataManager().getPlayerDataMap().remove(event.getPlayer().getUniqueId());
    }
}
