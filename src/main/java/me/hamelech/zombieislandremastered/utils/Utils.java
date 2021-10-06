package me.hamelech.zombieislandremastered.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@UtilityClass
public class Utils {
    public static boolean isPlayer(CommandSender entity){
        return (entity instanceof Player);
    }

}
