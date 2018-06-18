package de.saschadoemer.agrirouter.protobuf.decoder.impl;

import agrirouter.commons.MessageOuterClass;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;

import java.util.Optional;

public class MessageOuterClassDecoder implements Decoder {

    public Optional<String> safeDecode(String utf8) {
        try {
            MessageOuterClass.Message message = MessageOuterClass.Message.parseFrom(ByteString.copyFromUtf8(utf8));
            JsonFormat
        } catch (InvalidProtocolBufferException e) {
            // NOP
        }
        return Optional.empty();
    }
}
