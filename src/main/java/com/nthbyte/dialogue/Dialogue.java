package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.Action;
import com.nthbyte.dialogue.action.context.ActionContext;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Object that represents dialogue between the plugin and a player.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.7.0
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

    /**
     * How much time the user has to complete the dialogue (in seconds).
     */
    private int timeLimit;

    private Dialogue(){}

    private Dialogue(Dialogue.Builder builder) {
        this.prompts = builder.prompts;
        this.endActions = builder.endActions;
        this.escapeSequences = builder.escapeSequences;
        this.repeatPrompt = builder.repeatPrompt;
        this.timeLimit = builder.timeLimit;
    }

    /**
     * Gets the current prompt this dialogue is on.
     *
     * @return The current prompt.
     */
    public Prompt getCurrentPrompt() {
        return prompts.get(currentIndexPrompt);
    }

    /**
     * Whether the dialogue has more prompts.
     *
     * @return If the dialogue has more prompts.
     */
    public boolean hasMorePrompts() {
        return currentIndexPrompt != prompts.size() - 1;
    }

    /**
     * Prompts the next prompt to the player.
     *
     * @param player The player we are prompting.
     */
    public void nextPrompt(JavaPlugin plugin, Player player) {
        currentIndexPrompt++;
        getCurrentPrompt().prompt(plugin, this, player);
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

    public int getTimeLimit() {
        return timeLimit;
    }

    public static class Builder {

        private boolean repeatPrompt = true;
        private String[] escapeSequences = new String[]{""};
        private List<Prompt> prompts = new ArrayList<>();
        private LinkedHashMap<Action.BasePromptAction, ActionContext> endActions = new LinkedHashMap<>();
        private int timeLimit;

        public Builder() {}

        public Builder addPrompt(Prompt.Builder prompt) {
            this.prompts.add(prompt.build());
            return this;
        }

        public Builder setTimeLimit(int timeLimit){
            this.timeLimit = timeLimit;
            return this;
        }

        public Builder setEscapeSequences(String... escapeSequences) {
            this.escapeSequences = escapeSequences;
            return this;
        }

        public Builder setRepeatPrompt(boolean repeatPrompt) {
            this.repeatPrompt = repeatPrompt;
            return this;
        }

        /**
         * Using a default action.
         *
         * @param defaultAction A default action.
         * @param context       The endActionContext for the action.
         * @return The builder.
         * @see Action
         */
        public <U extends ActionContext> Builder addEndAction(Action.DefaultAction<U> defaultAction, U context) {
            this.endActions.put(defaultAction, context);
            return this;
        }

        /**
         * Defines your own end action.
         *
         * @param action Your action.
         * @return The builder.
         */
        public <T extends ActionContext> Builder addEndAction(Action.EndAction<T> action) {
            this.endActions.put(action, null);
            return this;
        }

        public Dialogue build() {
            return new Dialogue(this);
        }

    }

}
