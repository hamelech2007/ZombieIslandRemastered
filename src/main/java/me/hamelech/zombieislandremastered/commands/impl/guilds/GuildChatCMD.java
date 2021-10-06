package me.hamelech.zombieislandremastered.commands.impl.guilds;

import me.hamelech.zombieislandremastered.commands.BaseCommand;
import me.hamelech.zombieislandremastered.commands.CommandInfo;
import me.hamelech.zombieislandremastered.commands.CommandType;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import me.hamelech.zombieislandremastered.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandInfo(name = "guildchat", permission = "zombieislandremasterd.guild.chat", type = CommandType.PLAYER)
public class GuildChatCMD extends BaseCommand {
    private final PluginManager pluginManager;

    public GuildChatCMD(PluginManager pluginManager) {
        super(pluginManager);
        this.pluginManager = pluginManager;
    }

    @Override
    public void executePlayer(Player player, String[] args){

        if(pluginManager.getPlayerDataManager().getPlayerDataMap().get(player.getUniqueId()).getGuild() == null){
            player.sendMessage(pluginManager.getMessages().get("not-in-guild"));
            return;
        }

        if(args.length == 0){
            player.sendMessage(pluginManager.getMessages().get("missing-arguments"));
            return;
        }

        String message = String.join(" ", args);

        for(UUID member : pluginManager.getGuildsManager().getPlayerGuild(player).getMembers()){
            OfflinePlayer target = Bukkit.getOfflinePlayer(member);
            if(target.isOnline()){
                ((Player) target).sendMessage(ChatUtils.colorize("&2&lGC > &r" + player.getDisplayName() + " : &e") + message);
            }
        }
    }

}
