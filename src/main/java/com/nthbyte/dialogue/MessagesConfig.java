package com.nthbyte.dialogue;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MessagesConfig {

    public final String INVALID_INPUT;
    public final String REACHED_RETRY_LIMIT;

    public final String REACHED_TIME_LIMIT;

    public final String ESCAPE_SEQUENCE_ENTERED;

    private FileConfiguration config;

    public MessagesConfig(JavaPlugin hookingPlugin) {
        loadConfig(hookingPlugin);
        INVALID_INPUT = config.getString("invalid-input");
        REACHED_RETRY_LIMIT = config.getString("reached-retry-limit");
        REACHED_TIME_LIMIT = config.getString("reached-time-limit");
        ESCAPE_SEQUENCE_ENTERED = config.getString("escape-seq-dialogue-end");
    }

    private void loadConfig(JavaPlugin hookingPlugin) {
        File file = new File(hookingPlugin.getDataFolder() + File.separator + "dialogue", "messages.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

}
