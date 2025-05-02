package com.example.demo3;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String position;
    private double basicSalary;
    private double workingHours;

    public Employee(int employeeId, String name, String department, String position, double basicSalary, double workingHours) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.basicSalary = basicSalary;
        this.workingHours = workingHours;
    }

    // Getters and setters for all fields
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", basicSalary=" + basicSalary +
                ", workingHours=" + workingHours +
                '}';
    }
}