package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Load FXML
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main_dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();

            // Attempt to load the image directly from the file system
            try {
                String imagePath = "/com/example/demo3/images/generated_nail_image.png"; // Replace with your actual path
                Image image = new Image("file:" + imagePath); // Load from file system
                System.out.println("Image loaded successfully from file system.");
            } catch (Exception imageException) {
                System.err.println("Error loading image from file system: " + imageException.getMessage());
                imageException.printStackTrace();
            }
        }
    }
}
