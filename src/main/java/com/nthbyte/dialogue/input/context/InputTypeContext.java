package com.nthbyte.dialogue.input.context;

public abstract class InputTypeContext<T> {

    protected T contextData;
    public InputTypeContext(T contextData){
        this.contextData = contextData;
    }

    public InputTypeContext(){}

    public abstract boolean isValidInputType(String input);

}
