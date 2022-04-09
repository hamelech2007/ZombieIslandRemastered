package me.hamelech.zombieislandremastered.commands.impl.zombie;

import me.hamelech.zombieislandremastered.commands.BaseCommand;
import me.hamelech.zombieislandremastered.commands.CommandInfo;
import me.hamelech.zombieislandremastered.commands.CommandType;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import me.hamelech.zombieislandremastered.mobs.zombies.TankZombie;
import net.minecraft.server.level.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

@CommandInfo(name = "spawnzombie", permission = "zombieislandremasterd.spawnzombie", type = CommandType.PLAYER)
public class SpawnZombieCMD extends BaseCommand {
    private final PluginManager pluginManager;

    public SpawnZombieCMD(PluginManager pluginManager) {
        super(pluginManager);
        this.pluginManager = pluginManager;
    }

    @Override
    public void executePlayer(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(pluginManager.getMessages().get("missing-arguments"));
            return;
        }
        switch (args[0]){
            case "tank":
                TankZombie tankZombie = new TankZombie(pluginManager, player.getLocation());
                spawnZombie(tankZombie.getZombie(), player.getLocation().getWorld());
                break;
        }




    }

    private void spawnZombie(Object zombie, World world){
        try {
            Object nmsWorld = pluginManager.getNmsManager().getCraftBukkitClass("World").getDeclaredMethod("getHandle").invoke(pluginManager.getNmsManager().getCraftBukkitClass("World").cast(world));
            nmsWorld.getClass().getDeclaredMethod("addEntity", pluginManager.getNmsManager().getNMSClass("Entity")).invoke(nmsWorld, zombie);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
