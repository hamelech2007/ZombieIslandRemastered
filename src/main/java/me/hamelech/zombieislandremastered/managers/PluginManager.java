package me.hamelech.zombieislandremastered.managers;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.hamelech.zombieislandremastered.ZombieIslandRemastered;
import me.hamelech.zombieislandremastered.configuration.Config;
import me.hamelech.zombieislandremastered.utils.ChatUtils;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class PluginManager implements Manager{

    private final ZombieIslandRemastered plugin;

    private final Config messagesFile;

    private final Config gunsFile;

    private final MessagesManager messagesManager = new MessagesManager(this);

    private final AreaManager areaManager = new AreaManager(this);

    private final GuildsManager guildsManager = new GuildsManager(this);

    private final GunsManager gunsManager = new GunsManager(this);

    private final PlayerDataManager playerDataManager = new PlayerDataManager(this);

    private WorldEditPlugin worldEditPlugin = null;;

    private final Map<String, String> messages = new HashMap<>();

    private final List<Manager> managers = new ArrayList<>();

    public PluginManager(ZombieIslandRemastered plugin){
        this.plugin = plugin;
        this.messagesFile = new Config("messages", plugin);
        this.gunsFile = new Config("guns", plugin);
        Plugin curr = plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        if(!(curr instanceof WorldEditPlugin)){
            ChatUtils.consoleError("WorldEdit was not found! Disabling plugin.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        this.worldEditPlugin = (WorldEditPlugin) curr;
    }

    private void registerManager(Manager manager){
        managers.add(manager);
    }

    @Override
    public void initialize(){
        registerManager(messagesManager);
        registerManager(areaManager);
        registerManager(guildsManager);
        registerManager(playerDataManager);
        registerManager(guildsManager);

        for(Manager manager : managers){
            manager.initialize();
        }
    }
}
