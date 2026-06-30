
package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.StartVisit1And2StepsPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class StartVisit1And2StepsTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	StartVisit1And2StepsPage startVisit1And2StepsPage;
	AddNewPatientPage addNewPatientPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws InterruptedException, IOException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");

		// 1. Verify the driver session is alive before doing anything.
		if (!SessionHealthManager.isSessionAlive(getDriver())) {
			throw new IllegalStateException("Driver session is dead before test: " + m.getName());
		}
		// clearLogcat();
		// 2. Reset and wait for app to be fully ready.
		resetApp();

		appSetupPage = new AppSetupPage();
		addNewPatientPage = new AddNewPatientPage();
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
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
		// Complete the setup process with a valid user's credentials
		appSetupPage.completeSetup();
		appSetupPage.refreshUIAndWait();

		// Register a new patient using provided personal and address details
		addNewPatientPage.registerAPatient(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

	}

	@Test(priority = 1, description = "Verify the UI of first page of Vitals (1/4 Vitals)", enabled = true)
	public void IDA4_2413_verifyVitalsScreenUi() throws InterruptedException, IOException {

// Verify UI elements of the First Vitals Screen on the Start Visit page
		Assert.assertTrue(startVisit1And2StepsPage.verifyUiOfFirstVitalsScreen());

		Assert.assertEquals(startVisit1And2StepsPage.getUIElementsOfVitalsScreen(),
				Arrays.asList(expectedAssertProp.getProperty("vitals.screen.enter.patient.body.measurements.label"),
						expectedAssertProp.getProperty("vitals.screen.enter.patient.vitals.label"),
						expectedAssertProp.getProperty("vitals.screen.height.label"),
						expectedAssertProp.getProperty("vitals.screen.weight.label"),
						expectedAssertProp.getProperty("vitals.screen.bmi.auto.calculated.label"),
						expectedAssertProp.getProperty("vitals.screen.bpsystolic.label"),
						expectedAssertProp.getProperty("vitals.screen.bpdiastolic.label"),
						expectedAssertProp.getProperty("vitals.screen.pulse.bpm.label"),
						expectedAssertProp.getProperty("vitals.screen.temperature.label"),
						expectedAssertProp.getProperty("vitals.screen.sp02.label"),
						expectedAssertProp.getProperty("vitals.screen.respiratory.breaths.label"),
						expectedAssertProp.getProperty("vitals.screen.blood.group.label")));

	}

	/**
	 * Locators are missing for vitals summary screen
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 2, description = "Verify the functionality of Next button on 1st vital screen", enabled = true)
	public void IDA4_2415_verifyNextButtonFunctionality() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);
// Click on the "Next" button on the First Vitals Screen of the Start Visit page
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

// Verify that the Vitals Summary Screen is displayed after clicking the "Next" button

		Assert.assertTrue(startVisit1And2StepsPage.verifyVitailsSummaryScreenIsDisplayed());

		Assert.assertEquals(startVisit1And2StepsPage.getVitalSummaryTitle(),
				expectedAssertProp.getProperty("vitals.summary.screen.title"));

	}

	@Test(priority = 3, description = "Verify the functionality of BM index(auto-calculated) textfield on 1st vital screen", enabled = true)
	public void IDA4_2419_verifyBMIValueIsAutoCalculated() throws JSONException, InterruptedException {

		ExtentReport.startTest("IDA4_2419_verifyBMIValueIsAutoCalculated",
				"Verify the functionality of BM index(auto-calculated) textfield on 1st vital screen");
// Enter the patient's height as "160" on the Start Visit page
		startVisit1And2StepsPage.enterHeight(appData.getJSONObject("patientVitalsDetails").getString("height"));

// Enter the patient's weight as "60" on the Start Visit page
		startVisit1And2StepsPage.enterWeight(appData.getJSONObject("patientVitalsDetails").getString("weight"));

// Verify that BMI is automatically calculated and matches the expected BMI index value
		Assert.assertTrue(startVisit1And2StepsPage.verifyCalculatedBMI(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight")));

	}

	/**
	 * Locators are missing for vitals summary screen
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 4, description = "Verify the UI of Vital summary", enabled = true)
	public void IDA4_2429_verifyTheUiOfVitalSummary() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Click on the "Next" button on the First Vitals Screen of the Start Visit page
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Verify the UI elements on the Vitals Summary Screen after clicking the "Next"
		// button
		Assert.assertTrue(startVisit1And2StepsPage.verifyVitalslSummaryUi());
		Assert.assertEquals(startVisit1And2StepsPage.getUIElementsOfVitalsSummaryScreen(),
				Arrays.asList(expectedAssertProp.getProperty("vitals.summary.screen.title"),
						expectedAssertProp.getProperty("vitals.summary.screen.height.label"),
						expectedAssertProp.getProperty("vitals.summary.screen.weight.label"),
						expectedAssertProp.getProperty("vitals.summary.screen.bmi.label"),
						expectedAssertProp.getProperty("vitals.screen.bp.label"),
						expectedAssertProp.getProperty("vitals.summary.screen.pulse.bpm.label"),
						expectedAssertProp.getProperty("vitals.screen.temperature.label"),
						expectedAssertProp.getProperty("vitals.screen.sp02.label"),
						expectedAssertProp.getProperty("vitals.summary.screen.respiratory.label"),
						expectedAssertProp.getProperty("vitals.summary.screen.blood.group.label")));

	}

	/**
	 * @author SrinivasBandi
	 * 
	 */
	@Test(priority = 5, description = "Verify the status of BMI index by changing different height & weight and if editable field", enabled = true)
	public void IDA4_2421_checkBMIStatusWithDifferentValues() throws JSONException, InterruptedException {
		ExtentReport.startTest("IDA4_2421_checkBMIStatusWithDifferentValues",
				"Verify the status of BMI index by changing different height & weight and if editable field");
		List<String> weightArray = List.of(appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("weights").getString("weight1"),
				appData.getJSONObject("weights").getString("weight2"),
				appData.getJSONObject("weights").getString("weight3"),
				appData.getJSONObject("weights").getString("weight4"),
				appData.getJSONObject("weights").getString("weight5"));
		List<String> expectedStatusArray = Arrays.asList(
				appData.getJSONObject("weightStatus").getString("expectedStatus1"),
				appData.getJSONObject("weightStatus").getString("expectedStatus2"),
				appData.getJSONObject("weightStatus").getString("expectedStatus3"),
				appData.getJSONObject("weightStatus").getString("expectedStatus4"),
				appData.getJSONObject("weightStatus").getString("expectedStatus5"),
				appData.getJSONObject("weightStatus").getString("expectedStatus6"));
		for (int i = 0; i < weightArray.size(); i++) {
			Assert.assertTrue(startVisit1And2StepsPage.verifyStatus(
					appData.getJSONObject("patientVitalsDetails").getString("height"), weightArray.get(i),
					expectedStatusArray));
		}
	
	}

	/**
	 * Locators are missing for vitals summary screen
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 6, description = "Verify the details section on Vital summary", enabled = true)
	public void IDA4_2430_verifyDeatilsOnVitalSummaryScreen() throws JSONException, InterruptedException {
// Enter patient vitals details using data from the appData object
		startVisit1And2StepsPage.enterPatientVitals(appData);

// Click on the "Next" button on the First Vitals Screen of the Start Visit page
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		List<String> expectedVitalsDetailsInSummary = List.of(
				appData.getJSONObject("vitalsSummaryDetails").getString("height"),
				appData.getJSONObject("vitalsSummaryDetails").getString("weight"),
				appData.getJSONObject("vitalsSummaryDetails").getString("bp"),
				appData.getJSONObject("vitalsSummaryDetails").getString("pulse"),
				appData.getJSONObject("vitalsSummaryDetails").getString("temperature"),
				appData.getJSONObject("vitalsSummaryDetails").getString("spo2"),
				appData.getJSONObject("vitalsSummaryDetails").getString("respiratoryRate"),
				appData.getJSONObject("vitalsSummaryDetails").getString("bMI"),
				appData.getJSONObject("vitalsSummaryDetails").getString("bloodGroup"));
		List<String> actualVitalsDetailsInSummary = startVisit1And2StepsPage.verifyPatientVitalsDetails();

		IntStream.range(0, actualVitalsDetailsInSummary.size()).parallel().forEach(i -> {
			String actual = actualVitalsDetailsInSummary.get(i);
			String expected = expectedVitalsDetailsInSummary.get(i);
		});

	}

	/**
	 * Locators are missing for vitals summary screen
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 7, description = "Verify clicking on the change button of details section on Vital summary", enabled = true)
	public void IDA4_2432_VerifyChangeButtonOfVitalSummaryScreen() throws JSONException, InterruptedException {
// Entering patient vitals details on the startVisit1And2StepsPage
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnChangeInVitalsSummaryScreen();
		Assert.assertEquals(startVisit1And2StepsPage.isDisplayedVitalsCollectionScreen(),
				expectedAssertProp.getProperty("vitals.screen.title"));
	}

	@Test(priority = 8, description = "Verify that the updated 1/4 details are saved", enabled = true)
	public void IDA4_2433_VerifyVitalsdetailsAreSaved() throws JSONException, InterruptedException {
// Entering patient vital details using the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

// Clicking on the next button after entering vital details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnChangeInVitalsSummaryScreen();
		List<String> expectedChangedVitalsDetailsInSummary = Arrays.asList(
				appData.getJSONObject("patientVitalsEditDetails").getString("height"),
				appData.getJSONObject("patientVitalsEditDetails").getString("weight"),
				appData.getJSONObject("patientVitalsSummaryEditDetails").getString("bp"),
				appData.getJSONObject("patientVitalsEditDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsEditDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsEditDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsEditDetails").getString("respiratoryRate"),
				appData.getJSONObject("patientVitalsSummaryEditDetails").getString("bmi"));

		startVisit1And2StepsPage.checkVitalsUpdateScreenDetailsEditable(expectedChangedVitalsDetailsInSummary);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Verifying entered patient vital details against expected values
		List<String> actualVitalsDetailsInSummary = startVisit1And2StepsPage.verifyPatientVitalsDetails();

		Assert.assertEquals(actualVitalsDetailsInSummary,
				Arrays.asList(appData.getJSONObject("patientVitalsSummaryEditDetails").getString("height"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("weight"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("bp"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("pulse"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("temperature"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("spo2"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("respiratoryRate"),
						appData.getJSONObject("patientVitalsSummaryEditDetails").getString("bmi"),
						appData.getJSONObject("vitalsSummaryDetails").getString("bloodGroup")));

	}

	@Test(priority = 9, description = "Verify the functionality of Confirm button on 1/4 vital summary screen", enabled = true)
	public void IDA4_2435_verifyConfirmButtonfuntionality() throws JSONException, InterruptedException {
// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton();
// Verifying that the visit reason screen is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyVisitReasonScreenIsDisplayed());

		Assert.assertEquals(startVisit1And2StepsPage.getVisitReasonHeader(),
				expectedAssertProp.getProperty("visit.reason.screen.header"));
	}

	@Test(priority = 10, description = "Verify the functionality of Selected reason section", enabled = true)
	public void IDA4_2438_verifyFuntionalityOfSelectedReason() throws JSONException, InterruptedException {
// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

//Clicks on the 1/4 vitals screen next button
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton();
//Clicks On confirm button

		/**
		 * Locators are missing for vitals summary screen
		 * 
		 * @throws InterruptedException
		 */

		startVisit1And2StepsPage.clickOnConfirmButton();
