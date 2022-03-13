package com.nthbyte.dialogue;

/**
 * The different types of input for prompts.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.1.0.0
 */
public enum PromptInputType {

    /**
     * A decimal, i.e. 5.0.
     */
    DECIMAL,

    /**
     * An integer.
     */
    INTEGER,
    /**
     * Any number whether it's a decimal or integer.
     */
    NUMERIC,

    /**
     * Alphabetic.
     */
    LETTERS,

    /**
     * Only allows letters and numbers in the input, i.e. "H3ll0 W0r1d"
     */
    LETTERS_AND_NUMBERS,

    /**
     * Only lowercase letters in the input, i.e. "hello world"
     */
    LOWERCASE_LETTERS,

    /**
     * Only uppercase letters in the input, i.e. "HELLO WORLD"
     */
    UPPERCASE_LETTERS,

    /**
     * A UUID, i.e. 1bf82fb3-95a0-4313-a2c9-ee5eda211c69.
     */
    UUID,

    /**
     * A string with colors in it, i.e. "&aHello World!"
     */
    COLORFUL_STRING,

    /**
     * Base64 texture.
     */
    BASE_64,

    /**
     * An online player.
     */
    PLAYER,

    /**
     * You either do not care about the type of input they pass through, or you want to do your own format and/or validation checks.
     */
    NONE,

}
