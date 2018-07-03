package de.saschadoemer.agrirouter.protobuf.client.javafx.ui;

import de.saschadoemer.agrirouter.protobuf.client.javafx.ui.components.EncodeMessageWithContentPane;
import de.saschadoemer.agrirouter.protobuf.encoder.Encoder;
import de.saschadoemer.agrirouter.protobuf.encoder.impl.CapabilitiesMessageContentEncoder;

import java.util.Optional;

public class EncodeCapabilitiesPaneWith extends EncodeMessageWithContentPane {

    private Encoder capabilitiesMessageContentEncoder = new CapabilitiesMessageContentEncoder();

    @Override
    protected void defineOnAction() {
        Optional<String> messageContent = this.capabilitiesMessageContentEncoder.safeEncode(this.messageContentInputProperty.get());
        if (messageContent.isPresent()) {
            this.messageContentOutputProperty.set(messageContent.get());
            this.encodeMessage(messageContent.get());
        }
    }
}
