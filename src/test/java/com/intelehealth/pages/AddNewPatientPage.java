package com.intelehealth.pages;

import java.io.InputStream;
import java.time.Year;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;
import com.intelehealth.base.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Page class representing the Add New Patient screen. Contains elements,
 * actions, and verifications specific to this screen.
 */
public class AddNewPatientPage extends BaseTest {
	JSONObject appData;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add Patients\"]")
	private WebElement addNewPatientButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_accept_privacy")
	private WebElement acceptButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Add new patient\")")
	private WebElement addNewPatientTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputETPhoneNumber")
	private WebElement inpPhoneNumber;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_accept_consent")
	private WebElement acceptButtonInProcessingPersonalData;
	@AndroidFindBy(id = "org.intelehealth.app:id/tvIndicatorPatientPersonal")
	private WebElement personal;
	@AndroidFindBy(id = "org.intelehealth.app:id/tvIndicatorPatientPersonal")
	private WebElement personalText;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_decline_privacy")
	private WebElement declineButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement location;
	@AndroidFindBy(id = "org.intelehealth.app:id/textInputETFName")
	// accessibility = "Identification First Screen First Name EditText")
	private WebElement firstName;
	@AndroidFindBy(id = "org.intelehealth.app:id/textInputETLName")
	private WebElement lastName;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnMale")
	private WebElement male;
	@AndroidFindBy(id = "org.intelehealth.app:id/btnFemale")
	private WebElement female;

	@AndroidFindBy(id = "org.intelehealth.app:id/text_input_start_icon")
	private WebElement dobIcon;
	@AndroidFindBy(id = "org.intelehealth.app:id/textInputETDob")
	private WebElement txtDOB;

	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Month Spinner")
	private WebElement monthSpinner;

	@AndroidFindBy(id = "android:id/date_picker_header_year")
	private WebElement lbldate;

	@AndroidFindBy(xpath = "//android.widget.DatePicker[@resource-id=\"android:id/datePicker\"]/android.widget.LinearLayout/android.widget.ScrollView")
	private WebElement scrolyear;

	@AndroidFindBy(id = "android:id/date_picker_header_year")
	private WebElement yearSpinner;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement okayButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='March']")
	private WebElement march;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='1993']")
	private WebElement year;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"18\")")
	private WebElement date;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1']")
	private List<WebElement> lstYear;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputETAge")
	private WebElement ageTextBox;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnPatientPersonalNext")
	private WebElement btnNextInPersonalScreen;

	@AndroidFindBy(id = "org.intelehealth.app:id/lblPostalCode")
	private WebElement postalCodeTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputPostalCode")
	private WebElement postalCode;

	@AndroidFindBy(id = "org.intelehealth.app:id/postalcode")
	private WebElement postalCodeValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/autoCompleteState")
	private WebElement stateSpinner;

	@AndroidFindBy(id = "org.intelehealth.app:id/autoCompleteDistrict")
	private WebElement districtSpinner;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputCityVillage")
	private WebElement villageTextBox;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputAddress1")
	private WebElement correspondingAddress1;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputAddress2")
	private WebElement correspondingAddress2;

	@AndroidFindBy(id = "org.intelehealth.app:id/frag2_btn_next")
	private WebElement nextButton2;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvPhoneNumber")
	private WebElement phoneNumberTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Karnataka']")
	private WebElement state;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Uttara Kannada (Karwar)']")
	private WebElement district;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputNationalId")
	private WebElement nationalIDTextBox;

	@AndroidFindBy(id = "org.intelehealth.app:id/textInputOccupation")
	private WebElement occupationTextBox;
	// @AndroidFindBy(accessibility = "Identification Third Screen Caste Spinner")
	@AndroidFindBy(id = "org.intelehealth.app:id/autoCompleteSocialCategory")

	private WebElement categorySpinner;
	@AndroidFindBy(id = "org.intelehealth.app:id/autoCompleteEducation")
	private WebElement educationSpinner;
