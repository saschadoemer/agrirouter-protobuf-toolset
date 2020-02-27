package de.saschadoemer.arts.client.commandline.decoder.impl;

import agrirouter.feed.response.FeedResponse;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class FailedMessageQueryResponseDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(FeedResponse.FailedMessageQueryResponse.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
