package me.hamelech.zombieislandremastered.configuration;

import me.hamelech.zombieislandremastered.ZombieIslandRemastered;
import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private FileConfiguration config = new YamlConfiguration();
    private final JavaPlugin plugin;
    private final String name;
    private final String path;

    public static Config fromFile(File file){
        return new Config(file);
    }

    public Config(@NotNull File file){
        this.path = file.getParentFile().getPath();
        this.name= file.getName();
        this.plugin = null;
        this.file = file;

        try {

            config.load(file);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    /**
     * Creates a new configuration file which can be retrieved by the getConfig() method.
     * @param name The name of the config file.
     * @param plugin The plugin that uses the config.
     */
    public Config(@NotNull String name, @NotNull JavaPlugin plugin){
        //if(!name.endsWith(".yml")) throw new UnsupportedOperationException("Config name must end with .yml!");
        if(!name.endsWith(".yml")) name = name + ".yml";
        this.path = null;

        this.name = name;
        this.plugin = plugin;

        file = new File(plugin.getDataFolder(), name);

        if (!file.exists()) {

            if(!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();

            try {

                file.createNewFile();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        try {

            config.load(file);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }

    }

    public Config(@NotNull String name, @NotNull String path){
        if(!name.endsWith(".yml")) name = name + ".yml";

        this.name = name;
        this.plugin = null;
        this.path = path;
        File folder = new File(path);

        if(!folder.exists()) folder.mkdirs();

        file = new File(folder, name);

        if (!file.exists()) {


            try {

                file.createNewFile();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        try {

            config.load(file);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    /**
     * Sets an object with a given key inside of the config file.
     * @param path The path of the object to be saved at.
     * @param value The object to save.
     */
    public void set(@NotNull String path, @NotNull Object value){
        config.set(path, value);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reloads the configuration file.
     */
    public void reload(){
        if(path == null)
            config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), name));
        else
            config = YamlConfiguration.loadConfiguration(new File(new File(path), name));
    }

    /**
     * Gets the config file made by the constructor.
     * @return The {@link FileConfiguration} config.
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Gets the file made by the constructor.
     * @return The {@link File} file.
     */
    public File getFile(){
        return file;
    }

}

