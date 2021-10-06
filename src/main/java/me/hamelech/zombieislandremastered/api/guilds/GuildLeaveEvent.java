package me.hamelech.zombieislandremastered.api.guilds;

import me.hamelech.zombieislandremastered.guilds.Guild;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GuildLeaveEvent extends Event implements Cancellable {

    private final Guild guild;
    private final Player player;
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    public GuildLeaveEvent(Guild guild, Player player){
        this.guild = guild;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }
}