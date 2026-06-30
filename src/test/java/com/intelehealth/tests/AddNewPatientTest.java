package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

import io.appium.java_client.android.SupportsNetworkStateManagement;

public class AddNewPatientTest extends BaseTest {

	AppSetupPage appSetupPage;
	// JSON object to store test data
	JSONObject appData;
	AddNewPatientPage addNewPatientPage;
	FindPatientPage findPatientPage;
	Faker faker = new Faker(new Locale("en-IND"));
	String lastName = faker.name().lastName();

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {

		// Log information about the test
		TestUtils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// Launch the app and initialize necessary screens
		  if (!SessionHealthManager.isSessionAlive(getDriver())) {
		        throw new IllegalStateException(
		            "Driver session is dead before test: " + m.getName());
		    }
		resetApp();
	//	launchApp();
		appSetupPage = new AppSetupPage();
		addNewPatientPage = new AddNewPatientPage();
		findPatientPage = new FindPatientPage();
		InputStream datais = null;
		try {
			// Load test data from JSON file
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
		// grant all the permission
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		appSetupPage.refreshUIAndWait();
	}

	@Test(priority = 1, description = "Verify the functionality of Accept button on Privacy Policy screen", enabled = true)
	public void IDA4_2258_verifyAcceptButtonFunctionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2258_verifyAcceptButtonFunctionality",
				"Verify the functionality of Accept button on Privacy Policy screen");
		// Verify that the location is displayed
		appSetupPage.locationIsDisplayed();

		// Navigate to Add Patients screen
		addNewPatientPage.clickOnAddPatients();

		// Click on the Accept button on the Privacy Policy screen
		addNewPatientPage.clickOnAcceptButton();

		// Verify that the Add New Patient screen is displayed
		Assert.assertTrue(addNewPatientPage.verifyAddNewPatientScreen());

		Assert.assertEquals(addNewPatientPage.getAddNewPatientText(),
				Arrays.asList(expectedAssertProp.getProperty("add.new.patient.screen.title"),
						expectedAssertProp.getProperty("add.new.patient.screen.next")));
	}

	@Test(priority = 2, description = "Verify the functionality of Decline button on Privacy Policy screen", enabled = true)
	public void IDA4_2259_verifyDeclineButtonFunctionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2259_verifyDeclineButtonFunctionality",
				"Verify the functionality of Decline button on Privacy Policy screen");
		// Navigate to Add Patients screen
		addNewPatientPage.clickOnAddPatients();

		// Click on the Decline button on the Privacy Policy screen
		addNewPatientPage.clickOnDeclineButton();

		// Verify that the Dashboard screen is visible after declining
		Assert.assertTrue(addNewPatientPage.verifyDashboardScreenIsVisible());

	}

//need to check 
	@Test(priority = 3, description = "Verify Okay button functionality after selecting DOB", enabled = true)
	public void IDA4_2278_verifyOkayButtonFunctionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2278_verifyOkayButtonFunctionality",
				"Verify Okay button functionality after selecting DOB");
		// Navigate to Add Patients screen
		addNewPatientPage.clickOnAddPatients();

		// Click on the Accept button on the Privacy Policy screen
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name and last name from test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));

		// Scroll to the phone number section and select the gender
		addNewPatientPage.scrollToPhoneNumber();
		addNewPatientPage.selectGender();

		// Click on the Date of Birth (DOB) icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();

		Assert.assertTrue(addNewPatientPage.getSelectedDOB()
				.contains(expectedAssertProp.getProperty("add.new.patient.screen.dob")));
	}

