package com.intelehealth.pages;

import java.awt.Robot;
//import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPath;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.tests.AddNewPatientTest;
import com.intelehealth.tests.VisitSummaryTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

/**
 * Page class representing the Visit summary screen. Contains elements, actions,
 * and verifications specific to this screen.
 */

public class VisitSummaryPage extends BaseTest {

	// JSON object to store test data
	JSONObject appData;

	AddNewPatientTest addNewPatientTest;
	StartVisit1And2StepsPage startVisit1And2StepsPage;
	StartVisit3And4StepsPage startVisit3And4StepsPage;
	VisitSummaryTest visitSummaryTest;
	AddNewPatientPage addNewPatientPage;
	AppSetupPage appSetupPage;
	// JSON object to store test data

	@AndroidFindBy(accessibility = "editext for height input in Vital Collection Fragment")
	private WebElement heightEditText;

	@AndroidFindBy(accessibility = "edittext for weight in Vital Collection Fragment")
	private WebElement weightEditText;

	@AndroidFindBy(accessibility = "bp systolic edittext input in Vital Collection Fragment")
	private WebElement bpSystolicEditText;

	@AndroidFindBy(accessibility = "bp diastolic edittext input in Vital Collection Fragment")
	private WebElement bpDiastolicEditText;

	@AndroidFindBy(accessibility = "pulse input edittext in Vital Collection Fragment")
	private WebElement pulseEditText;

	@AndroidFindBy(accessibility = "temperature input edittext in Vital Collection Fragment")
	private WebElement temperatureEditText;

	@AndroidFindBy(accessibility = "spo2 input edittext in Vital Collection Fragment")
	private WebElement spo2EditText;

	@AndroidFindBy(accessibility = "Respiratory Rate input edittext in Vital Collection Fragment")
	private WebElement respiratoryRateEditText;

