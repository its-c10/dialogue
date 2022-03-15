package com.nthbyte.dialogue;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An API that completely eliminates your need for the ConversationsAPI
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.1.1.1
 */
public class DialogueAPI {

    private static DialogueManager dialogueManager;

    /**
     * Hooks your plugin into the API. Creates a new dialogue manager and registers dialogue listener.
     *
     * @param hookingPlugin A plugin instance.
     */
    public static void hook(JavaPlugin hookingPlugin){
        dialogueManager = new DialogueManager(hookingPlugin);
        hookingPlugin.getServer().getPluginManager().registerEvents(new DialogueListener(hookingPlugin, dialogueManager), hookingPlugin);
    }

    /**
     * Whether the player is having dialogue or being prompted.
     *
     * @param player The player we are checking.
     * @return If the player is having dialogue or being prompted.
     */
    public static boolean isHavingDialogue(Player player){
        return dialogueManager.isConversing(player);
    }

    /**
     * Starts a new dialogue.
     *
     * @param player The player you wish to start a dialogue with.
     * @param dialogue The dialogue.
     */
    public static void startDialogue(Player player, Dialogue dialogue){
        dialogueManager.startDialogue(player, dialogue);
    }

    /**
     * Ends the player's dialogue.
     *
     * @param player The player that we wish to end dialogue for.
     * @param cause The reason the dialogue ended.
     */
    public static void endDialogue(Player player, DialogueEndCause cause){
        dialogueManager.endDialogue(player, cause);
    }

}
