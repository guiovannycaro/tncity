package com.tncity.util;

public class OsUtil {

    public static String osName() {
        return System.getProperty("os.name");
    }

    public static int osBits() {
        String arch = System.getProperty("os.arch");
        if (arch.contains("64")) {
            return 64;
        }
        return 32;
    }
}
