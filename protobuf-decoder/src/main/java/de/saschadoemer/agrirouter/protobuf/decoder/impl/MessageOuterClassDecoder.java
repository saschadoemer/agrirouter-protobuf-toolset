package de.saschadoemer.agrirouter.protobuf.decoder.impl;

import agrirouter.commons.MessageOuterClass;
import com.google.protobuf.ByteString;
import com.google.protobuf.util.JsonFormat;
import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;

import java.io.IOException;
import java.util.Optional;

public class MessageOuterClassDecoder implements Decoder {

    public Optional<String> safeDecode(String utf8) {
        try {
            MessageOuterClass.Message message = MessageOuterClass.Message.parseFrom(ByteString.copyFromUtf8(utf8));
            StringBuilder output = new StringBuilder();
            JsonFormat.printer().appendTo(message, output);
            return Optional.of(output.toString());
        } catch (IOException e) {
            // NOP
        }
        return Optional.empty();
    }
}
