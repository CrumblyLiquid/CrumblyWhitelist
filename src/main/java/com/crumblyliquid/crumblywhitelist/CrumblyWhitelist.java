package com.crumblyliquid.crumblywhitelist;

import co.aikar.commands.MessageKeys;
import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrumblyWhitelist extends JavaPlugin {

    private static PaperCommandManager commandManager;
    private static WhitelistController controller;

    @Override
    public void onEnable() {
//        Called on startup
        getLogger().info("Loading configuration...");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        controller = new WhitelistController(this);
        getLogger().info("Registering events...");
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getLogger().info("Loading commands...");
        registerCommands();
        getLogger().info("Enabled.");
    }

    private void registerCommands() {
//        Register ACL stuff
        commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new WhitelistCommands(this).setExceptionHandler((command, registeredCommand, sender, args, t) -> {
            sender.sendMessage(MessageType.ERROR, MessageKeys.ERROR_GENERIC_LOGGED);
            return true;
        }));

        commandManager.setDefaultExceptionHandler((command, registeredCommand, sender, args, t) -> {
            getLogger().warning("Error occurred while executing command " + command.getName());
            return false;
        });
    }

    public static WhitelistController getController() {
        return controller;
    }
}
