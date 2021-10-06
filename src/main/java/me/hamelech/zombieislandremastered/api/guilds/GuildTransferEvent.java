package me.hamelech.zombieislandremastered.api.guilds;

import me.hamelech.zombieislandremastered.guilds.Guild;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GuildTransferEvent extends Event implements Cancellable {

    private final Guild guild;
    private final Player prev;
    private final OfflinePlayer curr;
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    public GuildTransferEvent(Guild guild, Player prev, OfflinePlayer curr){
        this.guild = guild;
        this.prev = prev;
        this.curr = curr;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Guild getGuild() {
        return guild;
    }

    public Player getPrev() {
        return prev;
    }

    public OfflinePlayer getCurr(){
        return curr;
    }
}