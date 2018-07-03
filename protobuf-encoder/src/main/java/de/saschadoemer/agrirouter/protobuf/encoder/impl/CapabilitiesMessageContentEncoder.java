package de.saschadoemer.agrirouter.protobuf.encoder.impl;

import agrirouter.request.payload.endpoint.Capabilities;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import de.saschadoemer.agrirouter.protobuf.api.exceptions.CouldNotEncodeProtobufException;
import de.saschadoemer.agrirouter.protobuf.encoder.Encoder;
import de.saschadoemer.agrirouter.protobuf.encoder.JsonFormatParserProvider;

import java.util.Base64;
import java.util.Optional;

public class CapabilitiesMessageContentEncoder implements Encoder, JsonFormatParserProvider {

    @Override
    public Optional<String> safeEncode(String json) {
        try {
            Message.Builder builder = Capabilities.CapabilitySpecification.newBuilder();
            this.createParser().merge(json, builder);
            Message message = builder.build();
            return Optional.ofNullable(Base64.getEncoder().encodeToString(message.toByteArray()));
        } catch (InvalidProtocolBufferException e) {
            throw new CouldNotEncodeProtobufException(json, e);
        }
    }


}
