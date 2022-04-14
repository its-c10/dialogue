package com.nthbyte.dialogue;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MessagesConfig {

    public final String INVALID_INPUT;
    public final String REACHED_RETRY_LIMIT;

    private FileConfiguration config;

    public MessagesConfig(JavaPlugin hookingPlugin){
        loadConfig(hookingPlugin);
        INVALID_INPUT = config.getString("invalid-input");
        REACHED_RETRY_LIMIT = config.getString("reached-retry-limit");
    }

    private void loadConfig(JavaPlugin hookingPlugin){
        File file = new File(hookingPlugin.getDataFolder() + File.separator + "dialogue" + File.separator + "messages.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

}
