package de.artus.chatmentions.listeners;

import de.artus.chatmentions.utils.ChatMessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class onChatEvent implements Listener {


    @EventHandler
    public void onChat(PlayerChatEvent e) {
        e.setCancelled(true);
        Bukkit.getOnlinePlayers().forEach(p -> p.spigot().sendMessage(ChatMessageHandler.onChat(e.getPlayer(), e.getMessage())));
    }


}
