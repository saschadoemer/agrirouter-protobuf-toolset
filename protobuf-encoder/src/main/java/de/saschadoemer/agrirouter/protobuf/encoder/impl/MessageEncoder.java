package de.saschadoemer.agrirouter.protobuf.encoder.impl;

import com.google.protobuf.ByteString;
import de.saschadoemer.agrirouter.protobuf.encoder.Encoder;

import java.util.Optional;

public class MessageEncoder implements Encoder {

    @Override
    public Optional<ByteString> safeEncode(String json) {
        return Optional.empty();
    }
}
