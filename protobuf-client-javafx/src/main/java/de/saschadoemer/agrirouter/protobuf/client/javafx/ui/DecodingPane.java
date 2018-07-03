package de.saschadoemer.agrirouter.protobuf.client.javafx.ui;

import de.saschadoemer.agrirouter.protobuf.client.javafx.ui.components.DefaultGridPane;
import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;
import de.saschadoemer.agrirouter.protobuf.decoder.impl.MessagesDecoder;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DecodingPane extends DefaultGridPane {

    private SimpleStringProperty inputProperty;
    private SimpleStringProperty outputProperty;
    private Decoder decoder = new MessagesDecoder();


    DecodingPane() {
        this.initProperties();
        this.initComponents();
    }

    @Override
    public void initComponents() {
        TextArea inputArea = new TextArea();
        inputArea.setWrapText(true);
        inputArea.textProperty().bindBidirectional(this.inputProperty);
        this.add(inputArea, 0, 0);

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.textProperty().bindBidirectional(this.outputProperty);
        this.add(outputArea, 0, 1);

        Button decodeMessageButton = new Button("Decode");
        decodeMessageButton.setOnAction(event -> {
            decoder.safeDecode(this.inputProperty.get()).ifPresent(this.outputProperty::set);
        });
        this.add(decodeMessageButton, 0, 2);
    }

    @Override
    public void initProperties() {
        this.inputProperty = new SimpleStringProperty();
        this.outputProperty = new SimpleStringProperty();
    }

}