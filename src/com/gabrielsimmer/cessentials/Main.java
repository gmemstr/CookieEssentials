package com.gabrielsimmer.cessentials;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void loadConfig() {

    }

    @Override
    public void onEnable() {
        // Connect to the database!
        getLogger().info("CookieEssentials Loaded");
        getCommand("cehelp").setExecutor(new Help(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("CookieEssentials Disabled");
    }
}
