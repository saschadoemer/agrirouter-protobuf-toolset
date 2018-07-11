package de.saschadoemer.arts.api.exceptions;

public class UUIDUtils {

    public static String generate(){
        return java.util.UUID.randomUUID().toString();
    }

}
