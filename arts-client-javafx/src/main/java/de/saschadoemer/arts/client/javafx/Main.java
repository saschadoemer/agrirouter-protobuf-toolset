package de.saschadoemer.arts.client.javafx;

import de.saschadoemer.arts.client.javafx.ui.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ARTS - Client (JavaFX)");
        primaryStage.setScene(new Scene(new MainPane()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
