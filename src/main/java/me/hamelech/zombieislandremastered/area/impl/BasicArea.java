package me.hamelech.zombieislandremastered.area.impl;

import me.hamelech.zombieislandremastered.area.Area;

import org.bukkit.Location;

import java.util.UUID;

public class BasicArea implements Area {

    private final String name;
    private final Location pos1;
    private final Location pos2;
    private final UUID uniqueId;

    public BasicArea(String name, Location pos1, Location pos2, UUID uuid) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.uniqueId = uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getPos1() {
        return pos1;
    }

    @Override
    public Location getPos2() {
        return pos2;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }
}
