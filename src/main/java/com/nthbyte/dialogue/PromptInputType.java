package com.nthbyte.dialogue;

import java.util.UUID;

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
     * Only allows letters and numbers in the input.
     */
    LETTERS_AND_NUMBERS,

    /**
     * Only lowercase letters in the input.
     */
    LOWERCASE_LETTERS,

    /**
     * Only uppercase letters in the input.
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
     * You either do not care about the type of input they pass through, or you want to do your own format check.
     * @see Prompt Variable onValidateInputAction can be used to do your own validation/format checks.
     */
    NONE,

}
