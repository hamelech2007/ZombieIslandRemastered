package me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands;

import me.hamelech.zombieislandremastered.api.guilds.GuildJoinEvent;
import me.hamelech.zombieislandremastered.api.guilds.GuildTransferEvent;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class TransferSCMD {
    private final PluginManager pluginManager;

    public TransferSCMD(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void execute(Player player, String[] args){
        if(args.length < 2){
            player.sendMessage(pluginManager.getMessages().get("missing-arguments"));
            return;
        }

        if(pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild() == null){
            player.sendMessage(pluginManager.getMessages().get("not-in-guild"));
            return;
        }
        Guild currentGuild = pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild();
        if(!pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild().getOwnerUUID().equals(player.getUniqueId())){
            player.sendMessage(pluginManager.getMessages().get("not-guild-owner"));
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

        if(!currentGuild.getMembers().contains(target.getUniqueId())){
            player.sendMessage(pluginManager.getMessages().get("player-not-in-guild"));
            return;
        }

        currentGuild.setOwnerUUID(target.getUniqueId());

        pluginManager.getGuildsManager().saveGuild(currentGuild);

        player.sendMessage(pluginManager.getMessages().get("ownership-changed").replace("%player%", target.getName()));
        Bukkit.getPluginManager().callEvent(new GuildTransferEvent(currentGuild, player, target));
    }
}
