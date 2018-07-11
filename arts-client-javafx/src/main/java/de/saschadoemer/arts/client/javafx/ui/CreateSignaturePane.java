package de.saschadoemer.arts.client.javafx.ui;

import de.saschadoemer.arts.client.javafx.ui.components.DefaultGridPane;
import de.saschadoemer.arts.decoder.Decoder;
import de.saschadoemer.arts.decoder.SignatureService;
import de.saschadoemer.arts.decoder.impl.MessageDecoder;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CreateSignaturePane extends DefaultGridPane {

    private SimpleStringProperty privateKeyInputProperty;
    private SimpleStringProperty publicKeyInputProperty;
    private SimpleStringProperty requestInputProperty;
    private SimpleStringProperty signatureOutputProperty;

    private SignatureService signatureService = new SignatureService();

    CreateSignaturePane() {
        this.initProperties();
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.add(new Label("Private Key:"), 0, 0);

        TextArea privateKeyTextArea = new TextArea();
        privateKeyTextArea.textProperty().bindBidirectional(this.privateKeyInputProperty);
        this.add(privateKeyTextArea, 1, 0);

        this.add(new Label("Public Key:"), 0, 1);

        TextArea publicKeyTextArea = new TextArea();
        publicKeyTextArea.textProperty().bindBidirectional(this.publicKeyInputProperty);
        this.add(publicKeyTextArea, 1, 1);

        this.add(new Label("Request:"), 0, 2);

        TextArea requestTextArea = new TextArea();
        requestTextArea.textProperty().bindBidirectional(this.requestInputProperty);
        this.add(requestTextArea, 1, 2);

        this.add(new Label("Signature:"), 0, 3);

        TextArea signatureTextArea = new TextArea();
        signatureTextArea.textProperty().bindBidirectional(this.signatureOutputProperty);
        this.add(signatureTextArea, 1, 3);

        Button decodeMessageButton = new Button("Create Signature");
        decodeMessageButton.setOnAction(event -> {
            String signature = this.signatureService.createSignature(this.privateKeyInputProperty.get(), this.publicKeyInputProperty.get(), this.requestInputProperty.get());
            this.signatureOutputProperty.set(signature);
        });
        this.add(decodeMessageButton, 0, 4);
    }

    @Override
    public void initProperties() {
        this.privateKeyInputProperty = new SimpleStringProperty();
        this.publicKeyInputProperty = new SimpleStringProperty();
        this.requestInputProperty = new SimpleStringProperty();
        this.signatureOutputProperty = new SimpleStringProperty();
    }

}
