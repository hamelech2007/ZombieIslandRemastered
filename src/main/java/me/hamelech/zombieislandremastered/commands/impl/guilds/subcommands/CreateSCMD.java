package me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands;

import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.entity.Player;

public class CreateSCMD {
    private final PluginManager pluginManager;

    public CreateSCMD(PluginManager pluginManager) {
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

        if(pluginManager.getGuildsManager().doesGuildExist(args[1])){
            player.sendMessage(pluginManager.getMessages().get("guild-already-exists"));
            return;
        }

        player.sendMessage(pluginManager.getMessages().get("guild-created").replace("%guild%", args[1]));
        pluginManager.getGuildsManager().createGuild(args[1], player);
    }
}
