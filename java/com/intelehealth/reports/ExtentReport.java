/**
 * Author: Shweta Naik
 * Description: Class for managing ExtentReports and ExtentTests.
 */
package com.intelehealth.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    static ExtentReports extent;
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String folderPath = "TestReports";  // Specify your folder name here
            String filePath = folderPath + File.separator + "Extent_" + timeStamp + ".html";

            ExtentSparkReporter html = new ExtentSparkReporter(filePath);
            html.config().setDocumentTitle("Appium Framework");
            html.config().setReportName("Intelehealth");
            html.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(html);
        }

        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
