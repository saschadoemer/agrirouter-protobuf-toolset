package de.saschadoemer.agrirouter.protobuf.decoder.impl;

import agrirouter.response.Response;
import com.google.protobuf.Any;
import com.google.protobuf.util.JsonFormat;
import de.saschadoemer.agrirouter.protobuf.api.exceptions.CouldNotDecodeProtobufException;
import de.saschadoemer.agrirouter.protobuf.api.exceptions.CouldNotEncodeProtobufException;
import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;
import de.saschadoemer.agrirouter.protobuf.decoder.JsonFormatPrinterProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

public class MessageDecoder implements Decoder, JsonFormatPrinterProvider {

    public Optional<String> safeDecode(String base64EncodedValue) {
        try {
            ByteArrayInputStream inputStream = this.decodeFromBase64(base64EncodedValue);
            Response.ResponseEnvelope.Builder responseEnvelope = Response.ResponseEnvelope.parseDelimitedFrom(inputStream).toBuilder();

            Response.ResponsePayloadWrapper.Builder responsePayloadWrapper = Response.ResponsePayloadWrapper.parseDelimitedFrom(inputStream).toBuilder();
            Any.Builder any = responsePayloadWrapper.getDetails().toBuilder();
            any.setTypeUrl(this.addMissingPrefixIfNecessary(any.getTypeUrl()));
            responsePayloadWrapper.setDetails(any.build());

            return Optional.of(this.createOutput(responseEnvelope.build(), responsePayloadWrapper.build()).toString());
        } catch (IOException e) {
            throw new CouldNotDecodeProtobufException(base64EncodedValue, e);
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
