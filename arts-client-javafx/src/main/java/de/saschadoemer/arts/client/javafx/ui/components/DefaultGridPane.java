package de.saschadoemer.arts.client.javafx.ui.components;

import de.saschadoemer.arts.client.javafx.ui.api.ErrorDialogProvider;
import de.saschadoemer.arts.client.javafx.ui.api.StandardComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public abstract class DefaultGridPane extends GridPane implements StandardComponent, ErrorDialogProvider {

    protected DefaultGridPane() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
    }
}