//	@AndroidFindBy(xpath = "Identification Third Screen Economic Spinner")
	@AndroidFindBy(id = "org.intelehealth.app:id/autoCompleteEducation")
	private WebElement economicSpinner;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='OBC']")
	private WebElement obc;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Primary']")
	private WebElement primary;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='BPL']")
	private WebElement bPL;
	// Can remove this element and use the address screen next element
	@AndroidFindBy(id = "org.intelehealth.app:id/frag2_btn_next")
	private WebElement nextButton3;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvChange")
	private WebElement personalDetailsChangeIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Update Patient\")")
	private WebElement updatePatientScreenTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/startVisitBtn")
	private WebElement startVisitButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement patientRegistrationDialogTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_subtitle")
	private WebElement patientRegistrationSubTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/negative_btn")
	private WebElement cancelButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement continueButton;
	@AndroidFindBy(accessibility = "Visit Creation Subtitle TextView")
	// Visit Creation Subtitle TextView-vitals")
	private WebElement visitCreationVitalsTitle;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_sub_title' and @text='1/4 Vitals']")
	// Visit Creation Subtitle TextView-vitals")
	private WebElement visitCreationVitalsScreen;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Prescription received']")
	private WebElement prescriptionReceived;
	@AndroidFindBy(id = "org.intelehealth.app:id/name_txtview")
	private WebElement patientName;
	@AndroidFindBy(id = "org.intelehealth.app:id/openmrsID_txt")
	private WebElement patientID;
	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Header Title TextView")
	private WebElement personalDetails;
	@AndroidFindBy(accessibility = "Patient Details Title TextView")
	private WebElement patientDetails;
	@AndroidFindBy(id = "org.intelehealth.app:id/toolbar_title")
	private WebElement visitSummaryTitle;
	@AndroidFindBy(accessibility = " Summary Item Card Vitals Title TextView")
	private WebElement vitalsTitle;
	@AndroidFindBy(accessibility = "Visit Summary Item Card Vitals Title TextView")
	private WebElement vitalsTitleInVisitSummary;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reason for visit']")
	private WebElement visitReason;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Physical Examination']")
	private WebElement physicalExamination;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medical history']")
	private WebElement medicalHistory;

	@AndroidFindBy(id = "org.intelehealth.app:id/add_docs_title")
	private WebElement additionalDocument;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Doctor's Speciality']")
	private WebElement doctorsSpeciality;

	@AndroidFindBy(accessibility = "org.intelehealth.app:id/flaggedcheckbox")
	private WebElement priorityToggle;

	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement printButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnPrescriptionView")
	private WebElement btnViewPrescription;

	@AndroidFindBy(id = "org.intelehealth.app:id/fabStartChat")
	private WebElement chatIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/vs_arrowRight")
	private WebElement lblOpnVisitChiefComplaint;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Prescription pending\"])[1]")
	private WebElement prescriptionPending;

	@AndroidFindBy(accessibility = "Patient Details Open Visits Title TextView")
	private WebElement openVisits;

	@AndroidFindBy(accessibility = "Visit Summary Appointment Button")
	private WebElement appointmentButton;
	@AndroidFindBy(accessibility = "Visit Summary Send Visit Button")
	private WebElement sendVisitButton;

	@AndroidFindBy(accessibility = "Visit Summary Add Documents Edit ImageButton")
	private WebElement addDocumentsButton;
//	@AndroidFindBy(accessibility  = "Identification First Screen Next Button")
//	private WebElement  saveButton;
//	@AndroidFindBy(accessibility  = "Identification First Screen Next Button")
//	private WebElement  saveButton;
//	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tvChange\"])[2]")
	private WebElement addressDetailsChangeIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/national_ID")
	private WebElement updatedName;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tvChange\"])[2]")
	private WebElement otherDetailsChangeIcon;
	@AndroidFindBy(accessibility = "Patient Details Screen Other Details Header Title TextView")
	private WebElement otherDetailsTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/lblNationalId")
	private WebElement nationalIdLabel;
	@AndroidFindBy(id = "org.intelehealth.app:id/btnPatientPersonalNext")
	private WebElement saveButton;

	@AndroidFindBy(accessibility = "Patient Details Refresh ImageView")
	private WebElement refreshButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_app_sync_time")
	private WebElement appSyncTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement refreshButtonInHomeScreen;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Add a picture']")
	private WebElement addPicture;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/camera_switch_iv']")
	private WebElement camera;
	@AndroidFindBy(id = "org.intelehealth.app:id/utils_take_picture")
	private WebElement captureButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/patient_imgview")
	private WebElement image;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_name_value")
	private WebElement lblVisitSummaryPatientName;
	@AndroidFindBy(id = "org.intelehealth.app:id/textView_id_value")
	private WebElement lblVisitSummaryPatientID;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Vitals']")
	private WebElement lblVisitSummaryVitalsSubTitle;

