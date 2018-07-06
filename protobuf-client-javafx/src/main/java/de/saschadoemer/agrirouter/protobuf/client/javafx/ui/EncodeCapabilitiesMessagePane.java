package de.saschadoemer.agrirouter.protobuf.client.javafx.ui;

import com.google.protobuf.Message;
import de.saschadoemer.agrirouter.protobuf.client.javafx.ui.components.EncodeMessageWithContentPane;
import de.saschadoemer.agrirouter.protobuf.encoder.impl.CapabilitiesMessageContentEncoder;

import java.util.Optional;

public class EncodeCapabilitiesMessagePane extends EncodeMessageWithContentPane {

    private CapabilitiesMessageContentEncoder capabilitiesMessageContentEncoder = new CapabilitiesMessageContentEncoder();

    @Override
    protected Optional<Message> encodeMessage() {
        return this.capabilitiesMessageContentEncoder.encode(this.messageContentInputProperty.get());
    }
}
