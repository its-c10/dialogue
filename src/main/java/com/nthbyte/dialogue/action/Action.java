package com.nthbyte.dialogue.action;

import com.nthbyte.dialogue.DialogueEndCause;
import com.nthbyte.dialogue.action.context.LocationContext;
import com.nthbyte.dialogue.action.context.ResponderContext;

/**
 * Default actions that you can use for different stages of the prompt.
 */
public final class Action<C extends ResponderContext> {

    public static final PromptAction<ResponderContext, DialogueEndCause> TELEPORT = (actionContext, s) -> {
//        actionContext.getResponder().teleport(actionContext.getLocation());
    };

}