//write constructor to initialize the elements of the page using PageFactory
	public AddNewPatientPage() {
		super();
	}

	public AddNewPatientPage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		this.driver = driver;

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
	}
	// Actions for interacting with page elements

	// Click on the "Add Patients" button to initiate the patient addition process
	public void clickOnAddPatients() throws InterruptedException {
		Thread.sleep(5000);
		click(addNewPatientButton);
	}

	// Click on the "Accept" button to confirm or acknowledge an action
	public void clickOnAcceptButton() {
		click(acceptButton);
		click(acceptButtonInProcessingPersonalData);

	}

	public void clickOnAcceptButtonInTeleConsultation() throws InterruptedException {
		click(acceptButton);

	}

	public void enterPhoneNumber(String phoneNumber) throws InterruptedException {
		sendKeys(inpPhoneNumber, phoneNumber);
	}

	// Click on the "Decline" button to reject or decline an action
	public void clickOnDeclineButton() throws InterruptedException {
		click(declineButton, "Clicked on the 'Decline' button to reject or decline an action");
	}

	public void selectDateNew() {

	}

	// Click on the month spinner to interact with the month selection dropdown
	public void clickOnMonthSpinner() throws InterruptedException {
		click(monthSpinner);
	}

	// Click on the year spinner to interact with the year selection dropdown
	public void clickOnYearSpinner() throws InterruptedException {
		click(yearSpinner);
	}

	// Click on the state spinner to interact with the state selection dropdown
	public void clickOnStateSpinner() throws InterruptedException {
		click(stateSpinner);
	}

	// Click on the district spinner to interact with the district selection
	// dropdown
	public void clickOnDistrictSpinner() throws InterruptedException {
		click(districtSpinner);
	}

	// Click on the date of birth (DOB) icon to initiate date selection
	public void clickOnDobIcon() throws InterruptedException {
		click(dobIcon);
	}

	// Select the month, e.g., March, from the dropdown
	public void selectMonth() throws InterruptedException {
		click(march);
	}

	// Select the year from the dropdown

	/*
	 * @SuppressWarnings("deprecation") public void selectYear() throws
	 * InterruptedException {
	 * 
	 * for (int scrollcount = 0; scrollcount < 5; scrollcount++) { try { scrollUp();
	 * if (scrollcount >= 3) { click(year); break; } } catch (Exception e) {
	 * 
	 * } }
	 * 
	 * }
	 */

	public String getSelectedYearText() {
		return year.getText();
	}

	// Select a date by interacting with the date element
	public void selectDate() throws InterruptedException {
		click(date);
	}

	public void selectYear() throws InterruptedException {
		for (int scrollcount = 0; scrollcount < 5; scrollcount++) {
			try {
				scrollUp();
				if (scrollcount >= 3) {
					for (int yearNum = 0; yearNum < lstYear.size(); yearNum++) {
						if (lstYear.get(yearNum).getText().equals("1993")) {
							// click(year);
							lstYear.get(yearNum).click();
							break;
						}
					}
				}
			} catch (Exception e) {

			}
		}

	}

	// Click on the "Okay" button to confirm or finalize an action
	public void clickOnOkayButton() throws InterruptedException {
		click(okayButton);
		System.out.println("clicked");
	}

	// Click on the Next button to proceed to the next screen
	public void clickOnNextButton1() throws InterruptedException {
		click(btnNextInPersonalScreen);
	}

	// Verify the elements on the Add New Patient screen
	public boolean verifyAddNewPatientScreen() throws InterruptedException {
		return isDisplayed(addNewPatientTitle, "Add New Patient title is displayed on the screen")
				&& personal.isEnabled(); // Assuming `personal` is an element, you might want to check its state
	}

	public List<String> getAddNewPatientText() {
		return getElementsText(addNewPatientTitle, btnNextInPersonalScreen);
	}

	// Click on the Next button to proceed to the next screen
	public void clickOnNextButton2() throws InterruptedException {
		click(nextButton2);
	}

	// Click on the Next button (Step 3) to proceed to the next screen
	public void clickOnNextButton3() throws InterruptedException {
		click(nextButton3);
	}

	// Enter the first name in the corresponding field
	public void enterFirstName(String txt) throws InterruptedException {
		int maxAttempts = 5;
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {

				// Clear the first name field
				clear(firstName);

				// Enter the provided text into the first name field
				sendKeys(firstName, txt);

				// Additional actions if needed, such as clicking another element or submitting
				// the form
				// For example: click(submitButton);

				break; // Break the loop if the actions are successful
			} catch (StaleElementReferenceException ex) {
				System.out.println("StaleElementReferenceException caught. Retrying enterFirstName attempt " + attempt);
				// Add a small delay before retrying (customize based on your needs)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// Enter a randomly generated last name into the corresponding field
//	public void enterLastName() {
//	    Faker faker = new Faker(new Locale("en-IND"));
//	    String text = faker.name().lastName();
//	    sendKeys(lastName, text);
//	}
	public void enterLastName() throws InterruptedException {
		int maxAttempts = 3; // Maximum number of attempts to handle StaleElementReferenceException
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				Faker faker = new Faker(new Locale("en-IND"));
				String text = faker.name().lastName();
				sendKeys(lastName, text);
				break; // Break out of the loop if the operation is successful
			} catch (StaleElementReferenceException e) {
				// Handle StaleElementReferenceException by refreshing the element or waiting
				// You may customize this part based on your application's behavior
				attempt++;
				System.out.println("StaleElementReferenceException occurred. Retrying... (Attempt " + attempt + ")");
			}
		}

		if (attempt == maxAttempts) {
			// Log or throw an exception if the maximum attempts are reached
			System.out.println("Maximum attempts reached. Unable to handle StaleElementReferenceException.");
			// Alternatively, you can throw a custom exception or log the error as needed
		}
	}

	// Enter a specific last name value into the corresponding field
	public void enterLastNameValue(String txt) throws InterruptedException {
		sendKeys(lastName, txt, "Entered the last name: " + txt);
	}

	// Select the gender, e.g., Female, from the available options
	public void selectGender() throws InterruptedException {
		click(female);
	}

	// Select the state from the available options
	public void selectState() throws InterruptedException {
		/*
		 * for(int scrollcount = 0; scrollcount < 5; scrollcount++) { try {
		 * scrollDown(); if(scrollcount >= 3) { click(state); break; } } catch(Exception
		 * e) {
		 * 
		 * } }
		 */
		// click(state);
		AndroidDriver androidDriver = (AndroidDriver) driver.get();

		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select Telemedicine 2
		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select Telemedicine 3
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select Telemedicine 3
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}

	// Select the district from the available options
	public void selectDistrict() throws InterruptedException {
		// click(district);
		AndroidDriver androidDriver = (AndroidDriver) driver.get();

		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select Telemedicine 2
		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select Telemedicine 3
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select Telemedicine 3
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));

	}

	// Click on the "Change" icon in the personal details section
	public void clickOnChange() throws InterruptedException {
		click(personalDetailsChangeIcon, "Clicked on the 'Change' icon in the personal details section");
	}

	// Click on the Save button to save the entered details
	public void clickOnSaveButton() throws InterruptedException {
		click(saveButton);
	}

