package com.gabrielsimmer.cessentials;

import com.gabrielsimmer.cessentials.commands.Help;
import com.gabrielsimmer.cessentials.commands.Home;
import com.gabrielsimmer.cessentials.commands.SetHome;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void loadConfiguration() {
        getConfig().addDefault("api_url", "https://localhost:8080");

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {
        loadConfiguration();
        // Connect to the database!
        getLogger().info("CookieEssentials Loaded");
        getCommand("cehelp").setExecutor(new Help(this));
        getCommand("sethome").setExecutor(new SetHome(this));
        getCommand("home").setExecutor(new Home(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("CookieEssentials Disabled");
    }
}
