package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label totalEmployeesLabel;
    @FXML
    private Label totalPayrollLabel;
    @FXML
    private BarChart<String, Number> salaryByDepartmentChart;
    @FXML
    private PieChart payrollBreakdownChart;
    @FXML
    private LineChart<String, Number> payrollTrendChart;

    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        welcomeLabel.setText("Welcome, Admin " + username);
    }

    @FXML
    public void initializeData() {
        // Mock data for testing
        totalEmployeesLabel.setText("100");
        totalPayrollLabel.setText("500000");

        // Mock data for Salary by Department Chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        salaryByDepartmentChart.setTitle("Salary by Department");
        xAxis.setLabel("Department");
        yAxis.setLabel("Salary");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2024");
        series1.getData().add(new XYChart.Data("IT", 150000));
        series1.getData().add(new XYChart.Data("HR", 100000));
        series1.getData().add(new XYChart.Data("Sales", 120000));
        salaryByDepartmentChart.getData().addAll(series1);

        // Mock data for Payroll Breakdown Chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Basic", 60),
                        new PieChart.Data("Overtime", 20),
                        new PieChart.Data("Deductions", 20));
        payrollBreakdownChart.setData(pieChartData);
        payrollBreakdownChart.setTitle("Payroll Breakdown");

        // Mock data for Payroll Trend Chart
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Payroll Trend");
        series2.getData().add(new XYChart.Data("Jan", 450000));
        series2.getData().add(new XYChart.Data("Feb", 480000));
        series2.getData().add(new XYChart.Data("Mar", 500000));
        payrollTrendChart.getData().addAll(series2);
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
    void goToManageEmployees(ActionEvent event) {
        // Implement the action for the Manage Employees button
        System.out.println("Manage Employees button clicked");
    }

    @FXML
    void goToProcessPayroll(ActionEvent event) {
        // Implement the action for the Process Payroll button
        System.out.println("Process Payroll button clicked");
    }

    @FXML
    void goToGeneratePayslips(ActionEvent event) {
        // Implement the action for the Generate Payslips button
        System.out.println("Generate Payslips button clicked");
    }

    @FXML
    void goToReportsCharts(ActionEvent event) {
        // Implement the action for the Reports & Charts button
        System.out.println("Reports & Charts button clicked");
    }

    @FXML
    void goToExportData(ActionEvent event) {
        // Implement the action for the Export Data button
        System.out.println("Export Data button clicked");
    }

    @FXML
    void addEmployee(ActionEvent event) {
        // Implement the action for the Add Employee button
        System.out.println("Add Employee button clicked");
    }

    @FXML
    void processPayroll(ActionEvent event) {
        // Implement the action for the Process Payroll button
        System.out.println("Process Payroll button clicked");
    }

    @FXML
    void generatePayslip(ActionEvent event) {
        // Implement the action for the Generate Payslip button
        System.out.println("Generate Payslip button clicked");
    }
}