//	// Verify if the displayed age matches the expected age
//	public void verifyAge(String expectedAge) {
//	    // Get the current age value from the ageTextBox
//	    String age = ageTextBox.getText();
//
//	    // Compare the displayed age with the expected age
//	    if (Integer.parseInt(age) == Integer.parseInt(expectedAge)) {
//	        System.out.println("Age verification successful!");
//	    } else {
//	        System.out.println("Age verification failed. Please enter the correct age.");
//	    }
//	}

	public boolean verifyAge(String birthYear) {

		/*
		 * // Get the current age value from the ageTextBox String age =
		 * ageTextBox.getText(); // Extract numeric parts from the age and expectedAge
		 * strings int actualAge = extractNumeric(age); int expectedAgeValue =
		 * extractNumeric(expectedAge);
		 * 
		 * // Compare the extracted age with the expected age if (actualAge ==
		 * expectedAgeValue) { // System.out.println("Age verification successful!");
		 * return true; } else { return false; }
		 */

		try {
			int currentYear = Year.now().getValue();
			int expectedAge = currentYear - Integer.parseInt(birthYear.trim()); // Calculate expected age

			String actualAgeText = ageTextBox.getText().split(" ")[0]; // Extract numeric part of "42 years"
			int actualAge = Integer.parseInt(actualAgeText.trim());

			return expectedAge == actualAge; // Return comparison result
		} catch (NumberFormatException e) {
			// Log error or handle the exception as per your application's logging standards
			System.err.println("Error parsing age: " + e.getMessage());
			return false;
		}
	}

	// Helper method to extract numeric part from a string
	private int extractNumeric(String input) {
		String numericPart = input.replaceAll("[^0-9]", "");
		try {
			return Integer.parseInt(numericPart);
		} catch (NumberFormatException e) {
			// Handle the exception or log an error message
			e.printStackTrace();
			return -1; // Return a default value or handle the error accordingly
		}
	}

	// Scroll to the element related to the phone number in the Identification First
	// Screen
	public WebElement scrollToPhoneNumber() {
		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().resourceId(\"org.intelehealth.app:id/textInputETPhoneNumber\"))"));
	}

	// Scroll to view the element related to the year, e.g., "1981"
	public WebElement scrollToViewYear() {

		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"1981\"));"));
	}

	// Scroll to view the element related to the state, e.g., "Kerala"
	public WebElement scrollToViewState() {
		return null;// getDriver().findElement(
		// AppiumBy.androidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"Kerala\"));"));
	}

	// Scroll to view the element related to the district, e.g., "Vijayapura
	// (Bijapur)"
	public WebElement scrollToViewDistrict() {
		return null;/*
					 * getDriver().findElement(AppiumBy.androidUIAutomator(
					 * "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Vijayapura (Bijapur)\"));"
					 * ));
					 */
	}

	// Scroll to view the element related to other details on the Patient Details
	// screen
	public WebElement scrollToViewOtherDetails() {
		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Patient Details Screen Other Details Header Title TextView\"));"));
	}

	// Scroll to view the element related to past visits on the Patient Details
	// screen
	public WebElement scrollToViewPastVisits() {
		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().description(\"Open Visit\"));"));
	}

	// Scroll to view the element related to priority visits in the Visit Summary
	public WebElement scrollToViewPriorityVisits() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"Visit Summary Priority Title TextView\"));"));
	}

	// Scroll to view the element related to prescription received in the Patient
	// List
	public WebElement scrollToViewPrescriptionReceived() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Prescription received\").instance(0))"));
	}

	// Scroll to view the element related to open visits in the Patient Details
	public WebElement scrollToViewOpenVisits() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"Patient Details Open Visits Title TextView\"));"));
	}

	// Scroll to view the element related to prescription pending in the Patient
	// List
	public WebElement scrollToViewPrescriptionPending() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Prescription pending\").instance(0))"));

	}

	// Scroll to view the element related to the Send Visit Button in the Visit
	// Summary
	public WebElement scrollToViewSendVisitButton() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"Visit Summary Send Visit Button\"));"));
	}

	// Verify that the Address Screen is displayed by checking the presence of the
	// postal code title
	public void verifyAddressScreenIsDisplayed() throws InterruptedException {
		isDisplayed(postalCodeTitle, "Address Screen is displayed by checking the presence of the postal code title");
	}

	public String getPostalCodeText() {
		return postalCodeTitle.getText();
	}

	// Enter the postal code into the corresponding field
	public void enterPostalCode(String txt) throws InterruptedException {
		clear(postalCode);
		sendKeys(postalCode, txt, "Entered the postal code: " + txt);
	}

	// Enter patient address details, including postal code, village, and address
	// lines
	public void enterPatientAddressDetails(String code, String village, String address1, String address2)
			throws InterruptedException {
		sendKeys(postalCode, code);
		sendKeys(villageTextBox, village);
		sendKeys(correspondingAddress1, address1);
		sendKeys(correspondingAddress2, address2);
	}

