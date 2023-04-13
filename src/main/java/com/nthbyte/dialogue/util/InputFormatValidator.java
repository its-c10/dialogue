package com.nthbyte.dialogue.util;

import com.nthbyte.dialogue.PromptInputType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Validates the format of input.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.7.1
 */
public class InputFormatValidator {

    /**
     * Validates the input format. If the prompt type isn't supported, then the input automatically is passed as "valid."
     *
     * @param inputType The type of input this prompt accepts.
     * @param input     The input from the player.
     * @return Whether the input is in the valid format. Returns true if the prompt input type isn't supported or checked for in the switch statement.
     */
    public static boolean isValidFormat(PromptInputType inputType, String input) {
        switch (inputType) {
            case UUID:
                return isUUID(input);
            case NUMERIC:
                return isNumeric(input);
            case DECIMAL:
                return isDecimal(input);
            case INTEGER:
                return isInteger(input);
            case LETTERS:
                return StringUtils.isAlpha(input);
            case LETTERS_AND_NUMBERS:
                return StringUtils.isAlphanumericSpace(input);
            case LOWERCASE_LETTERS:
                return StringUtils.isAllLowerCase(input);
            case UPPERCASE_LETTERS:
                return StringUtils.isAllUpperCase(input);
            case COLORFUL_STRING:
                return isColoredString(input);
            case BASE_64:
                return input.length() == 180;
            case PLAYER:
                return isPlayer(input);
            default:
                return true;
        }
    }

    private static boolean isNumeric(String input) {
        if (input == null) return false;
        if (!input.contains(".") && StringUtils.isNumeric(input)) {
            return true;
        } else if (input.contains(".")) {
            // There is a period at the beginning and some
            return isDecimal(input);
        }
        return false;
    }

    private static boolean isDecimal(String input) {
        return input.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isInteger(String input) {
        return StringUtils.isNumeric(input);
    }

    private static boolean isUUID(String input) {
        boolean isUUID = true;
        try {
            UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            isUUID = false;
        }
        return isUUID;
    }

    private static boolean isColoredString(String input) {
        String colored = Utils.tr(input);
        return !colored.equals(input);
    }

    private static boolean isPlayer(String input) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

}
