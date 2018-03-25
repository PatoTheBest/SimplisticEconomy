package net.megaplanet.simplisticeconomy.util;

import java.util.EnumSet;

public class Utils {

    /**
     * Gets an enum element from its name
     *
     * @param enumClass  the enum class
     * @param enumString the element's name
     * @param <E>        the element type
     *
     * @return the element
     */
    public static <E extends Enum<E>> E getEnumValueFromString(Class<E> enumClass, String enumString) {
        for (E enumElement : EnumSet.allOf(enumClass)) {
            if (enumElement.name().equalsIgnoreCase(enumString)) {
                return enumElement;
            }
        }

        return null;
    }

}
