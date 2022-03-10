package com.nthbyte.dialogue;

import org.bukkit.entity.Player;

import java.util.*;

public class ConversationManager {

    /**
     * The players current in conversation.
     */
    private Map<UUID, Conversation> playersInConversation = new HashMap<>();

    public Map<UUID, Conversation> getPlayersInPrompt() {
        return playersInConversation;
    }

    public boolean isConversing(Player player){
        return playersInConversation.containsKey(player.getUniqueId());
    }

    public void startConversation(Player player, Conversation conversation){
        player.closeInventory();
        playersInConversation.put(player.getUniqueId(), conversation);
        conversation.getCurrentPrompt().prompt(player);
    }

    public void endConversation(Player player){
        playersInConversation.remove(player.getUniqueId()).getEndAction().accept(null);
    }

    public Conversation getConversation(Player player){
        return playersInConversation.get(player.getUniqueId());
    }

}
