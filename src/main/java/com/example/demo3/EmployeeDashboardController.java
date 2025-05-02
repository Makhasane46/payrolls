package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class EmployeeDashboardController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label basicSalaryLabel;
    @FXML
    private Label workingHoursLabel;
    @FXML
    private Label netSalaryLabel;
    @FXML
    private Label grossSalaryLabel;
    @FXML
    private Label deductionsLabel;
    @FXML
    private LineChart<String, Number> salaryOverTimeChart;

    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        welcomeLabel.setText("Welcome, " + username);
    }

    @FXML
    public void initializeData() {
        // Mock data for testing
        nameLabel.setText("John Doe");
        departmentLabel.setText("IT");
        positionLabel.setText("Developer");
        basicSalaryLabel.setText("50000");
        workingHoursLabel.setText("40");
        netSalaryLabel.setText("40000");
        grossSalaryLabel.setText("45000");
        deductionsLabel.setText("5000");

        // Mock data for Salary Over Time Chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        salaryOverTimeChart.setTitle("Salary Over Time");
        xAxis.setLabel("Month");
        yAxis.setLabel("Salary");

        XYChart.Series series = new XYChart.Series();
        series.setName("Salary Trend");
        series.getData().add(new XYChart.Data("Jan", 38000));
        series.getData().add(new XYChart.Data("Feb", 39000));
        series.getData().add(new XYChart.Data("Mar", 40000));
        salaryOverTimeChart.getData().add(series);
    }
    @FXML
    void logout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToDashboard(ActionEvent event) {
        // Implement the action for the Dashboard button
        System.out.println("Dashboard button clicked");
    }

    @FXML
    void goToViewMyPayslips(ActionEvent event) {
        // Implement the action for the View My Payslips button
        System.out.println("View My Payslips button clicked");
    }

    @FXML
    void goToMySalaryBreakdown(ActionEvent event) {
        // Implement the action for the My Salary Breakdown button
        System.out.println("My Salary Breakdown button clicked");
    }

    @FXML
    void downloadPayslip(ActionEvent event) {
        // Implement the action for the Download Payslip button
        System.out.println("Download Payslip button clicked");
    }

    public void addEmployee(ActionEvent actionEvent) {
    }

    public void updateEmployee(ActionEvent actionEvent) {
    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }

    public void setUserRole(String upperCase) {
    }

    public void updateUI() {

    }
}