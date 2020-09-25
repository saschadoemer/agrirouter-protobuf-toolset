package de.saschadoemer.arts.client.commandline.decoder.impl.response;

import com.google.protobuf.Any;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;

import java.util.Optional;

public class UndefinedContentDecoder implements ContentDecoder {

    @Override
    public Optional<Object> decode(Any content) {
        return Optional.empty();
    }
    
}
