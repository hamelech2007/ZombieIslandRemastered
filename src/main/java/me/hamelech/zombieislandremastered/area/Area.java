package me.hamelech.zombieislandremastered.area;

import org.bukkit.Location;

import java.util.UUID;

public interface Area {
    String getName();
    Location getPos1();
    Location getPos2();
    UUID getUniqueId();


}