//Select the Abdominal pain reason

		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//Verify the abdominal pain is selected
		Assert.assertTrue(startVisit1And2StepsPage.verifyVisitReasonAbdominalPainIsSelected());

	}

	/**
	 * We need to check after adding the state based change on selected elements
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	@Test(priority = 11, description = "Verify user can remove the selected reason", enabled = true)
	public void IDA4_2439_VerifyTheFunctionalityOfRemoveOption() throws JSONException, InterruptedException {
// Start entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

// Click on the "Next" button after entering patient vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
// Select the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// need to add remove icon method..clion close
		startVisit1And2StepsPage.removeSelectedReason();

		// Verify that the selected reason is removed
		Assert.assertFalse(startVisit1And2StepsPage.verifySelectedReasonIsRemoved());

	}

	@Test(priority = 12, description = "Verify whether all selected reasons from All reason section are getting reflected under Selected reason section", enabled = true)
	public void IDA4_2440_verifySelectedReasons() throws InterruptedException {
// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

// Click on the "Next" button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
// Select all reasons on the page

		Assert.assertTrue(startVisit1And2StepsPage.verifyAllSelectedReasonsAreDisplayed(
				Arrays.asList(expectedAssertProp.getProperty("visit.reason.screen.abdominal.chief.complaint"),
						expectedAssertProp.getProperty("visit.reason.screen.diarrhea.chief.complaint"),
						expectedAssertProp.getProperty("visit.reason.screen.fever.chief.complaint"),
						expectedAssertProp.getProperty("visit.reason.screen.hypertension.chief.complaint"))));

	}

	@Test(priority = 13, description = "Verify the functionality of Back button after selecting a reason on 2/4 visit reason screen", enabled = true)
	public void IDA4_2452_verifySelectedReasons() throws JSONException, InterruptedException {
		// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Click on the "Next" button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
		// Select visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// click On back button
		startVisit1And2StepsPage.clickOnBackButton();
		// Verify VitalssummaryScreen Is displayed

		Assert.assertTrue(startVisit1And2StepsPage.verifyVitailsSummaryScreenIsDisplayed());

		Assert.assertEquals(startVisit1And2StepsPage.getVitalSummaryTitle(),
				expectedAssertProp.getProperty("vitals.summary.screen.title"));

	}

	@Test(priority = 14, description = "Verify the functionality of Next button on 2/4 Visit reason screen", enabled = true)
	public void IDA4_2454_VerifyConfirmVisitPopup() throws JSONException, InterruptedException {
		// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Click on the "Next" button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Select visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Click next button
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// verify confirmvisit popup is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyConfirmVisitPopup());

		Assert.assertEquals(startVisit1And2StepsPage.getConfirmVisitPopupContent(),
				Arrays.asList(expectedAssertProp.getProperty("confirm.visit.reason.popup.title"),
						expectedAssertProp.getProperty("confirm.visit.reason.popup.sub.title"),
						expectedAssertProp.getProperty("confirm.visit.reason.popup.reason"),
						expectedAssertProp.getProperty("confirm.visit.reason.popup.yes.button"),
						expectedAssertProp.getProperty("confirm.visit.reason.popup.no.button")));
	}

	@Test(priority = 15, description = "Verify clicking on No button in Confirm visit reason popup", enabled = true)
	public void IDA4_2455_VerifyConfirmVisitPopupNoButtonFuntionality() throws JSONException, InterruptedException {
		// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Click on the next button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirm the entered patient vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Select visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Click on the next button after selecting visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// Verify the confirm visit popup
		// Click on the "No" button in the confirm visit popup
		startVisit1And2StepsPage.clickOnNoButton();
		// Verify that the visit reason screen is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyVisitReasonScreenIsDisplayed());

		Assert.assertEquals(startVisit1And2StepsPage.getVisitReasonHeader(),
				expectedAssertProp.getProperty("visit.reason.screen.header"));

	}

	@Test(priority = 16, description = "Verify clicking on Yes button in Confirm visit reason popup", enabled = true)
	public void IDA4_2456_VerifyConfirmVisitPopupYesButtonFuntionality() throws JSONException, InterruptedException {
		// Enter patient vitals details using data from the appData object
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Navigate to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirm the entered patient vitals
		 startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Select the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Proceed to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// Verify and handle the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// Verify that the first question related to abdominal pain is displayed

		Assert.assertTrue(startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed());

	}

	/**
	 * Need to add selected attribute and if option is selected , its value should
	 * be true, else false
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	@Test(priority = 17, description = "Verify the options displayed for the 1st question", enabled = true)
	public void IDA4_2459_VerifyOptions() throws JSONException, InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
		// Clicking next after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirming the entered vitals (1/4 steps)
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Choosing 'Abdominal Pain' as the visit reason
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Moving to the next step
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// Verifying the confirmation popup
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		// Confirming the visit
		startVisit1And2StepsPage.clickOnYesButton();
		// Verifying the display of the first abdominal question
		// Verifying that all options are displayed

		Assert.assertTrue(startVisit1And2StepsPage.verifyAllOptionsAreDisplayed());
		// Choosing 'Upper Right Hypochondrium'
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		// Choosing 'All Over'
		startVisit1And2StepsPage.clickAllOver();
		Assert.assertTrue(startVisit1And2StepsPage.isSelectedAllOverOption());

	}

	@Test(priority = 18, description = "Verify the functionality of Submit button on 1st question", enabled = true)
	public void IDA4_2462_verifySubmitButtonFuntionality() throws JSONException, InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		// Assert.assertTrue(startVisit1And2StepsPage.verifyConfirmVisitPopup());

		startVisit1And2StepsPage.clickOnYesButton();
		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		Assert.assertTrue(startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed(
				expectedAssertProp.getProperty("two.of.twelve.questions.counter")));
	}

	@Test(priority = 19, description = "Verify whether user able to go to 3rd question without selecting option/answer for 2nd question", enabled = true)
	public void IDA4_2464_verifyUserCanGoTo3rdQuestionWithoutSelecting2ndQuestion()
			throws JSONException, InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

		// Verifying that all options for the question are displayed

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions

		startVisit1And2StepsPage.scrollDown();

		// Verifying that the third out of twelve questions is not present
		Assert.assertFalse(startVisit1And2StepsPage.threeOfTwelveQuestioNotPresent());

	}

	/**
	 * Pain radiates to options are not displayed in the provided build
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 20, description = "Verify after selecting one option from 2nd question option list 'pain radiates to' option selected", enabled = true)
	public void IDA4_2465_selectPainRadiatesToOptionAndVerifyDescribeSectionDisplayed() throws InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		// Verifying that all options for the question are displayed

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions

		// Selecting the option related to pain radiating
		startVisit1And2StepsPage.selectPainRadiatesOption();

		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();

		// Verifying that options related to pain radiation are displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyPainRadiatesToOptionsAreDisplayed());
		Assert.assertFalse(startVisit1And2StepsPage.threeOfTwelveQuestioNotPresent());

	}

	/**
	 * Pain radiates to options are not displayed in the provided build commented
	 * because we dont have describe section in the current build
	 * 
	 * @throws InterruptedException
	 */
