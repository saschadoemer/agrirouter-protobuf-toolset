package de.saschadoemer.agrirouter.protobuf.decoder;

import java.util.Optional;

/**
 * Decoder, decoding protobuf to human readable JSON.
 */
public interface Decoder {

    /**
     * Decoding.
     *
     * @return JSON
     */
    Optional<String> safeDecode(String utf8);
}
