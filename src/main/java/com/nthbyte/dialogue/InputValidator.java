package com.nthbyte.dialogue;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

public class InputValidator {

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

    public static boolean isValidInput(PromptType promptType, String input){
        switch(promptType){
            case UUID: return isUUID(input);
            case NUMERIC: return isNumeric(input);
            case DECIMAL: return isDecimal(input);
            case INTEGER: return isInteger(input);
            default: return true;
        }
    }

}
