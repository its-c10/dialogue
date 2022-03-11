package com.nthbyte.dialogue;

import org.bukkit.entity.Player;

import java.util.*;

public class DialogueManager {

    /**
     * The players current in dialogue.
     */
    private Map<UUID, Dialogue> playersInDialogue = new HashMap<>();

    public Map<UUID, Dialogue> getPlayersInPrompt() {
        return playersInDialogue;
    }

    public boolean isConversing(Player player){
        return playersInDialogue.containsKey(player.getUniqueId());
    }

    public void startDialogue(Player player, Dialogue dialogue){
        player.closeInventory();
        playersInDialogue.put(player.getUniqueId(), dialogue);
        dialogue.getCurrentPrompt().prompt(player);
    }

    public void endDialogue(Player player){
        playersInDialogue.remove(player.getUniqueId()).getEndAction().accept(null);
    }

    public Dialogue getDialogue(Player player){
        return playersInDialogue.get(player.getUniqueId());
    }

}
