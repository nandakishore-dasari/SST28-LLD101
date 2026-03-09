package com.example.reports;

public class ReportProxy implements Report {
    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    
    // Virtual Proxy / Caching: Reference to the real object
    private RealReport realReport; 

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1. Protection Proxy: Check Access
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED for " + user.getName() 
                + " (Role: " + user.getRole() + ") on [" + classification + "] report.");
            return;
        }

        // 2. Virtual Proxy: Lazy Loading & Caching
        if (realReport == null) {
            realReport = new RealReport(reportId, title);
        }

        realReport.display(user);
    }
}