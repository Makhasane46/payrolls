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

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private static final Map<String, User> registeredUsers = new HashMap<>();

    @FXML
    public void initialize() {
        // Hardcoded users for testing
        registeredUsers.put("mo", new User("mo", "21", "admin"));
        registeredUsers.put("dee", new User("dee", "59", "employee"));

        System.out.println("Admin username: " + registeredUsers.get("mo").getUsername());
        System.out.println("Admin password: " + registeredUsers.get("mo").getPassword());
        System.out.println("Employee username: " + registeredUsers.get("dee").getUsername());
        System.out.println("Employee password: " + registeredUsers.get("dee").getPassword());
    }

    @FXML
    void loginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = registeredUsers.get(username);

        System.out.println("Entered username: " + username);
        System.out.println("Entered password: " + password);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            loadDashboard(event, username, user.getRole());
        } else {
            System.out.println("Login failed!");
            showAlert("Login Failed", "Invalid credentials.");
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
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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