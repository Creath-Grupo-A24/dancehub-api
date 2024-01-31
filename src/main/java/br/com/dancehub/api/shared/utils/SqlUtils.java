package br.com.dancehub.api.shared.utils;

public class SqlUtils {


    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }
}
