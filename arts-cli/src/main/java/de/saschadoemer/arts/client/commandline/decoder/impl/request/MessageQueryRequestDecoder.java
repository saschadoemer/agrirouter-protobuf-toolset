package de.saschadoemer.arts.client.commandline.decoder.impl.request;

import agrirouter.feed.request.FeedRequests;
import agrirouter.request.payload.endpoint.Capabilities;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class MessageQueryRequestDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(FeedRequests.MessageQuery.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
