package de.saschadoemer.arts.api.exceptions;

public class CouldNotEncodeProtobufException extends RuntimeException {

    private final String messageWhichCouldNotBeDecoded;

    public CouldNotEncodeProtobufException(String messageWhichCouldNotBeDecoded, Throwable cause) {
        super(cause);
        this.messageWhichCouldNotBeDecoded = messageWhichCouldNotBeDecoded;
    }

    @Override
    public String getMessage() {
        return String.format("Could not encode message [%s].", messageWhichCouldNotBeDecoded);
    }
}
