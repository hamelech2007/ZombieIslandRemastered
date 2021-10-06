package me.hamelech.zombieislandremastered.managers;

import lombok.Getter;
import me.hamelech.zombieislandremastered.api.guilds.GuildCreateEvent;
import me.hamelech.zombieislandremastered.api.guilds.GuildDisbandEvent;
import me.hamelech.zombieislandremastered.area.Area;
import me.hamelech.zombieislandremastered.area.impl.BasicArea;
import me.hamelech.zombieislandremastered.configuration.Config;
import me.hamelech.zombieislandremastered.exceptions.GuildNotFoundException;
import me.hamelech.zombieislandremastered.guilds.Guild;
import me.hamelech.zombieislandremastered.utils.LocUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class GuildsManager implements Manager {
    private final PluginManager pluginManager;

    public GuildsManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Getter
    Set<Guild> guilds = new HashSet<>();

    @Override
    public void initialize() {
        File guildsFolder = new File(pluginManager.getPlugin().getDataFolder() + "/guilds");
        if(!guildsFolder.exists()) guildsFolder.mkdirs();

        for(File file : guildsFolder.listFiles()){
            Config config = Config.fromFile(file);

            String name = Objects.requireNonNull(config.getConfig().getString("name"));
            UUID uuid = UUID.fromString(Objects.requireNonNull(config.getConfig().getString("uuid")));
            UUID ownerUUID = UUID.fromString(Objects.requireNonNull(config.getConfig().getString("owner")));
            Set<UUID> members = new HashSet<>();
            for(String playerUUID : Objects.requireNonNull(config.getConfig().getStringList("members"))){
                members.add(UUID.fromString(playerUUID));
            }

            guilds.add(new Guild(name, uuid, members, ownerUUID));
        }
    }

    public void createGuild(String name, Player creator){
        Set<UUID> members = new HashSet<>();
        members.add(creator.getUniqueId());
        Guild guild = new Guild(name, UUID.randomUUID(),members , creator.getUniqueId());

        guilds.add(guild);

        saveGuild(guild);

        pluginManager.getPlugin().getServer().getPluginManager().callEvent(new GuildCreateEvent(guild, creator));
    }

    public void saveGuild(Guild guild){
        File guildsFolder = new File(pluginManager.getPlugin().getDataFolder() + "/guilds");
        for(File file : guildsFolder.listFiles()){
            if(file.getName().equals(String.valueOf(guild.getUuid()))){
                Config config = Config.fromFile(file);
                config.set("uuid", guild.getUuid().toString());
                config.set("owner", guild.getOwnerUUID().toString());
                config.set("name", guild.getName());
                List<String> uuids = new ArrayList<>();
                for(UUID member : guild.getMembers()){
                    uuids.add(member.toString());
                }
                config.set("members", uuids);
                return;
            }
        }
        Config config = new Config(String.valueOf(guild.getUuid()), pluginManager.getPlugin().getDataFolder() + "/guilds");

        config.set("uuid", guild.getUuid().toString());
        config.set("owner", guild.getOwnerUUID().toString());
        config.set("name", guild.getName());
        List<String> uuids = new ArrayList<>();
        for(UUID member : guild.getMembers()){
            uuids.add(member.toString());
        }
        config.set("members", uuids);
    }

    public Guild getPlayerGuild(Player player){
        for(Guild guild : guilds){
            if(guild.getMembers().contains(player.getUniqueId())) return guild;
        }

        try {
            throw new GuildNotFoundException();
        } catch (GuildNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean doesGuildExist(String guildName){
        for(Guild guild : guilds){
            if (guild.getName().equals(guildName)) return true;
        }
        return false;
    }

    public Guild getGuild(String guildName){
        for(Guild guild : guilds){
            if (guild.getName().equals(guildName)) return guild;
        }

        return null;
    }

    public void deleteGuild(Guild guild){
        for(Guild g : guilds){
            if (g.getUuid().equals(guild.getUuid())){
                guilds.remove(g);
                File file = new File(pluginManager.getPlugin().getDataFolder() + "/guilds", g.getUuid() + ".yml");
                file.delete();
                Bukkit.getPluginManager().callEvent(new GuildDisbandEvent(g, Bukkit.getPlayer(g.getOwnerUUID())));
            }
        }


    }
}
