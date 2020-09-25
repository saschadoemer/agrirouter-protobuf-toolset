package de.saschadoemer.arts.client.commandline.decoder.impl.request;

import agrirouter.cloud.registration.CloudVirtualizedAppRegistration;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class OnboardingRequestDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(CloudVirtualizedAppRegistration.OffboardingRequest.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
