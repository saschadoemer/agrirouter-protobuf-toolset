package de.saschadoemer.arts.api.exceptions;

public class CouldNotCreateSignatureException extends RuntimeException {

    public CouldNotCreateSignatureException(String message) {
        super(message);
    }

    public CouldNotCreateSignatureException(Throwable cause) {
        super(cause);
    }

}
