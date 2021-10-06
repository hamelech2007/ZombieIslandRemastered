package me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands;

import me.hamelech.zombieislandremastered.api.guilds.GuildJoinEvent;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class JoinSCMD {
    private final PluginManager pluginManager;

    public JoinSCMD(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void execute(Player player, String[] args){
        if(args.length < 2){
            player.sendMessage(pluginManager.getMessages().get("missing-arguments"));
            return;
        }

        if(pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild() != null){
            player.sendMessage(pluginManager.getMessages().get("already-in-guild"));
            return;
        }

        Guild guild = pluginManager.getGuildsManager().getGuild(args[1]);
        if(guild == null){
            player.sendMessage(pluginManager.getMessages().get("guild-does-not-exist"));
            return;
        }

        pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).setGuild(guild);
        guild.getMembers().add(player.getUniqueId());
        pluginManager.getGuildsManager().saveGuild(guild);
        player.sendMessage(pluginManager.getMessages().get("joined-guild").replace("%guild%", guild.getName()));
        Bukkit.getPluginManager().callEvent(new GuildJoinEvent(guild, player));
    }
}
