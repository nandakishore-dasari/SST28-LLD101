package com.example.reports;

public class App {
    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // Use Report interface and ReportProxy implementation
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        // Should work (Public)
        viewer.open(publicReport, student);
        
        // Should be DENIED (Student accessing Faculty report)
        viewer.open(facultyReport, student);
        
        // Should work & Load from disk
        viewer.open(facultyReport, faculty);
        
        // Should work & Load from disk
        viewer.open(adminReport, admin);
        
        // Should work & USE CACHE (No [disk] log should appear)
        viewer.open(adminReport, admin);
    }
}