// need to check
	//@Test(priority = 4, description = " Verify that Age is auto-calculated", enabled = true)
	public void IDA4_2280_verifyAgeIsAutoCalculated() throws InterruptedException {
		ExtentReport.startTest("IDA4_2280_verifyAgeIsAutoCalculated", " Verify that Age is auto-calculated");
		// Navigate to Add Patients screen
		addNewPatientPage.clickOnAddPatients();

		// Click on the Accept button on the Privacy Policy screen
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name and last name from test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		// Scroll to the phone number section and select the gender
		addNewPatientPage.scrollToPhoneNumber();
		addNewPatientPage.selectGender();

		// Scroll to the desired element (in this case, the phone number)
		// scrollToElement();

		// Optional: Click on the Date of Birth (DOB) icon if needed
		addNewPatientPage.clickOnDobIcon();

		// Select the month
		// addNewPatientPage.clickOnMonthSpinner();
//		addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		// addNewPatientPage.scrollToViewYear();
		String birthYear = "1993";
		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();

		// Verify the age after entering the date of birth
		// From where we need to pass this data

		Assert.assertTrue(addNewPatientPage.verifyAge(birthYear));

	}

	@Test(priority = 5, description = " Verify Next button functionality from Personal Screen of Add new patient", enabled = true)
	public void IDA4_2283_verifyNextButtonFuntionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2283_verifyNextButtonFuntionality",
				" Verify Next button functionality from Personal Screen of Add new patient");

		// Navigate to Add Patients screen
		addNewPatientPage.clickOnAddPatients();

		// Click on the Accept button on the Privacy Policy screen
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name and last name from test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		// Scroll to the phone number section and select the gender
		addNewPatientPage.scrollToPhoneNumber();
		addNewPatientPage.selectGender();

		// Click on the Date of Birth (DOB) icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));
		// Click on the Next button to proceed to the next screen
		addNewPatientPage.clickOnNextButton1();

		// Verify that the Address Screen is displayed
		addNewPatientPage.verifyAddressScreenIsDisplayed();

		Assert.assertEquals(addNewPatientPage.getPostalCodeText(),
				expectedAssertProp.getProperty("add.new.patient.screen.postalcode"));

	}

	@Test(priority = 6, description = "Verify Next button functionality on Address screen", enabled = true)
	public void IDA4_2295_verifyNextButtonFunctionalityOnAddressScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_2295_verifyNextButtonFunctionalityOnAddressScreen",
				"Verify Next button functionality on Address screen");

		// Navigate to Add Patients screen
		addNewPatientPage.clickOnAddPatients();

		// Click on the Accept button on the Privacy Policy screen
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name and last name from test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select gender and scroll to the desired element (phone number)
		addNewPatientPage.selectGender();

		// Click on the Date of Birth (DOB) icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month
		// addNewPatientPage.clickOnMonthSpinner();
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		// addNewPatientPage.scrollToViewYear();
		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on the Next button to proceed to the next screen
		addNewPatientPage.clickOnNextButton1();

		// Select state and district
		addNewPatientPage.clickOnStateSpinner();
		addNewPatientPage.scrollToViewState();
		addNewPatientPage.selectState();
		addNewPatientPage.clickOnDistrictSpinner();
		//addNewPatientPage.scrollToViewDistrict();
		addNewPatientPage.selectDistrict();

		// Enter patient address details and click on the Next button
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));
		addNewPatientPage.clickOnNextButton2();

		Assert.assertTrue(addNewPatientPage.getNationalIDLabel().contains(
				expectedAssertProp.getProperty("add.new.patient.screen.nationalid")));
	}

