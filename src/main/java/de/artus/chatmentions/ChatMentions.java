package de.artus.chatmentions;

import de.artus.chatmentions.listeners.onChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatMentions extends JavaPlugin {

    @Override
    public void onEnable() {


        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new onChatEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
