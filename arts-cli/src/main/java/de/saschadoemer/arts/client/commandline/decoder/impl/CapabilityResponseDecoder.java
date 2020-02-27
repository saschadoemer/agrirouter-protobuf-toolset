package de.saschadoemer.arts.client.commandline.decoder.impl;

import agrirouter.response.payload.account.Endpoints;
import agrirouter.response.payload.endpoint.Capability;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class CapabilityResponseDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(Capability.CapabilityResponse.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
