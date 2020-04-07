package co.edu.javeriana.eas.patterns.users.utilities;

import org.apache.commons.codec.binary.Base64;

public class Base64Utility {

    private final static Base64 BASE_64 = new Base64();

    private Base64Utility() {
    }

    public static String decode(String encodeString) {
        return new String(BASE_64.decode(encodeString.getBytes()));
    }

}