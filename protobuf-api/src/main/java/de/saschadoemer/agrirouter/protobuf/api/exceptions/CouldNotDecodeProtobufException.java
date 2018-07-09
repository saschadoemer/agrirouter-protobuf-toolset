package de.saschadoemer.agrirouter.protobuf.api.exceptions;

public class CouldNotDecodeProtobufException extends RuntimeException {

    private final String messageWhichCouldNotBeDecoded;

    public CouldNotDecodeProtobufException(String messageWhichCouldNotBeDecoded, Throwable cause) {
        super(cause);
        this.messageWhichCouldNotBeDecoded = messageWhichCouldNotBeDecoded;
    }

    @Override
    public String getMessage() {
        return String.format("Could not decode message [%s].", messageWhichCouldNotBeDecoded);
    }
}
