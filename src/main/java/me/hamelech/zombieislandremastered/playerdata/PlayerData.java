package me.hamelech.zombieislandremastered.playerdata;

import lombok.Data;
import lombok.Setter;
import me.hamelech.zombieislandremastered.area.Area;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    @Setter
    private Guild guild;
    @Setter
    private Area currentArea;
    private final PluginManager pluginManager;



    public Guild getGuild(){
        for(Guild guild : pluginManager.getGuildsManager().getGuilds()){
            if (guild.getMembers().contains(uuid)) setGuild(guild);
        }
        return guild;
    }
    public Area getCurrentArea(){
        setCurrentArea(pluginManager.getAreaManager().getCurrentArea(Bukkit.getPlayer(uuid)));
        return getCurrentArea();
    }


    public PlayerData(UUID uuid, Guild guild, Area currentArea, PluginManager pluginManager) {
        this.uuid = uuid;
        this.guild = guild;
        this.currentArea = currentArea;
        this.pluginManager = pluginManager;
    }
}
