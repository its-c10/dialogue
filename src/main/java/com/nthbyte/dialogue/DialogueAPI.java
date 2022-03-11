package com.nthbyte.dialogue;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class DialogueAPI {

    private static JavaPlugin instance;
    private static DialogueManager dialogueManager;

    public static void hook(JavaPlugin hookingPlugin){
        instance = hookingPlugin;
        dialogueManager = new DialogueManager();
        hookingPlugin.getServer().getPluginManager().registerEvents(new DialogueListener(hookingPlugin), hookingPlugin);
    }

    public static boolean isHavingDialogue(Player player){
        return dialogueManager.isConversing(player);
    }

    public static void startDialogue(Player player, Dialogue dialogue){
        dialogueManager.startDialogue(player, dialogue);
    }

    public static void endDialogue(Player player){
        dialogueManager.endDialogue(player);
    }

    public static Dialogue getDialogue(Player player){
        return dialogueManager.getDialogue(player);
    }

    public static Map<UUID, Dialogue> getPlayersInDialogue(){
        return dialogueManager.getPlayersInPrompt();
    }

}
