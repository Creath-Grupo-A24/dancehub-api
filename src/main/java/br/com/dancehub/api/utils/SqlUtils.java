package br.com.dancehub.api.utils;

public class SqlUtils {


    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }
}
