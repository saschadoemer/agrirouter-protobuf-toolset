package de.saschadoemer.arts.client.javafx.ui;

import de.saschadoemer.arts.client.javafx.ui.api.StandardComponent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainPane extends TabPane implements StandardComponent {

    public MainPane() {
        this.initComponents();
    }

    @Override
    public void initComponents() {
        Tab capabilitiesTab = new Tab();
        capabilitiesTab.setText("Capabilities");
        capabilitiesTab.setContent(new EncodeCapabilitiesMessagePane());
        capabilitiesTab.setClosable(false);

        Tab signatureTab = new Tab();
        signatureTab.setText("Signature");
        signatureTab.setContent(new CreateSignaturePane());
        signatureTab.setClosable(false);

        Tab decodingTab = new Tab();
        decodingTab.setText("Decode Message ");
        decodingTab.setContent(new DecodeMessagePane());
        decodingTab.setClosable(false);

        this.getTabs().addAll(capabilitiesTab, signatureTab, decodingTab);
    }

    @Override
    public void initProperties() {
        // NOP
    }
}
