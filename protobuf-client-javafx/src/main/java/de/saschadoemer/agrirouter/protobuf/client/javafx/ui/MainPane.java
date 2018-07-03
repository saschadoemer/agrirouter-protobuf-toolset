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
        Tab encodingTab= new Tab();
        encodingTab.setText("Encoding");
        encodingTab.setContent(new DecodeMessagePane());
        encodingTab.setClosable(false);

        Tab decodingTab = new Tab();
        decodingTab.setText("Decoding");
        decodingTab.setContent(new DecodeMessagePane());
        decodingTab.setClosable(false);

        this.getTabs().addAll(decodingTab);
    }

    @Override
    public void initProperties() {
        // NOP
    }
}
