package de.saschadoemer.agrirouter.protobuf.encoder;

import com.google.protobuf.ByteString;

import java.util.Optional;

public interface Encoder {

    /**
     * Encoding.
     *
     * @return JSON
     */
    Optional<String> safeEncode(String json);

}
