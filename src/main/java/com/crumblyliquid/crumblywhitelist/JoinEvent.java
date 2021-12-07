package com.crumblyliquid.crumblywhitelist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class JoinEvent implements Listener {

    private static CrumblyWhitelist plugin;

    public JoinEvent(CrumblyWhitelist pl) {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        if (getPlugin().getConfig().getBoolean("active")) {
            String name = event.getName();
            if (getPlugin().getController().check(name)) {
                event.allow();
            }
            else {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, "You're not whitelisted!");
            }
        }
        else {
            event.allow();
        }
    }

    public static CrumblyWhitelist getPlugin() {
        return plugin;
    }
}