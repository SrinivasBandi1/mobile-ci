package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.intelehealth.api.APIServices;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.AppointmentsPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

import io.qameta.allure.Description;

public class AppointmentsTest extends BaseTest {
//	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	FindPatientPage findPatientPage;
	AppointmentsPage appointmentsPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws Throwable {
		TestUtils.log().info("\n" + "****** starting test : " + m.getName() + "******" + "\n");

	//	APIServices.createPriorityVisitUsingRestAssured(buildRequestWithNurseAuthorization());
	//	APIServices.startVisitUsingRestAssured(buildRequestWithDoctorAuthorization());
	//	APIServices.signAndSubmitUsingRestAssured(buildRequestWithDoctorAuthorization());
	//	resetApp();
	//	launchApp();
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	//    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		findPatientPage = new FindPatientPage();
		appointmentsPage = new AppointmentsPage(driver);
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
		boolean appointmentCreated = APIServices
				.createAppointmentUsingRestAssured(buildRequestWithNurseAuthorization());
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		appSetupPage.refreshUIAndWait();

	}

	@Test(priority = 1, description = "verify that the appointment details screen is displayed when clicking on 'Visit' under the upcoming section", enabled = true)
	public void IDA4_2397_verifyAppointmentDetailsScreenIsDisplayedFromUpcomingAppointments() throws Throwable {
		// Verify that the appointment details screen is displayed when clicking on
		// 'Visit' under the completed section
		Assert.assertTrue(appointmentsPage.verifyAppointmentDetailsScreenIsDisplayed());
		Assert.assertTrue(appointmentsPage.verifyAppointmentDetailsScreen());

	}

	@Test(priority = 2, description = "verify that the appointment details screen is displayed when clicking on 'Visit' under the past section", enabled = true)
	public void IDA4_2397_verifyAppointmentDetailsScreenIsDisplayedFromPastAppointments() throws Throwable {
		// Verify that the appointment details screen is displayed when clicking on
		// 'Visit' under the completed section
		Assert.assertTrue(appointmentsPage.verifyAppointmentDetailsScreenIsDisplayedFromPastAppointments());
		Assert.assertTrue(appointmentsPage.verifyAppointmentDetailsScreen());

	}

	@Test(priority = 3, description = "Verify the No button functionality in cancel appointment popup", enabled = true)
	public void IDA4_2380_verifyCancelAppointment() throws Throwable {

		Assert.assertTrue(appointmentsPage.verifyCancelButton());
	}

	@Test(priority = 4, description = "Verify the Yes button functionality in cancel appointment popup", enabled = true)
	public void IDA4_2382_verifyYesButtonFunctionality() throws Throwable {

		appointmentsPage
				.verifyYesButton(expectedAssertProp.getProperty("appointments.cancel.appointment.dialog.header"));
	}

	// call reshecdule appointment functionality

	@Test(priority = 5, description = "Verify the functionality of 'Completed' tab", enabled = true)
	public void IDA4_2391_verifyCompletedTabFunctionality() throws Throwable {

		Assert.assertTrue(appointmentsPage.verifyPastAppointmentsTab());
	}

	@Test(priority = 6, description = "Verify user can cancel the appointment by clicking save", enabled = true)
	public void IDA4_2385_verifycancelAppointmentSaveButton() throws Throwable {

		Assert.assertTrue(appointmentsPage.verifyAppointmentSaveButton());
	}

	/**
	 * need to check
	 * 
	 * @throws Throwable
	 */
	// @Test(priority = 7, description = "Verify clicking on Schedule appointment
	// CTA for a cancelled appointment", enabled = true)
	public void IDA4_2388_verifyScheduleAppointmentScreenIsDisplayed() throws Throwable {

		appointmentsPage.verifySchedulaAppointmentPageIsDisplayed();
	}

