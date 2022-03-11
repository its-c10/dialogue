package com.nthbyte.dialogue;

import org.bukkit.plugin.java.JavaPlugin;

public class DialogueAPI {

    private static JavaPlugin instance;
    private static DialogueManager dialogueManager;

    public static void hook(JavaPlugin hookingPlugin){
        instance = hookingPlugin;
        dialogueManager = new DialogueManager();
        hookingPlugin.getServer().getPluginManager().registerEvents(new DialogueListener(hookingPlugin), hookingPlugin);
    }

    public static DialogueManager getDialogueManager() {
        return dialogueManager;
    }

}
