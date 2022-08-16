package utils;

import javax.inject.Singleton;

@Singleton
public class AppUtils {

    public static boolean isPalindrome(String value) {
        boolean returnValue = false;
        if (value != null) {
            StringBuilder output = new StringBuilder(value).reverse();
            returnValue = value.equalsIgnoreCase(output.toString());
        }
        return returnValue;
    }
}
