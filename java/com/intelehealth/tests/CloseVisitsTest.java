package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.CloseVisitsPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class CloseVisitsTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	CloseVisitsPage closeVisitsPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		TestUtils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
//		resetApp();
//		launchApp();
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	//    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		closeVisitsPage = new CloseVisitsPage();
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
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		appSetupPage.refreshUIAndWait();
	}

	@Test(priority = 1, description = "Verify the functionality of Close visit button under Recent visit/Older Visits section", enabled = true)
	public void IDA4_2239_verifyCloseVisitButtonFunctionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2239_verifyCloseVisitButtonFunctionality",
				"Verify the functionality of Close visit button under Recent visit/Older Visits section");

		Assert.assertTrue(closeVisitsPage.verifyCloseVisitButtonFunctionality());
		Assert.assertEquals(closeVisitsPage.getCloseVisitDialogText(),
				Arrays.asList(expectedAssertProp.getProperty("close.visit.dialog.title"),
						expectedAssertProp.getProperty("close.visit.dialog.sub.title"),
						expectedAssertProp.getProperty("patient.registration.dialog.cancel"),
						expectedAssertProp.getProperty("close.visit.dialog.confirm")));
	}

	@Test(priority = 2, description = "Verify clicking on Confirm button", enabled = true)
	public void IDA4_2241_verifyConfirmButton() throws InterruptedException {
		ExtentReport.startTest("IDA4_2241_verifyConfirmButton", "Verify clicking on Confirm button");

		closeVisitsPage.verifyConfirmButtonFunctionality();
		Assert.assertEquals(closeVisitsPage.getFeedbackTitle(),
				expectedAssertProp.getProperty("feedback.screen.title"));
	}

	@Test(priority = 3, description = "Verify entering text in Give feedback area and click on submit button", enabled = true)
	public void IDA4_2245_enterTextInFeedbackAreaAndSubmit() throws Exception {
		ExtentReport.startTest("IDA4_2245_enterTextInFeedbackAreaAndSubmit",
				"Verify entering text in Give feedback area and click on submit button");

		closeVisitsPage.enterFeedbackAndverify();

		Assert.assertEquals(closeVisitsPage.getThankYouForYourFeedbackMessage(),
				expectedAssertProp.getProperty("feedback.screen.message"));
		Assert.assertTrue(appSetupPage.locationIsDisplayed());
	}

	@Test(priority = 4, description = "Verify whether user able to close visits of both Recent and Older visits section", enabled = true)
	public void IDA4_2249_verifyUserAbleToCloseVisitsFromRecentAndOlderSection() throws Exception {
		ExtentReport.startTest("IDA4_2249_verifyUserAbleToCloseVisitsFromRecentAndOlderSection",
				"Verify whether user able to close visits of both Recent and Older visits section");

		Assert.assertTrue(closeVisitsPage.closeVisitsInRecentAndOlderVisitsSection());
		Assert.assertEquals(closeVisitsPage.getThankYouForYourFeedbackMessage(),
				expectedAssertProp.getProperty("feedback.screen.message"));
		Assert.assertTrue(appSetupPage.locationIsDisplayed());
	}

	/**
	 * after start visit
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 5, description = "Verify the functionality of Recent Visits section", enabled = true)
	public void IDA4_2236_verifyRecentVisitsSection() throws InterruptedException {
		ExtentReport.startTest("IDA4_2236_verifyRecentVisitsSection",
				"Verify the functionality of Recent Visits section");


		Assert.assertTrue(closeVisitsPage.verifyRecentVisitsSection());

		Assert.assertEquals(closeVisitsPage.getRecentVisitTitle(),
				Arrays.asList(expectedAssertProp.getProperty("close.visit.screen.recent.visit.title"),
						expectedAssertProp.getProperty("close.visit.screen.recent.visit.close.button")));
	}

	@Test(priority = 6, description = "Verify the count once user closed the visit", enabled = true)
	public void IDA4_2247_verifyClosedCountIsDecreased() throws InterruptedException {
		ExtentReport.startTest("IDA4_2247_verifyClosedCountIsDecreased", "Verify the count once user closed the visit");

		Thread.sleep(2000);
		Assert.assertTrue(closeVisitsPage.verifyCloseVisitsCountIsDecreased());
	}

//do scroll for 3 times 
	@Test(priority = 7, description = "Verify whether above 7 days visited Patient's are only reflecting under Older Visits section", enabled = true)
	public void IDA4_2238_verifyAboveSevenDaysPatientsAreDisplayedInOlderVisits()
			throws InterruptedException, ParseException {
		ExtentReport.startTest("IDA4_2238_verifyAboveSevenDaysPatientsAreDisplayedInOlderVisits",
				"Verify whether above 7 days visited Patient's are only reflecting under Older Visits section");
		Assert.assertTrue(closeVisitsPage.checkAllElementsForOldDates());
	}

	/**
	 * 
	 * need to check after completion of start visit
	 * 
	 */
	@Test(priority = 8, description = "Verify whether last 7 days visited Patient's are only reflecting under Recent Visits section", enabled = true)
	public void IDA4_2237_verifyAboveLastSevenDaysPatientsAreDisplayedInRecentVisits() throws InterruptedException {
		ExtentReport.startTest("IDA4_2237_verifyAboveLastSevenDaysPatientsAreDisplayedInRecentVisits",
				"Verify whether last 7 days visited Patient's are only reflecting under Recent Visits section");

		closeVisitsPage.verifyLastSevenDaysPatientsAreDisplayed();

	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}

}