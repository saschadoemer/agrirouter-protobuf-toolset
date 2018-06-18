package de.saschadoemer.agrirouter.protobuf.decoder;

/**
 * Decoder, decoding protobuf to human readable JSON.
 */
public interface Decoder {

    /**
     * Decoding.
     *
     * @return JSON
     */
    String decode(String utf8);
}
