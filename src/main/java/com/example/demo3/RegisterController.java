package com.example.demo3; // Replace with your actual package name

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController {

    @FXML
    private TextField registerUsernameField;

    @FXML
    private PasswordField registerPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label registrationMessage;

    private Map<String, User> users = new HashMap<>();  // Use the same user map as LoginController

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

    @FXML
    public void initialize() {
        // You might load existing users from a file here in a real application
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    @FXML
    public void register(ActionEvent actionEvent) {
        String username = registerUsernameField.getText().trim();
        String password = registerPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            registrationMessage.setText("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            registrationMessage.setText("Passwords do not match.");
            return;
        }

        if (users.containsKey(username)) {
            registrationMessage.setText("Username already exists.");
            return;
        }

        // Create a new user WITHOUT a role
        User newUser = new User(username, password);
        users.put(username, newUser);

        registrationMessage.setText("Registration successful.");
        clearFields();

        // Automatically go to dashboard screen after successful registration
        try {
            URL resource = getClass().getResource("dashboard.fxml");
            System.out.println("Resource URL: " + resource); // Debugging
            if (resource == null) {
                System.out.println("Resource not found!"); // Debugging
            }
            FXMLLoader loader = new FXMLLoader(resource); // Corrected path
            Parent root = loader.load();

            MainDashboardController dashboardController = loader.getController();
            dashboardController.setUsers(users);
            dashboardController.setLoggedInUsername(username); // Pass the username

            Stage stage = (Stage) registerUsernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error navigating to dashboard screen", e);
            registrationMessage.setText("Error navigating to dashboard screen.");
        }
    }

    private void clearFields() {
        registerUsernameField.clear();
        registerPasswordField.clear();
        confirmPasswordField.clear();
    }
}