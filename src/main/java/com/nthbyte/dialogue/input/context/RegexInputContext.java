package com.nthbyte.dialogue.input.context;

/**
 * Regex input context developers can use to require input from players that follows a certain regex expression.
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.5.0.0
 */
public class RegexInputContext extends InputTypeContext<String> {

    public RegexInputContext(String regex) {
        super(regex);
    }

    @Override
    public boolean isValidInputType(String input) {
        return input.matches(contextData);
    }

}
