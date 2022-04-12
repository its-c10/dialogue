package com.nthbyte.dialogue;

/**
 * Reasons that the dialogue ended.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.3.0
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
     * You have reached the retry limit.
     */
    REACHED_RETRY_LIMIT,

    /**
     * The start of another dialogue has halted the current one.
     */
    STARTED_ANOTHER_DIALOGUE;

}
