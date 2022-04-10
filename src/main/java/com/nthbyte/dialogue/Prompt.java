package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.Action;
import com.nthbyte.dialogue.action.context.ActionContext;
import com.nthbyte.dialogue.util.Utils;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Function;

/**
 * Represents a question or a request.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.1.0
 */
public class Prompt {

    /**
     * The identifier for the prompt. Useful for listening for specific prompt input.
     */
    private String id;

    /**
     * The type of prompt this is.
     */
    private PromptInputType type;

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

    private Prompt(Prompt.Builder builder){
        this.id = builder.id;
        this.allText = builder.allText;
        this.type = builder.type;
        this.receiveInputActions = builder.receiveInputActions;
        this.onValidateInputAction = builder.onValidateInputAction;
    }

    public String getId() {
        return id;
    }

    public PromptInputType getType() {
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

    public void prompt(Player player){
        for(String s : allText){
            player.sendMessage(Utils.tr(s));
        }
    }

    public static class Builder{

        private String id = "";
        private List<String> allText = new ArrayList<>();
        private PromptInputType type = PromptInputType.NONE;
        private LinkedHashMap<Action.BasePromptAction, ActionContext> receiveInputActions = new LinkedHashMap<>();

        // Prompt validator returns true by default.
        private Function<String, Boolean> onValidateInputAction = s -> true;

        public Builder(){
            allText.add("No prompt text given.");
        }

        public Builder addText(String text){
            allText.add(text);
            return this;
        }

        public Builder setText(String... text){
            allText = Arrays.asList(text);
            return this;
        }

        public Builder setType(PromptInputType type){
            this.type = type;
            return this;
        }

        public Builder setId(String id){
            this.id = id;
            return this;
        }

        /**
         * Adds a default action that gets executed when input is received for this prompt.
         * @param defaultAction The default action.
         * @param context The context/information that the action needs in order to execute.
         * @param <U> The type of context needed for this action.
         * @return The builder.
         * @see Action
         */
        public <U extends ActionContext> Builder addReceiveInputAction(Action.DefaultAction<U> defaultAction, U context){
            receiveInputActions.put(defaultAction, context);
            return this;
        }

        /**
         * Adds your own action that gets executed when input is received for this prompt.
         * @param action Your defined action.
         * @return The builder.
         */
        public Builder addReceiveInputAction(Action.BasePromptAction<ActionContext, String> action){
            receiveInputActions.put(action, null);
            return this;
        }

        public Builder setOnValidateInputAction(Function<String, Boolean> onValidateInputAction) {
            this.onValidateInputAction = onValidateInputAction;
            return this;
        }

        public Prompt build(){
            return new Prompt(this);
        }

    }

}