//dropdown elements no xpath
	// Enter other details including national ID, occupation, category, education,
	// and economic status
	public void enterOtherDetails(String id, String occupation) throws InterruptedException {
		sendKeys(nationalIDTextBox, id);
		sendKeys(occupationTextBox, occupation);/**
												 * to be uncommented when locators available for drpdowns
												 */
		// click(categorySpinner);
		// click(obc);
		// click(educationSpinner);
		// click(primary);
		// click(economicSpinner);
		// click(bPL);
	}

	// Enter the national ID into the corresponding field
	public void enterNationalID(String id) throws InterruptedException {
		clear(nationalIDTextBox);
		sendKeys(nationalIDTextBox, id, "Entered national ID: " + id);
	}

	// Enter the occupation into the corresponding field
	public void enterOccupation(String occupation) throws InterruptedException {
		sendKeys(occupationTextBox, occupation, "Entered occupation: " + occupation);
	}

	// Click on the "Change" icon in the personal details section
	public void clickOnPersonalDetailsChangeIcon() throws InterruptedException {
		click(personalDetailsChangeIcon, "Clicked on the 'Change' icon in the personal details section");
	}

	// Verify that the Update Patient Screen is visible by checking the presence of
	// the title
	public boolean verfyUpdatePatientScreenIsVisible() throws InterruptedException {
		return isDisplayed(updatePatientScreenTitle,
				"Update Patient Screen is visible by checking the presence of the title");
	}

	// Verify if the last name update is successful by comparing the actual and
	// expected text
	public boolean verifyLastNameUpdateIsSuccessful(String expectedText) {
		try {
			String actualText = updatedName.getAttribute("value");
			return actualText.equals(expectedText);
		} catch (Exception e) {
			// Handle any exceptions here (e.g., element not found)
			e.printStackTrace();
			return false;
		}
	}

	// Click on the "Change" icon in the address details section
	public void clickOnAddressDetailsChangeIcon() throws InterruptedException {
		click(addressDetailsChangeIcon, "Clicked on the 'Change' icon in the address details section");
	}

	// Verify if the postal code update is successful by comparing the actual and
	// expected text
	public boolean verifyPostalCodeUpdateIsSuccessful1(String expectedText) {
		try {
			String actualText = postalCode.getAttribute("value");
			System.out.println(actualText);
			return actualText.equals(expectedText);
		} catch (Exception e) {
			// Handle any exceptions here (e.g., element not found)
			e.printStackTrace();
			return false;
		}
	}

	// Verify if the national ID update is successful by comparing the actual and
	// expected text
	public boolean verifyNationalIDUpdateIsSuccessful(String expectedText) {
		try {
			String actualText = updatedName.getAttribute("text");
			return actualText.equals(expectedText);
		} catch (Exception e) {
			// Handle any exceptions here (e.g., element not found)
			e.printStackTrace();
			return false;
		}
	}

	// Update the postal code with the provided code
	public void updatePostalCode(String code) throws InterruptedException {
		sendKeys(postalCode, code, "Updated the postal code to: " + code);
	}

	// Verify if the postal code update is successful by comparing the actual and
	// expected text
	public boolean verifyPostalCodeUpdateIsSuccessful(String expectedText) {
		try {
			int actualText = Integer.valueOf(postalCodeValue.getText());
			return actualText == Integer.valueOf(expectedText);
		} catch (Exception e) {
			// Handle any exceptions here (e.g., element not found)
			e.printStackTrace();
			return false;
		}
	}

	// Click on the "Change" icon in the other details section
	public void clickOnOtherDetailsChangeIcon() throws InterruptedException {
		scrollDown();
		click(otherDetailsChangeIcon, "Clicked on the 'Change' icon in the other details section");
	}

	// Verify that the Update Other Details Screen is visible by checking the
	// presence of the national ID label
	public boolean verifyUpdateOtherDetailsScreenIsVisible() throws InterruptedException {
		return isDisplayed(nationalIdLabel,
				"Update Other Details Screen is visible by checking the presence of the national ID label");
	}

	// Click on the "Start Visit" button
	public void clickOnStartVisitButton() throws InterruptedException {
		click(startVisitButton);
	}

	// Verify that the Patient Registered Popup is displayed by checking the
	// presence of its elements
	public boolean verifyPatientRegisteredPopupIsDisplayed() throws InterruptedException {
		return isDisplayed(patientRegistrationDialogTitle, "Patient Registered Popup is displayed - Title")
				&& isDisplayed(patientRegistrationSubTitle, "Patient Registered Popup is displayed - Subtitle")
				&& isDisplayed(cancelButton, "Patient Registered Popup is displayed - Cancel Button")
				&& isDisplayed(continueButton, "Patient Registered Popup is displayed - Continue Button");
	}

	public List<String> getPatientRegisteredDialogTexts() {
		return getElementsText(patientRegistrationDialogTitle, patientRegistrationSubTitle, cancelButton,
				continueButton);
	}

	// Click on the "Continue" button in the Patient Registered Popup
	/*
	 * public void clickOnContinueButton() throws InterruptedException { for (int i
	 * = 0; i < 2; i++) { try { waitForVisibility(continueButton);
	 * click(continueButton); } catch (Exception e) { click(startVisitButton); } } }
	 */
	public void clickOnContinueButton() {
	    for (int i = 0; i < 2; i++) {
	        try {
	           waitForVisibility(continueButton);
	            continueButton.click();
	            return; 
	        } catch (TimeoutException e) {
	            if (i == 0) {
	               
	                click(startVisitButton);
	            } else {
	                throw new RuntimeException("Continue button not clickable after retry", e);
	            }
	        }
	    }
	}

	// Verify that the Vitals Screen is displayed by checking the presence of its
	// title
	public void verifyVitalsScreenIsDisplayed() throws InterruptedException {
		isDisplayed(visitCreationVitalsScreen, "Vitals Screen is Displayed ");
	}

	public String getVitalsHeader() {
		return visitCreationVitalsScreen.getText();

	}

	// Click on the "Prescription Received" checkbox
	public void clickOnPrescriptionReceived() throws InterruptedException {
		click(prescriptionReceived, "Clicked on the 'Prescription Received' checkbox");
	}

	// Click on the arrow (presumably for navigation)
	public void clickOpenVisitChiefComplaint() throws InterruptedException {
		tapElement(lblOpnVisitChiefComplaint);

		// click(arrow, "Clicked on the 'Arrow' for navigation");
	}

	// Verify elements on the Past Visit Summary Screen
	public boolean verifyPastVisitSummaryScreen() throws InterruptedException {
		boolean isVisitSummaryScreenDisplayed = isDisplayed(visitSummaryTitle,
				"Past Visit Summary Screen - Title is Displayed")
				&& isDisplayed(lblVisitSummaryPatientName, "Past Visit Summary Screen - Patient Name is Displayed")
				&& isDisplayed(lblVisitSummaryPatientID, "Past Visit Summary Screen - Patient ID is Displayed")
				&& isDisplayed(lblVisitSummaryVitalsSubTitle,
						"Past Visit Summary Screen - Visit Creation Vitals Title is Displayed")
				&& isDisplayed(vitalsTitleInVisitSummary, "Past Visit Summary Screen - Vitals Title is Displayed")
				&& isDisplayed(visitReason, "Past Visit Summary Screen - Visit Reason is Displayed");

		if (!isVisitSummaryScreenDisplayed) {
			return false;
		}

		scrollDown();
		boolean isPhysicalExaminationDisplayed = isDisplayed(physicalExamination,
				"Past Visit Summary Screen - Physical Examination is Displayed");

		scrollDown();
		boolean isMedicalHistoryDisplayed = isDisplayed(medicalHistory,
				"Past Visit Summary Screen - Medical History is Displayed");
		scrollDown();

		boolean areOtherElementsDisplayed = isDisplayed(additionalDocument,
				"Past Visit Summary Screen - Additional Document is Displayed")
				&& isDisplayed(doctorsSpeciality, "Past Visit Summary Screen - Doctor's Speciality is Displayed")
				&& isDisplayed(btnViewPrescription, "Past Visit Summary Screen - View Prescription Button is Displayed")
				&& isDisplayed(chatIcon, "Past Visit Summary Screen - Chat Icon is Displayed");

		// Combine all checks into a single result
		return isVisitSummaryScreenDisplayed && isPhysicalExaminationDisplayed && isMedicalHistoryDisplayed
				&& areOtherElementsDisplayed;
	}

	// Click on the "Prescription Pending" checkbox
	public void clickOnPrescriptionPending() throws InterruptedException {
		click(prescriptionPending, "Clicked on the 'Prescription Pending' checkbox");
	}

	// Verify elements on the Open Visit Summary Screen
	public boolean verifyOpenVisitSummaryScreen() throws InterruptedException {
		boolean isVisitSummaryScreenDisplayed = isDisplayed(visitSummaryTitle,
				"Open Visit Summary Screen - Title  is Dsiplayed")
				&& isDisplayed(lblVisitSummaryPatientName, "Past Visit Summary Screen - Patient Name is Displayed")
				&& isDisplayed(lblVisitSummaryPatientID, "Past Visit Summary Screen - Patient ID is Displayed")
				&& isDisplayed(lblVisitSummaryVitalsSubTitle,
						"Past Visit Summary Screen - Visit Creation Vitals Title is Displayed")
				&&

				isDisplayed(visitReason, "Open Visit Summary Screen - Visit Reason  is Dsiplayed");
		if (!isVisitSummaryScreenDisplayed) {
			return false;
		}

		scrollDown();

		boolean phyExam = isDisplayed(physicalExamination,
				"Open Visit Summary Screen - Physical Examination  is Dsiplayed");
		scrollDown();
		boolean isMedicalHistoryDisplayed = isDisplayed(medicalHistory,
				"Open Visit Summary Screen - Medical History  is Dsiplayed");
		// scrollToTextContains_Android("Priority Visits");
		scrollDown();
		boolean remainingElementsDisplayed = isDisplayed(additionalDocument,
				"Open Visit Summary Screen - Additional Document  is Dsiplayed")
				&& isDisplayed(doctorsSpeciality, "Open Visit Summary Screen - Doctor's Speciality  is Dsiplayed")
				&& isDisplayed(priorityToggle, "Open Visit Summary Screen - Priority Toggle  is Dsiplayed")
				&& isDisplayed(appointmentButton, "Open Visit Summary Screen - Appointment Button  is Dsiplayed")
				&& isDisplayed(sendVisitButton, "Open Visit Summary Screen - Send Visit Button  is Dsiplayed");

		return isVisitSummaryScreenDisplayed && phyExam && isMedicalHistoryDisplayed && remainingElementsDisplayed;
	}

	public void registerAPatient(String txt, String phoneNumber, String code, String village, String address1,
			String address2, String id, String occupation) throws InterruptedException {
		waitForVisibility(addNewPatientButton);
		// Click on the 'Add Patients' button to initiate the registration process
		clickOnAddPatients();

		// Click on the 'Accept' button to agree to the registration terms
		clickOnAcceptButton();
		// Thread.sleep(8000);

//		// Enter the first name of the patient

		enterFirstName(txt);
		// Enter the last name of the patient (assuming it's handled within the method)
		enterLastName();
		// Select the gender of the patient
		selectGender();
		// Scroll to an element on the page (assuming it's a required action)
		scrollToPhoneNumber();
		// Click on the 'Date of Birth' icon to set the patient's birthdate
		clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		selectYear();

		// Select the date and click on the Okay button
		selectDate();
		clickOnOkayButton();
		enterPhoneNumber(phoneNumber);
		// Move to the next registration step
		clickOnNextButton1();

		// Select the state of the patient's address
		// clickOnStateSpinner();
		// scrollToViewState();
		// selectState();

		// Select the district of the patient's address
		// clickOnDistrictSpinner();
		// scrollToViewDistrict();
		// selectDistrict();

		// Enter the patient's address details
		enterPatientAddressDetails(code, village, address1, address2);

		// Move to the next registration step
		clickOnNextButton2();

		// Enter additional details such as national ID and occupation
		enterOtherDetails(id, occupation);

		// Move to the next registration step
		clickOnNextButton3();

		// Click on the 'Start Visit' button to initiate the patient visit
		clickOnStartVisitButton();

		// Click on the 'Continue' button to proceed with the registration process
		clickOnContinueButton();
		clickOnAcceptButtonInTeleConsultation();

	}

	/*
	 * public void verifySyncFunctionality(String txt, String code, String village,
	 * String address1, String address2, String id, String occupation) throws
	 * InterruptedException { String syncTime = appSyncTime.getText();
	 * System.out.println(syncTime); // Click on the 'Add Patients' button to
	 * initiate the registration process clickOnAddPatients();
	 * 
	 * // Click on the 'Accept' button to agree to the registration terms
	 * clickOnAcceptButton();
	 * 
	 * // // Enter the first name of the patient
	 * 
	 * enterFirstName(txt); // Enter the last name of the patient (assuming it's
	 * handled within the method) enterLastName(); // Select the gender of the
	 * patient selectGender(); // Scroll to an element on the page (assuming it's a
	 * required action) scrollToPhoneNumber(); // Click on the 'Date of Birth' icon
	 * to set the patient's birthdate clickOnDobIcon();
	 * 
	 * // Click on the month spinner to select the birth month
	 * clickOnMonthSpinner(); selectMonth();
	 * 
	 * // Click on the year spinner to select the birth year clickOnYearSpinner();
	 * 
	 * // Scroll to view and select the birth year scrollToViewYear(); selectYear();
	 * 
	 * // Select the birth date selectDate();
	 * 
	 * // Click on the 'Okay' button to confirm the selected date
	 * clickOnOkayButton();
	 * 
	 * // Move to the next registration step clickOnNextButton1();
	 * 
	 * // Select the state of the patient's address clickOnStateSpinner();
	 * scrollToViewState(); selectState();
	 * 
	 * // Select the district of the patient's address clickOnDistrictSpinner();
	 * scrollToViewDistrict(); selectDistrict();
	 * 
	 * // Enter the patient's address details enterPatientAddressDetails(code,
	 * village, address1, address2);
	 * 
	 * // Move to the next registration step clickOnNextButton2();
	 * 
	 * // Enter additional details such as national ID and occupation
	 * enterOtherDetails(id, occupation);
	 * 
	 * // Move to the next registration step clickOnNextButton3();
	 * 
	 * 
	 * 
	 * 
	 * click(refreshButton, "Clicked on Sync"); getDriver().navigate().back();
	 * String currentSyncTime = appSyncTime.getText();
	 * System.out.println(currentSyncTime); if (syncTime.equals(currentSyncTime)) {
	 * ExtentReport.getTest().log(Status.INFO, "Data not Synced");
	 * System.out.println("Data Sync Failed"); } else {
	 * ExtentReport.getTest().log(Status.INFO,
	 * "Verification of Data sync is success");
	 * System.out.println("Data synced Successfully"); }
	 * 
	 * }
	 */
	public String createVisit(String txt, String phoneNumber, String code, String village, String address1,
			String address2, String id, String occupation) throws InterruptedException {
		String syncTime = appSyncTime.getText();
		System.out.println(syncTime);
		// Click on the 'Add Patients' button to initiate the registration process
		clickOnAddPatients();

		// Click on the 'Accept' button to agree to the registration terms
		clickOnAcceptButton();

//	// Enter the first name of the patient

		enterFirstName(txt);
		// Enter the last name of the patient (assuming it's handled within the method)
		enterLastName();
		// Select the gender of the patient
		selectGender();
		// Scroll to an element on the page (assuming it's a required action)
		scrollToPhoneNumber();
		// Click on the 'Date of Birth' icon to set the patient's birthdate
		clickOnDobIcon();

		// Select the month and wait for a while
		// addNewPatientPage.clickOnMonthSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.selectMonth();

		// Select the year
		clickOnYearSpinner();
		Thread.sleep(3000);
		// addNewPatientPage.scrollToViewYear();
		Thread.sleep(3000);

		selectYear();

		// Select the date and click on the Okay button
		selectDate();
		clickOnOkayButton();

		enterPhoneNumber(phoneNumber);
		// Move to the next registration step
		clickOnNextButton1();

		// Select the state of the patient's address
		enterPatientAddressDetails(code, village, address1, address2);

		// Move to the next registration step
		clickOnNextButton2();

		// Enter additional details such as national ID and occupation
		enterOtherDetails(id, occupation);

		// Move to the next registration step
		clickOnNextButton3();

		return syncTime;
	}

	public String getOpenMRSID() {
		return patientID.getText();
	}

	public String clickOnSync() {
		refreshButtonInHomeScreen.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return appSyncTime.getText();
	}

	// Add a profile picture and verify the process
	public void addProfilePictureAndVerify() {
		click(addNewPatientButton, "Clicked on 'Add Patients'");
		clickOnAcceptButton();
		// click(acceptButton, "Accepted the Privacy Policy");
		click(addPicture, "Clicked on 'Add Picture'");
		// click(camera, "Clicked on 'Camera'");
		click(captureButton, "Captured an Image");

	}

	public boolean verifyProfilePictureIsDisplayed() {
		return isDisplayed(image, "Captured Image is Displayed");

	}

	public boolean verifyDashboardScreenIsVisible() {
		return isDisplayed(location);

	}

	public String getSelectedDOB() {
		return txtDOB.getText();
	}

	public String getNationalIDLabel() {
		return nationalIdLabel.getText();
	}

	public String getUpdatedName() {
		return updatePatientScreenTitle.getText();
	}

	public void selectYear1996() {
		// WebElement scrollView =
		// driver.findElement(By.xpath("//android.widget.ScrollView"));

		boolean found = false;

		for (int i = 0; i < 10; i++) {
			try {

				WebElement year = scrolyear.findElement(By.xpath("//android.widget.TextView[@text='1996']"));
				year.click();
				found = true;
				break;
			} catch (NoSuchElementException e) {
				swipeUpInsideElement(scrolyear);
			}
		}

		if (!found) {
			throw new RuntimeException("Year 1996 not found after scrolling.");
		}
	}

	public void swipeUpInsideElement(WebElement element) {
		driver.get().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\"1996\"));")).click();
	}

}