package com.nthbyte.dialogue;

/**
 * Reasons that the dialogue ended.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.2.0.1
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
    OTHER,

    /**
     * The start of another dialogue has halted the current one.
     */
    STARTED_ANOTHER_DIALOGUE;

}