	@Test(priority = 7, description = "Verify the patient visit details in Upcoming section", enabled = true)
	public void IDA4_2352_verifyThePatientVisitDetailsInUpcomingSection() throws Throwable {
		appointmentsPage.verifyThePatientVisitDetailsInUpcomingSection();
	}

//create patient with mobile number
	@Test(priority = 8, description = "Verify clicking on call and whatsapp icon on appointment details page when number is provided", enabled = true)
	public void IDA4_2356_verifyClickingOnCallAndWhatsappIconAnAppointmentDetailsPageWhenNumberIsProvided()
			throws Throwable {
		Assert.assertTrue(
				appointmentsPage.verifyClickingOnCallAndWhatsappIconAnAppointmentDetailsPageWhenNumberIsProvided());
	}

	/**
	 * @need to check again when locators of visit summary screen gets updated
	 * @throws Throwable
	 */

	@Test(priority = 9, description = "Verify user clicking on arrow next to visit summary on appointment details page", enabled = true)
	public void IDA4_2357_verifyUserClickingOnArrowNextToVisitSummaryOnAppointmentDetailsPage() throws Throwable {
		Assert.assertTrue(appointmentsPage.verifyUserClickingOnArrowNextToVisitSummaryOnAppointmentDetailsPage());
	}

	@Test(priority = 10, description = "Verify the functionality of Reschedule in Appointment details page", enabled = true)
	public void IDA4_2359_verifyTheFunctionalityOfRescheduleInAppointmentDetailsPage() throws Throwable {
		Assert.assertTrue(appointmentsPage.verifyTheFunctionalityOfRescheduleInAppointmentDetailsPage());
	}

	@Test(priority = 11, description = "Verify user gets the select Reschedule reason popup by clicking yes", enabled = true)
	public void IDA4_2361_verifyUserGetsTheSelectRescheduleReasonPopupByClickingYes() throws Throwable {
		Assert.assertTrue(appointmentsPage.verifyUserGetsTheSelectRescheduleReasonPopupByClickingYes());
	}

	@Test(priority = 12, description = "Verify clicking on Save button by selecting reason in Reschedule reason popup", enabled = true)
	public void IDA4_2364_verifyClickingOnSaveButtonBySelectingReasonInRescheduleReasonPopup() throws Throwable {
		Assert.assertTrue(appointmentsPage.verifyClickingOnSaveButtonBySelectingReasonInRescheduleReasonPopup());
	}

	@Test(priority = 13, description = "Verify selecting any time slots & clicking on book appointment", enabled = true)
	public void IDA4_2367_verifySelectingAnyTimeSlotsAndClickingOnBookAppointment() throws Throwable {
		Assert.assertEquals(appointmentsPage.verifySelectingAnyTimeSlotsAndClickingOnBookAppointment(),
				Arrays.asList(expectedAssertProp.getProperty("appointments.reschedule.confirmation.dialog.header"),
						expectedAssertProp.getProperty("appointments.reschedule.confirmation.dialog.yes.button"),
						expectedAssertProp.getProperty("appointments.reschedule.confirmation.dialog.no.button")),
				"Confirmation dialog with correct header, message, and buttons should be displayed");

		Assert.assertTrue(appointmentsPage.isDisplayedconfirmAppointmentPopupText(
				expectedAssertProp.getProperty("appointments.reschedule.confirmation.dialog.message")));
	}

	@Test(priority = 14, description = "Verify the confirm appointment popup", enabled = true)
	public void IDA4_2368_verifyTheConfirmAppointmentPopup() throws Throwable {
		Assert.assertTrue(appointmentsPage.verifyTheConfirmAppointmentPopup());
	}

	@Test(priority = 15, description = "Verify when user clicks Yes in confirm appointment popup", enabled = true)
	public void IDA4_2370_VerifyWhenUserClicksYesInConfirmAppointmentPopup() throws Throwable {
		appointmentsPage.verifyTheConfirmAppointmentPopup();
		Assert.assertTrue(appointmentsPage.verifyWhenUserClicksYesInConfirmAppointmentPopup());
	}

