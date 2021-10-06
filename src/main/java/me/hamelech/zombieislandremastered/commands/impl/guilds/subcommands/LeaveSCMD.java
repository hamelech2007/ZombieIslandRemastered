package me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands;

import me.hamelech.zombieislandremastered.api.guilds.GuildJoinEvent;
import me.hamelech.zombieislandremastered.api.guilds.GuildLeaveEvent;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LeaveSCMD {
    private final PluginManager pluginManager;

    public LeaveSCMD(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void execute(Player player, String[] args){
        if(pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild() == null){
            player.sendMessage(pluginManager.getMessages().get("not-in-guild"));
            return;
        }

        if(pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild().getOwnerUUID().equals(player.getUniqueId())){
            player.sendMessage(pluginManager.getMessages().get("owner-cannot-leave-guild"));
            return;
        }
        pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild().getMembers().remove(player.getUniqueId());
        Guild oldGuild = pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild();
        pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).setGuild(null);
        pluginManager.getGuildsManager().saveGuild(oldGuild);
        player.sendMessage(pluginManager.getMessages().get("left-guild").replace("%guild%", oldGuild.getName()));
        Bukkit.getPluginManager().callEvent(new GuildLeaveEvent(oldGuild, player));
    }
}
