package com.intelehealth.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;

public class SessionHealthManager {

    /**
     * Validates that a driver instance is non-null and its session is alive.
     * Returns false gracefully rather than throwing, so callers can decide
     * whether to skip, fail, or attempt recovery.
     */
    public static boolean isSessionAlive(AppiumDriver driver) {
        if (driver == null) {
            TestUtils.log().warn("SessionHealthManager: driver is null.");
            return false;
        }
        try {
            driver.getPageSource();
            return true;
        } catch (WebDriverException e) {
            TestUtils.log().warn("SessionHealthManager: session is dead — " + e.getMessage());
            return false;
        }
    }

    /**
     * Polls until the app package is in the foreground.
     * Prevents test execution against a restarting or crashed application context.
     *
     * @param driver          active driver
     * @param expectedPackage e.g. "org.intelehealth.app"
     * @param timeoutSeconds  maximum time to wait
     * @return true if app is in foreground within timeout
     */
    public static boolean waitForAppInForeground(AppiumDriver driver,
                                                  String expectedPackage,
                                                  int timeoutSeconds) {
        long deadline = System.currentTimeMillis() + Duration.ofSeconds(timeoutSeconds).toMillis();
        while (System.currentTimeMillis() < deadline) {
            try {
                String current = ((AndroidDriver) driver).getCurrentPackage();
                if (current != null && current.contains(expectedPackage)) {
                    return true;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                TestUtils.log().warn("waitForAppInForeground poll failed: " + e.getMessage());
            }
        }
        return false;
    }
}