package com.nthbyte.dialogue;

import com.nthbyte.dialogue.DialogueEndCause;
import com.nthbyte.dialogue.action.context.LocationContext;
import com.nthbyte.dialogue.action.context.ActionContext;

import java.util.function.BiConsumer;

/**
 * Default actions that you can use for different stages of the prompt.
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.3.0.0
 */
public final class Action {

    /**
     * Base prompt action. Can run when prompt ends, or on input receive.
     */
    public interface BasePromptAction<T extends ActionContext, U> extends BiConsumer<T, U> {}

    public interface ReceiveInputAction<T extends ActionContext> extends BasePromptAction<T, String> {}

    /**
     * Action that runs at the end of dialogue.
     */
    public interface EndAction<T extends ActionContext> extends BasePromptAction<T, DialogueEndCause> {}

    /**
     * Action that runs at the end of dialogue.
     */
    protected interface DefaultEndAction<T extends ActionContext> extends EndAction<T> {}

    /**
     * No action.
     */
    public static final EndAction<ActionContext> NO_END_ACTION = (context, input) -> {};



    // END ACTIONS

    /**
     * Teleports the player to a specific location.
     */
    public static final DefaultEndAction<LocationContext> TELEPORT = (context, endCause) -> {
        context.getResponder().teleport(context.getLocation());
    };

}

