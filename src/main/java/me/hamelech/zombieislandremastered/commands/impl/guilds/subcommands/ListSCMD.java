package me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands;

import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListSCMD {
    private final PluginManager pluginManager;

    public ListSCMD(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void execute(Player player, String[] args){
        ArrayList<String> guildsNames = new ArrayList<>();
        pluginManager.getGuildsManager().getGuilds().forEach(guild -> guildsNames.add(guild.getName()));

        String guilds = String.join(", ", guildsNames);
        player.sendMessage(pluginManager.getMessages().get("guilds-list").replace("%guilds%", guilds));
    }
}
