package com.nthbyte.dialogue.action;

import com.nthbyte.dialogue.DialogueEndCause;
import com.nthbyte.dialogue.action.context.ActionContext;
import com.nthbyte.dialogue.action.context.LocationContext;
import com.nthbyte.dialogue.util.Utils;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Default actions that you can use for different stages of the prompt.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.7.1
 */
public final class Action {

    /**
     * Base prompt action. Can run when prompt ends, or on input receive.
     *
     * @param <T> The type of action context.
     * @param <U> Another useful piece of information. Could be any data type.
     */
    public interface BasePromptAction<T extends ActionContext, U> extends BiConsumer<T, U> {}

    /**
     * Actions that runs when input is received.
     *
     * @param <T>
     */
    public interface ReceiveInputAction<T extends ActionContext> extends BasePromptAction<T, String> {}

    /**
     * Action that runs at the end of dialogue.
     */
    public interface EndAction<T extends ActionContext> extends BasePromptAction<T, DialogueEndCause> {}

    /**
     * Action that has its functionality defined already.
     */
    public interface DefaultAction<T extends ActionContext> extends BasePromptAction<T, String> {}

    private Action() {
    }

    // DEFAULT ACTIONS

    /**
     * No action.
     */
    public static final DefaultAction<ActionContext> NO_ACTION = (context, input) -> {};

    /**
     * Teleports the player to a specific location.
     */
    public static final DefaultAction<LocationContext> TELEPORT = (context, input) -> {
        context.getResponder().teleport(context.getData());
    };

    /**
     * Stores the input in the list of stored inputs. Useful if you want to use previous input in a future prompt, end action, or validation action.
     */
    public static final DefaultAction<ActionContext<String>> STORE_INPUT = ((context, input) -> {
        String key = context.getData();
        context.getInputStorage().put(key, input);
    });

    /**
     * Messages the responder. Replaces input storage placeholders.
     */
    public static final DefaultAction<ActionContext<String>> MESSAGE = ((context, input) -> {
        String message = context.getData();
        Map<String, String> inputStorage = context.getInputStorage();
        // Replaces all placeholders with values that are within the input storage.
        for (Map.Entry<String, String> entry : inputStorage.entrySet()) {
            String dataKey = entry.getKey();
            String inputValue = entry.getValue();
            String placeholder = "%" + dataKey + "%";
            if (message.contains(placeholder)) {
                message = message.replace(placeholder, inputValue);
            }
        }
        context.getResponder().sendMessage(Utils.tr(message));
    });

}

