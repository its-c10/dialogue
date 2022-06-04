package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.Action;
import com.nthbyte.dialogue.action.context.ActionContext;
import com.nthbyte.dialogue.event.ReceiveInputEvent;
import com.nthbyte.dialogue.event.ValidateInputEvent;
import com.nthbyte.dialogue.util.InputFormatValidator;
import com.nthbyte.dialogue.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.Function;

/**
 * The listener for all input and dialogue.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.6.0
 */
public class DialogueListener implements Listener {

    private DialogueManager dialogueManager;
    private JavaPlugin hookedPlugin;

    public DialogueListener(JavaPlugin hookedPlugin, DialogueManager dialogueManager) {
        this.hookedPlugin = hookedPlugin;
        this.dialogueManager = dialogueManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        Set<UUID> playersBeingPrompted = dialogueManager.getPlayersInPrompt().keySet();

        // Blocks all messages for recipients that are being prompted at the moment.
        // Players that are being prompted shouldn't receive any messages from other players.
        e.getRecipients().removeIf(recipient -> playersBeingPrompted.contains(recipient.getUniqueId()));

        Player player = e.getPlayer();
        if (DialogueAPI.isHavingDialogue(player)) {
            e.setCancelled(true);
            Dialogue dialogue = dialogueManager.getCurrentDialogue(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(hookedPlugin, () -> {
                fireReceiveInputEvent(player, dialogue, e.getMessage());
            });
        }

    }

    /*
        Another way to deal with the receiving of input if you don't want to use the consumer.
     */

/*    @EventHandler
    public void onReceiveInput(ReceiveInputEvent e) {

        Prompt prompt = e.getPrompt();

        if (!prompt.getId().equalsIgnoreCase("creation-tier")) return;

        int tier = (int) e.getInput();
        Player player = e.getPlayer();
        Toady.Builder currentSession = plugin.getToadyManager().getToadyCreationSessions().get(player.getUniqueId());
        currentSession.setTier(tier);

        player.sendMessage(StringUtils.colorString("&bThe tier has been set to "));

    }*/

    /**
     * Deals with input storage (If the prompt as an input storage action)
     */
    @EventHandler
    public void onReceiveInput(ReceiveInputEvent e) {

        Prompt prompt = e.getPrompt();
        String input = e.getInput();
        Map<Action.BasePromptAction, ActionContext> receiveInputActions = prompt.getReceiveInputActions();
        for (Map.Entry<Action.BasePromptAction, ActionContext> entry : receiveInputActions.entrySet()) {

            Action.BasePromptAction<ActionContext, String> onReceiveInputAction = entry.getKey();
            Player player = e.getPlayer();
            if (onReceiveInputAction != null) {

                Map<String, String> inputStorage = DialogueManager.getInputStoragePerPlayer().get(player);
                if (inputStorage == null) {
                    inputStorage = new HashMap<>();
                }

                ActionContext context = entry.getValue();
                if (context == null) {
                    context = new ActionContext<>();
                }
                context.setResponder(player);
                context.setInputStorage(inputStorage);

                onReceiveInputAction.accept(context, input);

                if (!inputStorage.isEmpty()) {
                    DialogueManager.getInputStoragePerPlayer().put(player, inputStorage);
                }

            }

        }

    }

    private void fireReceiveInputEvent(Player player, Dialogue dialogue, String input) {

        Prompt prompt = dialogue.getCurrentPrompt();
        PromptInputType inputType = prompt.getType();

        if (Arrays.stream(dialogue.getEscapeSequences()).anyMatch(input::equalsIgnoreCase)) {
            player.sendMessage(Utils.tr(DialogueAPI.getMessagesConfig().ESCAPE_SEQUENCE_ENTERED));
            dialogueManager.endDialogue(player, DialogueEndCause.ESCAPE_SEQUENCE);
            return;
        }

        boolean shouldRepeatPrompt = dialogue.shouldRepeatPrompt();
        if (!InputFormatValidator.isValidFormat(inputType, input)) {
            String rawMsg = DialogueAPI.getMessagesConfig().INVALID_INPUT.replace("%inputType%", inputType.toString());
            String msg = rawMsg.replace("%inputType%", inputType.toString());
            player.sendMessage(Utils.tr(msg));
            if (shouldRepeatPrompt) {
                prompt.prompt(hookedPlugin, dialogue, player);
            }
            return;
        }

        ValidateInputEvent validationEvent = new ValidateInputEvent(player, prompt.getId(), input);
        Bukkit.getPluginManager().callEvent(validationEvent);

        Function<String, Boolean> validationAction = prompt.getOnValidateInputAction();

        if (prompt.getRetryLimit() != -1) {
            prompt.incrementRetries();
        }

        boolean isValidInput = validationEvent.isValidInput() && (validationAction == null || validationAction.apply(input));
        if (isValidInput) {
            Bukkit.getPluginManager().callEvent(new ReceiveInputEvent(player, prompt, input));
        }

        boolean atRetryLimit = prompt.isAtRetryLimit();
        if (!isValidInput && shouldRepeatPrompt && !atRetryLimit) {
            prompt.prompt(hookedPlugin, dialogue, player);
        } else if (!isValidInput && atRetryLimit) {
            player.sendMessage(Utils.tr(DialogueAPI.getMessagesConfig().REACHED_RETRY_LIMIT));
            if (prompt.willStopDialougeOnFailure()) {
                endDialogue(dialogue, player);
            } else {
                dialogue.nextPrompt(hookedPlugin, player);
            }
        } else if (dialogue.hasMorePrompts()) {
            dialogue.nextPrompt(hookedPlugin, player);
        } else {
            endDialogue(dialogue, player);
        }

    }

    private void endDialogue(Dialogue dialogue, Player player) {
        Map<String, String> inputStorage = DialogueManager.getInputStoragePerPlayer().get(player);
        for (ActionContext context : dialogue.getEndActions().values()) {
            if (context != null) {
                context.setInputStorage(inputStorage);
            }
        }
        dialogueManager.endDialogue(player, DialogueEndCause.NO_MORE_PROMPTS);
    }

}
