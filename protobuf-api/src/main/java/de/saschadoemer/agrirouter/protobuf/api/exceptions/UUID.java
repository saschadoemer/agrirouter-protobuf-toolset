package de.saschadoemer.agrirouter.protobuf.api.exceptions;

public class UUID {

    public static String generate(){
        return java.util.UUID.randomUUID().toString();
    }

}
