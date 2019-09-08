package com.gabrielsimmer.cessentials.commands;

import com.gabrielsimmer.cessentials.Main;
import com.gabrielsimmer.cessentials.api.ApiClient;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class SetHome implements CommandExecutor {
    Main plugin;

    public SetHome(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player == false) {
            commandSender.sendMessage("Invalid command sender!");
            return false;
        }

        Player player = (Player) commandSender;
        String[] payload = new String[2];
        payload[0] = "username=" + player.getUniqueId();
        payload[1] = "coordinates=" + player.getLocation().toString();

        HttpsURLConnection con = ApiClient.Request(plugin.getConfig().get("api_url").toString() + "/homes");
        JSONObject response = ApiClient.Post(con, payload);

        Boolean ok = response.get("status").equals("ok");

        if (ok) {
            player.sendMessage("[CE] Home set!");
        }
        else {
            player.sendMessage("[CE] There was a problem setting the home location.");
        }

        return false;
    }
}
