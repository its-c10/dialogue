package com.nthbyte.dialogue;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class Prompt {

    /**
     * The identifier for the prompt. Useful for listening for specific prompt input.
     */
    private String id;

    /**
     * The type of prompt this is.
     */
    private PromptType type;

    /**
     * The text that is sent to the player.
     */
    private String text;

    /**
     * The action that runs whenever you receive input.
     */
    private Consumer<String> onReceiveInputAction;

    private Prompt(String id, String text, PromptType type, Consumer<String> onReceiveInputAction){
        this.id = id;
        this.text = text;
        this.type = type;
        this.onReceiveInputAction = onReceiveInputAction;
    }

    public String getId() {
        return id;
    }

    public PromptType getType() {
        return type;
    }

    public Consumer<String> getOnReceiveInputAction() {
        return onReceiveInputAction;
    }

    public void prompt(Player player){
        player.sendMessage(Utils.tr(text));
    }

    public static class Builder{

        private String text;
        private PromptType type;
        private String id;
        private Consumer<String> onReceiveInputAction;

        public Builder(){}

        public Builder setText(String text){
            this.text = text;
            return this;
        }

        public Builder setType(PromptType type){
            this.type = type;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setOnReceiveInputAction(Consumer<String> action){
            this.onReceiveInputAction = action;
            return this;
        }

        public Prompt build(){
            return new Prompt(id, text, type, onReceiveInputAction);
        }

    }



}
