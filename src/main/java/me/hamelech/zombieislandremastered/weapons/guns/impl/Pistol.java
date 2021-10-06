package me.hamelech.zombieislandremastered.weapons.guns.impl;

import me.hamelech.zombieislandremastered.managers.PluginManager;
import me.hamelech.zombieislandremastered.weapons.guns.Gun;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Pistol implements Gun {
    private final PluginManager pluginManager;

    private final ItemStack gunItem;

    public Pistol(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
        ItemStack pistolItem = new ItemStack(Material.valueOf(pluginManager.getGunsFile().getConfig().getString("pistol.material")));

        List<String> lore = pluginManager.getGunsFile().getConfig().getStringList("pistol.lore");
        ItemMeta pistolMeta = pistolItem.getItemMeta();
        pistolMeta.setLore(lore);
        pistolMeta.setDisplayName(pluginManager.getGunsFile().getConfig().getString("pistol.name"));

        pistolItem.setItemMeta(pistolMeta);

        gunItem = pistolItem;

    }

    @EventHandler
    @Override
    public void shoot(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) || !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(event.getItem().getType() != gunItem.getType()) return;
        if(event.getItem().getItemMeta().getDisplayName() != gunItem.getItemMeta().getDisplayName()) return;
        if(!event.getItem().getItemMeta().getLore().equals(gunItem.getItemMeta().getLore())) return;

        event.setCancelled(true);

        //TODO functionality




    }

    @Override
    public void reload(PlayerInteractEvent playerInteractEvent) {

    }
}
