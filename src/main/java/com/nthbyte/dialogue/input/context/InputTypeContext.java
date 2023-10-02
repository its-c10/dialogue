package com.nthbyte.dialogue.input.context;

/**
 * Context for the input type.
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.5.0.0
 * @see com.nthbyte.dialogue.input.PromptInputType for default ones.
 * @param <T> The type of context data, i.e. a regex expression.
 */
public abstract class InputTypeContext<T> {

    /**
     * Context data for the input. Allows developers to have more freedom as to how they want to validate the input type.
     */
    protected T contextData;

    public InputTypeContext(T contextData){
        this.contextData = contextData;
    }

    public InputTypeContext(){}

    /**
     * Whether the input is the correct type of input.
     * @param input The input.
     * @return If the input is of valid type.
     */
    public abstract boolean isValidInputType(String input);

    //public abstract String getInputDescription();

}
