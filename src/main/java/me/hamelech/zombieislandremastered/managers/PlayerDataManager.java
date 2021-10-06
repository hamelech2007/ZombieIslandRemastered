package me.hamelech.zombieislandremastered.managers;

import lombok.Getter;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager implements Manager {
    private  final PluginManager pluginManager;

    @Getter
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public PlayerDataManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public void initialize() {
        for(Player player : Bukkit.getOnlinePlayers()){
            Guild guild = null;
            pluginManager.getGuildsManager().guilds.forEach(guild1 -> System.out.println(guild1.getName()));
            for(Guild cguild : pluginManager.getGuildsManager().guilds){
                if (guild == null) continue;
                if (!guild.getMembers().contains(player.getUniqueId())) continue;

                guild = cguild;
            }
            playerDataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId(), guild, pluginManager.getAreaManager().getCurrentArea(player), pluginManager));
        }
    }
}
