package com.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label userRoleLabel; // fx:id must match in main_dashboard.fxml
    @FXML
    private Label loggedInUserLabel; // fx:id must match in main_dashboard.fxml

    private String userRole;
    private String loggedInUsername;

    public void setUserRole(String userRole) {
        System.out.println("setUserRole called with: " + userRole); // Debugging
        this.userRole = userRole;
        updateUI();
    }

    public void setLoggedInUsername(String loggedInUsername) {
        System.out.println("setLoggedInUsername called with: " + loggedInUsername); // Debugging
        this.loggedInUsername = loggedInUsername;
        updateUI();
    }

    @FXML
    public void initialize() {
        System.out.println("MainController initialize() called"); // Debugging
    }

    private void updateUI() {
        System.out.println("updateUI() called"); // Debugging
        if (userRoleLabel != null) {
            userRoleLabel.setText("Role: " + userRole);
        } else {
            System.out.println("userRoleLabel is null"); // Debugging
        }
        if (loggedInUserLabel != null) {
            loggedInUserLabel.setText("Logged in as: " + loggedInUsername);
        } else {
            System.out.println("loggedInUserLabel is null"); // Debugging
        }
    }
}