package com.gabrielsimmer.cessentials.commands;

import com.gabrielsimmer.cessentials.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {
    Main plugin;

    public Help(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player == false) {
            commandSender.sendMessage("Invalid command sender!");
            return false;
        }

        Player player = (Player) commandSender;

        player.sendMessage("======");
        player.sendMessage("CookieEssentials Help");
        player.sendMessage("/home - Teleport back home");
        player.sendMessage("/sethome - Set you home location");
        player.sendMessage("/warp <name> - List all warps, or teleport to selected point");
        if (player.hasPermission("cookie.setwarp")) {
            player.sendMessage("/setwarp <name> - Set a named warp point");
        }
        player.sendMessage("Coded by Gabriel (@gmem_)");
        player.sendMessage("======");

        return false;
    }
}