//2312 LAST 4 FROM15
	@Test(priority = 7, description = " Verify clicking on change option for personal details", enabled = true)
	public void IDA4_2309_verifyChangeOptionForPersonalDetails() throws InterruptedException {
		ExtentReport.startTest("IDA4_2309_verifyChangeOptionForPersonalDetails",
				" Verify clicking on change option for personal details");
		// Click on the "Add Patients" button
		addNewPatientPage.clickOnAddPatients();

		// Click on the "Accept" button
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name from the test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter the last name from the test data
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select the gender
		addNewPatientPage.selectGender();

		// Click on the date of birth icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton1();

		// Click on the state spinner
		addNewPatientPage.clickOnStateSpinner();

		// Scroll to view the desired state
		addNewPatientPage.scrollToViewState();

		// Select the state from the test data
		// addNewPatientPage.selectState();

		// Click on the district spinner
		// addNewPatientPage.clickOnDistrictSpinner();

		// Scroll to view the desired district
//		addNewPatientPage.scrollToViewDistrict();

		// Select the district from the test data
		// addNewPatientPage.selectDistrict();

		// Enter patient address details from the test data
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));
		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton2();

		// Enter other details such as national ID and occupation
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton3();

		// Click on the personal details change icon
		addNewPatientPage.clickOnPersonalDetailsChangeIcon();

		// patient details title assert here

		// Verify that the update patient screen is visible
		Assert.assertTrue(addNewPatientPage.verfyUpdatePatientScreenIsVisible());

		Assert.assertEquals(addNewPatientPage.getUpdatedName(),
				expectedAssertProp.getProperty("patient.details.screen.title"));
		
	}

	@Test(priority = 8, description = " Verify clicking on change option for personal details", enabled = true)

	public void IDA4_2310_verifyPersonalDetailsUpdation() throws InterruptedException {
		ExtentReport.startTest("IDA4_2310_verifyPersonalDetailsUpdation",
				" Verify clicking on change option for personal details");

		// Click on the "Add Patients" button
		addNewPatientPage.clickOnAddPatients();

		// Click on the "Accept" button
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name from the test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter the last name from the test data
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select the gender
		addNewPatientPage.selectGender();

		// Click on the date of birth icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton1();

		// Click on the state spinner
		// addNewPatientPage.clickOnStateSpinner();

		// Scroll to view the desired state
		// addNewPatientPage.scrollToViewState();

		// Select the state from the test data
		// addNewPatientPage.selectState();

		// Click on the district spinner
		// addNewPatientPage.clickOnDistrictSpinner();

		// Scroll to view the desired district
		// addNewPatientPage.scrollToViewDistrict();

		// Select the district from the test data
		// addNewPatientPage.selectDistrict();

		// Enter patient address details from the test data
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton2();

		// Enter other details such as national ID and occupation
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton3();

		// Click on the personal details change icon
		addNewPatientPage.clickOnPersonalDetailsChangeIcon();

		boolean patientLastName = addNewPatientPage
				.verifyLastNameUpdateIsSuccessful(appData.getJSONObject("personalDetails").getString("lastname"));
		// Enter a new last name value
		addNewPatientPage.enterLastNameValue("B");

		// Click on the "Save" button
		addNewPatientPage.clickOnSaveButton();

		// Verify that the last name update is successful
		boolean updatedPatientLastName = addNewPatientPage.verifyLastNameUpdateIsSuccessful("Automation B");

		Assert.assertEquals(patientLastName, updatedPatientLastName);

	}

	@Test(priority = 9, description = " Verify clicking on change option for Address details", enabled = true)
	public void IDA4_2311_verifyChangeOptionForAddressDetails() throws InterruptedException {
		ExtentReport.startTest("IDA4_2311_verifyChangeOptionForAddressDetails",
				" Verify clicking on change option for Address details");
		// Click on the "Add Patients" button
		addNewPatientPage.clickOnAddPatients();

		// Click on the "Accept" button
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name from the test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter the last name from the test data
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select the gender
		addNewPatientPage.selectGender();

		// Click on the date of birth icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton1();

		// Click on the state spinner
		// addNewPatientPage.clickOnStateSpinner();

		// Scroll to view the desired state
		// addNewPatientPage.scrollToViewState();

		// Select the state from the test data
		// addNewPatientPage.selectState();

		// Click on the district spinner
		// addNewPatientPage.clickOnDistrictSpinner();

		// Scroll to view the desired district
		// addNewPatientPage.scrollToViewDistrict();

		// Select the district from the test data
		// addNewPatientPage.selectDistrict();

		// Introduce a delay for 3000 milliseconds (3 seconds)
		Thread.sleep(3000);

		// Enter patient address details from the test data
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton2();

		// Introduce a delay for 3000 milliseconds (3 seconds)
		Thread.sleep(3000);

		// Enter other details such as national ID and occupation
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		// Introduce a delay for 3000 milliseconds (3 seconds)
		Thread.sleep(3000);

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton3();

		// Click on the address details change icon
		addNewPatientPage.clickOnAddressDetailsChangeIcon();

		// Verify that the update patient screen is visible
		Assert.assertTrue(addNewPatientPage.verfyUpdatePatientScreenIsVisible());
		Assert.assertEquals(addNewPatientPage.getPostalCodeText(),
				expectedAssertProp.getProperty("add.new.patient.screen.postalcode"));

	}

	@Test(priority = 10, description = " Verify the update of address details reflects on patient details screen", enabled = true)
	public void IDA4_2312_verifyAddressDetailsUpdation() throws InterruptedException {
		ExtentReport.startTest("IDA4_2312_verifyAddressDetailsUpdation",
				" Verify the update of address details reflects on patient details screen");
		// Click on the "Add Patients" button
		addNewPatientPage.clickOnAddPatients();

		// Click on the "Accept" button
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name from the test data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter the last name from the test data
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select the gender
		addNewPatientPage.selectGender();

		// Click on the date of birth icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton1();

		// Click on the state spinner
		// Click on the state spinner
		// addNewPatientPage.clickOnStateSpinner();

		// Scroll to view the desired state
		// addNewPatientPage.scrollToViewState();

		// Select the state from the test data
		// addNewPatientPage.selectState();

		// Click on the district spinner
		// addNewPatientPage.clickOnDistrictSpinner();

		// Scroll to view the desired district
		// addNewPatientPage.scrollToViewDistrict();

		// Select the district from the test data
		// addNewPatientPage.selectDistrict();

		// Introduce a delay for 3000 milliseconds (3 seconds)
		Thread.sleep(3000);

		// Enter patient address details from the test data
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton2();

		// Introduce a delay for 3000 milliseconds (3 seconds)
		Thread.sleep(3000);

		// Enter other details such as national ID and occupation
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		// Introduce a delay for 3000 milliseconds (3 seconds)
		Thread.sleep(3000);

		// Click on the "Next" button to proceed to the next step
		addNewPatientPage.clickOnNextButton3();

		// Click on the address details change icon
		addNewPatientPage.clickOnAddressDetailsChangeIcon();

		// Enter a new postal code
		addNewPatientPage.enterPostalCode(appData.getJSONObject("patientAddress").getString("pincode1"));

		// Click on the "Save" button
		addNewPatientPage.clickOnNextButton2();

		// Verify that the postal code update is successful
		Assert.assertTrue(addNewPatientPage
				.verifyPostalCodeUpdateIsSuccessful(appData.getJSONObject("patientAddress").getString("pincode1")));

	}

	@Test(priority = 11, description = "Verify clicking on change option for Other details", enabled = true)
	public void IDA4_2313_verifyChangeOptionForAOtherDetailsScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_2313_verifyChangeOptionForAOtherDetailsScreen",
				"Verify clicking on change option for Other details");
		// Click on the "Add Patients" button to initiate the patient addition process
		addNewPatientPage.clickOnAddPatients();

		// Click on the "Accept" button to proceed with adding a new patient
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name of the patient from the provided data
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter the last name of the patient from the provided data
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select the gender of the patient
		addNewPatientPage.selectGender();

		// Click on the date of birth (DOB) icon to set the birthdate
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on the "Next" button to proceed to the next section
		addNewPatientPage.clickOnNextButton1();

		// Click on the state spinner
		// addNewPatientPage.clickOnStateSpinner();

		// Scroll to view the desired state
		// addNewPatientPage.scrollToViewState();

		// Select the state from the test data
		// addNewPatientPage.selectState();

		// Click on the district spinner
		// addNewPatientPage.clickOnDistrictSpinner();

		// Scroll to view the desired district
		// addNewPatientPage.scrollToViewDistrict();

		// Select the district from the test data
		// addNewPatientPage.selectDistrict();

		// Pause execution for 3 seconds to allow time for the page to load
		Thread.sleep(3000);

		// Enter patient address details using the provided data
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		// Click on the "Next" button to proceed to the next section
		addNewPatientPage.clickOnNextButton2();

		// Pause execution for 3 seconds to allow time for the page to load
		Thread.sleep(3000);

		// Enter other details such as national ID and occupation from the provided data
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		// Pause execution for 3 seconds to allow time for the page to load
		Thread.sleep(3000);

		// Click on the "Next" button to proceed to the final section
		addNewPatientPage.clickOnNextButton3();

		// Scroll to view additional details on the page
		addNewPatientPage.scrollDown();

		// Click on the change icon for other details
		addNewPatientPage.clickOnOtherDetailsChangeIcon();

		// Verify that the update other details screen is visible
		addNewPatientPage.verifyUpdateOtherDetailsScreenIsVisible();

		Assert.assertTrue(addNewPatientPage.getNationalIDLabel()
				.contains(expectedAssertProp.getProperty("add.new.patient.screen.nationalid")));

	}

	@Test(priority = 12, description = " Verify the update of other details reflects on patient details screen", enabled = true)
	public void IDA4_2314_verifyUpdatedOtherDetails() throws InterruptedException {
		ExtentReport.startTest("IDA4_2314_verifyUpdatedOtherDetails",
				" Verify the update of other details reflects on patient details screen");
		// Click on the "Add Patients" button to initiate the patient addition process.
		addNewPatientPage.clickOnAddPatients();

		// Click on the "Accept" button to proceed with adding a new patient.
		addNewPatientPage.clickOnAcceptButton();

		// Enter the first name of the patient.
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter the last name of the patient.
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select the gender of the patient.
		addNewPatientPage.selectGender();

		// Click on the date of birth (DOB) icon to enter the patient's date of birth.
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Proceed to the next step of patient addition.
		addNewPatientPage.clickOnNextButton1();

		// Select the state for the patient's address.
		// addNewPatientPage.clickOnStateSpinner();
		// addNewPatientPage.scrollToViewState();
		// addNewPatientPage.selectState();

		// Select the district for the patient's address.
		/*
		 * addNewPatientPage.clickOnDistrictSpinner();
		 * addNewPatientPage.scrollToViewDistrict(); addNewPatientPage.selectDistrict();
		 */
		// Enter patient's address details including pincode, village, and address
		// lines.
		Thread.sleep(3000); // Sleep added for stability; consider using explicit waits instead.
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		// Proceed to the next step of patient addition.
		addNewPatientPage.clickOnNextButton2();
		Thread.sleep(3000); // Sleep added for stability; consider using explicit waits instead.

		// Enter other details of the patient, such as national ID and occupation.
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		Thread.sleep(3000); // Sleep added for stability; consider using explicit waits instead.

		// Proceed to the next step of patient addition.
		addNewPatientPage.clickOnNextButton3();

		// Scroll to view other details and initiate the update process.
		addNewPatientPage.scrollDown();
		boolean nationalID = addNewPatientPage
				.verifyNationalIDUpdateIsSuccessful(appData.getJSONObject("personalDetails").getString("nationalId"));

		addNewPatientPage.clickOnOtherDetailsChangeIcon();

		// Enter the updated national ID.
		addNewPatientPage.enterNationalID(appData.getJSONObject("personalDetails").getString("updatedNationalId"));

		// Save the changes made to the patient's other details.
		addNewPatientPage.clickOnNextButton2();
		addNewPatientPage.scrollDown();
		// Verify that the national ID update is successful.
		boolean updatedNationalID = addNewPatientPage.verifyNationalIDUpdateIsSuccessful(
				appData.getJSONObject("personalDetails").getString("updatedNationalId"));
		Assert.assertEquals(nationalID, updatedNationalID);
	}

	@Test(priority = 13, description = "Verify Start Visit button functionality", enabled = true)
	public void IDA4_2315_verifyStartVisitButtonFunctionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2315_verifyStartVisitButtonFunctionality",
				"Verify Start Visit button functionality");
		// Click on 'Add Patients' button
		addNewPatientPage.clickOnAddPatients();

		// Click on 'Accept' button
		addNewPatientPage.clickOnAcceptButton();

		// Enter patient's first name
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));

		// Enter patient's last name
		addNewPatientPage.enterLastNameValue(appData.getJSONObject("personalDetails").getString("lastname"));
		addNewPatientPage.scrollToPhoneNumber();
		// Select patient's gender
		addNewPatientPage.selectGender();

		// Click on 'Date of Birth' icon
		addNewPatientPage.clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		addNewPatientPage.clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		addNewPatientPage.selectYear();

		// Select the date and click on the Okay button
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		addNewPatientPage.enterPhoneNumber(appData.getJSONObject("personalDetails").getString("phoneNumber"));

		// Click on 'Next' button (Step 1)
		addNewPatientPage.clickOnNextButton1();

		// Select state
		addNewPatientPage.clickOnStateSpinner();
		addNewPatientPage.scrollToViewState();
		addNewPatientPage.selectState();

		// Select district
		addNewPatientPage.clickOnDistrictSpinner();
		addNewPatientPage.scrollToViewDistrict();
		addNewPatientPage.selectDistrict();
		Thread.sleep(3000);

		// Enter patient address details
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		// Click on 'Next' button (Step 2)
		addNewPatientPage.clickOnNextButton2();
		Thread.sleep(3000);

		// Enter other details
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		Thread.sleep(3000);

		// Click on 'Next' button (Step 3)
		addNewPatientPage.clickOnNextButton3();

		// Click on 'Start Visit' button
		addNewPatientPage.clickOnStartVisitButton();

		// Verify that the 'Patient Registered' popup is displayed
		addNewPatientPage.verifyPatientRegisteredPopupIsDisplayed();

		Assert.assertEquals(addNewPatientPage.getPatientRegisteredDialogTexts(),
				Arrays.asList(expectedAssertProp.getProperty("patient.registration.dialog.title"),
						expectedAssertProp.getProperty("patient.registration.dialog.subtitle"),
						expectedAssertProp.getProperty("patient.registration.dialog.cancel"),
						expectedAssertProp.getProperty("patient.registration.dialog.continue")));
	}

	@Test(priority = 14, description = "Verify Continue button functionality when clicked on Start visit", enabled = true)
	public void IDA4_2317_verifyContinueButtonFuntionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2317_verifyContinueButtonFuntionality",
				"Verify Continue button functionality when clicked on Start visit");
		// Register a patient
		addNewPatientPage.registerAPatient(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

		// Verify that the Vitals screen is displayed
		addNewPatientPage.verifyVitalsScreenIsDisplayed();
		// addNewPatientPage.clickOnStartVisitButton();
		// addNewPatientPage.clickOnContinueButton();
		// addNewPatientPage.clickOnAcceptButtonInTeleConsultation();
		Assert.assertEquals(addNewPatientPage.getVitalsHeader(), expectedAssertProp.getProperty("vitals.screen.title"));
	}

