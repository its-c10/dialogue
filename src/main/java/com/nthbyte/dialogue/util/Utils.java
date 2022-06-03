package com.nthbyte.dialogue.util;

import org.bukkit.ChatColor;

/**
 * Utility class.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.5.0
 */
public class Utils {

    /**
     * <p>Colors the string.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String tr(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
