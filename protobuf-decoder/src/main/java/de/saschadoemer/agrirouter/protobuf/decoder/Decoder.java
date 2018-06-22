package de.saschadoemer.agrirouter.protobuf.decoder;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.ByteArrayInputStream;
import java.util.Base64;
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

    default ByteArrayInputStream decodeFromBase64(String base64EncodedValue) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedValue);
        return new ByteArrayInputStream(decodedBytes);
    }

    default String addMissingPrefixIfNecessary(String typeUrl) {
        String[] parts = typeUrl.split("/");
        if (parts.length == 1) {
            typeUrl = "types.agrirouter/" + typeUrl;
        }
        return typeUrl;
    }
}
