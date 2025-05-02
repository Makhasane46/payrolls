package com.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.scene.image.ImageView; // Import ImageView
import javafx.scene.image.Image;     // Import Image
import java.io.InputStream;

public class MainDashboardController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField registerUsernameField;
    @FXML
    private PasswordField registerPasswordField;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private ImageView generatedImageView; // Add this line

    private static final Map<String, User> registeredUsers = new HashMap<>();

    @FXML
    public void initialize() {
        // Hardcoded users for testing
        registeredUsers.put("mo", new User("mo", "21", "admin"));
        registeredUsers.put("dee", new User("dee", "59", "employee"));

        // Populate the role choice box
        roleChoiceBox.setItems(FXCollections.observableArrayList("admin", "employee"));

        // Load the image (ADD THIS BLOCK)
        if (generatedImageView != null) { // Check if ImageView is injected
            try {
                String imagePath = "images/generated_nail_image.png"; // Corrected path
                System.out.println("Image path: " + imagePath);
                InputStream inputStream = getClass().getResourceAsStream(imagePath);

                if (inputStream == null) {
                    System.err.println("Input stream is NULL!  File not found.");
                    showAlert("Error", "Could not find image file.");
                    return;
                }

                Image image = new Image(inputStream);
                generatedImageView.setImage(image);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                e.printStackTrace(); // Uncommented for debugging
                showAlert("Error", "Could not load image.");
            }
        } else {
            System.err.println("ImageView not injected! Check FXML.");
        }
    }

    @FXML
    void loginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleChoiceBox.getValue(); // Get the selected role

        User user = registeredUsers.get(username);

        System.out.println("Entered username: " + username);
        System.out.println("Entered password: " + password);

        if (user != null && user.getPassword().equals(password) && role != null) { // Check if role is selected
            System.out.println("Login successful!");
            loadDashboard(event, username, role); // Pass the selected role
        } else {
            System.out.println("Login failed!");
            showAlert("Login Failed", "Invalid credentials or role.");
        }
    }

    @FXML
    void registerAction(ActionEvent event) {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            if (!registeredUsers.containsKey(username)) {
                registeredUsers.put(username, new User(username, password, "employee")); // Default role
                showAlert("Registration Successful", "User " + username + " registered.");
            } else {
                showAlert("Registration Failed", "Username already exists.");
            }
        } else {
            showAlert("Registration Failed", "Please fill in all registration fields.");
        }
    }

    private void loadDashboard(ActionEvent event, String username, String role) {
        String fxmlFile;
        String title;

        if ("admin".equals(role)) {
            fxmlFile = "admin_dashboard.fxml";
            title = "Admin Dashboard";
        } else if ("employee".equals(role)) {
            fxmlFile = "employee_dashboard.fxml";
            title = "Employee Dashboard";
        } else {
            showAlert("Error", "Invalid user role.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent dashboardRoot = (Parent) fxmlLoader.load();

            // Get the controller instance
            Object controller = fxmlLoader.getController();

            // Pass the username and role to the controller
            if (controller instanceof AdminDashboardController) {
                AdminDashboardController adminController = (AdminDashboardController) controller;
                adminController.setLoggedInUsername(username);
                adminController.initializeData(); //Initialize data for admin
            } else if (controller instanceof EmployeeDashboardController) {
                EmployeeDashboardController employeeController = (EmployeeDashboardController) controller;
                employeeController.setLoggedInUsername(username);
                employeeController.initializeData(); //Initialize data for employee
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(dashboardRoot));
            stage.setTitle(title);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide(); // Close login window
        } catch (IOException e) {
            System.err.println("Error loading dashboard: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Could not load dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION); // Changed to INFORMATION
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUsers(Map<String,com.example.demo3.User> users) {
    }

    public void setLoggedInUsername(String username) {
    }

    // Inner class to represent a user
    private static class User {
        private String username;
        private String password;
        private String role;

        public User(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }
    }
}