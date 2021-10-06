package me.hamelech.zombieislandremastered.commands;

import me.hamelech.zombieislandremastered.managers.PluginManager;
import me.hamelech.zombieislandremastered.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BaseCommand implements CommandExecutor {
    private final CommandInfo commandInfo;
    private PluginManager pluginManager;
    public BaseCommand(PluginManager pluginManager){
        this.pluginManager = pluginManager;
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Commands must have CommandInfo annotations!");
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!commandInfo.permission().isEmpty()){
            if(!sender.hasPermission(commandInfo.permission())){
                sender.sendMessage(pluginManager.getMessages().get("no-permissions"));
                return true;
            }
        }

        if(commandInfo.type() == CommandType.PLAYER){
            if(!Utils.isPlayer(sender)){
                sender.sendMessage(pluginManager.getMessages().get("must-be-a-player"));
                return true;
            }

            executePlayer((Player) sender, args);
        }else if(commandInfo.type() == CommandType.CONSOLE){
            if(Utils.isPlayer(sender)){
                sender.sendMessage(pluginManager.getMessages().get("only-console"));
                return true;
            }

            executeConsole(sender, args);
        }else if(commandInfo.type() == CommandType.EVERYONE){
            executeEveryone(sender, args);
        }
        return true;
    }

    public void executePlayer(Player player, String[] args){}
    public void executeConsole(CommandSender sender, String[] args){}
    public void executeEveryone(CommandSender sender, String[] args){}
}
