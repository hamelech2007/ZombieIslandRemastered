package me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands;

import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DisbandSCMD {
    private final PluginManager pluginManager;

    public DisbandSCMD(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void execute(Player player, String[] args){
        if(pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild() == null){
            player.sendMessage(pluginManager.getMessages().get("not-in-guild"));
            return;
        }
        if(!pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild().getOwnerUUID().equals(player.getUniqueId())){
            player.sendMessage(pluginManager.getMessages().get("not-guild-owner"));
            return;
        }

        Guild disbandedGuild = pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild();

        for(UUID uuid : disbandedGuild.getMembers()){
            OfflinePlayer member = Bukkit.getOfflinePlayer(uuid);
            if(member.isOnline()){
                ((Player) member).sendMessage(pluginManager.getMessages().get("guild-disbanded"));
                pluginManager.getPlayerDataManager().getPlayerDataMap().get(member.getUniqueId()).setGuild(null);
            }
        }

        pluginManager.getGuildsManager().deleteGuild(disbandedGuild);
    }
}
