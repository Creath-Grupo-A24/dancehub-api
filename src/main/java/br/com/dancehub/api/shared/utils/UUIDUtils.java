package br.com.dancehub.api.shared.utils;

import java.util.UUID;

public class UUIDUtils {

    public static UUID getFromString(String uuidStr) {
        final UUID uuid;
        try {
            uuid = UUID.fromString(uuidStr);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid UUID: " + uuidStr);
        }
        return uuid;
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }


    public static boolean isValidUUID(String uuid) {
        try {
            getFromString(uuid);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
