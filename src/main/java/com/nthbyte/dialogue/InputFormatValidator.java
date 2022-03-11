package com.nthbyte.dialogue;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;
import java.util.regex.Pattern;

public class InputFormatValidator {

    private final static Pattern LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]*$");

    /**
     * Validates the input format. If the prompt type isn't supported, then the input automatically is passed as "valid."
     * @param inputType The type of input this prompt accepts.
     * @param input The input from the player.
     * @return Whether the input is in the valid format. Returns true if the prompt input type isn't supported or checked for in the switch statement.
     */
    public static boolean isValidFormat(PromptInputType inputType, String input){
        switch(inputType){
            case UUID: return isUUID(input);
            case NUMERIC: return isNumeric(input);
            case DECIMAL: return isDecimal(input);
            case INTEGER: return isInteger(input);
            case LETTERS: return StringUtils.isAlpha(input);
            case LETTERS_AND_NUMBERS: return StringUtils.isAlphanumeric(input);
            case LOWERCASE_LETTERS: return StringUtils.isAllLowerCase(input);
            case UPPERCASE_LETTERS: return StringUtils.isAllUpperCase(input);
            case COLORFUL_STRING: return isColoredString(input);
            case BASE_64: return input.length() == 180;
            default: return true;
        }
    }

    private static boolean isNumeric(String input){
        if(input == null) return false;
        if(!input.contains(".") && StringUtils.isNumeric(input)){
            return true;
        }else if(input.contains(".")){
            // There is a period at the beginning and some
            return isDecimal(input);
        }
        return false;
    }

    private static boolean isDecimal(String input){
        return input.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isInteger(String input){
        return StringUtils.isNumeric(input);
    }

    private static boolean isUUID(String input){
        boolean isUUID = true;
        try{
            UUID.fromString(input);
        }catch(IllegalArgumentException e){
            isUUID = false;
        }
        return isUUID;
    }

    private static boolean isColoredString(String input){
        String colored = Utils.tr(input);
        return !colored.equals(input);
    }

}
