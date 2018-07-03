package de.saschadoemer.agrirouter.protobuf.client.javafx.ui;

import de.saschadoemer.agrirouter.protobuf.client.javafx.ui.components.StandardComponent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainPane extends TabPane implements StandardComponent {

    public MainPane() {
        this.initComponents();
    }

    @Override
    public void initComponents() {
        Tab encodingTab = new Tab();
        encodingTab.setText("Encode (Capabilities) ");
        encodingTab.setContent(new EncodeCapabilitiesPaneWith());
        encodingTab.setClosable(false);

        Tab decodingTab = new Tab();
        decodingTab.setText("Decode (Base64)");
        decodingTab.setContent(new DecodeMessagePane());
        decodingTab.setClosable(false);

        this.getTabs().addAll(encodingTab, decodingTab);
    }

    @Override
    public void initProperties() {
        // NOP
    }
}
