package de.saschadoemer.arts.client.commandline.decoder.impl.response;

import agrirouter.response.payload.account.Endpoints;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class ListEndpointsResponseDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(Endpoints.ListEndpointsResponse.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
