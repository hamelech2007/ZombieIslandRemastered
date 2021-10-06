package me.hamelech.zombieislandremastered.managers;

import lombok.Getter;
import me.hamelech.zombieislandremastered.area.Area;
import me.hamelech.zombieislandremastered.area.impl.BasicArea;
import me.hamelech.zombieislandremastered.configuration.Config;
import me.hamelech.zombieislandremastered.utils.LocUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class AreaManager implements Manager {
    private final PluginManager pluginManager;

    public AreaManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Getter
    private final Set<Area> areas = new HashSet<>();

    @Override
    public void initialize() {
        File areasFolder = new File(pluginManager.getPlugin().getDataFolder() + "/areas");
        if(!areasFolder.exists()) areasFolder.mkdirs();

        for(File file : areasFolder.listFiles()){
            Config config = Config.fromFile(file);

            UUID uuid = UUID.fromString(Objects.requireNonNull(config.getConfig().getString("uuid")));
            String type = Objects.requireNonNull(config.getConfig().getString("type"));
            String name = Objects.requireNonNull(config.getConfig().getString("name"));
            Location pos1 = LocUtils.deserialize(config, "pos1");
            Location pos2 = LocUtils.deserialize(config, "pos2");
            Area area = null;
            switch (type){
                case "basic":
                    area = new BasicArea(name, pos1, pos2, uuid);
                    break;
                default:
                    throw new UnsupportedOperationException("Area type is not recognized: " + config.getFile().getName());
            }
            areas.add(area);
        }
    }

    public Area getCurrentArea(Player player){
        //TODO
        return null;
    }
}
