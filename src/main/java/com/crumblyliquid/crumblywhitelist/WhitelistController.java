package com.crumblyliquid.crumblywhitelist;

import java.util.List;

public class WhitelistController {
    public static CrumblyWhitelist plugin;

    WhitelistController(CrumblyWhitelist pl) {
        plugin = pl;
    }

    public static List<String> get() {
        return getPlugin().getConfig().getStringList("whitelisted");
    }

    public static boolean active() {
        return getPlugin().getConfig().getBoolean("active");
    }

    public static void on() {
        getPlugin().getConfig().set("active", true);
        getPlugin().saveConfig();
    }

    public static void off() {
        getPlugin().getConfig().set("active", false);
        getPlugin().saveConfig();
    }

    public static boolean check(String name) {
        List<String> whitelisted = get();
        if (whitelisted.contains(name)) {
            return true;
        }
        return false;
    }

    public static void add(String name) {
        List<String> whitelisted = get();
        whitelisted.add(name);
        getPlugin().getConfig().set("whitelisted", whitelisted);
        getPlugin().saveConfig();
    }

    public static void remove(String name) {
        List<String> whitelisted = get();
        whitelisted.remove(name);
        getPlugin().getConfig().set("whitelisted", whitelisted);
        getPlugin().saveConfig();
    }

    public static CrumblyWhitelist getPlugin() {
        return plugin;
    }
}
