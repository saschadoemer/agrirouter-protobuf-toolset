package de.saschadoemer.agrirouter.protobuf.client.javafx.ui.api;

import javafx.scene.control.Alert;

public interface ErrorDialogProvider {

    default void error(String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("¯\\\\_(ツ)_/¯");
        alert.setHeaderText("ლ(ಠ_ಠლ) - " + headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