	@AndroidFindBy(accessibility = "Associated Symptoms Questionnaire Main View Submit Button")
	private WebElement associatedSymptomsSubmit;

	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement washHandsOkayButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private WebElement jaundiceNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Normal']")
	private WebElement pallorNormalButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Slow']")
	private WebElement pinchSkinSlowButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clubbing']")
	private WebElement clubbingButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Nails are pale']")
	private WebElement nailsArePaleButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No oedema']")
	private WebElement noOedemaButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='No'])[last()]")
	private WebElement lastNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Redness']")
	private WebElement rednessButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private WebElement noButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No tenderness']")
	private WebElement noTendernessButton;

	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Submit Button")
	private WebElement visitReasonSummaryScreenConfirmButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No known allergies']")
	private WebElement noKnownAllergiesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Denied to Answer']")
	private WebElement deniedToAnswerButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Denied']")
	private WebElement deniedButton;

	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Skip'])[last()]")
	private WebElement lastSkipButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None of the above']")
	private WebElement noneOfTheAboveButton;

	@AndroidFindBy(accessibility = "Question Node Submit Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Submit']")
	private WebElement familyHistorySubmit;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='org.intelehealth.app:id/frame_10034']/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView")
	private WebElement editDetailsIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update Patient']")
	private WebElement updatePatientTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvIndicatorPatientPersonal")
	private WebElement personalSectionText;

	@AndroidFindBy(id = "org.intelehealth.app:id/imagebutton_edit_additional_document")
	private WebElement addAdditionalDocumentsButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvTitleDialogImagePicker")
	private WebElement addAdditionalDocumentText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/title_tv' and @text='Take Photo']")
	private WebElement addAdditionalDocumentTakePhoto;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/title_tv' and @text='Choose from Gallery']")
	private WebElement addAdditionalDocumentChooseFromGallery;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/title_tv' and @text='Cancel']")
	private WebElement addAdditionalDocumentCancel;

	@AndroidFindBy(id = "org.intelehealth.app:id/utils_take_picture")
	private WebElement clickPicture;

	@AndroidFindBy(accessibility = "Physical Examination Summary Fragment Submit Button")
	private WebElement physicalExaminationConfirmButton;

	@AndroidFindBy(accessibility = "Medical History Summary Fragment Submit Button")
	private WebElement medicalHistoryConfirmButton;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement doctorsSpecialityDropdown;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"General Physician\"]")
	private WebElement generalPhysician;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_vs_sendvisit")
	private WebElement sendVisitButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement sendVisitYesButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement visitSentSuccessfullyText;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_subtitle")
	private WebElement visitSentSuccessfullyToDoctorText;

	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement okayButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_vs_appointment")
	private WebElement appointmentButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tvTime_new']")
	private WebElement appointmentFirstTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_book_appointment")
	private WebElement bookAppointmentButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_yes_appointment")
	private WebElement appointmentYesButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/photo_label_tv")
	private WebElement photoText;

	@AndroidFindBy(id = "org.intelehealth.app:id/title_incoming_tv")
	private WebElement takeImageText;

	@AndroidFindBy(id = "org.intelehealth.app:id/utils_take_picture")
	private WebElement clickPictureButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/camera_switch_iv")
	private WebElement switchCamera;

	@AndroidFindBy(id = "org.intelehealth.app:id/cancel_tv")
	private WebElement cameraCancel;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select a photo']")
	private WebElement selectAPhotoText;

	@AndroidFindBy(id = "org.intelehealth.app:id/flaggedcheckbox")
	private WebElement priorityVisitToggleButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement scheduleAppointmentText;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement myAppointmentsTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private WebElement addedAppointmentPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_name_value")
	private WebElement visitSummaryPatientName;

	@AndroidFindBy(accessibility = "Home Fragment Closed Visit CardView")
	private WebElement openCloseVisits;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Share\"])[1]")
	private WebElement firstShareIcon;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,\"org.intelehealth.app:id/frame\")]")
	private WebElement navigateToCloseVisitScreen;

	@AndroidFindBy(accessibility = "move to previous screen")
	private WebElement moveToPreviousScreen;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(accessibility = "Visit Details Patient OpenMRS ID TextView")
	private WebElement visitDetailsPatientId;

	@AndroidFindBy(accessibility = "Visit Details 'End Visit' Button")
	private WebElement endVisitButton;

	@AndroidFindBy(accessibility = "Patient Survey Screen Give Feedback EditText")
	private WebElement feedbackTextArea;

	@AndroidFindBy(accessibility = "Patient Survey Screen Submit Button")
	private WebElement patientSurveySubmitButton;

	@AndroidFindBy(accessibility = "Home Fragment Find Patient TextView")
	private WebElement findPatientTextfield;

	@AndroidFindBy(accessibility = "Find Patient Screen Search Bar EditText")
	private WebElement searchPatientTextfield;

	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement alertMessageOKButton;

	@AndroidFindBy(accessibility = "Patient Details Past Visits Title TextView")
	private WebElement pastVisitTitle;

	@AndroidFindBy(accessibility = "Find Patient List Item Name TextView")
	private WebElement foundPatientName;

	@AndroidFindBy(accessibility = "Past Visit List Item Parent RelativeLayout")
	private WebElement navigateToPastVisit;

	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement visitSummaryPrintButton;

	@AndroidFindBy(id = "com.android.printspooler:id/destination_spinner")
	private WebElement selectAPrinter;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Save as PDF']")
	private WebElement saveAsPDF;

	@AndroidFindBy(accessibility = "Files")
	private WebElement files;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[1]")
	private WebElement openDownloadedPDF;

	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@text=''])[1]")
	private WebElement downloadedPDF;

	@AndroidFindBy(accessibility = "Save to PDF")
	private WebElement saveToPDF;

	@AndroidFindBy(accessibility = "Visit Summary Share Button")
	private WebElement visitSummaryShareButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SAVE']")
	private WebElement saveToFileButton;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Message TextView")
	private WebElement mobileNoToWhichYouWantToSharePrescriptionAccessibilityID;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the mobile number to which you want to share the prescription.']")
	private WebElement mobileNoToWhichYouWantToSharePrescriptionXpath;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Mobile Num EditText")
	private WebElement prescriptionToMobileNoShareButton;

	@AndroidFindBy(accessibility = "Share Prescription Dialog 'Share' Button")
	private WebElement mobileNumberTextField;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement turnOnSyncNoThanks;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement turnOnSyncAddAccount;

	@AndroidFindBy(accessibility = "Identification First Screen First Name EditText")
	private WebElement updateDetailsFirstNameTextField;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnPatientPersonalNext")
	private WebElement updateDetailsSaveButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/name")
	private WebElement updatedFirstName;

	@AndroidFindBy(accessibility = "Identification First Screen Last Name EditText")
	private WebElement updateDetailsLastNameTextField;

	@AndroidFindBy(accessibility = "Find Patient Screen Search ImageView")
	private WebElement findPatientSearchIcon;

	@AndroidFindBy(accessibility = "Identification First Screen Phone Num EditText")
	private WebElement addNewPatientPhoneNo;

	@AndroidFindBy(accessibility = "Home Fragment Prescription Arrow Icon ImageView")
	private WebElement homeScreenPrescriptionArrowIcon;

	@AndroidFindBy(accessibility = "Visit Summary Item Card Patient History Details TextView")
	private WebElement medicalHistoryDetails;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement patientDetailsScreenPersonalDetailsNameValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Edit Icon ImageView")
	private WebElement patientDetailsScreenPersonalDetailsEditIcon;

	@AndroidFindBy(accessibility = "Identification First Screen First Name EditText")
	private WebElement firstName;

	@AndroidFindBy(accessibility = "Identification First Screen Next Button")
	private WebElement updatePatientSaveButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/camera_switch_iv")
	private WebElement cameraSwitchButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/document_name_TextView")
	private WebElement addedAdditionalDocument;

	@AndroidFindBy(accessibility = "Shutter")
	private WebElement shutterButton;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='com.google.android.apps.photos:id/image']")
	private WebElement photos;

	@AndroidFindBy(xpath = "(//android.view.ViewGroup)[2]")
	private WebElement image;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='org.intelehealth.app:id/document_delete_button_ImageView']")
	private WebElement documentDeleteIcon;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"org.intelehealth.app:id/frame_10014\"]")
	private WebElement prescriptionPatients;

	@AndroidFindBy(accessibility = "Identification Activity Refresh ImageButton")
	private WebElement addNewPatientRefreshButton;

	By byPictureTaken = By.xpath("//android.widget.Toast[@text='Picture taken']");
	By byAddedAdditionalDocument = By.id("org.intelehealth.app:id/document_name_TextView");

	// Constructor
	public VisitSummaryPage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		this.driver = driver;
		addNewPatientTest = new AddNewPatientTest();
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
		startVisit3And4StepsPage = new StartVisit3And4StepsPage();
		addNewPatientPage = new AddNewPatientPage();
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

	// Adds phone number
	public void phoneNumber() throws InterruptedException {
		click(addNewPatientPhoneNo, "Clicking on phone number");
		sendKeys(addNewPatientPhoneNo, "8765985376", "Entering the phone number");
	}

	// Adds patients
	public void addPatients() throws Throwable {
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				// Test code
				Thread.sleep(20000);
				waitForVisibility(homeScreenRefreshButton);
				click(homeScreenRefreshButton, "Clicking on app sync icon");
				addNewPatientPage.clickOnAddPatients();
				Thread.sleep(3000);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnAcceptButton();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
//		        addNewPatientPage.clickRefreshButton();
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(3000);
				addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(4000);
				click(addNewPatientRefreshButton);
				addNewPatientPage.enterLastName();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.selectGender();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				scrollToElement();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnDobIcon();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnMonthSpinner();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.selectMonth();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnYearSpinner();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.scrollToViewYear();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.selectYear();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.selectDate();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnOkayButton();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				phoneNumber();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnNextButton1();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnStateSpinner();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.scrollToViewState();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.selectState();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnDistrictSpinner();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.scrollToViewDistrict();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.selectDistrict();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.enterPatientAddressDetails(
						appData.getJSONObject("patientAddress").getString("pincode"),
						appData.getJSONObject("patientAddress").getString("village"),
						appData.getJSONObject("patientAddress").getString("address1"),
						appData.getJSONObject("patientAddress").getString("address2"));
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnNextButton2();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
						appData.getJSONObject("personalDetails").getString("occupation"));
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnNextButton3();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(6000);
				waitForVisibility(patientDetailsScreenPersonalDetailsNameValue);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		String nameBeforeChanging = patientDetailsScreenPersonalDetailsNameValue.getText();
		while (attempt < maxAttempts) {
			try {
				click(patientDetailsScreenPersonalDetailsEditIcon, "Clicking on edit icon");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				click(firstName, "Clicking on first name textfield");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				sendKeys(firstName, "Automate", "Entering the name");
				click(updatePatientSaveButton, "Clicking on save button");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				waitForVisibility(patientDetailsScreenPersonalDetailsNameValue);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		String nameAfterChanging = patientDetailsScreenPersonalDetailsNameValue.getText();
		if (!nameBeforeChanging.contains("Automate") && nameAfterChanging.contains("Automate")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the details have been updated successfully");
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnStartVisitButton();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				addNewPatientPage.clickOnContinueButton();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	public void complete1and2stepsInHypertesionScreeningProtocol() throws JSONException, InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// Click on the 'Next' button after entering the first set of vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Click on the 'Confirm' button to confirm the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Scroll to the HyperTension section on the page
		startVisit3And4StepsPage.scrollToHyperTensionScreening();

		// Select and confirm the visit reason as Hypertension
		startVisit3And4StepsPage.selectAndConfirmVisitReason();

		// Handle the Hypertension-related visit questions
		startVisit3And4StepsPage.handleHypertensionVisitQuestions();
		// Verify that the 'Associated Symptoms' title is displayed on the page
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();

		// Select 'No' for the first associated symptom
		startVisit3And4StepsPage.selectAssociatedSymptomFirstNoButton();

		// Click on the main 'Submit' button for associated symptoms
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();

		// Click on the 'Confirm' button on the visit reason summary screen
		startVisit1And2StepsPage.clickOnConfirmButtonOnVisitReasonSummaryScreen();

		startVisit1And2StepsPage.verifyPleaseWashOrSanitizeYourHandsPopupIsDisplayed();
		// Click on the 'Okay' button to proceed
		startVisit1And2StepsPage.clickOnOkayButton();
	}

	// Completes all four steps of a process
	public void completing4OutOf4Steps() throws Throwable {
		complete1and2stepsInHypertesionScreeningProtocol();
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.verifyDescribeTextBoxIsDisplayed();
		// chnage xpath
		startVisit1And2StepsPage.clickOnConfirmButton();
	}

	// Verifies the functionality of the edit option beside the patient's name
	public void verifyEditOptionFunctionalityBesidePatientName() throws Throwable {
		// addPatients();
		addNewPatientPage.createVisit(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		completing4OutOf4Steps();
		click(editDetailsIcon, "Clicking on edit details icon");
		String Title = getText(updatePatientTitle, "Update patient title is displayed");
		String Text = getText(personalSectionText, "Personal Section Text is displayed");
		if (Title.equalsIgnoreCase("Update Patient") && Text.equalsIgnoreCase("Personal")) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to update patient details screen");
			System.out.println("User is navigated to update patient details screen");
		} else {
			throw new Exception("Update patient details screen is not displayed");
		}
	}

	// Verifies that changes are saved when patient details are updated
	public void verifyTheChangesAreSavedWhenPatientDetailsAreUpdated() throws Throwable {
		// addPatients();
		addNewPatientPage.createVisit(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),

				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		completing4OutOf4Steps();
		waitForVisibility(visitSummaryPatientName);
		String beforeChangingPatientName = visitSummaryPatientName.getText();
		click(editDetailsIcon, "Clicking on edit details icon");
		addNewPatientPage.enterFirstName("summary");
		addNewPatientPage.enterLastNameValue("one");
		// clear(updateDetailsFirstNameTextField, "Clearing the update details first
		// name textfield");
		// sendKeys(updateDetailsFirstNameTextField, "Update", "Entering the first
		// name");
		// sendKeys(updateDetailsLastNameTextField, "Testing", "Entering the last
		// name");
		click(updateDetailsSaveButton, "Clicking on update details save button");
		waitForVisibility(updatedFirstName);
		String afterChangingPatientName = updatedFirstName.getText();
		if (!beforeChangingPatientName.equals(afterChangingPatientName)
				&& afterChangingPatientName.equals("summary one")) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the changes made in personal screen is reflected in Visit summary screen");
		}
	}

	// Clicking on add additional documents button
	public void clickOnAddAdditionalDocument() throws Throwable {
		click(addAdditionalDocumentsButton, "Clicking on add additional documents button");
	}

	// Verifies the functionality of clicking on "Add Additional Document" button
	public void verifyClickingOnAddAdditionalDocument() throws Throwable {
		// addPatients();
		addNewPatientPage.createVisit(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),

				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		completing4OutOf4Steps();
		for (int i = 0; i < 3; i++) {
			scrollDown();
		}
		clickOnAddAdditionalDocument();
		if (isDisplayed(addAdditionalDocumentText) && isDisplayed(addAdditionalDocumentTakePhoto)
				&& isDisplayed(addAdditionalDocumentChooseFromGallery) && isDisplayed(addAdditionalDocumentCancel)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the popup is displayed");
			System.out.println("Popup is displayed");
		} else {
			throw new Exception("Popup is not displayed");
		}
	}

	// Verifies that the user is able to click a photo
	public void verifyUserIsAbleToClickPhoto() throws Throwable {
		verifyClickingOnAddAdditionalDocument();
		click(addAdditionalDocumentTakePhoto, "Clicking on take photo");
		// click(clickPicture, "Clicking on click picture icon");
		if (isDisplayed(photoText) && isDisplayed(takeImageText) && isDisplayed(clickPictureButton)
				&& isDisplayed(switchCamera) && isDisplayed(cameraCancel)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether camera is opened");
			System.out.println("Camera is opened");
		}
		Thread.sleep(5000);
		click(cameraSwitchButton, "Switching the camera");
		click(clickPictureButton, "Clicking on take picture button");
		if (isDisplayed2(byPictureTaken)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether 'Picture taken' message is displayed");
		}
		if (isDisplayed(addedAdditionalDocument)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added document is displayed");
		}
	}

	// Verifies the functionality of choosing from the gallery to add a document
	public void verifyChooseFromGalleryOptionToAddDocument() throws Throwable {
		/*
		 * launchCamera(); click(shutterButton, "Clicked on capture button");
		 * activateIntelehealth();
		 */
		
		
		verifyClickingOnAddAdditionalDocument();
		startVisit3And4StepsPage.choosePhotoFrommGallery();
		if (isDisplayed(addedAdditionalDocument)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added document is displayed");
			System.out.println("Added document is displayed");
		}

	}

	// Verifies the functionality of clicking on the close button for the uploaded
	// document
	public void verifyClickingOnCloseButtonOnTheDocumentUploaded() throws Throwable {
		verifyChooseFromGalleryOptionToAddDocument();
		click(documentDeleteIcon, "Clicking on document delete icon");
		boolean addedDocument = isDisplayed2(byAddedAdditionalDocument);
		if (addedDocument == false) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added document is deleted");
			System.out.println("Added document is deleted");
		} else {
			throw new Exception("Added document is not deleted");
		}
	}

	// Clicking on doctors speciality dropdown
	public void clickDoctorSpeciality() throws InterruptedException {
		click(doctorsSpecialityDropdown, "Clicking on doctors speciality dropdown");
	}

	// Verifies the functionality of the doctor's specialty dropdown
	public void verifyDoctorSpecialityDropdown() throws Throwable {
		addPatients();
		completing4OutOf4Steps();

		for (int i = 0; i < 3; i++) {
			scrollDown();
		}
		clickDoctorSpeciality();
		waitForVisibility(generalPhysician);
		String speciality = generalPhysician.getText();
		waitForVisibility(doctorsSpecialityDropdown);
		click(generalPhysician, "Selecting pediatrician from the dropdown");
		String selectedSpeciality = doctorsSpecialityDropdown.getText();
		if (selectedSpeciality.equals(speciality)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is able to select the specialization from the dropdown");
			System.out.println("User is able to select the specialization from the dropdown");
		}

	}

	// Clicking on priority visit toggle button
	public void priorityVisitEnable() throws InterruptedException {
		click(priorityVisitToggleButton, "Clicking on priority visit toggle button");
	}

	// Verifies if the priority visit feature is enabled
	public void verifyIfPriorityVisitIsEnabledON() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		for (int i = 0; i < 3; i++) {
			scrollDown();
		}
		clickDoctorSpeciality();
		click(generalPhysician, "Selecting genral physician from the dropdown");
		scrollDown();

		priorityVisitEnable();
		click(sendVisitButton, "Clicking on send visit button");
		click(sendVisitYesButton, "Clicking on yes button");
		if (isDisplayed(visitSentSuccessfullyText) && isDisplayed(visitSentSuccessfullyToDoctorText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether visit sent successfully");
			System.out.println("Visit sent successfully");
			click(okayButton, "Clicking on okay button");
		}
		boolean PriorityVisit = priorityVisitToggleButton.isEnabled();
		if (PriorityVisit == true) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether priority visit is sent");
			System.out.println("Priority visit is sent");
		}
		if (PriorityVisit == false) {
			throw new Exception("Priority visit is not sent");
		}
	}

	// Verifies the behavior when clicking "Yes" in the send visit popup
	public void verifyBehaviorOnClickingYesInSendVisitPopup() throws Throwable {
		completing4OutOf4Steps();
		scrollToElementByDescription("Visit Summary Speciality Spinner");
		clickDoctorSpeciality();
		click(generalPhysician, "Selecting pediatrician from the dropdown");
		click(sendVisitButton, "Clicking on send visit button");
		click(sendVisitYesButton, "Clicking on yes button");
		if (isDisplayed(visitSentSuccessfullyText) && isDisplayed(visitSentSuccessfullyToDoctorText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether visit sent successfully");
			System.out.println("Visit sent successfully");
		}
	}

	// Verifies the functionality of clicking on the appointment button after the
	// visit is sent to the doctor
	public void verifyClickingOnAppointmentButtonFunctionalityAfterVisitIsSentToDoctor() throws Throwable {
		addNewPatientPage.createVisit(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),

				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		completing4OutOf4Steps();
		verifyBehaviorOnClickingYesInSendVisitPopup();
		click(okayButton, "Clicking on okay button");
		click(appointmentButton, "Clicking on appointment button");
		Thread.sleep(3000);
		boolean scheduleAppointment = isDisplayed(scheduleAppointmentText);
		boolean bookAppointment = isDisplayed(bookAppointmentButton);
		if (scheduleAppointment == true && bookAppointment == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to schedule appointment screen");
			System.out.println("User is navigated to schedule appointment screen");
		}
	}

	// Verifies if the booked appointment is reflecting correctly in the
	// Appointments section
	public void verifyIfBookedAppointmentIsReflectingInAppointments() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		waitForVisibility(visitSummaryPatientName);
		String patientName = visitSummaryPatientName.getText();
		scrollToElementByDescription("Visit Summary Speciality Spinner");
		clickDoctorSpeciality();
		click(generalPhysician, "Selecting pediatrician from the dropdown");
		click(sendVisitButton, "Clicking on send visit button");
		click(sendVisitYesButton, "Clicking on yes button");
		click(okayButton, "Clicking on okay button");
		click(appointmentButton, "Clicking on appointment button");
		Thread.sleep(7000);
		click(appointmentFirstTime, "Selecting the time slot");
		click(bookAppointmentButton, "Clicking on book appointment button");
		click(appointmentYesButton, "Clicking on yes button");
		isDisplayed(myAppointmentsTitle);
		waitForVisibility(addedAppointmentPatientName);
		String appointmentPatientName = addedAppointmentPatientName.getText();
		if (patientName.equals(appointmentPatientName)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the booked appointment is shown in Upcoming section of Appointment");
			System.out.println("The booked appointment is shown in Upcoming section of Appointment");
		}
	}

}
