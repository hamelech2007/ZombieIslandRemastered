package me.hamelech.zombieislandremastered.managers;

import lombok.Getter;
import lombok.SneakyThrows;
import me.hamelech.zombieislandremastered.exceptions.HighVersionException;
import me.hamelech.zombieislandremastered.exceptions.UnknownVersionException;
import me.hamelech.zombieislandremastered.nms.Version;
import net.minecraft.network.protocol.Packet;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class NMSManager implements Manager{

    private final PluginManager pluginManager;

    private String version;

    private String sendPacketMethodName;

    private String connectionFieldName;

    @Getter
    private Version gameVersion;

    @SneakyThrows
    public NMSManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public Class<?> getCraftBukkitClass(String className){
        try {
            return (Class.forName("org.bukkit.craftbukkit." + version + "." + className));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public Object getCraftBukkitPlayer(Player player){
        try {
            return (Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer")).cast(player);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public Object getNMSPlayer(Player player){
        try {
            return getCraftBukkitPlayer(player).getClass().getDeclaredMethod("getHandle").invoke(getCraftBukkitPlayer(player));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public Class<?> getNMSClass(String className){
        try{
            if(gameVersion == Version.v1_18_R1 || gameVersion == Version.v1_17_R1) {
                throw new HighVersionException();
            }
             return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException | HighVersionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPacket(Player player, Packet<?> packet){
        try {
            getNMSPlayer(player).getClass().getDeclaredField(connectionFieldName).get(getNMSPlayer(player)).getClass().getDeclaredMethod(sendPacketMethodName, Packet.class).invoke(getNMSPlayer(player).getClass().getDeclaredField(connectionFieldName).get(getNMSPlayer(player)), packet);
        }catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void initialize() {
        this.version = pluginManager.getPlugin().getServer().getClass().getPackage().getName().split("\\.")[3];

        this.gameVersion = Version.fromString(version);
        System.out.println(version);
        if(gameVersion == null) throw new UnknownVersionException();


        switch (this.version){
            case "v1_18_R2":
            case "v1_18_R1":
                this.sendPacketMethodName = "a";
                this.connectionFieldName = "b";
                break;
            case "v1_17_R1":
                this.sendPacketMethodName = "sendPacket";
                this.connectionFieldName = "b";
                break;
            case "v1_16_R3":
            case "v1_16_R2":
            case "v1_16_R1":
            case "v1_15_R1":
            case "v1_14_R1":
            case "v1_13_R2":
            case "v1_13_R1":
            case "v1_12_R1":
            case "v1_11_R1":
            case "v1_10_R1":
            case "v1_9_R2":
            case "v1_9_R1":
            case "v1_8_R3":
            case "v1_8_R2":
            case "v1_8_R1":
                //1.8
                //1.8.9
                //1.8.8
                //1.9.2
                //1.9.4
                //1.10.2
                //1.11.2
                //1.12.2
                //1.13
                //1.13.2
                //1.14.4
                //1.15.2
                //1.16.1
                //1.16.3
                //1.16.5
                this.sendPacketMethodName = "sendPacket";
                this.connectionFieldName = "playerConnection";
                break;
            default:
                throw new UnknownVersionException();
        }
    }

}