	@Test(priority = 16, description = "Verify Rescheduled appointment displays under Today's tab of upcoming section", enabled = true)
	public void IDA4_2371_verifyRescheduledAppointmentDisplaysUnderTodaysTabOfUpcomingSection() throws Throwable {
		Assert.assertTrue(appointmentsPage.verifyRescheduledAppointmentDisplaysUnderTodaysTabOfUpcomingSection());
	}

	/**
	 * Need to check again after the locators of Registering patient and visit
	 * creation screen gets updated
	 * 
	 * @throws Throwable
	 */
	// @Test(priority = 17, description = "Verify user can schedule the Appointment
	// successfully", enabled = true)
	public void IDA4_2389_verifyUserCanScheduleTheAppointmentSuccessfully() throws Throwable {
		appointmentsPage.verifyUserCanScheduleTheAppointmentSuccessfully();
	}

	@Test(priority = 18, description = "Verify the patient details in cancelled section of upcoming tab", enabled = true)
	public void verifyTheAppointmentsSortedInUpcomingTab() throws Throwable {
		//// AppointmentsTest.java
		appointmentsPage.refreshAppointments();
		// Click sort icon → Descending
		appointmentsPage.clickOnSortIcon();

		List<LocalDateTime> actualDescending = appointmentsPage.getAppointmentDateTimes();

		List<LocalDateTime> expectedDescending = new ArrayList<>(actualDescending);

		Collections.sort(expectedDescending, Collections.reverseOrder());

		Assert.assertEquals(actualDescending, expectedDescending, "Appointments NOT sorted in Descending order");

		// Click again → Ascending
		appointmentsPage.clickOnSortIcon();

		List<LocalDateTime> actualAscending = appointmentsPage.getAppointmentDateTimes();

		List<LocalDateTime> expectedAscending = new ArrayList<>(actualAscending);

		Collections.sort(expectedAscending);

		Assert.assertEquals(actualAscending, expectedAscending, "Appointments NOT sorted in Ascending order");
	}

	@Test(priority = 19, description = "Verify the HW can able to search the appointments by patient name", enabled = true)
	public void IDA4_2390_verifyHWCanAbleToSearchTheAppointmentsByPatientNameInUpcomingAppointments() throws Throwable {
		appointmentsPage.refreshAppointments();
		String patientName = appointmentsPage.isDisplayedSearchedPatient();
		appointmentsPage.clickOnSearch(patientName);
		Assert.assertEquals(appointmentsPage.isDisplayedSearchedPatient(), patientName);
	}

	@Test(priority = 20, description = "Verify the HW can able to search the appointments by patient name", enabled = true)
	public void IDA4_2390_verifyHWCanAbleToSearchTheAppointmentsByPatientNameInPastAppointments() throws Throwable {
		appointmentsPage.NavigateToPastAppointments();
		String patientName = appointmentsPage.isDisplayedSearchedPatient();
		appointmentsPage.clickOnSearch(patientName);
		Assert.assertEquals(appointmentsPage.isDisplayedSearchedPatient(), patientName);
	}

	@DataProvider(name = "appointmentFilters")
	public Object[][] appointmentFilters() {
		return new Object[][] { { "Missed" }, { "Completed" }, { "Cancelled" } };
	}

	@Test(priority = 21, description = "Verify the appointment filters in Past section", enabled = true, dataProvider = "appointmentFilters")
	public void testAppointmentFilter(String filterType) throws Throwable {
		boolean result = appointmentsPage.verifyFilteredAppointments(filterType);
		Assert.assertTrue(result, "Filter failed for type: " + filterType);
	}

	@Test(priority = 22, description = "Verify the HW can able to search the appointments by patient openmrs idIn Upcoming Appointments", enabled = true)
	public void IDA4_2392_verifySearchByOpenMrsIdInUpcomingAppointments() throws Throwable {
		appointmentsPage.refreshAppointments();

		String openMrsId = appointmentsPage.getOpenMRSIDFromAppointmentDetailsScreen();
		getDriver().navigate().back();
		appointmentsPage.clickOnSearch(openMrsId);
		Assert.assertEquals(appointmentsPage.getOpenMRSIDFromAppointmentDetailsScreen(), openMrsId,
				"Search by OpenMRS ID passed");
	}

