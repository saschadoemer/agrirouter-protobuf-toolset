package de.saschadoemer.arts.client.commandline.decoder.impl.response;

import agrirouter.feed.response.FeedResponse;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class HeaderQueryResponseDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(FeedResponse.HeaderQueryResponse.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
