package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.FollowUpVisitPage;
import com.intelehealth.pages.HomePage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class FollowupVisitsTest extends BaseTest {
	FollowUpVisitPage followUpVisitPage;
	HomePage homePage;
	AppSetupPage appSetupPage;
	JSONObject appData;

	@BeforeMethod
	public void setUp(Method m) throws Exception {
		TestUtils.log().info("\n****** starting test: " + m.getName() + " ******\n");
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	 //   clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		homePage = new HomePage();
		followUpVisitPage = new FollowUpVisitPage();
		appSetupPage = new AppSetupPage();

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
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		appSetupPage.refreshUIAndWait();

	}

	@Test(priority = 1, description = "Verify Follow Up Visit title is displayed")
	public void testGetFollowUpVisitLabels() {
		homePage.clickFollowUpVisitsTab();

		Assert.assertEquals(followUpVisitPage.getFollowUpVisitDetails(),
				Arrays.asList(expectedAssertProp.getProperty("followup.visit.today.label"),
						expectedAssertProp.getProperty("followup.visit.tomorrow.label"),
						expectedAssertProp.getProperty("followup.visit.others.label")));

		Assert.assertTrue(followUpVisitPage
				.isDisplayedFollowUpVisitDetails(expectedAssertProp.getProperty("followup.visit.title")));
	}

	@Test(priority = 2, description = "verify search patient by OpenMRS ID ")
	public void testEnterSearchPatientByOpenMRSID() {
		homePage.clickFollowUpVisitsTab();
		String patientName = followUpVisitPage.getFirstPatientIDInList();
		followUpVisitPage.enterSearchPatient(patientName);
		// Add assertion for search result if possible
		Assert.assertEquals(followUpVisitPage.getFirstPatientIDInList(), patientName);
	}

	@Test(priority = 3, description = "verify search patient by Name")
	public void testEnterSearchPatientByName() {
		homePage.clickFollowUpVisitsTab();
		String patientName = followUpVisitPage.getFirstPatientNameInList();
		followUpVisitPage.enterSearchPatient(patientName);
		// Add assertion for search result if possible
		Assert.assertEquals(followUpVisitPage.getFirstPatientNameInList(), patientName);
	}

	@Test(priority = 4, description = "Verify filter icon can be clicked")
	public void testClickFilterIconUIInDateTab() {
		homePage.clickFollowUpVisitsTab();
		followUpVisitPage.clickFilterIcon();
		// Assert filter UI is displayed
		Assert.assertTrue(followUpVisitPage.isDisplayedDateFilterOptions());
	}

	@Test(priority = 5, description = "Verify filter icon can be clicked")
	public void testClickFilterIconUIInDateRangeTab() {
		homePage.clickFollowUpVisitsTab();
		followUpVisitPage.clickFilterIcon();
		// Assert filter UI is displayed
		followUpVisitPage.clickOnDateRangeFilterOption();
		Assert.assertTrue(followUpVisitPage.isDisplayedDateRangeFilterOptions());
	}

	@Test(priority = 6, description = "Verify selecting a date in the date filter updates the filter icon")
	public void testSelectDateInDateFilter() {
		homePage.clickFollowUpVisitsTab();
		followUpVisitPage.clickFilterIcon();
		followUpVisitPage.clickOnDateFilterOption();
		String selectedDate = followUpVisitPage.selectDateFromCalendar();
		followUpVisitPage.clickApplyDateInFilter();
		String dateOnCard = followUpVisitPage.getFirstPatientFollowupDateInList();
		Assert.assertTrue(followUpVisitPage.areTheVisitsDisplayedAfterApplyingDateFilter(selectedDate, dateOnCard),
				"Selected date is not displayed in the filter icon");

	}

	@Test(priority = 7, description = "Verify selecting a date range in the date range filter updates the filter icon")
	public void testSelectDateRangeInDateRangeFilter() {
		homePage.clickFollowUpVisitsTab();
		followUpVisitPage.clickFilterIcon();
		followUpVisitPage.clickOnDateRangeFilterOption();
		String selectedStartDate = followUpVisitPage.selectStartDateFromCalendar();
		String selectedEndDate = followUpVisitPage.selectEndDateFromCalendar();
		followUpVisitPage.clickApplyDateRangeInFilter();
		Assert.assertTrue(followUpVisitPage.isFollowupDateInSelectedRange(selectedStartDate, selectedEndDate));
	}

	@Test(priority = 8, description = "Verify clicking on a patient in the follow-up visit list navigates to the correct patient details screen")
	public void testClickPatientInFollowUpVisitList() {
		homePage.clickFollowUpVisitsTab();
		String patientName = followUpVisitPage.getFirstPatientNameInList();
		followUpVisitPage.clickOnFirstPatientInList();
		Assert.assertEquals(followUpVisitPage.getPatientDetailsScreenHeader(),
				expectedAssertProp.getProperty("patient.details.screen.header"),
				"Patient details screen header is incorrect");
		String patientDetailsName = followUpVisitPage.getPatientNameFromPatientDetailsScreen();
		Assert.assertEquals(patientDetailsName, patientName, "Navigated to incorrect patient details screen");
	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}
}