package com.nthbyte.dialogue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class DialogueListener implements Listener {

    private DialogueManager dialogueManager;

    public DialogueListener(DialogueManager dialogueManager){
        this.dialogueManager = dialogueManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){

        Set<UUID> playersBeingPrompted = dialogueManager.getPlayersInPrompt().keySet();

        // Blocks all messages for recipients that are being prompted at the moment.
        // Players that are being prompted shouldn't receive any messages from other players.
        e.getRecipients().removeIf(recipient -> playersBeingPrompted.contains(recipient.getUniqueId()));

        Player player = e.getPlayer();
        if(DialogueAPI.isHavingDialogue(player)){
            e.setCancelled(true);
            Dialogue dialogue = dialogueManager.getCurrentDialogue(player);
            fireReceiveInputEvent(player, dialogue, e.getMessage());
        }

    }

    /*
        Another way to deal with the receiving of input if you don't want to use the consumer.
     */

//    @EventHandler
//    public void onReceiveInput(ReceiveInputEvent e) {
//
//        Prompt prompt = e.getPrompt();
//
//        if (!prompt.getId().equalsIgnoreCase("creation-tier")) return;
//
//        int tier = (int) e.getInput();
//        Player player = e.getPlayer();
//        Toady.Builder currentSession = plugin.getToadyManager().getToadyCreationSessions().get(player.getUniqueId());
//        currentSession.setTier(tier);
//
//        player.sendMessage(StringUtils.colorString("&bThe tier has been set to "));
//
//    }

    @EventHandler
    public void onReceiveInput(ReceiveInputEvent e){
        Prompt prompt = e.getPrompt();
        String input = e.getInput();
        Consumer<String> onReceiveInputAction = prompt.getOnReceiveInputAction();
        if(onReceiveInputAction != null){
            onReceiveInputAction.accept(input);
        }
    }

    private void fireReceiveInputEvent(Player player, Dialogue dialogue, String input){

        Prompt prompt = dialogue.getCurrentPrompt();
        PromptInputType inputType = prompt.getType();

        if(input.equalsIgnoreCase(dialogue.getEscapeSequence())){
            dialogueManager.endDialogue(player, DialogueEndCause.ESCAPE_SEQUENCE);
            return;
        }

        if(!InputFormatValidator.isValidFormat(inputType, input)){
            player.sendMessage(Utils.tr("&cThe input is not in the valid format! The input type should be " + inputType));
            prompt.prompt(player);
            return;
        }

        Function<String, Boolean> validationAction = prompt.getOnValidateInputAction();
        if(validationAction != null && !validationAction.apply(input)){
            return;
        }

        Bukkit.getPluginManager().callEvent(new ReceiveInputEvent(player, prompt, input));

        if(dialogue.hasMorePrompts()){
            dialogue.nextPrompt(player);
        }else{
            dialogueManager.endDialogue(player, DialogueEndCause.NO_MORE_PROMPTS);
        }

    }

}
