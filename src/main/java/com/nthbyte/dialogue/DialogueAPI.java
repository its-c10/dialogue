package com.nthbyte.dialogue;

import com.google.common.io.Files;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * An API that completely eliminates your need for the ConversationsAPI
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.6.1
 */
public class DialogueAPI {

    private static MessagesConfig messagesConfig;
    private static DialogueManager dialogueManager;

    /**
     * Hooks your plugin into the API. Creates a new dialogue manager and registers dialogue listener.
     *
     * @param hookingPlugin A plugin instance.
     */
    public static void hook(JavaPlugin hookingPlugin) {

        new Metrics(hookingPlugin, 15384);

        dialogueManager = new DialogueManager(hookingPlugin);
        hookingPlugin.getServer().getPluginManager().registerEvents(new DialogueListener(hookingPlugin, dialogueManager), hookingPlugin);

        File dialogueFolder = new File(hookingPlugin.getDataFolder() + File.separator + "dialogue");
        if(dialogueFolder.exists()){
            dialogueFolder.mkdir();
        }

        File messagesFile = new File(hookingPlugin.getDataFolder() + File.separator + "dialogue", "messages.yml");
        if (!messagesFile.exists()) {
            hookingPlugin.saveResource("dialogue_messages.yml", false);
            File currentMessagesFile = new File(hookingPlugin.getDataFolder(), "dialogue_messages.yml");
            try {
                Files.move(currentMessagesFile, messagesFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messagesConfig = new MessagesConfig(hookingPlugin);

    }

    /**
     * Whether the player is having dialogue or being prompted.
     *
     * @param player The player we are checking.
     * @return If the player is having dialogue or being prompted.
     */
    public static boolean isHavingDialogue(Player player) {
        return dialogueManager.isConversing(player);
    }

    /**
     * Starts a new dialogue.
     *
     * @param player   The player you wish to start a dialogue with.
     * @param dialogue The dialogue.
     */
    public static void startDialogue(Player player, Dialogue dialogue) {
        dialogueManager.startDialogue(player, dialogue);
    }

    /**
     * Ends the player's dialogue.
     *
     * @param player The player that we wish to end dialogue for.
     * @param cause  The reason the dialogue ended.
     */
    public static void endDialogue(Player player, DialogueEndCause cause) {
        dialogueManager.endDialogue(player, cause);
    }

    public static MessagesConfig getMessagesConfig() {
        return messagesConfig;
    }

}