//	@Test(priority = 21, description = "Verify the Describe section of 3 of 12 questions screen", enabled = true)
	public void IDA4_2466_VerifyDescribeSection() throws InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton();
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.selectPainRadiatesOption();

		startVisit1And2StepsPage.scrollToEndAction();
		Assert.assertFalse(startVisit1And2StepsPage.threeOfTwelveQuestioNotPresent());

		// startVisit1And2StepsPage.scrollUpToSkipButtonOnPainRadiatesScreen();
		/**
		 * Write assertion for inputfield
		 */
		// Verifying the display of the submit button
		// Assert.assertTrue(startVisit1And2StepsPage.verifySubmitButtonIsDisplayed());

	}

	@Test(priority = 22, description = "Verify by not selecting any options and click on submit button of 2 of 12", enabled = true)
	public void IDA4_2467_VerifyDescribeSection() throws InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		Assert.assertTrue(startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed());

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		// Selecting the option related to pain radiating
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.scrollToEndAction();
		Assert.assertFalse(startVisit1And2StepsPage.threeOfTwelveQuestioNotPresent());
		// Clicking on the submit button for the pain radiates question
		startVisit1And2StepsPage.clickOnGroin();

		// Verifying the display of the third out of twelve questions
		Assert.assertTrue(startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed());

	}

	@Test(priority = 23, description = "Verify selecting any one number in 3 of 12", enabled = true)
	public void IDA4_2470_selectNumberAndVerify() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Moving to the next steps in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4

		// Selecting visit reason as abdominal pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// Handling number spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		String selectedNumber = startVisit1And2StepsPage.selectTwo();
		Assert.assertTrue(startVisit1And2StepsPage.verifyNumberTwoIsSelected(selectedNumber));

	}

	@Test(priority = 24, description = "Verify selecting any duration in 3 of 12", enabled = true)
	public void IDA4_2474_selectNumberAndVerify() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// Moving to the next steps in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4

		// Selecting visit reason as abdominal pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed(
				expectedAssertProp.getProperty("two.of.twelve.questions.counter"));
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// Handling number spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		String selectedDays = startVisit1And2StepsPage.selectTwo();
		Assert.assertTrue(startVisit1And2StepsPage.verifyNumberTwoIsSelected(selectedDays));

		// Handling duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		String days = startVisit1And2StepsPage.selectDays();

		Assert.assertTrue(startVisit1And2StepsPage.verifyDaysIsSelected(days));

	}

	@Test(priority = 25, description = "Verify clicking on submit button by selecting both Number and duration type in 3 of 12", enabled = true)
	public void IDA4_2476_verifySubmitButtonOfThreeOfElevenQuestion() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();

		Assert.assertTrue(startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed());
	}

	@Test(priority = 26, description = "Verify whether user able to select any options in 4 of 12", enabled = true)
	public void IDA4_2478_selectOptionInFourOfTwelve() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.clickOnGradualOption();
		Assert.assertTrue(startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed());
	}

	@Test(priority = 27, description = "Verify when user selects any option other than Others[Describe]in 4 of 12", enabled = true)
	public void IDA4_2479_verifyFiveOfTwelveQuestionIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.clickOnsuddenOption();
		startVisit1And2StepsPage.clickOnRapidlyIncreasingOption();
		Assert.assertTrue(startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed());
	}

	@Test(priority = 28, description = "Verify clicking on Others [Describe] section in 4 of 12", enabled = true)
	public void IDA4_2480_verifyClickingOnOthersOption() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptionOfFourOfTwelveQuestions();

		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton());

	}

	@Test(priority = 29, description = "Verify entering any data in describe field and click on submit button in 4 of 12", enabled = true)
	public void IDA4_2483_enterDataInDescribeFieldAndclickSubmitButton() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptionOfFourOfTwelveQuestions();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));

		startVisit1And2StepsPage.scrollDown();
		startVisit1And2StepsPage.clickOnSubmitButtonOfFourOfTwelveQuestion();
		Assert.assertTrue(startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed());
	}

	@Test(priority = 30, description = "Verify whether user able to select any options in 5 of 12", enabled = true)
	public void IDA4_2485_verifyUserAbleToSelectOptionOfFiveOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4

		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.clickOnOtherOptionOfFourOfTwelveQuestions();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.scrollDown();
		startVisit1And2StepsPage.clickOnSubmitButtonOfFourOfTwelveQuestion();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		Assert.assertTrue(startVisit1And2StepsPage.isSelectedNIghtOption());
	}
