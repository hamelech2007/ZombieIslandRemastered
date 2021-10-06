package me.hamelech.zombieislandremastered.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> colorize(final List<String> list) {
        return list.stream().map(ChatUtils::colorize).collect(Collectors.toList());
    }

    public static void consoleError(String msg){
        Bukkit.getLogger().severe(msg);
    }
}