//	@Test(priority = 15, description = "Verify clicking on Past Visit", enabled = true)
	public void IDA4_2321_verifyPastVisitScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_2321_verifyPastVisitScreen", "Verify clicking on Past Visit");
		Thread.sleep(5000); // Click on the 'Find Patient' button
		findPatientPage.clickOnFindPatient();

		// Verify that the search box is visible
		findPatientPage.verifySearchBoxIsVisible();

		// Scroll to view the 'Prescription Received' section
		// addNewPatientPage.scrollToViewPrescriptionReceived();
		findPatientPage
				.enterValueInSearchBox(appData.getJSONObject("personalDetails").getString("prescriptionFirstName"));

		// Click on the 'Prescription Received' section
		addNewPatientPage.clickOnPrescriptionReceived();
		Thread.sleep(1000);
		// Scroll to view 'Past Visits'
		// addNewPatientPage.scrollToTextContains_Android("Open Visit");
		addNewPatientPage.scrollDown();

		// Click on the arrow (assuming it's a back arrow or similar)
		addNewPatientPage.clickOpenVisitChiefComplaint();

		// Verify that the 'Past Visit' summary screen is displayed
		Assert.assertTrue(addNewPatientPage.verifyPastVisitSummaryScreen());
	}

	//@Test(priority = 16, description = "Verify clicking on Open Visit", enabled = true)
	public void IDA4_2323_verifyOpenVisitScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_2323_verifyOpenVisitScreen", "Verify clicking on Open Visit");

		// Click on the 'Find Patient' button
		findPatientPage.clickOnFindPatient();

		// Introduce another delay for 4 seconds (assumes a reason for the delay)
		Thread.sleep(5000);

		// Scroll to view the 'Prescription Pending' section
		// addNewPatientPage.scrollToViewPrescriptionPending();
		findPatientPage
				.enterValueInSearchBox(appData.getJSONObject("personalDetails").getString("prescriptionFirstName"));

		// Click on the 'Prescription Pending' section
		addNewPatientPage.clickOnPrescriptionPending();

		// Scroll to view 'Open Visits'
		for (int i = 0; i < 2; i++) {
			// addNewPatientPage.scrollToTextContains_Android("Open Visit");
			 addNewPatientPage.scrollDown();
		}
		addNewPatientPage.scrollDown();

		// Click on the arrow (assuming it's a back arrow or similar)
		addNewPatientPage.clickOpenVisitChiefComplaint();

		// Verify that the 'Open Visit' summary screen is displayed

		Assert.assertTrue(addNewPatientPage.verifyOpenVisitSummaryScreen());

	}

	/**
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 17, description = "Verify Sync functionality", enabled = true)
	public void IDA4_2305_verifySyncFunctionality() throws InterruptedException {
		ExtentReport.startTest("IDA4_2305_verifySyncFunctionality", "Verify Sync functionality");
		toggleWiFi(false);
		// Register a patient
		String syncTime = addNewPatientPage.createVisit(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		String patientNotRegisteredText = addNewPatientPage.getOpenMRSID();
		getDriver().navigate().back();
		toggleWiFi(true);
		Thread.sleep(5000);
		String newSyncTime = addNewPatientPage.clickOnSync();

		findPatientPage.clickOnFindPatient();
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("personalDetails").getString("firstName"));
		findPatientPage.clickOnFirstPatientCard();
		String patientRegisteredText = addNewPatientPage.getOpenMRSID();
		// Assert.assertNotEquals(syncTime, newSyncTime);

		Assert.assertNotEquals(patientNotRegisteredText, patientRegisteredText);

	}

	/**
	 * Camera is not opening
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 18, description = "Verify if user clicks on While using the app/only this time", enabled = true)
	public void IDA4_2265_addProfilePhotoAndVerify() throws InterruptedException {
		ExtentReport.startTest("IDA4_2265_addProfilePhotoAndVerify",
				"Verify if user clicks on While using the app/only this time");
		addNewPatientPage.addProfilePictureAndVerify();

		Assert.assertTrue(addNewPatientPage.verifyProfilePictureIsDisplayed());

	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}
}