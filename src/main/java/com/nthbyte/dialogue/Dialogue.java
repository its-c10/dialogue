package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.context.ActionContext;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that represents dialogue between the plugin and a player.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.3.0.0
 */
public class Dialogue {

    /**
     * The sequence that you have to type in order to quit the dialogue.
     */
    private String escapeSequence;
    private List<Prompt> prompts;
    /**
     * Actions that are ran when the dialogue ends.
     */
    private Action.BasePromptAction endAction;
    private ActionContext context;
    private int currentIndexPrompt = 0;
    /**
     * Repeats the prompt if the input was invalid.
     */
    private boolean repeatPrompt;

    private Dialogue(){}

    private Dialogue(Dialogue.Builder builder){
        this.prompts = builder.prompts;
        this.context = builder.context;
        this.endAction = builder.endAction;
        this.escapeSequence = builder.escapeSequence;
        this.repeatPrompt = builder.repeatPrompt;
    }

    /**
     * Gets the current prompt this dialogue is on.
     *
     * @return The current prompt.
     */
    public Prompt getCurrentPrompt(){
        return prompts.get(currentIndexPrompt);
    }

    /**
     * Whether the dialogue has more prompts.
     *
     * @return If the dialogue has more prompts.
     */
    public boolean hasMorePrompts(){
        return currentIndexPrompt != prompts.size() - 1;
    }

//    /**
//     * Starts the dialogue with the first prompt.
//     * @param player The player we are conversing with.
//     */
//    public void start(Player player){
//        player.closeInventory();
//        getCurrentPrompt().prompt(player);
//    }

    /**
     * Prompts the next prompt to the player.
     *
     * @param player The player we are prompting.
     */
    public void nextPrompt(Player player){
        currentIndexPrompt++;
        getCurrentPrompt().prompt(player);
    }

    public Action.BasePromptAction getEndAction() {
        return endAction;
    }

    public String getEscapeSequence() {
        return escapeSequence;
    }

    public int getCurrentIndexPrompt() {
        return currentIndexPrompt;
    }

    public boolean shouldRepeatPrompt() {
        return repeatPrompt;
    }

    public ActionContext getContext() {
        return context;
    }

    public static class Builder<U extends ActionContext, T extends Action.EndAction<U>>{

        private boolean repeatPrompt = true;
        private String escapeSequence = "";
        private List<Prompt> prompts = new ArrayList<>();
        private T endAction = (T) Action.NO_END_ACTION;
        private U context;

        public Builder(){}

        public Builder<U, T> addPrompt(Prompt.Builder prompt){
            this.prompts.add(prompt.build());
            return this;
        }

        public Builder<U, T> setEscapeSequence(String escapeSequence){
            this.escapeSequence = escapeSequence;
            return this;
        }

        public Builder<U, T> setRepeatPrompt(boolean repeatPrompt){
            this.repeatPrompt = repeatPrompt;
            return this;
        }

        /**
         * Using a default action.
         * @param action A default action.
         * @param context The context for the action.
         * @see Action
         * @return The builder.
         */
        public Builder<U, T> setEndAction(Action.DefaultEndAction<U> action, U context){
            this.endAction = (T) action;
            this.context = context;
            return this;
        }

        /**
         * Defines your own end action.
         * @param action Your action.
         * @return The builder.
         */
        public Builder<U, T> setEndAction(T action){
            this.endAction = action;
            return this;
        }

        public Dialogue build(){
            return new Dialogue(this);
        }

    }

}
