package com.nthbyte.dialogue.input;

import com.nthbyte.dialogue.input.context.InputTypeContext;
import com.nthbyte.dialogue.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

/**
 * The different types of input for prompts.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.5.0.0
 */
public class PromptInputType {

    /**
     * Input type context for a decimal, i.e. 5.0.
     */
    public static final InputTypeContext DECIMAL = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return input.matches("-?\\d+(\\.\\d+)?");
        }
    };

    /**
     * Input type context for a integer.
     */
    public static final InputTypeContext INTEGER = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return StringUtils.isNumeric(input);
        }
    };

    /**
     * Input type context for any number whether it's a decimal or integer.
     */
    public static final InputTypeContext NUMERIC = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            if (input == null) return false;
            if (!input.contains(".") && StringUtils.isNumeric(input)) {
                return true;
            } else if (input.contains(".")) {
                // There is a period at the beginning and some
                return DECIMAL.isValidInputType(input);
            }
            return false;
        }
    };

    /**
     * Alphabetic input.
     */
    public static final InputTypeContext LETTERS = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return StringUtils.isAlpha(input);
        }
    };

    /**
     * Input type context for input that contains letters and numbers, i.e. "H3ll0 W0r1d"
     */
    public static final InputTypeContext LETTERS_AND_NUMBERS = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return StringUtils.isAlphanumericSpace(input);
        }
    };

    /**
     * Input type context for input that contains lowercase letters, i.e. "hello world"
     */
    public static final InputTypeContext LOWERCASE_LETTERS = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return StringUtils.isAllLowerCase(input);
        }
    };

    /**
     * Input type context for input that contains uppercase letters, i.e. "HELLO WORLD"
     */
    public static final InputTypeContext UPPERCASE_LETTERS = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return StringUtils.isAllUpperCase(input);
        }
    };

    /**
     * Input type context for input that contains a UUID, i.e. 1bf82fb3-95a0-4313-a2c9-ee5eda211c69.
     */
    public static final InputTypeContext UUID = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            boolean isUUID = true;
            try {
                java.util.UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                isUUID = false;
            }
            return isUUID;
        }
    };

    /**
     * Input type context for input that is a string that has colors in it, i.e. "&aHello World!"
     */
    public static final InputTypeContext COLORFUL_STRING = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            String colored = Utils.tr(input);
            return !colored.equals(input);
        }
    };

    /**
     * Input type context for input that contains a base64 texture.
     */
    public static final InputTypeContext BASE_64 = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            // TODO: Make this better.
            return input.length() == 180;
        }
    };

    /**
     * Input type context for input that contains a player's name.
     */
    public static final InputTypeContext PLAYER = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return Bukkit.getPlayer(input) != null;
        }
    };

    /**
     * Input type context you use when, either do not care about the type of input they pass through, or you want to do your own format and/or validation checks.
     */
    public static final InputTypeContext NONE = new InputTypeContext(null) {
        @Override
        public boolean isValidInputType(String input) {
            return true;
        }
    };

}
