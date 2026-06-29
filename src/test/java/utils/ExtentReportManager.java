package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentReportManager {
    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    public static synchronized ExtentReports getReporter() {
        if (extentReports == null) {
            try {
                Files.createDirectories(Path.of("reports"));
            } catch (Exception ignored) {
            }
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/CineBookAutomationReport.html");
            sparkReporter.config().setDocumentTitle("CineBook Automation Report");
            sparkReporter.config().setReportName("CineBook Selenium TestNG Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Application", "CineBook");
            extentReports.setSystemInfo("Framework", "Selenium + TestNG Hybrid");
        }
        return extentReports;
    }
}
