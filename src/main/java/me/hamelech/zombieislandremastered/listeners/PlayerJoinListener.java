package me.hamelech.zombieislandremastered.listeners;

import me.hamelech.zombieislandremastered.managers.PluginManager;
import me.hamelech.zombieislandremastered.playerdata.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final PluginManager pluginManager;

    public PlayerJoinListener(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        pluginManager.getPlayerDataManager().getPlayerDataMap().put(event.getPlayer().getUniqueId(), new PlayerData(event.getPlayer().getUniqueId(), pluginManager.getGuildsManager().getPlayerGuild(event.getPlayer()), pluginManager.getAreaManager().getCurrentArea(event.getPlayer()), pluginManager));
    }
}
