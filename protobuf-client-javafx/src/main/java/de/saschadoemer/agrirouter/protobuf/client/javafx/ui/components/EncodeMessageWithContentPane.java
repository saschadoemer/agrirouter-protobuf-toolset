package de.saschadoemer.agrirouter.protobuf.client.javafx.ui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.UUID;

public abstract class EncodeMessageWithContentPane extends DefaultGridPane {

    protected SimpleStringProperty applicationMessageIdProperty;
    protected SimpleStringProperty applicationMessageSeqNoProperty;
    protected SimpleStringProperty technicalMessageTypeProperty;
    protected SimpleStringProperty modeProperty;

    protected SimpleStringProperty messageContentInputProperty;
    protected SimpleStringProperty messageContentOutputProperty;
    protected SimpleStringProperty messageOutputProperty;


    public EncodeMessageWithContentPane() {
        this.initProperties();
        this.initComponents();
    }

    @Override
    public void initComponents() {
        Label applicationMessageIdLabel = new Label("Application Message Id");
        this.add(applicationMessageIdLabel, 0, 0);

        TextField applicationMessageIdTextField = new TextField();
        this.applicationMessageIdProperty.set(UUID.randomUUID().toString());
        applicationMessageIdTextField.textProperty().bindBidirectional(this.applicationMessageIdProperty);
        applicationMessageIdTextField.setEditable(false);
        this.add(applicationMessageIdTextField, 1, 0);

        Label applicationMessageSeqNoLabel = new Label("Application Message SeqNo");
        this.add(applicationMessageSeqNoLabel, 0, 1);

        TextField applicationMessageSeqNoTextField = new TextField();
        this.applicationMessageSeqNoProperty.set("1");
        applicationMessageSeqNoTextField.textProperty().bindBidirectional(this.applicationMessageSeqNoProperty);
        applicationMessageSeqNoTextField.setEditable(false);
        this.add(applicationMessageSeqNoTextField, 1, 1);

        Label technicalMessageTypeLabel = new Label("Technical Message Type");
        this.add(technicalMessageTypeLabel, 0, 2);

        TextField technicalMessageTypeTextField = new TextField();
        this.technicalMessageTypeProperty.set("dke:capabilities");
        technicalMessageTypeTextField.textProperty().bindBidirectional(this.technicalMessageTypeProperty);
        technicalMessageTypeTextField.setEditable(false);
        this.add(technicalMessageTypeTextField, 1, 2);

        Label modeLabel = new Label("Mode");
        this.add(modeLabel, 0, 3);

        TextField modeTextField = new TextField();
        modeProperty.set("0");
        modeTextField.textProperty().bindBidirectional(this.modeProperty);
        modeTextField.setEditable(false);
        this.add(modeTextField, 1, 3);

        Label inputLabel = new Label("Content (json):");
        this.add(inputLabel, 0, 4);

        TextArea inputArea = new TextArea();
        inputArea.setWrapText(true);
        inputArea.textProperty().bindBidirectional(this.messageContentInputProperty);
        this.add(inputArea, 1, 4);

        Label messageContentLabel = new Label("Content (base64):");
        this.add(messageContentLabel, 0, 5);

        TextField messageContentTextField = new TextField();
        messageContentTextField.setEditable(false);
        messageContentTextField.textProperty().bindBidirectional(this.messageContentOutputProperty);
        this.add(messageContentTextField, 1, 5);

        Label messageLabel = new Label("Message (base64):");
        this.add(messageLabel, 0, 6);

        TextField messageTextField = new TextField();
        messageTextField.setEditable(false);
        messageTextField.textProperty().bindBidirectional(this.messageOutputProperty);
        this.add(messageTextField, 1, 6);

        Button encodeMessageButton = new Button("Encode message");
        encodeMessageButton.setOnAction(event -> {
            this.defineOnAction();
        });

        this.add(encodeMessageButton, 0, 7);
    }

    protected abstract void defineOnAction();

    @Override
    public void initProperties() {
        this.applicationMessageIdProperty = new SimpleStringProperty();
        this.applicationMessageSeqNoProperty = new SimpleStringProperty();
        this.technicalMessageTypeProperty = new SimpleStringProperty();
        this.modeProperty = new SimpleStringProperty();

        this.messageContentInputProperty = new SimpleStringProperty();
        this.messageContentOutputProperty = new SimpleStringProperty();
        this.messageOutputProperty = new SimpleStringProperty();

    }

    protected void encodeMessage(String encodedMessageContent) {

    }
}
