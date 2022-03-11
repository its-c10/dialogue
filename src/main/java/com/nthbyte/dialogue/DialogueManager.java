package com.nthbyte.dialogue;

import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

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
        // They are trying start a dialogue that has previously already ended.
        if(!dialogue.hasMorePrompts()){
            throw new IllegalStateException("You can not start a dialogue that has already ended!");
        }
        endDialogue(player, DialogueEndCause.STARTED_ANOTHER_DIALOGUE);
        player.closeInventory();
        playersInDialogue.put(player.getUniqueId(), dialogue);
        dialogue.getCurrentPrompt().prompt(player);
    }

    public void endDialogue(Player player, DialogueEndCause cause){

        Dialogue endedDialogue = playersInDialogue.remove(player.getUniqueId());
        if(endedDialogue == null) return;

        Consumer<DialogueEndCause> endAction = endedDialogue.getEndAction();
        if(endAction != null){
            endAction.accept(cause);
        }

    }

    public Dialogue getCurrentDialogue(Player player){
        return playersInDialogue.get(player.getUniqueId());
    }

}
