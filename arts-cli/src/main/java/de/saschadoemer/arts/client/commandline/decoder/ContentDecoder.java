package de.saschadoemer.arts.client.commandline.decoder;

import com.google.protobuf.Any;

import java.util.Optional;

public interface ContentDecoder {

    Optional<Object> decode(Any content);

}
