package com.nthbyte.dialogue.input.context;

public class RegexInputContext extends InputTypeContext<String> {

    public RegexInputContext(String regex) {
        super(regex);
    }

    @Override
    public boolean isValidInputType(String input) {
        return input.matches(contextData);
    }

}