//===============================================================================================================================================
	@Test(priority = 31, description = "Verify when user selects any option other than Others[Describe] in 5 of 12", enabled = true)
	public void IDA4_2486_verifyWhenUserSelectsOptionInFiveOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		Assert.assertTrue(startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed());
	}

	@Test(priority = 32, description = "Verify clicking on Others [Describe] section in 5 of 12", enabled = true)
	public void IDA4_2487_verifyFiveOfTwelveQuestionsDescribeTextBoxIsDisplayedWithSubmitButton()
			throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.clickOnGradualOption();

		startVisit1And2StepsPage.selectOtherOptionOfFiveOfTwelveQuestions();
		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton());
	}

	@Test(priority = 33, description = "Verify entering any data in describe field and click on submit button in 5 of 12", enabled = true)
	public void IDA4_2489_verifySixOfTwelevQuestionIsAutoPopulated() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.selectOtherOptionOfFiveOfTwelveQuestions();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();

		Assert.assertTrue(startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed());
	}

//call submit
	@Test(priority = 34, description = "Verify whether user able to select any options in 6 of 12", enabled = true)
	public void IDA4_2491_verifyUserAbleToSelectAnyOptionsOfSixOfTwelveQusetions() throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

		Assert.assertTrue(startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed());

	}

	@Test(priority = 35, description = "Verify clicking on Others [Describe] section in 6 of 12", enabled = true)
	public void IDA4_2493_verifyClickingOnOtherOptionDisplaysDescribeTextArea() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectOtherOptionOfSixOfTwelveQuestions();

		Assert.assertTrue(startVisit1And2StepsPage.isdisplayedSelectOneOrMoreText());

		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton());
	}

	@Test(priority = 36, description = "Verify entering any data in describe field or select any option and click on submit button in 6 of 12", enabled = true)
	public void IDA4_2496_verifySixOftwelveQuestionsSubmitButtonFuntionality() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

		Assert.assertTrue(startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed());
	}

	/**
	 * Failing because is selected attribute is returing false after selection
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 37, description = "Verify whether user able to select any options in 7 of 12", enabled = true)
	public void IDA4_2498_verifyUserAbleToSelectAnyOptionInSevenOfTwelveQuestions() throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		// startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();

		 Assert.assertTrue(startVisit1And2StepsPage.isSelectedMildOneThreeOption());

	}

	@Test(priority = 38, description = "Verify whether user able to select any options in 7 of 12", enabled = true)
	public void IDA4_2499_verifyEightOfTwelveQuetionsIsDisplayed() throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		// startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();

		 Assert.assertTrue(startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed());
	}

	@Test(priority = 39, description = "Verify whether user able to select any options in 8 of 12", enabled = true)
	public void IDA4_2501_verifyUserAbleToSelectAnyOptionInEightOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
//		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
//		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		// startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		// startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();

		Assert.assertTrue(startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed());

	}

	@Test(priority = 40, description = "Verify clicking on Others [Describe] section in 8 of 12", enabled = true)
	public void IDA4_2503_verifyEightOfTwelveQuestionsDescribeTextBoxIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		// startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
//		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptonOfEightOfTwelveQusetions();
		Assert.assertTrue(startVisit1And2StepsPage.isdisplayedSelectOneOrMoreText());

		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton());
	}

	@Test(priority = 41, description = "Verify entering any data in describe field or select any option and click on submit button in 8 of 12", enabled = true)
	public void IDA4_2507_enterValueInDescribeTextBoxOFEightOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitals(appData);

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		// startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptonOfEightOfTwelveQusetions();
		// startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();

		Assert.assertTrue(startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed());
	}

	@Test(priority = 42, description = "Verify whether user able to select one or more options in 9 of 12", enabled = true)
	public void IDA4_2509_selectOneOrMoreOptionInnineOfTwelveQuestions() throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		// startVisit1And2StepsPage.selectPainRadiatesOption();
		// startVisit1And2StepsPage.clickOnGroin();
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		// startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		// startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		// startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		// startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		// startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		// startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectfoodOption();
		startVisit1And2StepsPage.selectLeaningForward();
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();

		Assert.assertTrue(startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed());
	}

	@Test(priority = 43, description = "Verify clicking on Others [Describe] section in 9 of 12", enabled = true)
	public void IDA4_2511_verifyDescribeTextAreaIsDisplayedInNineOfTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed(
				expectedAssertProp.getProperty("two.of.twelve.questions.counter"));
// Selecting the option "Pain Radiates"
		// startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		// startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
//Clicking on gradual option
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
// Verifying the display of the sixth question
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		// startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		// startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectOtherOption();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();

		Assert.assertTrue(startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed());
	}

	/**
	 * from here
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 44, description = "Verify entering any data in describe field or select any option and click on submit button in 9 of 12", enabled = true)
	public void IDA4_2515_verifyTenOfTwelveQuestionsIsAutoPopulated() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed(
				expectedAssertProp.getProperty("two.of.twelve.questions.counter"));
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
// Clicking on the "Groin" option

// Verifying the display of the third question

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();

		// startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		Assert.assertTrue(startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed());
	}

	@Test(priority = 45, description = "Verify whether user able to select any options in 10 of 12", enabled = true)
	public void IDA4_2517_verifyUserAbleToSelectOptionInTenOftTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question

// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
// Verifying the display of the third question

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
// Verifying the display of the sixth question
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
//
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that eleven out of twelve questions are displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed());

	}

	@Test(priority = 46, description = "Verify when user selects ''Is menstruating'' option in 10 of 12", enabled = true)
	public void IDA4_2519_verifyIsMenstruatingOptionInTenOftTwelveQuestions() throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitals(appData);
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
//// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
//// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
//// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
//// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
//// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
//// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
//// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
//// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
//// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
//// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
//// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
//// Verifying the display of the fifth question

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

//// Verifying the display of the sixth question
//// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
//// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
//// Verifying the display of the seventh question
//// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
//// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
//// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
//// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
//// Verifying the display of the tenth question
//// Select the option indicating that the user is currently menstruating
		startVisit1And2StepsPage.selectIsMenstruatingOption();

//// Verify that the age onset input field is displayed after selecting
//// menstruation
		// startVisit1And2StepsPage.verifyAgeAtOnSetIsDisplayed();
//
//// Enter the age of 41 in the age onset input field
		startVisit1And2StepsPage.enterAgeInAgeOnSetFeild("41");
//
//// Click on the submit button to proceed to the next set of questions
		startVisit1And2StepsPage.clickOnSubmitButtonOfTenOftwelveQuestions();
//
//// Verify that information about the last menstruation period is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyLastMenstruationPeriodIsDisplayed());
//
//// Verify that a calendar interface for selecting dates is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyCalenderIsDisplayed());
	}

	/**
	 * Failing because it is returning false even after the selection of date Check
	 * Age at onset webelement no such element exception
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 47, description = "Verify click on last menstruation period option in 10 of 12", enabled = true)
	public void IDA4_2520_verifyLastMenstruationPeriodOptionInTenOftTwelveQuestions() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton();
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// the display of the first question related to abdominal pain
		// Verifying the display of all available options for the question
		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
		// Verifying the display of the second question
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		// Verifying the display of the third question

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
		// Verifying the display of the fifth question

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Verifying the display of the sixth question
		// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		// Verifying the display of the seventh question
		// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
		// electing the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
		// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
		// Verifying the display of the tenth question
		// Select the option indicating that the user is currently menstruating
		startVisit1And2StepsPage.selectIsMenstruatingOption();

		// Verify that the age onset input field is displayed after selecting
		// menstruation
		startVisit1And2StepsPage.verifyAgeAtOnSetIsDisplayed();

		// Enter the age of 41 in the age onset input field
		startVisit1And2StepsPage.enterAgeInAgeOnSetFeild("41");

// Click on the submit button to proceed to the next set of questions
		startVisit1And2StepsPage.clickOnSubmitButtonOfTenOftwelveQuestions();

		// Verify that information about the last menstruation period is displayed

// Verify that a calendar interface for selecting dates is displayed

		startVisit1And2StepsPage.selectmenstruationDate();

		 Assert.assertTrue(startVisit1And2StepsPage.verifySelectedMenstruationDateIsDisplayed("01/Oct/2023"));
	}

	@Test(priority = 48, description = "Verify entering any data in describe field or select any option and click on submit button in 10 of 12", enabled = true)
	public void IDA4_2522_verifyElevenOftweOfTwelveQusetionsIsDisplayed() throws InterruptedException {
//// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
//// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
//// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
//// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
//// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
//// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
//// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
//// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
//// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
//// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
//// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
//// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
//// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
//// Verifying the display of the fifth question
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
//// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
//// Verifying the display of the seventh question
//// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
//// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
//// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
//// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
//// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

//// Verify that the eleventh question of the twelve-question set is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed());

	}

	/**
	 * Failing because is selected returing false even though it is selected
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 49, description = "Verify whether user able to select any options in 11 of 12", enabled = true)
	public void IDA4_2524_verifyUserAbleToSelectAnyOptionInelevenOfTwelveQuestions() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
//// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
//// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
//// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
//// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
//// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
//// Verifying the display of the first question related to abdominal pain
//// Verifying the display of all available options for the question
//// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
//// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
//// Verifying the display of the third question
//
// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
//// Selecting "Days" from the duration spinner

		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
/// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.clickOnGradualOption();
//// Verifying the display of the fifth question
//
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

//// Verifying the display of the sixth question
//// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
//// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
//// Verifying the display of the seventh question
//// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
//// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
//// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
//// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
//// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();
//
//// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.selectNoneOption();
		Assert.assertTrue(startVisit1And2StepsPage.isSelectedNoneOption());

	}

	@Test(priority = 50, description = "Verify clicking on Yes [Describe] section in 11 of 12", enabled = true)
	public void IDA4_2527_verifyDescribeTextAreaIsDisplayedInElevenOfTwelveQuestions() throws InterruptedException {
//// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
/// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
//// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
//// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
//// Verifying the confirmation popup for the visit
//// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
//// Verifying the display of the first question related to abdominal pain
//// Verifying the display of all available options for the question
//// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
//// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
//// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
//// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
//// Verifying the display of the fifth question
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();

//// Verifying the display of the sixth question
//// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
//// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
//// Verifying the display of the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
//// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();

//// Selecting the "Food" option for the ninth question

		startVisit1And2StepsPage.selectLeaningForward();
//// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
//// Verifying the display of the tenth question
//// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();
//
//// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();
//// Select the "Yes, Describe" option on the startVisit1And2StepsPage.
		startVisit1And2StepsPage.selectYesDescribeOption();
		startVisit1And2StepsPage.scrollDown();
//// Verify that the describe block is displayed along with the submit button on the startVisit1And2StepsPage.

		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton());

	}

	@Test(priority = 51, description = "Verify entering any data in describe field or select any option and click on submit button in 11 of 12", enabled = true)
	public void IDA4_2530_verifyTwelveOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
//// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
//// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
//// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
//// Verifying the confirmation popup for the visit
		// startVisit1And2StepsPage.verifyConfirmVisitPopup();
//// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();

//// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
//// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
//// Verifying the display of the third question
//
//// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
//// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
//// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
//// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
//// Verifying the display of the fifth question
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
//// Verifying the display of the sixth question
//// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
/// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
//// Verifying the display of the seventh question
//// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
//// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
//// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
//// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
//// Verifying the display of the tenth question
//// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();
		startVisit1And2StepsPage.selectNoneOption();
//// Verify that all twelve questions are displayed

		Assert.assertTrue(startVisit1And2StepsPage.verifyTwelveOfTwelveQuestionsIsDisplayed());
	}

	@Test(priority = 52, description = "Verify clicking on Skip button in 12 of 12", enabled = true)
	public void IDA4_2533_verifyAssociatedSymptomsQuestionIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);

//// Verify that the title for associated symptoms is displayed
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
//
//// Verify that the "Do you have the following symptom" question is displayed on
//// the startVisit1And2StepsPage
		Assert.assertTrue(startVisit1And2StepsPage.verifyDoYouHaveFollowingSymptomIsDisplayed());

	}

	/**
	 * till here
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 53, description = "Verify entering any data in describe field and click on submit button in 12 of 12", enabled = true)
	public void IDA4_2534_enterDataInAssociatedTextFieldAndSubmit() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitals(appData);
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
// Selecting the option "Pain Radiates"
// Clicking on the "Groin" option
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnDoesNotMoveOption();
// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the sixth question
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectLeaningForward();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that the eleventh question of the twelve-question set is displayed
// Select the "None" option on the startVisit1And2StepsPage.
		startVisit1And2StepsPage.selectNoneOption();
// Verify that all twelve questions are displayed

// Enter the value in the describe text box based on the value from the appData
// JSON object and submit
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnTwelevOfTwelveSubmitButton();

// Verify that the title for associated symptoms is displayed
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();

// Verify that the "Do you have the following symptom" question is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyDoYouHaveFollowingSymptomIsDisplayed());

	}

	@Test(priority = 54, description = "Verify when user selects yes option for '7. change in frequency of urination[Describe]' symptom", enabled = true)
	public void IDA4_2541_verifyDescribeTextAreaIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);
		startVisit1And2StepsPage.clickNoButtonInAssociatedSymptoms();
		startVisit1And2StepsPage.scrollDown();

		// Click on the seventh "Yes" button associated with a specific action
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();

		// Verify that the text area for describing something is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed());

	}

	@Test(priority = 55, description = "Verify when user selects yes option for 8th , 9th and 16th [Describe]'' symptom", enabled = true)
	public void IDA4_2543_verifyAssociatedSymptomsDescribeTextAreaIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);

		// startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		// Click on the "Yes" button for the eighth associated symptom
//		startVisit1And2StepsPage.clickOnEightAssociatedSymptomYesButton();
		// startVisit1And2StepsPage.clickOnNoButton();
		// Verify that the text area for describing symptoms is displayed
		// startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed();
		startVisit1And2StepsPage.clickOnEightAssociatedSymptomYesButton();
		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed());
		// Click on the "Yes" button for the ninth associated symptom
		startVisit1And2StepsPage.clickOnNineAssociatedSymptomYesButton();
		Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed());

		// Scroll to the sixteenth associated symptom on the startVisit1And2StepsPage
		for (int i = 0; i < 2; i++) {
			startVisit1And2StepsPage.scrollDown();
		}
		// Click on the "Yes" button for the sixteenth associated symptom
		startVisit1And2StepsPage.clickOnSixteenAssociatedSymptomYesButton();

		// Verify that the text area for describing symptoms is displayed again
		 Assert.assertTrue(startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed());

	}

	// **************************************************************??**************************************************************

	@Test(priority = 56, description = "Verify on clicking submit button after selecting yes or no option for symptoms", enabled = true)
	public void IDA4_2549_verifyAssociatedSymptomSubmitButton() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);

		startVisit1And2StepsPage.checkSymptomsNoButton();

		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		Assert.assertTrue(startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed());

	}

	/**
	 * Made false this methid because of irregular behaviour of the scroll action
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 57, description = "Verify the functionality of change button of Abdominal pain(selected visit reason) on 2/4 Visit Reason summary page", enabled = true)
	public void IDA4_2551_verifyChangeButtonOfAbdominalPain() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);
		Thread.sleep(2000);
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		startVisit1And2StepsPage.enterDataInDescribeTextField("Testing");
		// startVisit1And2StepsPage.scrollToEndAction();
		for (int i = 0; i < 4; i++) {
			scrollDown();
		}
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();

		/// Locators are not present for VisitReasonSummaryScreen
		startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed();
		startVisit1And2StepsPage.clickOnAbdominalPainChangeIcon();
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();

		startVisit1And2StepsPage.scrollToEightOfTwelveQuestions();
		startVisit1And2StepsPage.selectfoodOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
		for (int i = 0; i < 4; i++) {
			scrollDown();

		}
		startVisit1And2StepsPage.scrollToEndAction();
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
		startVisit1And2StepsPage.verifySelectedFoodOptionIsDisplayed();

		Assert.assertTrue(startVisit1And2StepsPage.verifyVomitingIsDisplayed());

	}

	@Test(priority = 58, description = "Verify the functionality of Back button on 2/4 Visit Reason summary page ", enabled = true)
	public void IDA4_2555_verifyFunctionalityOfBackButtonOnVisitReasonSummaryScreen() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);

		Thread.sleep(2000);
		startVisit1And2StepsPage.checkSymptomsNoButton();
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		// startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed();

		/// Locators are not present for VisitReasonSummaryScreen

		startVisit1And2StepsPage.clickOnBackButtonOnVisitReasonSummaryScreen();
		Assert.assertTrue(startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed());

		Assert.assertEquals(startVisit1And2StepsPage.getAssociatedSymptomsTitle(),
				expectedAssertProp.getProperty("visit.reason.abdominal.pain.title"));
	}

	@Test(priority = 59, description = "Verify the functionality of Confirm button on 2/4 Visit Reason summary page ", enabled = true)
	public void IDA4_2556_verifyFunctionalityOfConfirmButtonOnVisitReasonSummaryScreen() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);
		Thread.sleep(2000);

		startVisit1And2StepsPage.checkSymptomsNoButton();

		// click on the submit button
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		// verify visit summary screen is displayed
		Assert.assertTrue(startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed());

		/// add code for click on Confirm button on 2/4 Visit Reason summary page
	}

	@Test(priority = 60, description = "Verify the functionality of Associated symptoms section on 2/4 Visit Reason summary page", enabled = true)
	public void IDA4_2553_verifyAssociatedSymptomSectionOnVisitSummaryPage() throws InterruptedException {

		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);

		// verify the associated symptoms section
		startVisit1And2StepsPage.editAssociatedSymptoms();// check with @srinivas

		/// Locators are not present for VisitReasonSummaryScreen

		Assert.assertTrue(startVisit1And2StepsPage.verifyAssociatedSymptomsSectionOnSummaryPage());

	}

	@Test(priority = 61, description = "Verify whether user can select options for all 20 symptoms", enabled = true)
	public void IDA4_2538_selectAssociatedSymptomsTwentyOptionsAndVerify() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(appData);

		// verify the associated 20 options
		Assert.assertTrue(startVisit1And2StepsPage.selectAllTwentyOptionsForAssociatedSymptoms());
	}

	@Test(priority = 62, description = "Verify whether user able to select one or more options in 11 of 12", enabled = true)
	public void IDA4_2525_verifyUserAllowedSelectOnlyOneOptionInElevenOfTwelveQuestions() throws InterruptedException {
		// enter patient vitals and complete the ten questions

		startVisit1And2StepsPage.enterVitalsDetailsAndCompleteTenQuestions(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// verify user allowed select only one option
		Assert.assertTrue(startVisit1And2StepsPage.verifyUserAllowedToSelectOneOptionInElevenOfTwelveQuestion());

	}

	@Test(priority = 63, description = "Verify whether user able to select one or more options in 10 of 12", enabled = true)
	public void IDA4_2518_verifyUserAllowedSelectOnlyOneOptionInElevenOfTwelveQuestions() throws InterruptedException {

		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitals(appData);
		// verify user allowed to select one option
		startVisit1And2StepsPage.verifyUserAllowedToSelectOneOptionInTenOfTwelveQuestion();

		Assert.assertTrue(startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed());

	}

	@Test(priority = 64, description = "Verify selecting from search results", enabled = true)

	public void IDA4_2443_verifySelectingValueFromSearchResults() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitals(appData);

		// select value from serach results and verify
		Assert.assertTrue(startVisit1And2StepsPage.selectValueFromSearchProtocolResultsAndVerify());
	}

	/**
	 * need to add the assertions
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 65, description = "Verify the functionality of 'All reason' section", enabled = true)

	public void IDA4_2447_verifyAllReasonFunctionality() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify the all reason functionality

		Assert.assertTrue(startVisit1And2StepsPage.verifyAllReasonFuntionality());
	}

	@Test(priority = 66, description = "Verify all the reason in ''All reasons'' section", enabled = true)

	public void IDA4_2446_verifyAllReasonInAllReasonSection() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify the all reason in all reason sectionverifyAllReasonFuntionality

		Assert.assertTrue(startVisit1And2StepsPage.verifyAllReasonFuntionality());
	}

	@Test(priority = 67, description = "Verify the UI of 2/4 Visit Reason page", enabled = true)

	public void IDA4_2437_verifyUIOfVisitReasonPage() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		Assert.assertTrue(startVisit1And2StepsPage.verifyUiOfVisitReasonPage());
	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}

}
