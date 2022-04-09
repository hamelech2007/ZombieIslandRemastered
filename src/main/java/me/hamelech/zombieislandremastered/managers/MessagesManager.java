package me.hamelech.zombieislandremastered.managers;

import me.hamelech.zombieislandremastered.utils.ChatUtils;
import org.bukkit.Bukkit;

public class MessagesManager implements Manager{
    private final PluginManager pluginManager;

    public MessagesManager(PluginManager pluginManager){
        this.pluginManager = pluginManager;
    }


    @Override
    public void initialize() {
        setIfNull("no-permissions", "&cYou don't have enough permissions to do this!");
        setIfNull("must-be-a-player", "&cYou must be a player to do this!");
        setIfNull("only-console", "&cOnly the console can do this!");
        setIfNull("missing-arguments", "&cThis command requires more arguments to run.");
        setIfNull("unknown-subcommand", "&cUnknown subcommand.");
        setIfNull("already-in-guild", "&cYou are already in a guild!");
        setIfNull("guild-created", "&aSuccessfully created guild %guild%.");
        setIfNull("guilds-list", "&aAll guilds: %guilds%");
        setIfNull("not-in-guild", "&cYou are not in a guild!");
        setIfNull("owner-cannot-leave-guild", "&cThe owner of the guild cannot leave the guild! You can either transfer the ownership or disband it!");
        setIfNull("left-guild", "&aSuccessfully left the guild %guild%.");
        setIfNull("guild-already-exists", "&cThere is already a guild with that name!");
        setIfNull("guild-does-not-exist", "&cA guild with that name does not exist.");
        setIfNull("joined-guild", "&aSuccessfully joined the guild %guild%.");
        setIfNull("not-guild-owner", "&cYou are not the owner of the guild!");
        setIfNull("player-does-not-exist", "&cThat player does not exist or is not connected!");
        setIfNull("player-not-in-guild", "&cThat player is not in that guild!");
        setIfNull("ownership-changed", "&aSuccessfully changed the guild's owner to %player%!");
        setIfNull("guild-disbanded", "&aYour guild has been disbanded!");

        setNameIfNull("tank-zombie", "&c&lTank Zombie");

        for(String key : pluginManager.getNamesFile().getConfig().getKeys(false)){
            pluginManager.getNames().put(key, ChatUtils.colorize(pluginManager.getNamesFile().getConfig().getString(key)));
        }

        for(String key : pluginManager.getMessagesFile().getConfig().getKeys(false)){
            pluginManager.getMessages().put(key, ChatUtils.colorize(pluginManager.getMessagesFile().getConfig().getString(key)));
        }
    }

    private void setIfNull(String path, String object){
        if(pluginManager.getMessagesFile().getConfig().getString(path) != null) return;
        pluginManager.getMessagesFile().set(path, object);
    }

    private void  setNameIfNull(String path, String name){
        if (pluginManager.getNamesFile().getConfig().getString(path) != null) return;
        pluginManager.getNamesFile().set(path, name);
    }
}
