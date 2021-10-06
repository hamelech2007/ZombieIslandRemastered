package me.hamelech.zombieislandremastered.managers;

import java.util.ArrayList;
import java.util.List;

public class GunsManager implements Manager{
    private final PluginManager pluginManager;

    public GunsManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public void initialize() {

        //pistol
        setIfNull("pistol.name", "&8Pistol");
        List<String> pistolLore = new ArrayList<>();
        pistolLore.add("&7Click right-click to shoot the gun");
        pistolLore.add("&7Click left-click to reload the gun");
        pistolLore.add("&8The gun will only reload if you have the right bullets");
        setIfNull("pistol.name", "&8Pistol");
        setIfNull("pistol.lore", pistolLore);
        setIfNull("pistol.material", "MUSIC_DISC_11");
        setIfNull("pistol.bullet.material", "DIAMONDS");
        setIfNull("pistol.bullet.name", "&6Pistol Bullets");
        List<String> pistolBulletLore = new ArrayList<>();
        pistolBulletLore.add("&7The ammo used to reload the pistol.");
        setIfNull("pistol.bullet.lore", pistolBulletLore);
    }

    private void setIfNull(String path, Object object){
        if(pluginManager.getGunsFile().getConfig().get(path) != null) return;
        pluginManager.getGunsFile().set(path, object);
    }
}
