package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.Action;
import com.nthbyte.dialogue.action.context.ActionContext;
import com.nthbyte.dialogue.input.context.InputTypeContext;
import com.nthbyte.dialogue.input.PromptInputType;
import com.nthbyte.dialogue.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.Function;

/**
 * Represents a question or a request.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.5.0.0
 */
public class Prompt {

    /**
     * The identifier for the prompt. Useful for listening for specific prompt input.
     */
    private String id;

    /**
     * The type of prompt this is.
     */
    private InputTypeContext type;

    /**
     * The allText that is sent to the player.
     */
    private List<String> allText;

    /**
     * The actions that runs whenever you receive input SUCCESSFULLY, meaning it's valid input. Runs after the input format gets validated.
     */
    private LinkedHashMap<Action.BasePromptAction, ActionContext> receiveInputActions;

    /**
     * The context for the receive input action.
     */
    private ActionContext context;

    /**
     * The action that runs whenever you are validating input.
     */
    private Function<String, Boolean> onValidateInputAction;

    /**
     * How long, in ticks, the plugin will wait before playing this prompt.
     */
    private int delay;

    /**
     * How many times a player can try to answer a prompt.
     */
    private int retryLimit;

    /**
     * Whether the dialogue stops when a player fails (you have reached the retry limit) to enter valid input for a prompt.
     * This value only matters if the retry limit is not -1.
     */
    private boolean stopUponFailure;

    private int numRetries = 0;

    private Prompt(Prompt.Builder builder) {
        this.id = builder.id;
        this.allText = builder.allText;
        this.type = builder.inputTypeContext;
        this.receiveInputActions = builder.receiveInputActions;
        this.onValidateInputAction = builder.onValidateInputAction;
        this.delay = builder.delay;
        this.retryLimit = builder.retryLimit;
        this.stopUponFailure = builder.stopUponFailure;
        if(allText.isEmpty()){
            allText.add("No prompt text given.");
        }
    }

    public String getId() {
        return id;
    }

    public InputTypeContext getType() {
        return type;
    }

    public Map<Action.BasePromptAction, ActionContext> getReceiveInputActions() {
        return receiveInputActions;
    }

    public ActionContext getContext() {
        return context;
    }

    public Function<String, Boolean> getOnValidateInputAction() {
        return onValidateInputAction;
    }

    public void prompt(JavaPlugin plugin, Dialogue dialogue, Player player) {

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (String s : allText) {
                player.sendMessage(Utils.tr(s));
            }
        }, delay);

    }

    public void incrementRetries() {
        numRetries++;
    }

    public boolean isAtRetryLimit() {
        return numRetries == retryLimit;
    }

    public int getRetryLimit() {
        return retryLimit;
    }

    public boolean willStopDialougeOnFailure() {
        return stopUponFailure;
    }

    public static class Builder {

        private String id = "";
        private List<String> allText = new ArrayList<>();
        private InputTypeContext inputTypeContext = PromptInputType.NONE;
        private LinkedHashMap<Action.BasePromptAction, ActionContext> receiveInputActions = new LinkedHashMap<>();
        private int delay;
        private int retryLimit = -1;
        private boolean stopUponFailure;

        // Prompt validator returns true by default.
        private Function<String, Boolean> onValidateInputAction = s -> true;

        public Builder() {}

        public Builder addText(String text) {
            allText.add(text);
            return this;
        }

        public Builder addText(String... text){
            return addText(Arrays.asList(text));
        }

        public Builder addText(List<String> text){
            allText.addAll(text);
            return this;
        }

        public Builder setText(String... text) {
            allText = Arrays.asList(text);
            return this;
        }

        public Builder setText(List<String> text){
            allText = text;
            return this;
        }

        public Builder setType(InputTypeContext type) {
            this.inputTypeContext = type;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Adds a default action that gets executed when input is received for this prompt.
         *
         * @param defaultAction The default action.
         * @param context       The context/information that the action needs in order to execute.
         * @param <U>           The type of context needed for this action.
         * @return The builder.
         * @see Action
         */
        public <U extends ActionContext> Builder addReceiveInputAction(Action.DefaultAction<U> defaultAction, U context) {
            receiveInputActions.put(defaultAction, context);
            return this;
        }

        /**
         * Adds your own action that gets executed when input is received for this prompt.
         *
         * @param action Your defined action.
         * @return The builder.
         */
        public Builder addReceiveInputAction(Action.BasePromptAction<ActionContext, String> action) {
            receiveInputActions.put(action, null);
            return this;
        }

        /**
         * Sets the action used to validate the prompt input.
         * @param onValidateInputAction Your validate input action.
         * @return The builder.
         */
        public Builder setOnValidateInputAction(Function<String, Boolean> onValidateInputAction) {
            this.onValidateInputAction = onValidateInputAction;
            return this;
        }

        /**
         * Sets the delay i.e. the amount of time before it gives this prompt.
         * @param delay The delay in ticks.
         * @return The builder.
         */
        public Builder setDelay(int delay) {
            this.delay = delay;
            return this;
        }

        /**
         * Sets the amount of times a player can answer the prompt before it either ends the dialouge or goes to the next prompt.
         * @see Builder#stopDialogueUponFailure()
         * @param retries The number of retries the player has.
         * @return The builder.
         */
        public Builder setRetryLimit(int retries) {
            this.retryLimit = retries;
            return this;
        }

        /**
         * Sets whether the Dialogue ends if the player fails to answer the prompt correct i.e. reaches the retry limit.
         * @return The builder.
         */
        public Builder stopDialogueUponFailure() {
            this.stopUponFailure = true;
            return this;
        }

        public Prompt build() {
            return new Prompt(this);
        }

    }

}
