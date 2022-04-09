package me.hamelech.zombieislandremastered;

import me.hamelech.zombieislandremastered.commands.impl.guilds.GuildCMD;
import me.hamelech.zombieislandremastered.commands.impl.guilds.GuildChatCMD;
import me.hamelech.zombieislandremastered.commands.impl.zombie.SpawnZombieCMD;
import me.hamelech.zombieislandremastered.listeners.PlayerJoinListener;
import me.hamelech.zombieislandremastered.listeners.PlayerQuitListener;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZombieIslandRemastered extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pluginManager = new PluginManager(this);
        pluginManager.initialize();
        getCommand("guilds").setExecutor(new GuildCMD(pluginManager));
        getCommand("guildchat").setExecutor(new GuildChatCMD(pluginManager));
        getCommand("spawnzombie").setExecutor(new SpawnZombieCMD(pluginManager));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(pluginManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(pluginManager), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
