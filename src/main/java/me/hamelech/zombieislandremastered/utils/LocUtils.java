package me.hamelech.zombieislandremastered.utils;

import lombok.experimental.UtilityClass;

import me.hamelech.zombieislandremastered.configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@UtilityClass
public class LocUtils {
    public static void serialize(Config config, String path, Location location){
        ConfigurationSection section = config.getConfig().getConfigurationSection(path);
        section.set("world", Objects.requireNonNull(location.getWorld()).getName());
        section.set("x", location.getBlockX());
        section.set("y", location.getBlockY());
        section.set("z", location.getBlockZ());

        config.set(path, section);
    }

    public static Location deserialize(Config config, String path){
        ConfigurationSection section = config.getConfig().getConfigurationSection(path);
        Location location = new Location(Bukkit.getWorld((String) Objects.requireNonNull(section.get("world"))), (double) section.get("x"), (double) section.get("y"), (double) section.get("z"));
        return location;
    }
}
