package com.intelehealth.listeners;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.AllureManager;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TestListener implements ITestListener {

    // ─── onTestStart ──────────────────────────────────────────────────────────
    /**
     * CRITICAL: Never instantiate new BaseTest() here.
     * The BaseTest constructor calls PageFactory.initElements() which requires
     * a live driver. Instantiating it from the listener causes NullPointerException
     * when the driver ThreadLocal is not yet populated.
     * Always read ThreadLocal values directly via BaseTest.platform.get() etc.
     */
    @Override
    public void onTestStart(ITestResult result) {
        String platform   = BaseTest.platform.get()   != null ? BaseTest.platform.get()   : "Unknown";
        String deviceName = BaseTest.deviceName.get() != null ? BaseTest.deviceName.get() : "Unknown";

        // ExtentReport
        ExtentReport
            .startTest(result.getName(), result.getMethod().getDescription())
            .assignCategory(platform + "_" + deviceName)
            .assignAuthor("Srinivas Bandi");

        // Allure — device and platform visible in the Allure Behaviors/Suites view
        Allure.label("device", deviceName);
        Allure.label("platform", platform);
    }

    // ─── onTestSuccess ────────────────────────────────────────────────────────
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Passed");
        // Allure handles PASSED status automatically via the allure-testng listener.
        // Uncomment below if you want a pass screenshot for visual confirmation:
        // AllureManager.attachScreenshot("Pass Screenshot — " + result.getName());
    }

    // ─── onTestFailure ────────────────────────────────────────────────────────
    /**
     * Single call to AllureManager.attachFailureEvidence() handles everything:
     *  - Stack trace logged and attached to Allure as text
     *  - Screenshot captured and attached to both Allure and ExtentReport
     *  - XML page source attached to Allure for DOM-level debugging
     *
     * Note: logcat files are attached separately via BaseTest.preserveLogcatForTest()
     * which fires in afterMethod() — before the next test's clearLogcat() runs.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        AllureManager.attachFailureEvidence(result);
    }

    // ─── onTestSkipped ────────────────────────────────────────────────────────
    @Override
    public void onTestSkipped(ITestResult result) {
        String skipMessage = result.getThrowable() != null
            ? result.getThrowable().getMessage()
            : "No reason provided";

        ExtentReport.getTest().log(Status.SKIP, "Test Skipped: " + skipMessage);

        // Screenshot on skip — Allure + ExtentReport
        AllureManager.captureAndAttachScreenshot(result, Status.SKIP);
    }

    // ─── onFinish ─────────────────────────────────────────────────────────────
    @Override
    public void onFinish(ITestContext context) {
        // Flush ExtentReport
        ExtentReport.getReporter().flush();

        // Write Allure environment.properties — populates the Overview environment
        // block with device, platform, and app version details.
        writeAllureEnvironmentProperties(context);
    }

    // ─── Allure Environment File ──────────────────────────────────────────────
    /**
     * Writes target/allure-results/environment.properties.
     * This file populates the Environment panel on the Allure Overview page,
     * giving the reader immediate context about which device and app version
     * the results relate to — critical for mobile reporting.
     */
    private void writeAllureEnvironmentProperties(ITestContext context) {
        try {
            Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
            String allureResultsDir = System.getProperty(
                "allure.results.directory", "target/allure-results");

            File envFile = new File(allureResultsDir + "/environment.properties");
            envFile.getParentFile().mkdirs();

            StringBuilder sb = new StringBuilder();
            sb.append("Platform=").append(params.getOrDefault("platformName", "Android")).append("\n");
            sb.append("Device=").append(params.getOrDefault("deviceName", "Unknown")).append("\n");
            sb.append("UDID=").append(params.getOrDefault("udid", "Unknown")).append("\n");
            sb.append("App=ida.apk").append("\n");
            sb.append("AutomationName=UiAutomator2").append("\n");
            sb.append("AppiumVersion=3.4.2").append("\n");
            sb.append("JavaClientVersion=8.6.0").append("\n");
            sb.append("SeleniumVersion=4.21.0").append("\n");
            sb.append("ExecutionDateTime=").append(BaseTest.dateTime.get()).append("\n");

            FileUtils.writeStringToFile(envFile, sb.toString(), StandardCharsets.UTF_8);
            TestUtils.log().info("[AllureListener] environment.properties written: "
                + envFile.getAbsolutePath());

        } catch (Exception e) {
            TestUtils.log().warn("[AllureListener] Failed to write environment.properties: "
                + e.getMessage());
        }
    }

    // ─── Unused overrides ─────────────────────────────────────────────────────
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult r) { }
    @Override public void onStart(ITestContext c) { }
}
