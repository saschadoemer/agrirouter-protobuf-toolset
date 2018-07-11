package de.saschadoemer.arts.client.javafx.ui;

import de.saschadoemer.arts.client.javafx.ui.components.DefaultGridPane;
import de.saschadoemer.arts.decoder.DecodeSecurityTokenService;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Map;

public class DecodeSecurityTokenPane extends DefaultGridPane {

    private SimpleStringProperty queryInputProperty;

    private SimpleStringProperty signatureOutputProperty;
    private SimpleStringProperty stateOutputProperty;
    private SimpleStringProperty tokenOutputProperty;
    private SimpleStringProperty errorOutputProperty;
    private SimpleStringProperty decodedTokenOutputProperty;

    private DecodeSecurityTokenService decodeSecurityTokenService = new DecodeSecurityTokenService();

    DecodeSecurityTokenPane() {
        this.initProperties();
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.add(new Label("Query (the part after ?):"), 0, 0);

        TextArea redirectUrlTextArea = new TextArea();
        redirectUrlTextArea.setWrapText(true);
        redirectUrlTextArea.textProperty().bindBidirectional(this.queryInputProperty);
        this.add(redirectUrlTextArea, 1, 0);

        this.add(new Label("Signature:"), 0, 1);

        TextField signatureTextField = new TextField();
        signatureTextField.textProperty().bindBidirectional(this.signatureOutputProperty);
        this.add(signatureTextField, 1, 1);

        this.add(new Label("State:"), 0, 2);

        TextField stateTextField = new TextField();
        stateTextField.textProperty().bindBidirectional(this.stateOutputProperty);
        this.add(stateTextField, 1, 2);

        this.add(new Label("Token:"), 0, 3);

        TextField tokenTextField = new TextField();
        tokenTextField.textProperty().bindBidirectional(this.tokenOutputProperty);
        this.add(tokenTextField, 1, 3);

        this.add(new Label("Error:"), 0, 4);

        TextField errorTextField = new TextField();
        errorTextField.textProperty().bindBidirectional(this.errorOutputProperty);
        this.add(errorTextField, 1, 4);

        this.add(new Label("Decoded Token:"), 0, 5);

        TextArea decodedTokenTextArea = new TextArea();
        decodedTokenTextArea.textProperty().bindBidirectional(this.decodedTokenOutputProperty);
        this.add(decodedTokenTextArea, 1, 5);

        Button decodeMessageButton = new Button("Decode");
        decodeMessageButton.setOnAction(event -> {
            Map<String, String> authenticationResults = this.decodeSecurityTokenService.extractAuthenticationResults(queryInputProperty.get());
            this.signatureOutputProperty.set(authenticationResults.get(DecodeSecurityTokenService.SIGNATURE_KEY));
            this.stateOutputProperty.set(authenticationResults.get(DecodeSecurityTokenService.STATE_KEY));
            this.tokenOutputProperty.set(authenticationResults.get(DecodeSecurityTokenService.TOKEN_KEY));
            this.errorOutputProperty.set(authenticationResults.get(DecodeSecurityTokenService.ERROR_KEY));
            String registrationRequestToken = this.decodeSecurityTokenService.decode(this.tokenOutputProperty.get());
            this.decodedTokenOutputProperty.set(registrationRequestToken);

        });
        this.add(decodeMessageButton, 0, 6);
    }

    @Override
    public void initProperties() {
        this.queryInputProperty = new SimpleStringProperty();

        this.signatureOutputProperty = new SimpleStringProperty();
        this.stateOutputProperty = new SimpleStringProperty();
        this.tokenOutputProperty = new SimpleStringProperty();
        this.errorOutputProperty = new SimpleStringProperty();
        this.decodedTokenOutputProperty = new SimpleStringProperty();
    }

}
