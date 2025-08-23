package com.pablodev.shared.domain;

public class Utils {

    public static String toSnakeCase(String text) {
        return text.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

}
