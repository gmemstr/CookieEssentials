package com.gabrielsimmer.cessentials.commands;

import com.gabrielsimmer.cessentials.Main;
import com.gabrielsimmer.cessentials.api.ApiClient;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class Home implements CommandExecutor {
    Main plugin;

    public Home(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player == false) {
            commandSender.sendMessage("Invalid command sender!");
            return false;
        }

        Player player = (Player) commandSender;
        String[] payload = new String[1];
        payload[0] = "username=" + player.getUniqueId();

        HttpsURLConnection con = ApiClient.Request(plugin.getConfig().get("api_url").toString() + "/homes");
        JSONObject response  = ApiClient.Get(con, payload);

        Boolean ok = response.get("status").equals("ok");
        if (ok) {
            Location home = new Location(
                    player.getWorld(),
                    Double.parseDouble(response.get("homex").toString()),
                    Double.parseDouble(response.get("homey").toString()),
                    Double.parseDouble(response.get("homez").toString())
            );
            player.teleport(home);
        }
        else {
            player.sendMessage("[CE] There was a problem setting the home location.");
        }

        return false;
    }
}
