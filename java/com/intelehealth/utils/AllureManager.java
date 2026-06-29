package com.intelehealth.utils;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.model.StatusDetails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * AllureManager — Central reporting hub for the Intelehealth Mobile Automation framework.
 *
 * Responsibilities:
 *  - Screenshot capture and dual-attachment (Allure + ExtentReport) on failure/skip
 *  - Page source attachment for DOM-level debugging
 *  - Logcat file attachment per test failure
 *  - Step logging with status propagation
 *  - Plain text / JSON attachment for API test evidence
 *
 * Design principle: Every method in this class is null-safe and session-guarded.
 * A dead or null driver session always produces a clean log warning — never a
 * secondary exception that could mask the original test failure.
 *
 * Usage: Call AllureManager methods from TestListener, BaseTest, or Page Objects.
 * Never scatter raw Allure.addAttachment() calls across test classes.
 */
public class AllureManager{

    private AllureManager() {
        // Utility class — no instantiation
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREENSHOT — Primary failure evidence
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Captures a screenshot from the active driver session and attaches it to
     * BOTH Allure and ExtentReport simultaneously.
     *
     * This is the single method that should be called on every test failure or
     * skip. It handles all null/session checks internally so callers do not need
     * to guard before invoking it.
     *
     * @param result      the TestNG ITestResult from the listener callback
     * @param extentStatus the ExtentReports Status (FAIL or SKIP)
     */
    public static void captureAndAttachScreenshot(ITestResult result,
                                                   Status extentStatus) {
        AppiumDriver activeDriver = BaseTest.driver.get();

        if (!SessionHealthManager.isSessionAlive(activeDriver)) {
            TestUtils.log().warn("[AllureManager] Screenshot skipped — session not alive: "
                + result.getName());
            ExtentReport.getTest().log(extentStatus,
                "Screenshot unavailable — driver session is dead.");
            Allure.addAttachment("Screenshot Unavailable",
                "text/plain",
                new ByteArrayInputStream(
                    "Driver session was dead at time of screenshot capture."
                        .getBytes(StandardCharsets.UTF_8)),
                ".txt");
            return;
        }

        try {
            // Capture once — reuse bytes for both reporters
            byte[] screenshotBytes = activeDriver.getScreenshotAs(OutputType.BYTES);
            File tempFile = activeDriver.getScreenshotAs(OutputType.FILE);

            // ── Allure attachment ────────────────────────────────────────────
            String allureLabel = (extentStatus == Status.FAIL ? "Failure" : "Skip")
                + " Screenshot — " + result.getName();
            Allure.addAttachment(allureLabel, "image/png",
                new ByteArrayInputStream(screenshotBytes), ".png");

            // ── ExtentReport attachment ──────────────────────────────────────
            java.util.Map<String, String> params = result.getTestContext()
                .getCurrentXmlTest().getAllParameters();

            String imagePath = "Screenshots" + File.separator
                + params.get("platformName") + "_" + params.get("deviceName")
                + File.separator + BaseTest.dateTime.get() + File.separator
                + result.getTestClass().getRealClass().getSimpleName()
                + File.separator + result.getName() + ".png";

            File dest = new File(imagePath);
            dest.getParentFile().mkdirs();
            FileUtils.copyFile(tempFile, dest);

            byte[] encoded = org.apache.commons.codec.binary.Base64
                .encodeBase64(FileUtils.readFileToByteArray(tempFile));
            String base64 = new String(encoded, StandardCharsets.US_ASCII);

            ExtentReport.getTest().log(extentStatus, "Screenshot:",
                com.aventstack.extentreports.MediaEntityBuilder
                    .createScreenCaptureFromBase64String(base64).build());

            TestUtils.log().info("[AllureManager] Screenshot attached to both reports: "
                + imagePath);

        } catch (Exception e) {
            TestUtils.log().warn("[AllureManager] Screenshot capture failed: "
                + e.getMessage());
            ExtentReport.getTest().log(extentStatus,
                "Screenshot capture failed: " + e.getMessage());
        }
    }

    /**
     * Lightweight screenshot attach — Allure only, no ExtentReport write.
     * Use this inside Page Object methods or step-level debugging where you
     * want a mid-test snapshot without involving ExtentReport.
     *
     * @param label descriptive label shown in the Allure report
     */
    public static void attachScreenshot(String label) {
        AppiumDriver activeDriver = BaseTest.driver.get();
        if (!SessionHealthManager.isSessionAlive(activeDriver)) {
            TestUtils.log().warn("[AllureManager] Mid-test screenshot skipped — session not alive.");
            return;
        }
        try {
            byte[] screenshot = activeDriver.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(label, "image/png",
                new ByteArrayInputStream(screenshot), ".png");
            TestUtils.log().info("[AllureManager] Mid-test screenshot attached: " + label);
        } catch (Exception e) {
            TestUtils.log().warn("[AllureManager] Mid-test screenshot failed: " + e.getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // FAILURE DETAILS — Full failure package for a test result
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Attaches the complete failure evidence package to both Allure and
     * ExtentReport for a given test result. This is the single method
     * TestListener.onTestFailure should call — it handles everything in the
     * correct order.
     *
     * Evidence captured:
     *  1. Full stack trace as Allure text attachment
     *  2. Screenshot attached to both Allure and ExtentReport
     *  3. XML page source attached to Allure for DOM-level debugging
     *
     * @param result the failing ITestResult from TestNG
     */
    public static void attachFailureEvidence(ITestResult result) {
        // 1. Stack trace
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            result.getThrowable().printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();

            TestUtils.log().error(stackTrace);
            ExtentReport.getTest().fail(result.getThrowable());

            Allure.addAttachment("Stack Trace — " + result.getName(),
                "text/plain",
                new ByteArrayInputStream(stackTrace.getBytes(StandardCharsets.UTF_8)),
                ".txt");
        } else {
            ExtentReport.getTest().fail("Test failed with no throwable captured.");
        }

        // 2. Screenshot — dual attach
        captureAndAttachScreenshot(result, Status.FAIL);

        // 3. Page source
        attachPageSource("Page Source at Failure — " + result.getName());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // PAGE SOURCE
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Attaches the current XML page source to Allure.
     * Invaluable for diagnosing element-not-found failures — the developer
     * can see exactly what was on screen at the moment of failure without
     * needing to reproduce the issue.
     *
     * @param label descriptive label shown in the Allure report
     */
    public static void attachPageSource(String label) {
        AppiumDriver activeDriver = BaseTest.driver.get();
        if (!SessionHealthManager.isSessionAlive(activeDriver)) {
            TestUtils.log().warn("[AllureManager] Page source skipped — session not alive.");
            return;
        }
        try {
            String pageSource = activeDriver.getPageSource();
            Allure.addAttachment(label, "text/xml",
                new ByteArrayInputStream(
                    pageSource.getBytes(StandardCharsets.UTF_8)), ".xml");
            TestUtils.log().info("[AllureManager] Page source attached: " + label);
        } catch (Exception e) {
            TestUtils.log().warn("[AllureManager] Page source attachment failed: "
                + e.getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // LOGCAT FILE ATTACHMENT
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Attaches a logcat file saved to disk to the current Allure test result.
     * Should be called from BaseTest.preserveLogcatForTest() after the file
     * has been written, so the developer sees the crash log inline in the
     * Allure report rather than having to navigate to the filesystem.
     *
     * @param logFile    the logcat .log file on disk
     * @param attachName label shown in the Allure report
     */
    public static void attachLogcatFile(File logFile, String attachName) {
        if (logFile == null || !logFile.exists() || logFile.length() == 0) {
            TestUtils.log().info("[AllureManager] Logcat file empty or missing — skipping: "
                + attachName);
            return;
        }
        try {
            byte[] content = FileUtils.readFileToByteArray(logFile);
            Allure.addAttachment(attachName, "text/plain",
                new ByteArrayInputStream(content), ".log");
            TestUtils.log().info("[AllureManager] Logcat attached to Allure: " + attachName);
        } catch (Exception e) {
            TestUtils.log().warn("[AllureManager] Logcat attachment failed: " + e.getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // STEP LOGGING
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Logs a named step to Allure with PASSED status and mirrors it to
     * ExtentReport and the Log4j logger simultaneously.
     *
     * Use this inside Page Object methods for fine-grained step tracking.
     * Each call appears as a distinct row in the Allure test body timeline.
     *
     * @param stepName human-readable description of the step
     */
    public static void step(String stepName) {
        Allure.step(stepName);
        ExtentReport.getTest().log(Status.INFO, stepName);
        TestUtils.log().info("[Step] " + stepName);
    }

    /**
     * Logs a named step with an explicit Allure status.
     * Use Status.FAILED for a step that failed but should not abort the test,
     * or Status.BROKEN for environment/infrastructure issues.
     *
     * @param stepName human-readable description of the step
     * @param status   io.qameta.allure.model.Status enum value
     */
    public static void step(String stepName, io.qameta.allure.model.Status status) {
        Allure.step(stepName, status);
        Status extentStatus = mapAllureStatusToExtent(status);
        ExtentReport.getTest().log(extentStatus, stepName);
        TestUtils.log().info("[Step:" + status.value() + "] " + stepName);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // TEXT / JSON ATTACHMENT
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Attaches any plain text or JSON string to the current Allure result.
     * Use this for API request/response bodies, configuration snapshots,
     * or any structured string data that aids in failure diagnosis.
     *
     * @param name    label shown in the Allure report
     * @param content the string content to attach
     * @param mimeType MIME type — use "application/json" or "text/plain"
     */
    public static void attachText(String name, String content, String mimeType) {
        if (content == null || content.isEmpty()) {
            TestUtils.log().warn("[AllureManager] Skipping empty text attachment: " + name);
            return;
        }
        try {
            String extension = mimeType.contains("json") ? ".json" : ".txt";
            Allure.addAttachment(name, mimeType,
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                extension);
            TestUtils.log().info("[AllureManager] Text attached: " + name);
        } catch (Exception e) {
            TestUtils.log().warn("[AllureManager] Text attachment failed: " + e.getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // INTERNAL HELPERS
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Maps an Allure Status enum to its ExtentReports equivalent so that
     * step(String, Status) can write to both reporters consistently.
     */
    private static Status mapAllureStatusToExtent(io.qameta.allure.model.Status allureStatus) {
        switch (allureStatus) {
            case FAILED:  return Status.FAIL;
            case BROKEN:  return Status.WARNING;
            case SKIPPED: return Status.SKIP;
            case PASSED:
            default:      return Status.INFO;
        }
    }
}