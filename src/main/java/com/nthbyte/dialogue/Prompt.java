package com.nthbyte.dialogue;

import com.nthbyte.dialogue.action.PromptAction;
import com.nthbyte.dialogue.action.context.ResponderContext;
import org.bukkit.entity.Player;

import java.util.function.Function;

/**
 * Represents a question or a request.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.1.1.0
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
     * The text that is sent to the player.
     */
    private String text;

    /**
     * The action that runs whenever you receive input SUCCESSFULLY, meaning it's valid input. Runs after the input format gets validated.
     */
    private PromptAction<ResponderContext, String> onReceiveInputAction;

    /**
     * The action that runs whenever you are validating input.
     */
    private Function<String, Boolean> onValidateInputAction;

    private Prompt(Prompt.Builder builder){
        this.id = builder.id;
        this.text = builder.text;
        this.type = builder.type;
        this.onReceiveInputAction = builder.onReceiveInputAction;
        this.onValidateInputAction = builder.onValidateInputAction;
    }

    public String getId() {
        return id;
    }

    public PromptInputType getType() {
        return type;
    }

    public PromptAction<ResponderContext, String> getOnReceiveInputAction() {
        return onReceiveInputAction;
    }

    public Function<String, Boolean> getOnValidateInputAction() {
        return onValidateInputAction;
    }

    public void prompt(Player player){
        player.sendMessage(Utils.tr(text));
    }

    public static class Builder{

        private String id = "";
        private String text = "No prompt text given.";
        private PromptInputType type = PromptInputType.NONE;
        private PromptAction<ResponderContext, String> onReceiveInputAction = (player, input) -> {};

        // Prompt validator returns true by default.
        private Function<String, Boolean> onValidateInputAction = s -> true;

        public Builder(){}

        public Builder setText(String text){
            this.text = text;
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

        public Builder setOnReceiveInputAction(PromptAction<ResponderContext, String> action){
            this.onReceiveInputAction = action;
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
