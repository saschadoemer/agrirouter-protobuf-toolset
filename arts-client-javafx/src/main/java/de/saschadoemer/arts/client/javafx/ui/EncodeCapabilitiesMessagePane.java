package de.saschadoemer.arts.client.javafx.ui;

import com.google.protobuf.Message;
import de.saschadoemer.arts.client.javafx.ui.components.EncodeMessageWithContentPane;
import de.saschadoemer.arts.encoder.impl.CapabilitiesMessageContentEncoder;

import java.util.Optional;

public class EncodeCapabilitiesMessagePane extends EncodeMessageWithContentPane {

    private CapabilitiesMessageContentEncoder capabilitiesMessageContentEncoder = new CapabilitiesMessageContentEncoder();

    @Override
    protected Optional<Message> encodeMessage() {
        return this.capabilitiesMessageContentEncoder.encode(this.messageContentInputProperty.get());
    }

    @Override
    protected String getTechnicalMessageType() {
        return "dke:capabilities";
    }
}