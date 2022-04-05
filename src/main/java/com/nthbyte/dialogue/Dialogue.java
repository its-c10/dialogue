package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.context.ActionContext;
import org.bukkit.entity.Player;

import java.util.*;

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
    private String[] escapeSequences;
    private List<Prompt> prompts;
    /**
     * Actions that are ran when the dialogue ends.
     */
    private LinkedHashMap<Action.BasePromptAction, ActionContext> endActions;
    private int currentIndexPrompt = 0;
    /**
     * Repeats the prompt if the input was invalid.
     */
    private boolean repeatPrompt;

    private Dialogue(){}

    private Dialogue(Dialogue.Builder builder){
        this.prompts = builder.prompts;
        this.endActions = builder.endActions;
        this.escapeSequences = builder.escapeSequences;
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

    public Map<Action.BasePromptAction, ActionContext> getEndActions() {
        return endActions;
    }

    public String[] getEscapeSequences() {
        return escapeSequences;
    }

    public int getCurrentIndexPrompt() {
        return currentIndexPrompt;
    }

    public boolean shouldRepeatPrompt() {
        return repeatPrompt;
    }

    public static class Builder{

        private boolean repeatPrompt = true;
        private String[] escapeSequences = new String[]{""};
        private List<Prompt> prompts = new ArrayList<>();
        private LinkedHashMap<Action.BasePromptAction, ActionContext> endActions = new LinkedHashMap<>();

        public Builder(){}

        public Builder addPrompt(Prompt.Builder prompt){
            this.prompts.add(prompt.build());
            return this;
        }

        public Builder setEscapeSequence(String escapeSequence){
            this.escapeSequences = new String[]{escapeSequence};
            return this;
        }

        public Builder setEscapeSequences(String... escapeSequences) {
            this.escapeSequences = escapeSequences;
            return this;
        }

        public Builder setRepeatPrompt(boolean repeatPrompt){
            this.repeatPrompt = repeatPrompt;
            return this;
        }

        /**
         * Using a default action.
         * @param defaultAction A default action.
         * @param context The endActionContext for the action.
         * @see Action
         * @return The builder.
         */
        public <U extends ActionContext> Builder addEndAction(Action.DefaultAction<U> defaultAction, U context){
            this.endActions.put(defaultAction, context);
            return this;
        }

        /**
         * Defines your own end action.
         * @param action Your action.
         * @return The builder.
         */
        public <T extends ActionContext> Builder addEndAction(Action.EndAction<T> action){
            this.endActions.put(action, null);
            return this;
        }

        public Dialogue build(){
            return new Dialogue(this);
        }

    }

}
