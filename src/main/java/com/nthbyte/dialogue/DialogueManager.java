package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.context.ActionContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * The manager for all dialogue.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.3.0.0
 */
public class DialogueManager {

    /**
     * The players current in dialogue.
     */
    private Map<UUID, Dialogue> playersInDialogue = new HashMap<>();

    public Map<UUID, Dialogue> getPlayersInPrompt() {
        return playersInDialogue;
    }

    private JavaPlugin plugin;

    public DialogueManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public boolean isConversing(Player player){
        return playersInDialogue.containsKey(player.getUniqueId());
    }

    public void startDialogue(Player player, Dialogue dialogue){

        // They are trying start a dialogue that has previously already ended.
        if(dialogue.getCurrentIndexPrompt() != 0){
            throw new IllegalStateException("You can not start a dialogue that has already ended!");
        }
        // Ends any dialogue they could potentially be in currently.
        endDialogue(player, DialogueEndCause.STARTED_ANOTHER_DIALOGUE);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, player::closeInventory);

        playersInDialogue.put(player.getUniqueId(), dialogue);
        dialogue.getCurrentPrompt().prompt(player);
    }

    public void endDialogue(Player player, DialogueEndCause cause){

        Dialogue endedDialogue = playersInDialogue.remove(player.getUniqueId());
        if(endedDialogue == null) return;

        for(Map.Entry<Action.BasePromptAction, ActionContext> pair : endedDialogue.getEndActions().entrySet()){
            ActionContext context = pair.getValue();
            Map<String, String> inputStorage = (context == null || !context.hasStoredInputs()) ? new HashMap<>() : context.getInputStorage();
            Action.BasePromptAction endAction = pair.getKey();
            // They are defining their own action.
            if(endAction instanceof Action.EndAction || context == null){
                // Will be null if they are defining their own action (and not using a default one).
                context = new ActionContext();
            }
            context.setResponder(player);
            context.setInputStorage(inputStorage);

            if(!inputStorage.isEmpty() && context.getData() == null){
                context.initData();
            }

            if(endAction != null){
                if(endAction instanceof Action.EndAction){
                    endAction.accept(context, cause);
                }else if(endAction instanceof Action.DefaultAction && cause == DialogueEndCause.NO_MORE_PROMPTS){
                    endAction.accept(context, "");
                }
            }
        }


    }

    public Dialogue getCurrentDialogue(Player player){
        return playersInDialogue.get(player.getUniqueId());
    }

}
