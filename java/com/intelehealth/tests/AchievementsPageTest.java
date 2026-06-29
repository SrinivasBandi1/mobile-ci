package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AboutUsPage;
import com.intelehealth.pages.AchievementsPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.CallLogsPage;
import com.intelehealth.pages.EditProfilePage;
import com.intelehealth.pages.ForgotPasswordPage;
import com.intelehealth.pages.HelpPage;
import com.intelehealth.pages.HomePage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.pages.SplashPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class AchievementsPageTest extends BaseTest {
	AppSetupPage appSetupPage;
	LoginMenuPage loginMenuPage;
	EditProfilePage editProfilePage;
	ForgotPasswordPage forgotPasswordPage;
	AchievementsPage achievementsPage;
	CallLogsPage callLogsPage;
	HomePage homePage;
	HelpPage helpPage;
	AboutUsPage aboutUsPage;
	SplashPage splashPage;
	JSONObject appData;

	@BeforeMethod
	public void setUp(Method m) throws Exception {

		TestUtils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
	//	resetApp();
	//	launchApp();
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	    //clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		loginMenuPage = new LoginMenuPage();
		editProfilePage = new EditProfilePage();
		forgotPasswordPage = new ForgotPasswordPage();
		achievementsPage = new AchievementsPage();
		homePage = new HomePage();
		helpPage = new HelpPage();
		aboutUsPage = new AboutUsPage();
		callLogsPage = new CallLogsPage();
		splashPage = new SplashPage();
		InputStream datais = null;
		try {
			String dataFileName = "data/appData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			appData = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
	//	toggleDND(true);

		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		// appSetupPage.refreshUIAndWait();
		// Click on the hamburger menu in the login menu page
		 loginMenuPage.clickOnHamburgerMenu();
	}

	/**
	 * @AchievementsPage
	 */
	@Test(priority = 1, description = "Verify End Date can be selected from calendar", enabled = true)
	public void testVerifyUIElements() {
//call login page 
		homePage.clickAchievementsTab();

		String headerText = achievementsPage.getAchievementsHeaderText();
		String levelText = achievementsPage.getAchievementsLevelText();
		Assert.assertEquals(headerText, expectedAssertProp.getProperty("achievements.screen.header.text"));
		Assert.assertEquals(levelText, expectedAssertProp.getProperty("achievements.screen.level.text"));
	}

	@DataProvider(name = "reportTabs")
	public Object[][] reportTabs() {

		return new Object[][] { { "Overall" }, { "Daily" }, { "Date Range" } };
	}

	@Test(priority = 2, description = "Verify End Date can be selected from calendar", dataProvider = "reportTabs", enabled = true)
	public void verifyReportTabs(String tabName) {
		homePage.clickAchievementsTab();
		boolean isSelected = achievementsPage.clickTabAndVerifySelected(tabName);

		Assert.assertTrue(isSelected, tabName + " tab is NOT selected");

		System.out.println(tabName + " tab verified successfully");
	}

	@Test(priority = 3, description = "Verify End Date can be selected from calendar", enabled = true)
	public void testSelectDateFromCalendar() {
		homePage.clickAchievementsTab();
		achievementsPage.clickDateRangeTab();
		achievementsPage.clickStartDate();
		achievementsPage.selectDateFromCalendar();
		achievementsPage.clickEndDate();
		achievementsPage.selectDateFromCalendar();
	}

	/**
	 * @CallLogs
	 */
	@Test(priority = 4, description = "Verify End Date can be selected from calendar", enabled = true)
	public void testCallLogs() {
		homePage.clickHamburgerMenu();
		callLogsPage.clickOnCallLogsMenuItem();
		String callLogsHeaderText = callLogsPage.getCallLogsHeaderText();
		Assert.assertEquals(callLogsHeaderText, expectedAssertProp.getProperty("call.logs.screen.header.text"),
				"Call Logs header should be displayed");

	}

	/**
	 * @HelpPage
	 */
	@Test(priority = 5, description = "Verify Home page labels and icons are displayed", enabled = true)
	public void testHelpPageUIElements() {
		homePage.clickHelpTab();
		String helpHeaderText = helpPage.getHelpHeaderText();
		Assert.assertEquals(helpHeaderText, expectedAssertProp.getProperty("help.screen.header.text"),
				" should be displayed");
		boolean areElementsDisplayed = helpPage.isYoutubeVideoIconAndFrequentlyAskedQuestionsDisplayed();
		Assert.assertTrue(areElementsDisplayed,
				"YouTube video icon and Frequently Asked Questions label should be displayed");
	}

	/**
	 * @AboutUsPage
	 */

	@Test(priority = 6, description = "Verify Help page labels and icons are displayed", enabled = true)
	public void testAboutUsPageUIElements() {
		homePage.clickHamburgerMenu();
		homePage.clickAboutUsInHamburgerMenu();
		String aboutUsHeaderText = aboutUsPage.getAboutUsHeaderText();
		Assert.assertEquals(aboutUsHeaderText, expectedAssertProp.getProperty("about.us.screen.header.text"),
				" should be displayed");
		boolean areElementsDisplayed = aboutUsPage.isAboutUsContentDisplayed();
		Assert.assertTrue(areElementsDisplayed, "Company logo and Contact Information should be displayed");
	}

	@Test(priority = 7, description = "Verify whether user can able to click on terms and conditions link in about us page", enabled = true)
	public void testTermsAndConditionsLink() {
		homePage.clickHamburgerMenu();
		homePage.clickAboutUsInHamburgerMenu();
		aboutUsPage.clickOnTermsAndConditionsLink();
		driver.get().navigate().back();
		Assert.assertEquals(aboutUsPage.getAboutUsHeaderText(),
				expectedAssertProp.getProperty("about.us.screen.header.text"), " should be displayed");
	}

	@Test(priority = 8, description = "Verify whether user can able to click on Go to website link in about us page", enabled = true)
	public void testGoToWebsiteLink() {
		homePage.clickHamburgerMenu();
		homePage.clickAboutUsInHamburgerMenu();
		aboutUsPage.clickOnGoToWebsiteButton();
		driver.get().navigate().back();
		Assert.assertEquals(aboutUsPage.getAboutUsHeaderText(),
				expectedAssertProp.getProperty("about.us.screen.header.text"), " should be displayed");
	}

	/**
	 * @HomePage
	 */
	@Test(priority = 9, description = "Verify Home page labels and icons are displayed", enabled = true)
	public void testHomePageUIElements() {
		boolean areElementsDisplayed = homePage.isDisplayedHomePageIcons();
		Assert.assertTrue(areElementsDisplayed, "All expected icons and labels should be displayed on the Home page");
		Assert.assertEquals(homePage.getHomePageUI(),
				Arrays.asList(expectedAssertProp.getProperty("home.screen.search.patient.placeholder"),
						expectedAssertProp.getProperty("home.screen.prescriptions.label"),
						expectedAssertProp.getProperty("home.screen.open.visit.label"),
						expectedAssertProp.getProperty("home.screen.appointments.label"),
						expectedAssertProp.getProperty("home.screen.followup.visits.label")));
	}

	/**
	 * @SplashPage
	 */
	@Test(priority = 10, description = "Verify Splash page labels and icons are displayed", enabled = true)
	public void testSplashPageUIElements() {
		Assert.assertTrue(splashPage.isLogoDisplayed(), "Intelehealth logo should be displayed on the Splash page");
		String actualWelcomeText = splashPage.isWelcomeTextDisplayed();
		String expectedUsername = appData.getJSONObject("validUser").getString("username");
		String expectedPattern = "Welcome!\\s*" + expectedUsername;
		Assert.assertTrue(actualWelcomeText.matches(expectedPattern),
				"Welcome text should be displayed on the Splash page. Expected pattern: " + expectedPattern
						+ " but found: " + actualWelcomeText);
		/*
		 * Assert.assertEquals(splashPage.isWelcomeTextDisplayed(), expectedPattern,
		 * "Welcome text should be displayed on the Splash page");
		 */

		Assert.assertEquals(splashPage.isLoadingContentDescriptionDisplayed(),
				expectedAssertProp.getProperty("splash.screen.loading.content.description"),
				"Loading content description should be displayed on the Splash page");

		Assert.assertTrue(splashPage.isLoadingProgressPercentageDisplayed(),
				"Loading progress percentage should be displayed on the Splash page");

		Assert.assertEquals(splashPage.getSyncingText(), expectedAssertProp.getProperty("splash.screen.syncing.text"),
				"Syncing text should be displayed on the Splash page");
	}

//	@Test(priority = 11, description = "Verify app behavior under interrupted scenarios", enabled = true)
	public void testInterruptedBasedScenario() {

		lockDevice();
		unlockDevice();

		closeApp();
		switchToAnotherApp(getProps().getProperty("androidAppPackage"));

		switchAppToBackground(10);
		switchToAnotherApp("com.whatsapp");
		switchToAnotherApp(getProps().getProperty("androidAppPackage"));

		enableMobileData();
		disableMobileData();
		enableWifi();
		disableWifi();
		enableWifi();

		List<String> issues = verifyLayoutAfterRotation(ScreenOrientation.LANDSCAPE,
				By.id("org.intelehealth.app:id/tv_user_location_home")

		);

		// Assert no issues
		Assert.assertTrue(issues.isEmpty(), "Layout issues after rotation: " + String.join("; ", issues));
		ScreenOrientation actualOrientation = rotateAndGetOrientation(ScreenOrientation.LANDSCAPE);

		Assert.assertEquals(actualOrientation, ScreenOrientation.LANDSCAPE, "Orientation did not change to LANDSCAPE");

		ScreenOrientation actualOrientationPORTRAIT = rotateAndGetOrientation(ScreenOrientation.PORTRAIT);

		Assert.assertEquals(actualOrientationPORTRAIT, ScreenOrientation.PORTRAIT,
				"Orientation did not change to LANDSCAPE");
	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}

}