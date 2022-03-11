package com.nthbyte.dialogue;

/**
 * Reasons that the dialogue ended.
 */
public enum DialogueEndCause {

    /**
     * There are no more prompts to be given to the player.
     */
    NO_MORE_PROMPTS,

    /**
     * The input was invalid.
     */
    INVALID_INPUT,

    /**
     * The player has entered the escape sequence as input.
     */
    ESCAPE_SEQUENCE,

    /**
     * Some other reason for ending the dialogue.
     */
    OTHER

}
