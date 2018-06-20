package de.saschadoemer.agrirouter.protobuf.decoder.impl;

import agrirouter.response.Response;
import com.google.protobuf.util.JsonFormat;
import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;
import de.saschadoemer.agrirouter.protobuf.decoder.JsonFormatPrinterProvider;
import de.saschadoemer.agrirouter.protobuf.decoder.api.exceptions.CouldNotDecodeProtobufException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

public class MessagesDecoder implements Decoder, JsonFormatPrinterProvider {

    public Optional<String> safeDecode(String base64EncodedValue) {
        try {
            ByteArrayInputStream inputStream = this.decodeFromBase64(base64EncodedValue);
            Response.ResponseEnvelope responseEnvelope = Response.ResponseEnvelope.parseDelimitedFrom(inputStream);
            Response.ResponsePayloadWrapper responsePayloadWrapper = Response.ResponsePayloadWrapper.parseDelimitedFrom(inputStream);
            return Optional.of(this.createOutput(responseEnvelope, responsePayloadWrapper).toString());
        } catch (IOException e) {
            throw new CouldNotDecodeProtobufException(e);
        }
    }

    private StringBuilder createOutput(Response.ResponseEnvelope responseEnvelope, Response.ResponsePayloadWrapper responsePayloadWrapper) throws IOException {
        StringBuilder output = new StringBuilder();
        JsonFormat.Printer printer = this.createPrinter();
        printer.appendTo(responseEnvelope, output);
        printer.appendTo(responsePayloadWrapper, output);
        return output;
    }


}
