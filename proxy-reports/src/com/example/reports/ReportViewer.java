package com.example.reports;

public class ReportViewer {
    // Client now works with the abstraction
    public void open(Report report, User user) {
        report.display(user);
    }
}