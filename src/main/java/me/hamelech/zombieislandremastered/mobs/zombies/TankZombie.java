package me.hamelech.zombieislandremastered.mobs.zombies;

import de.tr7zw.nbtapi.NBTEntity;
import lombok.Getter;
import me.hamelech.zombieislandremastered.managers.PluginManager;
import me.hamelech.zombieislandremastered.nms.Version;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TankZombie  {

    private final PluginManager pluginManager;

    @Getter
    private Object zombie;

    private Class<?> entityClass;

    public TankZombie(PluginManager pluginManager, Location location){

        this.pluginManager = pluginManager;

        Object zombie;

        try {
            Object zombieEnum = pluginManager.getNmsManager().getNMSClass("EntityTypes").getDeclaredMethod("valueOf", String.class).invoke(pluginManager.getNmsManager().getNMSClass("EntityTypes"), pluginManager.getNmsManager().getGameVersion().isAbove(Version.v1_17_R1) ? "be" : "ZOMBIE");
            zombie = pluginManager.getNmsManager().getNMSClass("EntityZombie").getDeclaredConstructor(pluginManager.getNmsManager().getNMSClass("EntityTypes") ,pluginManager.getNmsManager().getNMSClass("World"))
                    .newInstance(zombieEnum ,pluginManager.getNmsManager().getCraftBukkitClass("World").getDeclaredMethod("getHandle").invoke(pluginManager.getNmsManager().getCraftBukkitClass("World").cast(location.getWorld())));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            zombie = null;
        }

        if(zombie == null) return;

        this.zombie = zombie;

        this.entityClass = zombie.getClass();

        /*EntityZombie entityZombie = new EntityZombie(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());

        entityZombie.setPosition(location.getX(), location.getY(), location.getZ());

        ChatComponentText chatComponentText = new ChatComponentText(pluginManager.getNames().get("tank-zombie"));

        entityZombie.setCustomName(chatComponentText);

        entityZombie.setCustomNameVisible(true);
        entityZombie.bP.a(0, new PathfinderGoalAvoidTarget(entityZombie, EntityPlayer.class, 15, 1.0D, 1.0D));

         */

        try {
            Object name = pluginManager.getNmsManager().getNMSClass("ChatComponentText").getDeclaredConstructor(String.class).newInstance(pluginManager.getNames().get("tank-zombie"));
            entityClass.getDeclaredMethod("setPosition", double.class, double.class, double.class).invoke(this.zombie, location.getX(), location.getY(), location.getZ());
            entityClass.getDeclaredMethod("setCustomName", pluginManager.getNmsManager().getNMSClass("ChatComponentText")).invoke(this.zombie, name);
            entityClass.getDeclaredMethod("setCustomNameVisible", boolean.class).invoke(this.zombie, true);
            entityClass.getDeclaredMethod("setHealth", int.class).invoke(this.zombie, 100);
            Object goalSelector = entityClass.getDeclaredField(pluginManager.getNmsManager().getGameVersion().isAbove(Version.v1_18_R1) ? "bP" : "goalSelector").get(this.zombie);
            Object targetSelector = entityClass.getDeclaredField(pluginManager.getNmsManager().getGameVersion().isAbove(Version.v1_18_R1) ? "bQ" : "targetSelector").get(this.zombie);
            Method aGoal = goalSelector.getClass().getDeclaredMethod("a", int.class, pluginManager.getNmsManager().getNMSClass("PathfinderGoal"));
            Method aTarget = targetSelector.getClass().getDeclaredMethod("a", int.class, pluginManager.getNmsManager().getNMSClass("PathfinderGoal"));
            aGoal.invoke(
                    goalSelector,
                    2,
                    pluginManager.getNmsManager().getNMSClass("PathfinderGoalZombieAttack").getDeclaredConstructor(entityClass, double.class, boolean.class).newInstance(this.zombie, 1.0, false)
            );
            aGoal.invoke(
                    goalSelector,
                    6,
                    pluginManager.getNmsManager().getNMSClass("PathfinderGoalRandomStrollLand").getDeclaredConstructor(entityClass, double.class).newInstance(this.zombie, 1.0)
            );
            aTarget.invoke(
              targetSelector,
                    2,
                    pluginManager.getNmsManager().getNMSClass("PathfinderGoalNearestAttackableTarget").getDeclaredConstructor(entityClass, pluginManager.getNmsManager().getNMSClass("EntityPlayer"), boolean.class).newInstance(this.zombie, pluginManager.getNmsManager().getNMSClass("EntityPlayer"), true)
            );

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }


    }




    /*public static Zombie make(Zombie zombie){
        zombie.setAdult();
        zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

        NBTEntity zombieNBT = new NBTEntity(zombie);



        zombieNBT.getStringList("Attributes").add("tank");


        return zombie;
    }*/
}
