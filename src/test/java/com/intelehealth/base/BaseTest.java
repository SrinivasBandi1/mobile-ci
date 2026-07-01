package com.intelehealth.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.ApkDownloadUtil;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

import io.appium.java_client.AppiumBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.SupportsRotation;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

	protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	public static ThreadLocal<String> platform = new ThreadLocal<String>();
	public static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	public static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	private static AppiumDriverLocalService server;
	private static final String SECRET_KEY = "YourSecretKey1234";
	protected static final String BASE_DIR = System.getProperty("user.dir");
	protected static Properties expectedAssertProp = null;
	protected static RequestSpecification hw_request = null;
	protected static RequestSpecification doctor_request = null;

	String appiumURL;

	public AppiumDriver getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	public HashMap<String, String> getStrings() {
		return strings.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}

	public String getPlatform() {
		return platform.get();
	}

	public void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	/*
	 * @BeforeMethod public void beforeMethod() { ((CanRecordScreen)
	 * getDriver()).startRecordingScreen();
	 * 
	 * }
	 */

	// @BeforeMethod
	public void beforeMethod() {
		if (!SessionHealthManager.isSessionAlive(getDriver())) {
			throw new IllegalStateException("Driver session is not alive at the start of the test. "
					+ "Check afterTest teardown and beforeTest initialisation.");
		}
		((CanRecordScreen) getDriver()).startRecordingScreen();
	}

	// ─── afterMethod
	// ──────────────────────────────────────────────────────────────
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) {
		// Guard: only attempt video capture if session is alive.
		if (!SessionHealthManager.isSessionAlive(getDriver())) {
			TestUtils.log().warn("Skipping video capture — driver session is dead.");
			return;
		}

		String media = null;
		try {
			media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
		} catch (Exception e) {
			TestUtils.log().warn("stopRecordingScreen failed: " + e.getMessage());
			return;
		}

		if (media == null || media.isEmpty()) {
			TestUtils.log().warn("Empty video payload returned — skipping write.");
			return;
		}

		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
				+ File.separator + getDateTime() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName();

		File videoDir = new File(dirPath);
		if (!videoDir.exists()) {
			videoDir.mkdirs();
		}

		try (FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4")) {
			stream.write(java.util.Base64.getDecoder().decode(media));
			TestUtils.log().info("Video saved: " + videoDir + File.separator + result.getName() + ".mp4");
		} catch (Exception e) {
			TestUtils.log().error("Video write failed: " + e.getMessage());
		}
	}

	// stop video capturing and create *.mp4 file
	/*
	 * @AfterMethod public synchronized void afterMethod(ITestResult result) throws
	 * Exception { String media = ((CanRecordScreen)
	 * getDriver()).stopRecordingScreen();
	 * 
	 * Map<String, String> params =
	 * result.getTestContext().getCurrentXmlTest().getAllParameters(); String
	 * dirPath = "videos" + File.separator + params.get("platformName") + "_" +
	 * params.get("deviceName") + File.separator + getDateTime() + File.separator +
	 * result.getTestClass().getRealClass().getSimpleName();
	 * 
	 * File videoDir = new File(dirPath);
	 * 
	 * synchronized (videoDir) { if (!videoDir.exists()) { videoDir.mkdirs(); } }
	 * FileOutputStream stream = null; try { stream = new FileOutputStream(videoDir
	 * + File.separator + result.getName() + ".mp4");
	 * stream.write(java.util.Base64.getDecoder().decode(media)); stream.close();
	 * TestUtils.log().info("video path: " + videoDir + File.separator +
	 * result.getName() + ".mp4"); } catch (Exception e) {
	 * TestUtils.log().error("error during video capture" + e.toString()); } finally
	 * { if (stream != null) { stream.close(); } } }
	 */
	public void setFilePath() throws IOException {

		String expectedAssertionsPath = BASE_DIR + "/src/test/resources/data/expected-assertions.properties";
		try (FileReader assertionsReader = new FileReader(expectedAssertionsPath)) {
			expectedAssertProp = new Properties();
			expectedAssertProp.load(assertionsReader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load expected assertions properties file", e);
		}

	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		System.out.println("===============================================================");
		this.setFilePath();
		ThreadContext.put("ROUTINGKEY", "ServerLogs");

		// In CI environments Appium is started externally by the workflow.
		// Skip local server management when CI=true to avoid Windows-only path failures.
		boolean isCi = "true".equalsIgnoreCase(System.getenv("CI"))
				|| "true".equalsIgnoreCase(System.getProperty("CI"));
		if (isCi) {
			TestUtils.log().info("CI environment detected — skipping local Appium server management");
			return;
		}

		server = getAppiumServerDefault();
		if (!checkIfAppiumServerIsRunnning(4723)) {
			server.start();
			TestUtils.log().info("Appium server started");
		} else {
			TestUtils.log().info("Appium server already running");
		}
	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			System.out.println("1");
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		// java.lang.NullPointerException: Cannot invoke
		// "io.appium.java_client.service.local.AppiumDriverLocalService.isRunning()"
		// because "com.intelehealth.base.BaseTest.server" is null.. im getting this
		// error could you pls fixit
		if (server != null && server.isRunning()) {
			server.stop();
			TestUtils.log().info("Appium server stopped");
		} else if (server == null) {
			TestUtils.log().warn("Appium server was not initialized; nothing to stop.");
		}

	}

	// for Windows

	public AppiumDriverLocalService getAppiumServerDefault() {
		int maxAttempts = 3;
		int attempt = 0;
		AppiumDriverLocalService server = null;

		while (attempt < maxAttempts) {
			try {
				HashMap<String, String> environment = new HashMap<>();
				// Get the user's home directory
				String userHome = System.getProperty("user.home");

				// Construct the complete path by appending the remaining path components
				String appiumJSPath = userHome + "/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
				String nodeExePath = "C:/Program Files/nodejs/node.exe";
				System.out.println(userHome);
				environment.put("PATH", userHome + "/local/bin:" + System.getenv("PATH"));

				AppiumServiceBuilder builder = new AppiumServiceBuilder();
				builder.withAppiumJS(new File(appiumJSPath)).usingDriverExecutable(new File(nodeExePath))
						.usingPort(4723).withEnvironment(environment).withArgument(GeneralServerFlag.LOCAL_TIMEZONE);

				server = AppiumDriverLocalService.buildService(builder);

				System.out.println("Server started at :" + server.getUrl());
				// Additional logic can be added here if needed
				break; // Exit the loop if the server is successfully created
			} catch (Exception e) {
				// Handle the exception (e.g., log, retry logic)
				e.printStackTrace();
				attempt++;
			}
		}
		return server;
	}

	@BeforeTest
	@Parameters({ "emulator", "platformName", "udid", "deviceName", "systemPort" })
	public void beforeTest(
			@Optional("false") String emulator,
			@Optional("Android") String platformName,
			@Optional("RZCWA20L2MF") String udid,
			@Optional("Samsung") String deviceName,
			@Optional("10000") String systemPort) throws Exception {

		// ── Thread-local bookkeeping ──────────────────────────────────────────
		setDateTime(TestUtils.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);

		// CI/CD: allow overriding params via system properties / env vars
		// so the pipeline can pass -DEMULATOR=true -DUDID=emulator-5554 etc.
		emulator    = resolveParam("EMULATOR",     emulator);
		platformName = resolveParam("PLATFORM_NAME", platformName);
		udid        = resolveParam("UDID",          udid);
		deviceName  = resolveParam("DEVICE_NAME",   deviceName);
		systemPort  = resolveParam("SYSTEM_PORT",   systemPort);

		// ── Log routing per thread / device ──────────────────────────────────
		String logKey = "logs" + File.separator + platformName + "_" + deviceName;
		new File(logKey).mkdirs();
		ThreadContext.put("ROUTINGKEY", logKey);
		TestUtils.log().info("[Setup] Platform={} Device={} Emulator={} UDID={}", platformName, deviceName, emulator, udid);

		InputStream configStream = null;
		try {
			// ── Load config.properties ────────────────────────────────────────
			Properties props = new Properties();
			configStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			if (configStream == null) {
				throw new IllegalStateException("config.properties not found on classpath");
			}
			props.load(configStream);
			setProps(props);

			// ── Appium server URL ─────────────────────────────────────────────
			// CI sets APPIUM_URL env var; local dev reads config.properties
			String appiumUrlStr = resolveParam("APPIUM_URL", props.getProperty("appiumURL", "http://127.0.0.1:4723/"));
			URL appiumUrl = new URI(appiumUrlStr).toURL();
			TestUtils.log().info("[Setup] Appium URL: {}", appiumUrlStr);

			// ── Capabilities ──────────────────────────────────────────────────
			UiAutomator2Options options = new UiAutomator2Options();
			options.setCapability("platformName", platformName);
			options.setCapability("deviceName", deviceName);
			options.setCapability("udid", udid);
			options.setCapability("ignoreHiddenApiPolicyError", true);
			options.setCapability("resetKeyboard", true);

			if (!platformName.equals("Android")) {
				throw new IllegalArgumentException("Unsupported platform: " + platformName
						+ ". Only Android is currently supported.");
			}

			options.setCapability("automationName",  props.getProperty("androidAutomationName"));
			options.setCapability("appPackage",       props.getProperty("androidAppPackage"));
			options.setCapability("appActivity",      props.getProperty("androidAppActivity"));
			options.setCapability("newCommandTimeout", 300);
			options.setCapability("autoDismissAlerts", true);
			options.setCapability("dontStopAppOnReset", false);
			options.setCapability("autoGrantPermissions", true);
			options.setNoReset(false);
			options.setCapability("fullContextList", true);

			if (emulator.equalsIgnoreCase("true")) {
				options.setCapability("avd", deviceName);
				options.setCapability("avdLaunchTimeout", 300000);
				options.setCapability("avdReadyTimeout", 300000);
				TestUtils.log().info("[Setup] Emulator mode — AVD: {}", deviceName);
			} else {
				TestUtils.log().info("[Setup] Real device mode — UDID: {}", udid);
			}

			// ── APK resolution (3-step: explicit path → URL download → local fallback) ──
			String cacheDir = System.getProperty("user.dir") + File.separator + "apk-cache";
			String apkPath  = ApkDownloadUtil.resolveApkPath("ida.apk", cacheDir);
			TestUtils.log().info("[Setup] APK resolved: {}", apkPath);
			options.setCapability("app", apkPath);

			// ── Driver init ───────────────────────────────────────────────────
			TestUtils.log().info("[Setup] Initialising AndroidDriver...");
			AndroidDriver driver = new AndroidDriver(appiumUrl, options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			setDriver(driver);
			TestUtils.log().info("[Setup] Driver ready — session: {}", driver.getSessionId());

			// Explicitly grant all runtime permissions immediately after Appium installs
			// the app. autoGrantPermissions:true may not cover all permissions on API 36
			// google_apis emulator (e.g. READ_CONTACTS triggers a dialog at first launch).
			String appPkg = props.getProperty("androidAppPackage");
			String[] grantPerms = {
				"android.permission.ACCESS_FINE_LOCATION",
				"android.permission.ACCESS_COARSE_LOCATION",
				"android.permission.CAMERA",
				"android.permission.READ_CONTACTS",
				"android.permission.WRITE_CONTACTS",
				"android.permission.CALL_PHONE",
				"android.permission.RECORD_AUDIO",
				"android.permission.POST_NOTIFICATIONS"
			};
			for (String perm : grantPerms) {
				try {
					driver.executeScript("mobile: shell",
						ImmutableMap.of("command", "pm",
							"args", Arrays.asList("grant", appPkg, perm)));
				} catch (Exception e) {
					TestUtils.log().warn("[Setup] pm grant failed for " + perm + ": " + e.getMessage());
				}
			}
			TestUtils.log().info("[Setup] Runtime permissions explicitly granted for: {}", appPkg);

		} catch (Exception e) {
			TestUtils.log().fatal("[Setup] Driver initialisation FAILED — {}", e.toString());
			throw e;
		} finally {
			if (configStream != null) {
				try { configStream.close(); } catch (IOException ignored) {}
			}
		}
	}

	/**
	 * Resolves a configuration value from system property → env var → supplied default.
	 * Enables GitHub Actions to override any parameter without changing XML files.
	 */
	private static String resolveParam(String key, String defaultValue) {
		String val = System.getProperty(key);
		if (val == null || val.isBlank()) val = System.getenv(key);
		return (val != null && !val.isBlank()) ? val : defaultValue;
	}

	public void enterTextUsingClipboard(String text) {
		try {

			// 1. Encode text to Base64 (required by Appium clipboard API)
			String base64Text = Base64.getEncoder().encodeToString(text.getBytes());

			// 2. Set clipboard
			getDriver().executeScript("mobile: setClipboard",
					Map.of("content", base64Text, "contentType", "plaintext"));

			// 3. Focus already on input field before calling this

			// 4. Paste clipboard
			getDriver().executeScript("mobile: shell", Map.of("command", "input", "args", List.of("keyevent", "279") // KEYCODE_PASTE
			));

		} catch (Exception e) {
			throw new RuntimeException("Failed to enter text using clipboard paste: " + text, e);
		}
	}

	public void waitForVisibility(WebElement e) {
		int maxAttempts = 2;

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtils.WAIT));
				wait.until(ExpectedConditions.visibilityOf(e));
				break; // Exit the loop if visibility is successful
			} catch (StaleElementReferenceException ex) {
				System.out.println("StaleElementReferenceException caught. Retrying visibility attempt " + attempt);
				// Add a small delay before retrying (customize based on your needs)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Exception caught while waiting for visibility: " + ex.getMessage());
				throw ex;
				// ex.printStackTrace();
			}
		}
	}

	public boolean isDisplayed(WebElement e, String msg) {
		int maxAttempts = 2;
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				waitForVisibility(e);
				TestUtils.log().info(msg);
				ExtentReport.getTest().log(Status.INFO, msg);
				return e.isDisplayed();
			} catch (StaleElementReferenceException ex) {
				System.out.println("StaleElementReferenceException caught. Retrying isDisplayed attempt " + attempt);
				// Add a small delay before retrying (customize based on your needs)
				try {
					Thread.sleep(1000);
				} catch (WebDriverException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false; // Return false if visibility is not successful after max attempts
	}

	public boolean isDisplayed2(WebElement e, String msg) {
		int maxAttempts = 5;
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				waitForVisibility(e);
				TestUtils.log().info(msg);
				ExtentReport.getTest().log(Status.INFO, msg);
				return e.isDisplayed();
			} catch (NoSuchElementException ex) {
				handleCustomException(new CustomElementNotFoundException(
						"StaleElementReferenceException caught after attempt " + attempt, "Stale Element", ex));

				/*
				 * try { Thread.sleep(1000); // Add a small delay before retrying }
				 * catch(InterruptedException ie) { Thread.currentThread().interrupt();
				 * handleCustomException(new CustomElementNotFoundException(
				 * "Thread was interrupted during retry delay", "Interrupted", ie)); }
				 */

			} catch (Exception ex) {
				handleCustomException(new CustomElementNotFoundException(
						"NoSuchElementException caught for element: " + msg, "Element Not Found", ex));
			}
		}

		handleCustomException(new CustomElementNotFoundException(
				"Element not found after " + maxAttempts + " attempts: " + msg, "Max Attempts Reached"));
		return false;
	}

	private void handleCustomException(CustomElementNotFoundException ex) {
		TestUtils.log().error("Custom exception occurred: " + ex.getMessage(), ex);
		ExtentReport.getTest().log(Status.WARNING, "Custom exception: " + ex.getMessage());

	}

	public void click(WebElement e, String msg) {
		int maxAttempts = 5;
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				waitForVisibility(e);
				TestUtils.log().info(msg);
				ExtentReport.getTest().log(Status.INFO, msg);
				e.click();
				break; // Exit the loop if click is successful
			} catch (StaleElementReferenceException ex) {
				System.out.println("StaleElementReferenceException caught. Retrying click attempt " + attempt);
				// Add a small delay before retrying (customize based on your needs)
				try {
					Thread.sleep(1000);
				} catch (WebDriverException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void sendKeys(WebElement e, String txt, String msg) {
		int maxAttempts = 5;
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				waitForVisibility(e);
				TestUtils.log().info(msg);
				ExtentReport.getTest().log(Status.INFO, msg);
				e.sendKeys(txt);
				break; // Exit the loop if sendKeys is successful
			} catch (StaleElementReferenceException ex) {
				System.out.println("StaleElementReferenceException caught. Retrying sendKeys attempt " + attempt);
				// Add a small delay before retrying (customize based on your needs)
				try {
					Thread.sleep(1000);
				} catch (WebDriverException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

//	public void waitForVisibility(WebElement e) {
//		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtils.WAIT));
//		wait.until(ExpectedConditions.visibilityOf(e));
//	}

	public void setImplicitWait() {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	/*
	 * public void waitForVisibility(WebElement e){ Wait<WebDriver> wait = new
	 * FluentWait<WebDriver>(getDriver()) .withTimeout(Duration.ofSeconds(30))
	 * .pollingEvery(Duration.ofSeconds(5)) .ignoring(NoSuchElementException.class);
	 * wait.until(ExpectedConditions.visibilityOf(e)); }
	 */
//	public boolean isDisplayed(WebElement e, String msg) {
//		waitForVisibility(e);
//		TestUtils.log().info(msg);
//		ExtentReport.getTest().log(Status.INFO, msg);
//		return e.isDisplayed();
//	}
	public boolean isDisplayed(WebElement e) {
		waitForVisibility(e);
		try {
			return e.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clear(WebElement e) throws InterruptedException {
		waitForVisibility(e);
		e.clear();
	}

	public void click(WebElement e) {
		waitForVisibility(e);
		waitForClickability(e);
		e.click();
	}

	public void waitForClickability(WebElement e) {
		new WebDriverWait(driver.get(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(e));
	}

//	public void click(WebElement e, String msg) {
//		waitForVisibility(e);
//		TestUtils.log().info(msg);
//		ExtentReport.getTest().log(Status.INFO, msg);
//		e.click();
//	}

	public void sendKeys(WebElement e, String txt) {
		try {
			if (e.isDisplayed()) {
				waitForVisibility(e);
				e.sendKeys(txt);
			}
		} catch (Exception ex) {
			// TODO: handle exception
		}

	}

	public void sendKeysObject(WebElement e, Object object) throws InterruptedException {
		waitForVisibility(e);
		e.sendKeys((CharSequence[]) object);

	}
//	public void sendKeys(WebElement e, String txt, String msg) {
//		waitForVisibility(e);
//		TestUtils.log().info(msg);
//		ExtentReport.getTest().log(Status.INFO, msg);
//		e.sendKeys(txt);
//	}

	public String getAttribute(WebElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public boolean isSelected(WebElement e) {
		return e.isSelected();

	}

	public boolean isEnabled(WebElement e, String msg) {
		waitForVisibility(e);
		TestUtils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		return e.isEnabled();

	}

	/*
	 * public boolean isSelected(WebElement e) { return e.isSelected();
	 * 
	 * }
	 */

	protected WebElement getElement(By recentVisitPatients) {
		WebElement element = null;
		try {
			element = getDriver().findElement(recentVisitPatients);
		} catch (Exception e) {
			System.out.println("some exception occurred while creating the webelement : " + recentVisitPatients);
		}
		return element;
	}

	public String doGetFormattedCurrentDDMonYYYY() {
		Date currentDate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
		String formattedDate = dateformat.format(currentDate);
		System.out.println("Current Date: " + formattedDate);
		return formattedDate;
	}

	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String extractBefore(String input, String splitString) {
		// Find the index of the split string
		int splitIndex = input.indexOf(splitString);

		// If the split string is found, extract the substring before that index
		if (splitIndex != -1) {
			return input.substring(0, splitIndex).trim();
		}

		// If the split string is not found, return the original string
		return input.trim();
	}

	public String extractBetween(String input, String startDelimiter, String endDelimiter) {
		// Find the index of the start delimiter
		int startIndex = input.indexOf(startDelimiter);

		// Find the index of the end delimiter
		int endIndex = input.indexOf(endDelimiter);

		// If both delimiters are found and the start comes before the end, extract the
		// substring between them
		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
			return input.substring(startIndex + startDelimiter.length(), endIndex).trim();
		}

		// If the delimiters are not found or the order is incorrect, return an empty
		// string or handle accordingly
		return "";
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element = getDriver().findElements(locator);
		} catch (Exception e) {
			System.out.println("some exception occurred while creating the webelement : " + locator);
		}
		return element;
	}

	public void clear(WebElement e, String msg) throws InterruptedException {
		waitForVisibility(e);
		TestUtils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.clear();
	}

	public boolean isDisplayedWithoutWaits(WebElement e) {
		return e.isDisplayed();
	}

	// Android actions

	public WebElement scrollToElementByDescription(String description) throws InterruptedException {
		WebElement scroll = getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().description(\"" + description + "\"));"));
		waitForVisibility(scroll);
		return scroll;
	}

	public WebElement scrollToElement() throws InterruptedException {
		WebElement scroll = getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Identification First Screen Phone Num Title LinearLayout\"));"));
		waitForVisibility(scroll);
		return scroll;

	}

	// Mobile gestures methods

	public void longPressAction(WebElement e) {
		((JavascriptExecutor) getDriver()).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) e).getId(), "duration", 2000));

	}

	public void swipeAction(WebElement e, String direction) {
		((JavascriptExecutor) getDriver()).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) e).getId(),

						"direction", direction, "percent", 0.75));

	}

	/*
	 * public void swipe(int startX, int startY, int endX, int endY, int millis)
	 * throws InterruptedException { TouchAction t = new TouchAction(driver);
	 * t.press(point(startX,
	 * startY)).waitAction(waitOptions(ofMillis(millis))).moveTo(point(endX,
	 * endY)).release() .perform(); }
	 */
	public void dragAndDropElement(WebElement source, WebElement target) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("mobile: dragGesture", ImmutableMap.of("elementId", ((RemoteWebElement) source).getId(),
				"endElementId", ((RemoteWebElement) target).getId(), "duration", 1.0 // Duration in seconds
		));
	}

	public String getText(WebElement e, String msg) {
		String txt = null;
		waitForVisibility(e);
		txt = getAttribute(e, "text");
		TestUtils.log().info(msg + txt);
		ExtentReport.getTest().log(Status.INFO, msg + txt);
		return txt;
	}

	// Close the currently running app
	/*
	 * public void closeApp() { ((InteractsWithApps)
	 * getDriver()).terminateApp(getProps().getProperty("androidAppPackage")); }
	 */
	/**
	 * Redesigned closeApp() for Samsung API 36 compatibility.
	 *
	 * Tier 1: terminateApp() — graceful stop via Appium (may throw on API 36) Tier
	 * 2: ADB force-stop — forceful kill via shell if Tier 1 times out Tier 3: pm
	 * clear — last resort if process is still alive after force-stop
	 *
	 * Never throws to the caller — failures are logged as warnings only, so a close
	 * failure never cascades into a test failure in @AfterMethod.
	 */
	public void closeApp() {
		String appPackage = getProps().getProperty("androidAppPackage");

		// ── Tier 1: Graceful Appium terminateApp ────────────────────────────────
		try {
			((InteractsWithApps) getDriver()).terminateApp(appPackage);
			TestUtils.log().info("[closeApp] App terminated gracefully: " + appPackage);
			return; // success — exit early
		} catch (WebDriverException e) {
			// Samsung API 36 throws "still running after 500ms timeout"
			// This is expected — fall through to Tier 2
			TestUtils.log().warn("[closeApp] terminateApp timed out (API 36 known issue) "
					+ "— escalating to force-stop. Reason: " + e.getMessage());
		} catch (Exception e) {
			TestUtils.log().warn("[closeApp] terminateApp failed unexpectedly " + "— escalating to force-stop. Reason: "
					+ e.getMessage());
		}

		// ── Tier 2: ADB force-stop ───────────────────────────────────────────────
		// am force-stop is immediate and does not wait for graceful shutdown.
		// This is the correct fix for Samsung API 36.
		try {
			getDriver().executeScript("mobile: shell",
					ImmutableMap.of("command", "am", "args", Arrays.asList("force-stop", appPackage)));
			TestUtils.log().info("[closeApp] App force-stopped via ADB: " + appPackage);

			// Brief wait — give the OS time to release the process slot
			Thread.sleep(800);
			return; // success — exit early

		} catch (Exception e) {
			TestUtils.log()
					.warn("[closeApp] ADB force-stop failed " + "— escalating to killall. Reason: " + e.getMessage());
		}

		// ── Tier 3: killall via shell ────────────────────────────────────────────
		// Last resort — sends SIGKILL directly to the process.
		// Used when both terminateApp and force-stop fail (extremely rare).
		try {
			getDriver().executeScript("mobile: shell",
					ImmutableMap.of("command", "killall", "args", Arrays.asList("-9", appPackage)));
			TestUtils.log().warn("[closeApp] App killed via killall -9: " + appPackage);
			Thread.sleep(500);

		} catch (Exception e) {
			// Log only — never throw from closeApp.
			// A failure to close the app must not cause @AfterMethod to fail
			// and mask the actual test result.
			TestUtils.log().warn("[closeApp] All close attempts failed for: " + appPackage + " — " + e.getMessage());
		}
	}

	// Launch the app using the specified activity
	public void launchApp() {
		/*
		 * getDriver().executeScript("mobile: shell", ImmutableMap.of("command",
		 * "am start", "args", Arrays.asList("-n",
		 * getProps().getProperty("androidAppPackage") + "/" +
		 * getProps().getProperty("androidAppActivity"))));
		 */
		String appPackage = getProps().getProperty("androidAppPackage");
		((InteractsWithApps) getDriver()).activateApp(appPackage);
	}

	// Reset the app to its initial state

	/*
	 * public void resetApp() {
	 * 
	 * getDriver().executeScript("mobile: shell", ImmutableMap.of("command",
	 * "pm clear", "args",
	 * Collections.singletonList(getProps().getProperty("androidAppPackage"))));
	 * String appPackage = getProps().getProperty("androidAppPackage");
	 * 
	 * InteractsWithApps appDriver = (InteractsWithApps) getDriver();
	 * 
	 * // Stop the app appDriver.terminateApp(appPackage);
	 * 
	 * // Optional small wait for stability try { Thread.sleep(1000); } catch
	 * (InterruptedException e) { Thread.currentThread().interrupt(); }
	 * 
	 * // Start the app cleanly appDriver.activateApp(appPackage); }
	 */
	public void resetApp() {
		String appPackage = getProps().getProperty("androidAppPackage");
		try {
			getDriver().executeScript("mobile: shell",
					ImmutableMap.of("command", "pm clear", "args", Collections.singletonList(appPackage)));
			TestUtils.log().info("App data cleared for: " + appPackage);
		} catch (Exception e) {
			TestUtils.log().warn("pm clear failed: " + e.getMessage());
		}

		// pm clear wipes all granted permissions — re-grant them so permission dialogs
		// never appear during the next test's execution flow.
		String[] runtimePermissions = {
			"android.permission.ACCESS_FINE_LOCATION",
			"android.permission.ACCESS_COARSE_LOCATION",
			"android.permission.CAMERA",
			"android.permission.READ_CONTACTS",
			"android.permission.WRITE_CONTACTS",
			"android.permission.CALL_PHONE",
			"android.permission.RECORD_AUDIO",
			"android.permission.POST_NOTIFICATIONS"
		};
		for (String permission : runtimePermissions) {
			try {
				getDriver().executeScript("mobile: shell",
					ImmutableMap.of("command", "pm",
						"args", Arrays.asList("grant", appPackage, permission)));
			} catch (Exception e) {
				TestUtils.log().warn("pm grant failed for " + permission + ": " + e.getMessage());
			}
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}

		((InteractsWithApps) getDriver()).activateApp(appPackage);

		// Critical: block until the app is actually in the foreground.
		boolean ready = SessionHealthManager.waitForAppInForeground(getDriver(), appPackage, 30);

		if (!ready) {
			throw new RuntimeException(
					"App did not reach foreground within 30s after reset. " + "Package: " + appPackage);
		}
		TestUtils.log().info("App is in foreground and ready: " + appPackage);
	}

	public void resetApp1() {
		try {
			TestUtils.log().info("Starting FULL APP RESET (Session Restart)");

			AppiumDriver oldDriver = getDriver();

			if (oldDriver != null) {
				oldDriver.quit();
				TestUtils.log().info("Old driver session quit successfully.");
			}

			// Recreate driver using same parameters
			String platformName = getPlatform();
			String deviceName = getDeviceName();
			String udid = oldDriver.getCapabilities().getCapability("udid").toString();

			UiAutomator2Options options = new UiAutomator2Options();

			options.setCapability("platformName", platformName);
			options.setCapability("deviceName", deviceName);
			options.setCapability("udid", udid);

			options.setCapability("automationName", getProps().getProperty("androidAutomationName"));
			options.setCapability("appPackage", getProps().getProperty("androidAppPackage"));
			options.setCapability("appActivity", getProps().getProperty("androidAppActivity"));
			// options.setCapability("autoGrantPermissions", true);
			options.setCapability("autoDismissAlerts", true);

			// 🔥 IMPORTANT FOR FULL RESET
			options.setCapability("noReset", false);
			options.setCapability("fullReset", true);

			String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "app" + File.separator + "ida.apk";

			options.setCapability("app", androidAppUrl);

			URI uri = new URI(getProps().getProperty("appiumURL"));
			URL url = uri.toURL();

			AppiumDriver newDriver = new AndroidDriver(url, options);
			newDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

			setDriver(newDriver);

			TestUtils.log().info("New driver session started successfully with full reset.");

		} catch (Exception e) {
			TestUtils.log().error("Full reset failed: " + e.getMessage());
			throw new RuntimeException("Full reset failed", e);
		}
	}

	// Launch the device's camera app
	public void launchCamera() {
		((JavascriptExecutor) getDriver()).executeScript("mobile:startActivity",
				ImmutableMap.of("intent", "com.android.camera2" + "/" + "com.android.camera.CameraLauncher"));
	}

	// Activate the Intelehealth app
	public void activateIntelehealth() {
		((InteractsWithApps) getDriver()).activateApp(getProps().getProperty("androidAppPackage"));
	}

	public WebElement scrollToTextContains_Android(String text) {
		WebElement scroll = getDriver()
				.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));
		waitForVisibility(scroll);
		return scroll;
	}

	public static String encrypt(String password) {
		try {
			// Generate a fixed-size key based on the password
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = Arrays.copyOf(sha.digest(SECRET_KEY.getBytes()), 16);

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedBytes = cipher.doFinal(password.getBytes());
			return java.util.Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String encryptedPassword) {
		try {
			// Generate a fixed-size key based on the password
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = Arrays.copyOf(sha.digest(SECRET_KEY.getBytes()), 16);

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedBytes = cipher.doFinal(java.util.Base64.getDecoder().decode(encryptedPassword));
			return new String(decryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void pressEnter(WebElement element) {
		element.click(); // Ensure the element is focused
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	}

	public boolean isDisplayed2(By recentVisitPatients) {
		int maxAttempts = 4;

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				WebElement element = getElement(recentVisitPatients);

				// Check if the element is not null before proceeding
				if (element != null) {
					WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

					// Wrap in try-catch to handle TimeoutException
					try {
						wait.until(ExpectedConditions.visibilityOf(element));
					} catch (TimeoutException timeoutEx) {
						System.out.println(
								"TimeoutException caught. Element is not visible. Retrying attempt " + attempt);
						continue; // Skip to the next attempt
					}

					// Check if the element is displayed
					if (element.isDisplayed()) {
						return true;
					}
				} else {
					System.out.println("Retrying attempt " + attempt);
				}
			} catch (StaleElementReferenceException ex) {
				System.out.println("StaleElementReferenceException caught. Retrying attempt " + attempt);
				// Add a small delay before retrying (customize based on your needs)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		return false; // Return false if the element is not displayed after max attempts
	}

	public boolean isDisplayedListofWebelemets(List<WebElement> webElements) {
		List<WebElement> elements = webElements;
		for (WebElement element : elements) {
			try {
				if (element != null && element.isDisplayed()) {
					return true;
				}
			} catch (Exception e1) {

			}
		}
		return false;
	}

	public List<String> getElementsText(WebElement... elements) {
		return List.of(elements).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public WebElement scrollToElementUsingItsText(String text) {

		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ text + "\").instance(0))"));

	}

	@SuppressWarnings("unused")
	protected void toggleMobileData(boolean enable) {
		try {
			AppiumDriver driverInstance = driver.get();
			if (driverInstance instanceof JavascriptExecutor) {
				((JavascriptExecutor) driverInstance).executeScript("mobile: shell",
						ImmutableMap.of("command", "svc", "args", enable ? "data enable" : "data disable"));
				TestUtils.log().info("Mobile data " + (enable ? "enabled" : "disabled"));
			} else {
				TestUtils.log().error("Driver does not support JavascriptExecutor");
			}
		} catch (Exception e) {
			TestUtils.log().error("Failed to toggle mobile data: " + e.toString());
		}

	}

	@SuppressWarnings("unused")
	protected void toggleWiFi(boolean enable) {
		try {
			AppiumDriver driverInstance = driver.get();
			if (driverInstance instanceof JavascriptExecutor) {
				((JavascriptExecutor) driverInstance).executeScript("mobile: shell",
						ImmutableMap.of("command", "svc", "args", enable ? "wifi enable" : "wifi disable"));
				TestUtils.log().info("WiFi " + (enable ? "enabled" : "disabled"));
			} else {
				TestUtils.log().error("Driver does not support JavascriptExecutor");
			}
		} catch (Exception e) {
			TestUtils.log().error("Failed to toggle WiFi: " + e.toString());
		}
	}

	public void clickUsingJavaScriptExecutor(WebElement element) {
		AppiumDriver driverInstance = driver.get();

		if (driverInstance instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}

		// click(arrow, "Clicked on the 'Arrow' for navigation");
	}

	public void tapElement(WebElement element) throws InterruptedException {

		Thread.sleep(2000);
		AppiumDriver driverInstance = driver.get();
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
						element.getLocation().getX(), element.getLocation().getY()))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driverInstance.perform(Arrays.asList(tap));

	}

	public static double calculateBMI(double weight, double heightCm) {
		double heightMeters = heightCm / 100; // Convert height from cm to meters
		return weight / (heightMeters * heightMeters);
	}

	public static String formatToTwoDecimalPlaces(double value) {
		return String.format("%.2f", value);
	}

	public void scrollDown() {
		getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
	}

	public boolean areDoublesEqual(double d1, double d2) {
		double epsilon = 1e-9; // Tolerance level
		return Math.abs(d1 - d2) < epsilon;
	}

	public void scrollUp() {
		getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"));
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		if (getDriver() != null) {
			getDriver().quit();

		}
	}

	/**
	 * GOing forward we will be using these methods for visit creation as CHW,
	 * starting the visit as doctor, sign and submit
	 */

	/**
	 * API Authorization methods
	 * 
	 */

	public static RequestSpecification buildRequestWithNurseAuthorization(/* Map<String, Object> body */) {
		return RestAssured.given().header("authorization", "Basic c3Jpbml2YXNuOk51cnNlQDEyMw==").
		// basic("nurse1", "Nurse@123")
				contentType(ContentType.JSON);
		// .body(body); // Set content type to JSON

	}

	public static RequestSpecification buildRequestWithDoctorAuthorization(/* Map<String, Object> body */) {
		return RestAssured.given().header("authorization", "Basic c2hhaWxhamFkOkRvY3RvckAxMjM0").
		// basic("nurse1", "Nurse@123")
				contentType(ContentType.JSON);
		// .body(body); // Set content type to JSON

	}

	public boolean isAppOpened(String expectedPackage) {

		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(15));

		try {

			return wait.until(d -> ((AndroidDriver) driver.get()).getCurrentPackage().contains(expectedPackage));

		} catch (Exception e) {
			return false;
		}
	}

	public void waitFor(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @interruption based scenario helper methods for ScreenOrientation
	 * @param expectedOrientation
	 * @return
	 */
	public ScreenOrientation rotateAndGetOrientation(ScreenOrientation expectedOrientation) {

		SupportsRotation rotation = (SupportsRotation) getDriver();
		rotation.rotate(expectedOrientation);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return rotation.getOrientation();
	}

	public List<String> verifyLayoutAfterRotation(ScreenOrientation expectedOrientation, By... criticalLocators) {
		List<String> issues = new ArrayList<>();

		// Rotate (use SupportsRotation)
		((SupportsRotation) getDriver()).rotate(expectedOrientation);

		// small stabilization wait
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignored) {
		}

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

		// get screen size
		Dimension screenSize = getDriver().manage().window().getSize();
		int screenWidth = screenSize.getWidth();
		int screenHeight = screenSize.getHeight();

		List<Rectangle> elementRects = new ArrayList<>();

		for (By locator : criticalLocators) {
			try {
				WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

				if (!el.isDisplayed()) {
					issues.add(locator.toString() + " is not displayed after rotation");
					continue;
				}

				Rectangle rect = el.getRect(); // x,y,width,height

				// Check element inside screen bounds
				int left = rect.getX();
				int top = rect.getY();
				int right = rect.getX() + rect.getWidth();
				int bottom = rect.getY() + rect.getHeight();

				if (left < 0 || top < 0) {
					issues.add(locator.toString() + " is positioned with negative coordinates (x:" + left + ", y:" + top
							+ ")");
				}
				if (right > screenWidth) {
					issues.add(locator.toString() + " extends beyond screen width (right:" + right + " > screenWidth:"
							+ screenWidth + ")");
				}
				if (bottom > screenHeight) {
					issues.add(locator.toString() + " extends beyond screen height (bottom:" + bottom
							+ " > screenHeight:" + screenHeight + ")");
				}

				// Optional: check minimal size (avoid overly tiny controls)
				if (rect.getWidth() < 20 || rect.getHeight() < 20) {
					issues.add(locator.toString() + " appears too small (w:" + rect.getWidth() + ", h:"
							+ rect.getHeight() + ")");
				}

				elementRects.add(rect);

			} catch (Exception e) {
				issues.add(locator.toString() + " not found / visible after rotation. Exception: " + e.getMessage());
			}
		}

		// Check pairwise overlap (simple rectangle intersection)
		for (int i = 0; i < elementRects.size(); i++) {
			for (int j = i + 1; j < elementRects.size(); j++) {
				if (rectanglesOverlap(elementRects.get(i), elementRects.get(j))) {
					issues.add("Overlap detected between element index " + i + " and " + j);
				}
			}
		}

		return issues;
	}

	private boolean rectanglesOverlap(Rectangle r1, Rectangle r2) {
		int left1 = r1.getX();
		int top1 = r1.getY();
		int right1 = r1.getX() + r1.getWidth();
		int bottom1 = r1.getY() + r1.getHeight();

		int left2 = r2.getX();
		int top2 = r2.getY();
		int right2 = r2.getX() + r2.getWidth();
		int bottom2 = r2.getY() + r2.getHeight();

		// If one rectangle is on left side of other
		if (right1 <= left2 || right2 <= left1)
			return false;
		// If one rectangle is above other
		if (bottom1 <= top2 || bottom2 <= top1)
			return false;

		// otherwise they overlap
		return true;
	}

	/**
	 * This is for enabling/ disabling mobile data and wifi
	 */
	private Map<String, Object> runShellCmd(String command, List<String> args) {
		Map<String, Object> params = new HashMap<>();
		params.put("command", command);
		if (args != null && !args.isEmpty())
			params.put("args", args);
		// optional: "includeStderr", "timeout" etc.
		Object res = getDriver().executeScript("mobile: shell", params);
		if (res instanceof Map)
			return (Map<String, Object>) res;
		return Collections.emptyMap();
	}

	public void disableWifi() {
		runShellCmd("svc", Arrays.asList("wifi", "disable"));
	}

	public void enableWifi() {
		runShellCmd("svc", Arrays.asList("wifi", "enable"));
	}

	public void disableMobileData() {
		// may fail on some devices (see caveats)
		runShellCmd("svc", Arrays.asList("data", "disable"));
	}

	public void enableMobileData() {
		runShellCmd("svc", Arrays.asList("data", "enable"));
	}

	/**
	 * These are for switching the apps in between
	 * 
	 */
	public void switchToAnotherApp(String packageName) {
		((InteractsWithApps) getDriver()).activateApp(packageName);
	}

	public void switchAppToBackground(int seconds) {
		((InteractsWithApps) getDriver()).runAppInBackground(Duration.ofSeconds(seconds));
	}

	/**
	 * @LOCKING AND UNLOCKING THE MOBILE
	 */

	public void lockDevice() {
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.POWER));

		// ((AndroidDriver) getDriver()).lockDevice(Duration.ofSeconds(seconds));
	}

	public void unlockDevice() {
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.POWER));
		swipeUp();
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
		((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));

		// ((AndroidDriver) getDriver()).unlockDevice();
	}

	public void swipeUp() {

		Dimension size = getDriver().manage().window().getSize();

		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));

		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), startX, endY));

		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		getDriver().perform(Collections.singletonList(swipe));
	}

	/**
	 * toggleDND(true); // Turn ON DND toggleDND(false); // Turn OFF DND
	 * 
	 * @param enable
	 */

	public void toggleDND(boolean enable) {

		try {
			if (enable) {
				Runtime.getRuntime().exec("adb shell settings put global zen_mode 2");
			} else {
				Runtime.getRuntime().exec("adb shell settings put global zen_mode 0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BaseTest.java

	/**
	 * Checks whether an Android application package is currently installed on the
	 * device. Use this to gate behaviour before tapping icons that depend on
	 * third-party apps.
	 *
	 * @param packageName e.g. "com.whatsapp"
	 * @return true if the package is installed
	 */

	/**
	 * Returns the package name of the currently active foreground application. Used
	 * to determine which app the OS navigated to after an intent tap.
	 */
	protected String getCurrentActivePackage() {
		return ((AndroidDriver) driver.get()).getCurrentPackage();
	}

	/**
	 * Polls for an element's visibility without throwing NoSuchElementException.
	 * Safe to use as a conditional check, not an assertion.
	 *
	 * @param element        the WebElement to wait for
	 * @param timeoutSeconds maximum wait time
	 * @return true if the element becomes visible within the timeout
	 */
	protected boolean isElementPresentWithTimeout(WebElement element, int timeoutSeconds) {
		try {
			new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutSeconds))
					.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns to the application under test from any third-party app or browser
	 * that was opened as a result of an intent tap (WhatsApp, Call, Maps, etc.).
	 *
	 * @param appPackage the main app package, e.g. "org.intelehealth.ekalarogya"
	 */
	protected void returnToApp(String appPackage) {
		((AndroidDriver) driver.get()).activateApp(appPackage);
		ExtentReport.getTest().log(Status.INFO, "Returned to app: " + appPackage);
	}

}
