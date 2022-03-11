package com.nthbyte.dialogue.event;

import com.nthbyte.dialogue.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event fired when you receive input from a player.
 */
public class ReceiveInputEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private Player player;
    private String input;
    private Prompt prompt;

    public ReceiveInputEvent(Player player, Prompt prompt, String input){
        super(true);
        this.player = player;
        this.input = input;
        this.prompt = prompt;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public String getInput() {
        return input;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
