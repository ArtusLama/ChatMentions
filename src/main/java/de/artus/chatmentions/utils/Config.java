package de.artus.chatmentions.utils;

import de.artus.chatmentions.ChatMentions;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static File configFile = new File(ChatMentions.getPlugin(ChatMentions.class).getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static void save() {
        try { config.save(configFile); } catch (Exception ignored) {}
    }


    public static void resetToDefaultConfig() {
        config.set("mentionSymbol", "@");
        config.set("mentionColor", ChatColor.AQUA);
    }


    public static String getMentionSymbol() {
        return config.getString("mentionSymbol");
    }

    public static ChatColor getMentionChatColor() {
        return ChatColor.valueOf(config.getString("mentionColor"));
    }

}
