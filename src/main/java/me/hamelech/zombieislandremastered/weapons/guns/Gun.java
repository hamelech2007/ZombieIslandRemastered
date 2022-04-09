package me.hamelech.zombieislandremastered.weapons.guns;

import me.hamelech.zombieislandremastered.weapons.Weapon;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Gun extends Listener, Weapon {
    void shoot(PlayerInteractEvent event);
    void reload(PlayerInteractEvent event);
}
