package de.artus.chatmentions.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatMessageHandler {


    public static TextComponent onChat(Player sender, String message) {

        List<ChatComponent> chatComponents = new ArrayList<>();

        boolean isMentioning = false;
        StringBuilder currentMention = new StringBuilder();
        StringBuilder currentText = new StringBuilder();

        for (char symbol : message.toCharArray()) {

            if (Character.toString(symbol).equals(Config.getMentionSymbol())) {
                if (isMentioning) chatComponents.add(new ChatComponent(currentMention.toString(), ChatElement.MENTION));
                else chatComponents.add(new ChatComponent(currentText.toString(), ChatElement.TEXT));

                isMentioning = true;
                currentMention = new StringBuilder();
                currentText = new StringBuilder();
                currentMention.append(symbol);
                continue;
            }
            if ((isMentioning && Character.isSpaceChar(symbol)) || (isMentioning && !Character.toString(symbol).matches("^[a-zA-Z0-9_]*$"))) {
                isMentioning = false;
                chatComponents.add(new ChatComponent(currentMention.toString(), ChatElement.MENTION));
            }
            if (isMentioning) {
                currentMention.append(symbol);
            } else {
                currentText.append(symbol);
            }
        }
        if (isMentioning) chatComponents.add(new ChatComponent(currentMention.toString(), ChatElement.MENTION));
        else chatComponents.add(new ChatComponent(currentText.toString(), ChatElement.TEXT));


        for (ChatComponent component : chatComponents) {
            if (component.type == ChatElement.MENTION) {
                Player player = component.getPlayer();
                if (player != null) mentionPlayer(player);
            }
        }

        TextComponent prefix = new TextComponent("<" + sender.getDisplayName() + ">");
        prefix.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "click to mention").create()));
        prefix.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "@" + sender.getName()));

        TextComponent msg = new TextComponent();
        msg.addExtra(prefix);
        msg.addExtra(" ");


        for (int i = 0; i < chatComponents.size(); i++) {
            msg.addExtra(chatComponents.get(i).display());
        }

        return msg;
    }


    public static void mentionPlayer(Player player) {

        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

    }

}
