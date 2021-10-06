package me.hamelech.zombieislandremastered.guilds;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class Guild {

    private String name;
    private final UUID uuid;
    private final Set<UUID> members;
    private UUID ownerUUID;

    public Guild(String name, UUID uuid, Set<UUID> members, UUID ownerUUID) {
        this.name = name;
        this.uuid = uuid;
        this.members = members;
        this.ownerUUID = ownerUUID;
    }
}
