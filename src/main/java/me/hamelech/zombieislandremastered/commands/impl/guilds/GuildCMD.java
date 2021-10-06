package me.hamelech.zombieislandremastered.commands.impl.guilds;

import me.hamelech.zombieislandremastered.commands.BaseCommand;
import me.hamelech.zombieislandremastered.commands.CommandInfo;
import me.hamelech.zombieislandremastered.commands.CommandType;
import me.hamelech.zombieislandremastered.commands.impl.guilds.subcommands.*;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

@CommandInfo(name = "guilds", permission = "zombieislandremasterd.guild", type = CommandType.PLAYER)
public class GuildCMD extends BaseCommand {
    private final PluginManager pluginManager;

    public GuildCMD(PluginManager pluginManager) {
        super(pluginManager);
        this.pluginManager = pluginManager;
    }

    @Override
    public void executePlayer(Player player, String[] args){
        if(args.length == 0){
            player.sendMessage(pluginManager.getMessages().get("missing-arguments"));
            return;
        }

        switch (args[0]){
            case "create":
                (new CreateSCMD(pluginManager)).execute(player, args);
                break;
            case "list":
                (new ListSCMD(pluginManager)).execute(player, args);
                break;
            case "leave":
                (new LeaveSCMD(pluginManager)).execute(player, args);
                break;
            case "join":
                (new JoinSCMD(pluginManager)).execute(player, args);
                break;
            case "transfer":
                (new TransferSCMD(pluginManager)).execute(player, args);
                break;
            case "disband":
                (new DisbandSCMD(pluginManager)).execute(player, args);
                break;
            default:
                player.sendMessage(pluginManager.getMessages().get("unknown-subcommand"));
                return;
        }
    }

}
