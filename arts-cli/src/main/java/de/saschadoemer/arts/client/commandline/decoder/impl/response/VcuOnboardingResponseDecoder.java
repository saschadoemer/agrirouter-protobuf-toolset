package de.saschadoemer.arts.client.commandline.decoder.impl.response;

import agrirouter.cloud.registration.CloudVirtualizedAppRegistration;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class VcuOnboardingResponseDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        try {
            return Optional.ofNullable(CloudVirtualizedAppRegistration.OnboardingResponse.parseFrom(content.getValue()));
        } catch (InvalidProtocolBufferException e) {
            return Optional.empty();
        }
    }

}
