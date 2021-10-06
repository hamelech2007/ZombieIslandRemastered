package me.hamelech.zombieislandremastered.weapons.guns;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Gun extends Listener {
    void shoot(PlayerInteractEvent event);
    void reload(PlayerInteractEvent event);
}
