package de.saschadoemer.arts.client.commandline.decoder.impl.request;

import agrirouter.request.payload.endpoint.SubscriptionOuterClass;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class SubscriptionRequestDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(SubscriptionOuterClass.Subscription.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
