package com.crumblyliquid.crumblywhitelist;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

@CommandAlias("crumblywhitelist|cw|cwhitelist")
@CommandPermission("crumblywhitelist.all")
public class WhitelistCommands extends BaseCommand {

    private static CrumblyWhitelist plugin;

    public WhitelistCommands(CrumblyWhitelist pl) {
        plugin = pl;
    }

    @Subcommand("on")
    @Description("Activates crumbly whitelist.")
    public static void onTurnOn(CommandSender sender) {
        boolean val = getPlugin().getController().active();
        if (!val) {
            getPlugin().getController().on();
            sender.sendMessage(ChatColor.DARK_AQUA + "CrumblyWhitelist turned on.");
        }
        else {
            sender.sendMessage(ChatColor.RED + "CrumblyWhitelist is already turned on.");
        }
    }
    @Subcommand("off")
    @Description("Deactivates crumbly whitelist.")
    public static void onTurnOff(CommandSender sender) {
        boolean val = getPlugin().getController().active();
        if (val) {
            getPlugin().getController().off();
            sender.sendMessage(ChatColor.DARK_AQUA + "CrumblyWhitelist turned off.");
        }
        else {
            sender.sendMessage(ChatColor.RED + "CrumblyWhitelist is already turned off.");
        }
    }

    @Subcommand("add")
    @Description("Adds player to crumbly whitelist.")
    @CommandCompletion("@players")
    public static void onAdd(CommandSender sender, String name) {
        if (!getPlugin().getController().check(name)) {
            getPlugin().getController().add(name);
            sender.sendMessage(ChatColor.DARK_AQUA + name + " added to CrumblyWhitelist.");
        }
        else {
            sender.sendMessage(ChatColor.RED + name + " already found in CrumblyWhitelist.");
        }
    }

    @Subcommand("remove")
    @Description("Removes player to crumbly whitelist.")
    @CommandCompletion("@players")
    public static void onRemove(CommandSender sender, String name) {
        if (getPlugin().getController().check(name)) {
            getPlugin().getController().remove(name);
            sender.sendMessage(ChatColor.DARK_AQUA + name + " removed from CrumblyWhitelist.");
        }
        else {
            sender.sendMessage(ChatColor.RED + name + " is not in CrumblyWhitelist.");
        }
    }

    @Subcommand("list")
    @Description("Lists all players crumbly whitelist.")
    public static void onList(CommandSender sender) {
        List<String> whitelistList = getPlugin().getController().get();
        for (int i = 0; i < whitelistList.size(); i++) {
            String original = whitelistList.get(i);
            whitelistList.set(i, ChatColor.AQUA+"- "+original);
        }
        whitelistList.add(0, ChatColor.DARK_AQUA+"Whitelisted players:");
        String[] whitelistArray = whitelistList.stream().toArray(String[]::new);
        sender.sendMessage(whitelistArray);
    }

    @Default
    @HelpCommand
//    Just a help command
    public static void onHelp(CommandSender sender) {
        String[] content = {ChatColor.DARK_AQUA + "CrumblyWhitelist / Made by CrumblyLiquid", ChatColor.DARK_AQUA + "Commands: add, remove, list"};
        sender.sendMessage(content);
    }

    public static CrumblyWhitelist getPlugin() {
        return plugin;
    }
}