	@Test(priority = 23, description = "verify search with open mrs id", enabled = true)
	public void IDA4_2392_verifySearchByOpenMrsIdInPastAppointments() throws Throwable {
		appointmentsPage.NavigateToPastAppointments();

		String openMrsId = appointmentsPage.getOpenMRSIDFromAppointmentDetailsScreen();
		getDriver().navigate().back();
		appointmentsPage.clickOnSearch(openMrsId);
		Assert.assertEquals(appointmentsPage.getOpenMRSIDFromAppointmentDetailsScreen(), openMrsId,
				"Search by OpenMRS ID passed");
	}

	/**
	 * @deprecated due to new UI redesign
	 * @throws Throwable
	 */
	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 2, description = "Verify the overall All tab section", enabled = true)
	public void IDA4_2404_verifyOverAllTabSection() throws Throwable {
		// Verify the overall All tab section on the appointments page
		appointmentsPage.verifyAllTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")

	@Test(priority = 3, description = "Verify when user selects specific radio button for appointments filter", enabled = true)
	public void IDA4_2406_verifySpecificRadioButtonForAppointments() throws Throwable {
		// Verify that specific radio buttons for appointments filter work as expected
		appointmentsPage.verifyAppointmentsRadioButton();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")

	@Test(priority = 4, description = "Verify user can select the FROM and TO dates from calendar filter in All tab section", enabled = true)
	public void IDA4_2408_verifyAllTabCalenderFilter() throws Throwable {
		// Verify that the user can select FROM and TO dates from the calendar filter in
		// the All tab section
		appointmentsPage.verifyUserCanSelectFromAndToDate();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")

	@Test(priority = 5, description = "Verify the count of cancelled tab", enabled = true)
	public void IDA4_2374_verifyCountOfCancelledTab() throws Throwable {
		appointmentsPage.verifyCountOfCancelledTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 6, description = "Verify the functionality of 'Cancelled' tab", enabled = true)
	public void IDA4_2373_verifyCancelledTab() throws Throwable {

		appointmentsPage.verifyCancelledTabFunctionality();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 12, description = "Verify the Cancelled appointment under Cancelled section and its tab", enabled = true)
	public void IDA4_2386_verifyCancelledAppointment() throws Throwable {

		appointmentsPage.verifyCancelledTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 13, description = "Verify the cancelled section under cancelled tab", enabled = true)
	public void IDA4_2375_verifyCancelledSection() throws Throwable {
		appointmentsPage.verifyCancelledSection();

	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 14, description = "Verify if only Today's date appointments are showing under Today's tab", enabled = true)
	public void IDA4_2341_verifyIfOnlyTodaysDateAppointmentsAreShowingUnderTodaysTab() throws Throwable {
		appointmentsPage.verifyIfOnlyTodaysDateAppointmentsAreShowingUnderTodaysTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 15, description = "Verify the functionality of upcoming tab", enabled = true)
	public void IDA4_2343_verifyTheFunctionalityOfUpcomingTab() throws Throwable {
		appointmentsPage.verifyTheFunctionalityOfUpcomingTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 16, description = "Verify Cancelled section under Upcoming tab", enabled = true)
	public void IDA4_2344_verifyCancelledSectionUnderUpcomingTab() throws Throwable {
		appointmentsPage.verifyCancelledSectionUnderUpcomingTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 17, description = "Verify Completed section under Upcoming tab", enabled = true)
	public void IDA4_2345_verifyCompletedSectionUnderUpcomingTab() throws Throwable {
		appointmentsPage.verifyCompletedSectionUnderUpcomingTab();
	}

	@Deprecated
	@Description("Deprecated due to new UI redesign")
	@Test(priority = 18, description = "Verify the count of Upcoming tab", enabled = true)
	public void IDA4_2346_verifyTheCountOfUpcomingTab() throws Throwable {
		appointmentsPage.verifyTheCountOfUpcomingTab();
	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}
}
