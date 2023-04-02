package de.artus.chatmentions.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatComponent {


    public String content;
    public ChatElement type;

    public ChatComponent(String content, ChatElement type) {
        this.content = content;
        this.type = type;

        if (type == ChatElement.MENTION && content.length() == 1) {
            this.type = ChatElement.TEXT;
        }
    }

    public TextComponent display() {
        if (type == ChatElement.MENTION) {
            TextComponent textComponent = new TextComponent(Config.getMentionChatColor() + content);
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "click to mention").create()));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, content));
            return textComponent;
        } else if (type == ChatElement.TEXT) {
            return new TextComponent(ChatColor.RESET + content);
        }
        return null;
    }

    public Player getPlayer() {
        if (type != ChatElement.MENTION) return null;
        return Bukkit.getPlayer(content.substring(1));

    }


}
