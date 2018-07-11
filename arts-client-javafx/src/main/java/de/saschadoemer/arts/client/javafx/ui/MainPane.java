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
        Tab encodeCapabilitiesTab = new Tab();
        encodeCapabilitiesTab.setText("[Messages] Encode Capabilities Message");
        encodeCapabilitiesTab.setContent(new EncodeCapabilitiesMessagePane());
        encodeCapabilitiesTab.setClosable(false);

        Tab decodeMessageTab = new Tab();
        decodeMessageTab.setText("[Messages] Decode Message");
        decodeMessageTab.setContent(new DecodeMessagePane());
        decodeMessageTab.setClosable(false);

        Tab createSignatureTab = new Tab();
        createSignatureTab.setText("[Secured] Create Signature");
        createSignatureTab.setContent(new CreateSignaturePane());
        createSignatureTab.setClosable(false);

        Tab decodeSecurityTokenTab = new Tab();
        decodeSecurityTokenTab.setText("[Secured] Decode Token");
        decodeSecurityTokenTab.setContent(new DecodeSecurityTokenPane());
        decodeSecurityTokenTab.setClosable(false);

        this.getTabs().addAll(encodeCapabilitiesTab, decodeMessageTab, createSignatureTab, decodeSecurityTokenTab);
    }

    @Override
    public void initProperties() {
        // NOP
    }
}
