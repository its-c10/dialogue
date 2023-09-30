package com.nthbyte.dialogue.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * The event that fires just before the prompt uses its own validation check (If there is any). Use this if you wish to do your own validation.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.5.0.0
 */
public class ValidateInputEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private boolean isValidInput = true;
    private Player responder;
    private String promptId, input;

    public ValidateInputEvent(Player responder, String promptId, String input) {
        this.responder = responder;
        this.promptId = promptId;
        this.input = input;
    }

    public void setValidInput(boolean validInput) {
        isValidInput = validInput;
    }

    public boolean isValidInput() {
        return isValidInput;
    }

    public String getPromptId() {
        return promptId;
    }

    public String getInput() {
        return input;
    }

    public Player getResponder() {
        return responder;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
