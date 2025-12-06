package com.danielglover.econav;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = createMainLayout();

        // Create scene
        Scene scene = new Scene(root, 1200, 1100);

        stage.setTitle("EcoNav");
        stage.setScene(scene);
        stage.show();
    }

    public BorderPane createMainLayout(){
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #F0F5F8");




        return borderPane;
    }
}
