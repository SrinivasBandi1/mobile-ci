package com.intelehealth.pages;

import java.awt.Robot;
import java.io.InputStream;

import javax.swing.Scrollable;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.api.APIServices;
import com.intelehealth.api.PayloadGenerator;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.restassured.specification.RequestSpecification;

public class PrescriptionsPage extends BaseTest {

	Robot robot;

	DashboardModulePage dashboardModulePage;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_prescriptions")
	private WebElement homeScreenPrescriptionArrowIcon;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_patname_txtview'])[1]")
	private WebElement recentVisitPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/fu_date_txtview")
	private WebElement recentVisitDateAndTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_share")
	private WebElement recentVisitShareIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/editText_mobileno")
	private WebElement enterTheMobileNoPopupText;

	@AndroidFindBy(id = "org.intelehealth.app:id/sharebtn")
	private WebElement sharePrescriptionPopupShareButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/message")
	private WebElement sharePrescriptionPopupHeader;

	@AndroidFindBy(id = "org.intelehealth.app:id/editText_mobileno")
	private WebElement sharePrescriptionPopupMobileNumTextfield;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement turnOnSyncNoThanks;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement turnOnSyncAddAccount;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Share\"])[last()]")
	private WebElement lastShare;

	@AndroidFindBy(id = "org.intelehealth.app:id/label")
	private WebElement visitDetailsPageTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/patname_txt")
	private WebElement visitDetailsPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/gender_age_txt")
	private WebElement visitDetailsPatientGenderAge;

	@AndroidFindBy(id = "org.intelehealth.app:id/openmrsID_txt")
	private WebElement visitDetailsPatientID;

	@AndroidFindBy(id = "org.intelehealth.app:id/pat_call_btn")
	private WebElement visitDetailsPatientCallIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/pat_whatsapp_btn")
	private WebElement visitDetailsPatientWhatsappIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/chief_complaint_txt")
	private WebElement visitDetailsReasonForVisit;

	@AndroidFindBy(id = "org.intelehealth.app:id/visitID")
	private WebElement visitDetailsVisitID;

	@AndroidFindBy(id = "org.intelehealth.app:id/visit_startDate")
	private WebElement visitDetailsDate;

	@AndroidFindBy(id = "org.intelehealth.app:id/visit_startTime")
	private WebElement visitDetailsTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/visit_speciality")
	private WebElement visitDetailsDoctorSpeciality;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Visit summary']")
	private WebElement visitDetailsVisitSummary;

	@AndroidFindBy(id = "org.intelehealth.app:id/visitDetails_text")
	private WebElement visitDetailsPrescription;

	@AndroidFindBy(id = "Visit Details Follow Up Parent RelativeLayout")
	private WebElement visitDetailsFollowUp;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_end_visit")
	private WebElement visitDetailsEndVisitButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_patname_txtview'])[1]")
	private WebElement prescriptionPatients;

	@AndroidFindBy(accessibility = "More options")
	private WebElement callMoreOptions;

	@AndroidFindBy(id = "com.android.dialer:id/digits")
	private WebElement phoneNumberDigits;

	@AndroidFindBy(accessibility = "backspace")
	private WebElement callBackSpace;

	@AndroidFindBy(id = "org.intelehealth.app:id/chief_complaint_txt")
	private WebElement visitSummaryVisitReasonComplaint;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_name_value")
	private WebElement visitSummaryPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_gender_value")
	private WebElement visitSummaryPatientGender;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_id_value")
	private WebElement visitSummaryPatientOpenMRSID;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Doctor's Speciality\"]")
	private WebElement doctorsSpecialityDropdown;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Doctor's Speciality\"]")
	private WebElement doctorsSpecialityValue;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Vitals']")
	private WebElement visitSummaryVitals;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reason for visit']")
	private WebElement visitSummaryReasonForVisit;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medical history']")
	private WebElement visitSummaryMedicalHistory;

	@AndroidFindBy(id = "org.intelehealth.app:id/physical_exam_tv")
	private WebElement visitSummaryPhysicalExaminaton;

	@AndroidFindBy(id = "org.intelehealth.app:id/add_docs_title")
	private WebElement visitSummaryAddDocumentsTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/flaggedcheckbox")
	private WebElement priorityVisitToggleButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/fabStartChat")
	private WebElement visitSummaryChat;

	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement visitSummaryPrintButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnPrescriptionView")
	private WebElement visitSummaryViewPrescriptionButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/label")
	private WebElement prescriptionScreenTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_name_value")
	private WebElement prescriptionPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_id_value")
	private WebElement prescriptionPatientId;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_gender_value")
	private WebElement prescriptionPatientAgeGender;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Consulted Doctor Details']")
	private WebElement prescriptionConsultedDoctorDetails;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Diagnosis']")
	private WebElement prescriptionDiagnosis;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Prescribed Medications\"]")
	private WebElement prescriptionPrescribedMedications;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Advice']")
	private WebElement prescriptionAdvice;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Test']")
	private WebElement prescriptionTest;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Referred Specialist']")
	private WebElement prescriptionReferredSpecialist;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Follow Up']")
	private WebElement prescriptionFollowUp;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_vs_print")
	private WebElement prescriptionPrintButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_vs_share")
	private WebElement prescriptionShareButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/refresh")
	private WebElement prescriptionScreenDownloadIcon;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Downloaded to: /storage/emulated/0/Documents/Intelehealth_PDF/Automation_A_XXXX2cdc.pdf\"]")
	private WebElement prescriptionFileDownloadedMessage;

	@AndroidFindBy(id = "com.android.printspooler:id/static_content")
	private WebElement printerPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select a printer\"]")
	private WebElement selectAPrinterText;

	@AndroidFindBy(id = "com.android.printspooler:id/page_content")
	private WebElement printedFile;

	@AndroidFindBy(id = "org.intelehealth.app:id/message")
	private WebElement sharePrescriptionPopupText;

	@AndroidFindBy(id = "org.intelehealth.app:id/editText_mobileno")
	private WebElement sharePrescriptionPopupMobileNumText;

	@AndroidFindBy(id = "org.intelehealth.app:id/filter")
	private WebElement prescriptionScreenKebabMenu;

	@AndroidFindBy(id = "org.intelehealth.app:id/reminder")
	private WebElement prescriptionScreenKebabMenuHome;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement homeScreenLocation;

	@AndroidFindBy(id = "org.intelehealth.app:id/incomplete_act")
	private WebElement PrescriptionScreenKebabMenuEndVisit;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement closeVisitsPopupTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Feedback']")
	private WebElement feedbackPageTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/editText_exit_survey")
	private WebElement feedbackPageGiveFeedbackTextArea;

	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement followupReminderAlertPopupOkButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/received_endvisit_no")
	private WebElement patientsAwaitingTheirPrescriptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Please remind the patient of their follow up date:')]")
	private WebElement followupReminderAlertPopupText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Received')]")
	private WebElement receivedTab;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Pending')]")
	private WebElement pendingTab;

	@AndroidFindBy(id = "org.intelehealth.app:id/pending_endvisit_no")
	private WebElement pendingPrescriptionCountWithMessage;

	@AndroidFindBy(id = "org.intelehealth.app:id/you_can_add")
	private WebElement pendingRecentVisits;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_patname_txtview'])[1]")
	private WebElement pendingRecentVisitsPatientName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_date_txtview']")
	private WebElement pendingRecentVisitsDate;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_patname_txtview'])[1]")
	private WebElement pendingVisitPatients;

	@AndroidFindBy(id = "com.android.chrome:id/terms_accept")
	private WebElement chromeAcceptAndContinue;
	@AndroidFindBy(id = "org.intelehealth.app:id/fu_patname_txtview")
	private WebElement recentVisitPatients;

	@AndroidFindBy(id = "com.whatsapp:id/send")
	private WebElement whatsappSendIcon;

	@AndroidFindBy(id = "com.whatsapp:id/titleSnippetUrlLayout")
	private WebElement sentSMS;

	By byChromeAcceptAndContinue = By.id("com.android.chrome:id/terms_accept");
	By byMobileNumNotProvided = By.xpath("//android.widget.Toast[@text=\"Mobile number not provided.\"]");
	By byCallMore = By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]");
	By byPhoneNumberDigits = By
			.xpath("//android.widget.EditText[@resource-id=\"com.samsung.android.dialer:id/digits\"]");
	By byCallBackSpace = By.xpath("com.samsung.android.dialer:id/delete_button_image");
	By byTurnOnSyncAddAccount = By.xpath("//android.widget.Button[@text='Add account']");
	By byTurnOnSyncNoThanks = By.xpath("//android.widget.Button[@text='No thanks']");
	By byPrescriptionPrintButton = By.id("org.intelehealth.app:id/btnPrintPresc");
	By byPrescriptionShareButton = By.id("org.intelehealth.app:id/btnSharePresc");
	By byfollowupReminderAlertPopupText = By
			.xpath("//android.widget.TextView[contains(@text,'Please remind the patient of their follow up date:')]");

	public PrescriptionsPage() throws Throwable {
		// Initialize AnotherPageClass object in the constructor
		dashboardModulePage = new DashboardModulePage(driver);
		InputStream datais = null;
	}

	// Refreshes the prescriptions
	public void refreshPrescriptions() throws Throwable {
		robot = new Robot();
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton, "Clicking on app sync icon");
				click(homeScreenPrescriptionArrowIcon, "Clicking on prescriptions");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}
	/*
	 * public void createVisit(RequestSpecification request) {
	 * APIServices.createVisitUsingRestAssured(request);
	 * System.out.println("=====================================================" );
	 * } public void startvisit(RequestSpecification request) {
	 * APIServices.startVisitUsingRestAssured(request);
	 * 
	 * APIServices.signAndSubmitUsingRestAssured(request); }
	 */

	// Verifies that patients with received prescriptions are listed in the recent
	// visit section
	public boolean verifyReceivedPrescriptionsPatientsListedInRecentVisit() throws Throwable {
		// restAssured sendPrescription = new restAssured();
		// sendPrescription.createPatientAndSharePrescription();
		// click(homeScreenRefreshButton , "clicked on resfresh button");
		// click(pre, BASE_DIR)
//		dashboardModulePage.verifyClickingOnPrescriptionsArrow();
		refreshPrescriptions();
		waitForVisibility(prescriptionPatients);
		boolean recentPatients = isDisplayed(recentVisitPatients);
		if (recentPatients == true) {
			if (isDisplayed(recentVisitPatientName) && isDisplayed(recentVisitDateAndTime)
					&& isDisplayed(recentVisitShareIcon)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether recent visit patients list is displayed along with patient name,date and time,share icon");
				System.out.println(
						"Recent visit patients list is displayed along with patient name,date and time,share icon");
				return true;
			}
		}
		if (recentPatients == false) {
			System.err.println("There are no recent visits");
			throw new Exception("There are no recent visits");
		}
		return false;
	}

	// Verifies the share functionality when clicked on any recent patient visit
	public boolean verifyShareFunctionalityWhenClickedOnAnyRecentPatientVisit() throws Throwable {
//		restAssured sendPrescription = new restAssured();
//		sendPrescription.createPatientAndSharePrescription();
		refreshPrescriptions();
		waitForVisibility(prescriptionPatients);
		boolean recentPatients = isDisplayed(recentVisitPatients);
		if (recentPatients == true) {
			click(recentVisitShareIcon, "Clicking on recent visit share icon");
			if (isDisplayed(sharePrescriptionPopupHeader) && isDisplayed(enterTheMobileNoPopupText)
					&& isDisplayed(sharePrescriptionPopupShareButton)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether popup is displayed with proper text and share button");
				System.out.println("Popup is displayed with proper text and share button");
				return true;
			}
		}
		if (recentPatients == false) {
			System.err.println("There are no recent visits");
			throw new Exception("There are no recent visits");
		}
		return false;
	}

	// Verifies if the prescription is shared upon entering a valid number
	public boolean verifyIfThePrescriptionIsSharedOnEnteringAValidNumber() throws Throwable {
		verifyShareFunctionalityWhenClickedOnAnyRecentPatientVisit();
		click(sharePrescriptionPopupMobileNumTextfield, "Clicking on mobile number textfield");
		sendKeys(sharePrescriptionPopupMobileNumTextfield, "8919395287", "Entering the mobile number");
		click(sharePrescriptionPopupShareButton, "Clicking on share button");
		boolean acceptAndContine = isDisplayed2(byChromeAcceptAndContinue);
		if (acceptAndContine == true) {
			click(chromeAcceptAndContinue);
			ExtentReport.getTest().log(Status.INFO, "There is no whatsapp application in the device");
			System.out.println("There is no whatsapp application in the device");

			return false;
		}
		if (isDisplayed(whatsappSendIcon)) {

			click(whatsappSendIcon);

		}
		return isDisplayed(sentSMS);
	}

	// Verifies that the count of patients awaiting prescriptions is correct
	public boolean verifyTheCountOfPatientsAwaitingPrescriptionsAreCorrect() throws Throwable {
		refreshPrescriptions();
		if (isDisplayed(patientsAwaitingTheirPrescriptions)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether '<No>Patients are awaiting their prescriptions' text is displayed properly");
			System.out.println("<No>Patients are awaiting their prescriptions text is displayed properly");
		}
		waitForVisibility(patientsAwaitingTheirPrescriptions);
		String awaitingText = patientsAwaitingTheirPrescriptions.getText();
		ExtentReport.getTest().log(Status.INFO, "Getting the number of patients awaiting for their prescriptions");
		String patientsCount = extractBefore(awaitingText, "patients are awaiting their prescriptions.");
		click(pendingTab, "Clicking on pending tab");
		waitForVisibility(pendingPrescriptionCountWithMessage);
		String pendingCountAndMessage = pendingPrescriptionCountWithMessage.getText();
		ExtentReport.getTest().log(Status.INFO, "Getting the count of pending prescriptions");
		String awaitingPatientsCount = extractBetween(pendingCountAndMessage,
				"Doctor is yet to send the prescriptions for", "patients, you can remind the doctor.");
		if (patientsCount.equals(awaitingPatientsCount)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the count of patients is correctly displayed");
			System.out.println("The count of patients is correctly displayed");
		return true;
		} else {
			throw new Exception("The count is not displayed correctly");
		}

	}

	// Verifies that the user is able to navigate to the visit details page by
	// clicking on any recent visit
	public boolean verifyThatUserIsAbleToNavigateToVisitDetailsPageByClickingOnAnyRecentVisit() throws Throwable {
		refreshPrescriptions();
		waitForVisibility(prescriptionPatients);
		boolean recentPatients = isDisplayed(recentVisitPatients);
		if (recentPatients == true) {
			click(recentVisitPatientName, "Clicking on patient from the list of recent visit");
			/*
			 * By followUp = By.xpath(
			 * "//android.widget.RelativeLayout[@content-desc=\"Visit Details Follow Up Parent RelativeLayout\"]"
			 * );
			 */ if (isDisplayed(visitDetailsPageTitle) && isDisplayed(visitDetailsPatientName)
					&& isDisplayed(visitDetailsPatientGenderAge) && isDisplayed(visitDetailsPatientID)
					&& isDisplayed(visitDetailsPatientCallIcon) && isDisplayed(visitDetailsPatientWhatsappIcon)
					&& isDisplayed(visitDetailsReasonForVisit) && isDisplayed(visitDetailsVisitID)
					&& isDisplayed(visitDetailsDate) && isDisplayed(visitDetailsTime)
					&& isDisplayed(visitDetailsDoctorSpeciality) && isDisplayed(visitDetailsVisitSummary)
					&& isDisplayed(visitDetailsPrescription) && isDisplayed(visitDetailsEndVisitButton)) {
				// boolean ifThere = isDisplayed2(followUp);
				/*
				 * if (ifThere == true) { ExtentReport.getTest().log(Status.INFO,
				 * "Verifying whether Follow up details is displayed");
				 * System.out.println("Follow up details is displayed"); } if (ifThere == false)
				 * { ExtentReport.getTest().log(Status.INFO,
				 * "There is no Follow up for the patient");
				 * System.out.println("There is no Follow up for the patient"); }
				 */
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whethwe user is navigated to Visit details pagePatient Name and Gender, Age,Patient Id,Call Icon,Whatsapp icon,Reason for visit,Visit Id,Date,Time,Doctor category,Visit summary detail,Prescription detail,End visit button are displayed");
				System.out.println(
						"User is navigated to Visit details page and Patient Name and Gender, Age,Patient Id,Call Icon,Whatsapp icon,Reason for visit,Visit Id,Date,Time,Doctor category,Visit summary detail,Prescription detail,End visit button are displayed");
				return true;
			}

		}

		if (recentPatients == false) {
			System.err.println("There are no recent visits");
			throw new Exception("There are no recent visits");
		}
		return false;
	}

	// Verifies that the user can initiate a call to the patient
	public boolean verifyThatUserCanCallPatient() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPatientCallIcon, "Clicking on call icon");
		try {
			if (!isDisplayed2(byCallMore) && !isDisplayed2(byPhoneNumberDigits) && !isDisplayed2(byCallBackSpace)) {
				ExtentReport.getTest().log(Status.INFO, "Verifying whether the mobile number is not provided");
				System.out.println("Mobile number is not provided");
				return false;
			} else {
				if (isDisplayed(callMoreOptions) && isDisplayed(phoneNumberDigits) && isDisplayed(callBackSpace)) {
					ExtentReport.getTest().log(Status.INFO,
							"Verifying whether the user is able to open the call list page and patient's number auto filled in dialer pad");
					System.out.println("Dial pad is opened");
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Verifies that the user can send a WhatsApp message to the patient
	public boolean verifyThatUserCanSendWhatsappMessageToThePatient() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPatientWhatsappIcon, "Clicking on whatsapp icon");
		try {
			if (!isDisplayed2(byChromeAcceptAndContinue)
					|| !isDisplayed2(byTurnOnSyncNoThanks) && !isDisplayed2(byTurnOnSyncAddAccount)) {
				ExtentReport.getTest().log(Status.INFO, "Verifying whether the mobile number is not provided");
				System.out.println("Mobile number is not provided");
				return false;
			}
			if (isDisplayed(whatsappSendIcon)) {

				click(whatsappSendIcon);
			}

		} catch (Exception e) {

		}
		return isDisplayed(sentSMS);

	}

	// Clicking on doctor speciality dropdown
	public void clickDoctorSpeciality() throws InterruptedException {
		click(doctorsSpecialityDropdown, "Clicking on doctors speciality dropdown");
	}

	// Verifies that the user is able to view the visit summary page
	public boolean verifyUserIsAbleToViewVisitSummaryPage() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		waitForVisibility(visitDetailsPatientName);
		String patientNameVisitDetails = visitDetailsPatientName.getText();
		waitForVisibility(visitDetailsPatientGenderAge);
		String genderAgeVisitDetails = visitDetailsPatientGenderAge.getText();
		waitForVisibility(visitDetailsPatientID);
		String patientIdVisitDetails = visitDetailsPatientID.getText();
		waitForVisibility(visitDetailsReasonForVisit);
		String reasonForVisitVisitDetails = visitDetailsReasonForVisit.getText();
		// check the xpath
		waitForVisibility(visitSummaryVisitReasonComplaint);

		String reasonForVisitVisitSummary = visitSummaryVisitReasonComplaint.getText();

		waitForVisibility(visitDetailsDoctorSpeciality);
		String doctorSpecialityVisitDetails = visitDetailsDoctorSpeciality.getText();
		click(visitDetailsVisitSummary, "Clicking on visit summary");
		waitForVisibility(visitSummaryPatientName);
		String patientNameVisitSummary = visitSummaryPatientName.getText();
		isDisplayed(visitSummaryPatientGender);
		waitForVisibility(visitSummaryPatientGender);
		String patientGenderVisitSummary = visitSummaryPatientGender.getText();
		waitForVisibility(visitSummaryPatientOpenMRSID);
		String patientIdVisitSummary = visitSummaryPatientOpenMRSID.getText();
		//click(visitDetailsVisitSummary, "Clicking on Visit Summary");
		boolean vitals = isDisplayed(visitSummaryVitals);
		boolean reasonForVisit = isDisplayed(visitSummaryReasonForVisit);
		scrollDown();
		// scrollToTextContains_Android("Physical Examination");
		boolean physicalExaminaton = isDisplayed(visitSummaryPhysicalExaminaton);
		// scrollToElementByDescription("Visit Summary Item Card Details 2 TextView");
		Thread.sleep(2000);
		scrollDown();
		boolean MedicalHistory = isDisplayed(visitSummaryMedicalHistory);

		Thread.sleep(2000);
		scrollDown();
		// scrollToElementByDescription("Visit Summary Speciality Header
		// RelativeLayout");
		boolean addAdditionalDocuments = isDisplayed(visitSummaryAddDocumentsTitle);
		clickDoctorSpeciality();
		waitForVisibility(doctorsSpecialityValue);
		String doctorSpecialityVisitSummary = doctorsSpecialityValue.getText();
		// click(doctorsSpecialityDropdown);
		if (patientNameVisitDetails.equals(patientNameVisitSummary)
				&& patientIdVisitDetails.equals(patientIdVisitSummary)
				&& reasonForVisitVisitDetails.equals(reasonForVisitVisitSummary)
				 && vitals == true
				&& reasonForVisit == true && physicalExaminaton == true && MedicalHistory == true
				&& addAdditionalDocuments == true && isDisplayed(priorityVisitToggleButton)
				&& isDisplayed(visitSummaryChat) && isDisplayed(visitSummaryViewPrescriptionButton)
				|| genderAgeVisitDetails.contains(patientGenderVisitSummary)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether 'Visit summary' screen is displayed with all the details entered in the previous screens Patient Name,Gender,Patient ID,Vitals,Reason for Visit,Physical examination,Medical history,Add additional document,Doctor speciality selection dropdown,Priority Visit toggle,Chat option with CTA buttons Print,Share");
			System.out.println(
					"'Visit summary' screen is displayed with all the details entered in the previous screens Patient Name,Gender,Patient ID,Vitals,Reason for Visit,Physical examination,Medical history,Add additional document,Doctor speciality selection dropdown,Priority Visit toggle,Chat option with CTA buttons Print,Share");
			return true;
		} else {
			throw new Exception("Details are not displayed properly");
		}

	}

	// Verifies that the user can successfully navigate to the prescription page
	public boolean verifythatUserCanNavigateToPrescriptionPage() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		waitForVisibility(visitDetailsPatientName);
		String patientNameVisitDetails = visitDetailsPatientName.getText();
		waitForVisibility(visitDetailsPatientGenderAge);
		String genderAgeVisitDetails = visitDetailsPatientGenderAge.getText();
		waitForVisibility(visitDetailsPatientID);
		String patientIdVisitDetails = visitDetailsPatientID.getText();
		click(visitDetailsPrescription, "Clicking on prescription");
		waitForVisibility(prescriptionPatientName);
		String patientNamePrescription = prescriptionPatientName.getText();
		waitForVisibility(prescriptionPatientAgeGender);
		String patientAgeGenderPrescription = prescriptionPatientAgeGender.getText();
		waitForVisibility(prescriptionPatientId);
		String patientIdPrescription = prescriptionPatientId.getText();
		//click(visitDetailsPrescription);
		isDisplayed(prescriptionConsultedDoctorDetails);
		isDisplayed(prescriptionDiagnosis);
		isDisplayed(prescriptionPrescribedMedications);
		// scrollToTextContains_Android("Follow Up");
		scrollDown();
		isDisplayed(prescriptionAdvice);
		isDisplayed(prescriptionTest);
		isDisplayed(prescriptionReferredSpecialist);
		isDisplayed(prescriptionFollowUp);
		isDisplayed(prescriptionPrintButton);
		isDisplayed(prescriptionShareButton);
		if (isDisplayed(prescriptionScreenTitle) && patientNameVisitDetails.equalsIgnoreCase(patientNamePrescription)
				&& patientIdVisitDetails.equalsIgnoreCase(patientIdPrescription)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to prescription page with Patient name,Gender and Age,Patient id,Consulted doctor details,Diagnosis,Prescribed medication,Advice,Test,Referred specialist,Follow up,Print button,Share button displayed");
			System.out.println(
					" User is navigated to prescription page with Patient name,Gender and Age,Patient id,Consulted doctor details,Diagnosis,Prescribed medication,Advice,Test,Referred specialist,Follow up,Print button,Share button displayed");
			return true;
		} else {
			throw new Exception("Details are not displayed properly");
		}
	}

	// Verifies that the user is able to download the prescription
	public boolean verifyThatUserIsAbleToDownloadThePrescription() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionScreenDownloadIcon, "Clicking on download icon");
		try {
			// xpath not available
			isDisplayedWithoutWaits(prescriptionFileDownloadedMessage);
		} catch (Exception e) {
		}
		if (isDisplayed(prescriptionPrintButton) && isDisplayed(prescriptionShareButton)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is able to download the prescription");
			return true;
		} else {
			if (isDisplayed(visitDetailsPageTitle)) {
				ExtentReport.getTest().log(Status.INFO, "Verifying whether file is already downloaded");
				System.out.println("File is already downloaded");
			}
		}
		return false;
	}

	// Verifies that the user can successfully print the prescription
	public boolean verifyUserCanPrintThePrescription() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionPrintButton, "Clicking on print button");
		if (isDisplayed(printedFile) &&  isDisplayed(printerPageTitle)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is able to print the prescription and navigated to select a printer page");
			System.out.println("Able to print the prescription");
			return true;
		}
		return false;
	}

	// Handles the functionality to share the prescription from visit details
	public void visitDetailsPrescriptionShare() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionShareButton, "Clicking on share button");
	}

	// Verifies that the user can successfully share the prescription with the
	// patient
	public boolean verifyThatUserCanShareThePrescriptionToPatient() throws Throwable {
		if (isDisplayed(sharePrescriptionPopupText) && isDisplayed(sharePrescriptionPopupMobileNumText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether popup is opened");
			System.out.println("Popup is opened");
		}

		waitForVisibility(sharePrescriptionPopupMobileNumTextfield);
		String ifEmpty = sharePrescriptionPopupMobileNumTextfield.getText();
		if (ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether patient has not given the mobile number");
			System.out.println("Patient has not given mobile number");
			return false;
		}
		if (!ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether popup is opened with patient number");
			System.out.println("Popup is opened with patient number");
			return true;
		}
		return false;
	}

	// Verifies that the user can successfully share the prescription through
	// WhatsApp
	public boolean verifyUserCanShareThroughWhatsapp() throws Throwable {
		waitForVisibility(sharePrescriptionPopupMobileNumText);
		String ifEmpty = sharePrescriptionPopupMobileNumText.getText();
		if (ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether patient has not given the mobile number");
			System.out.println("Patient has not given mobile number");
			click(sharePrescriptionPopupMobileNumTextfield, "Clicking on mobile number textfield");
			sendKeys(sharePrescriptionPopupMobileNumTextfield, "7892450759", "Entering the mobile number");
			click(sharePrescriptionPopupShareButton, "Clicking on share prescription button in the share popup");
			boolean acceptAndContine = isDisplayed2(byChromeAcceptAndContinue);
			if (acceptAndContine == true) {
				click(chromeAcceptAndContinue);
			}

		}

		if (!isDisplayed2(byChromeAcceptAndContinue)
				|| !isDisplayed2(byTurnOnSyncNoThanks) && !isDisplayed2(byTurnOnSyncAddAccount)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the mobile number is not provided");
			System.out.println("Mobile number is not provided");
			return false;
		}
		if (isDisplayed(whatsappSendIcon)) {

			click(whatsappSendIcon);
			return true;
		}
		return false;
	}

	// Verifies that clicking on the "Home" button navigates the user to the home
	// page
	public boolean verifyHomeNavigatesToHomePage() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionScreenKebabMenu, "Clicking on kebab menu");
		click(prescriptionScreenKebabMenuHome, "Clicking on Home");
		if (isDisplayed(homeScreenLocation)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to Home page");
			System.out.println("User is navigated to Home page");
			return true;
		}
		return false;

	}

	// Verifies that the user can successfully end a visit and navigated to Close
	// visits page
	public boolean verifyThatUserCanEndVisitFromPrescritionScreen() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionScreenKebabMenu, "Clicking on kebab menu");
		click(PrescriptionScreenKebabMenuEndVisit, "Clicking on end visit");
		if (isDisplayed(closeVisitsPopupTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to Close visits page");
			System.out.println("User is navigated to Close visits page");
			return true;

		}
		return false;
	}

	// Verifies that the user can successfully end a visit and feedback form is
	// opened
	public boolean verifyUserCanEndVisitFromVisitDetailsScreen() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsEndVisitButton, "Clicking on end visit");
		try {
			// check xpath
			followupReminderAlertPopupOkButton.click();
		} catch (Exception e) {
		}
		if (isDisplayed(feedbackPageTitle) && isDisplayed(feedbackPageGiveFeedbackTextArea)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether feedback form is opened");
			System.out.println("Feedback form is opened");
			return true;
		}
		return false;
	}

	// Verifies that the user receives a follow-up alert popup when ending a visit
	public boolean verifyUserWillGetTheFollowUpAlertPopUpWhenEndTheVisit() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsEndVisitButton, "Clicking on end visit");
		// check xpath
		boolean followUpReminder = isDisplayed2(byfollowupReminderAlertPopupText);
		if (followUpReminder == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether alert message is opened and proper text is displayed");
			System.out.println("Alert message is opened and proper text is displayed");
			// check xpath
			click(followupReminderAlertPopupOkButton, "Clicking on okay button in the alert popup");
			return true;
		}
		if (isDisplayed(feedbackPageTitle) && isDisplayed(feedbackPageGiveFeedbackTextArea)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether feedback form is opened");
			System.out.println("Feedback form is opened");
			return false;
		} else {
			ExtentReport.getTest().log(Status.INFO, "There is no follow up visit for the patient");
			System.err.println("There is no follow up visit for the patient");
			throw new Exception("There is no follow up visit for the patient");

		}
	}

	// Verifies that the awaiting prescription notification is displayed along with
	// the count
	public boolean verifyTheAwaitingPrescriptionNotificationIsDisplayedAlongWithTheCount() throws Throwable {
		refreshPrescriptions();
		click(pendingTab, "Clicking on pending tab");
		waitForVisibility(pendingPrescriptionCountWithMessage);
		String pendingCountAndMessage = pendingPrescriptionCountWithMessage.getText();
		String onlyCount = extractBetween(pendingCountAndMessage, "Doctor is yet to send the prescriptions for",
				"patients, you can remind the doctor.");
		if (onlyCount.matches("\\d+") && isDisplayed(pendingPrescriptionCountWithMessage)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether notification is displayed with pending prescription count.'Doctor is yet to send the prescriptions for <no> patients,you can remind the doctor' text is displayed");
			System.out.println(
					"Notification is displayed with pending prescription number.'Doctor is yet to send the prescriptions for <no> patients,you can remind the doctor' text is displayed");
			return true;
		} else {
			throw new Exception("Count is not displayed");
		}
	}

	// Verifies that recent visits are displayed in the list
	public boolean verifyThatRecentVisitsDisplayInList() throws Throwable {
		refreshPrescriptions();
		click(pendingTab, "Clicking on pending tab");
		if (isDisplayed(pendingRecentVisits) && isDisplayed(pendingRecentVisitsPatientName)
				&& isDisplayed(pendingRecentVisitsDate)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether recent visits are displayed along with Patient name and date");
			System.out.println("Recent visits are displayed along with Patient name and date");
			return true;
		}
		return false;
	}

	// Verifies that the user can successfully end a pending visit
	public boolean verifyThatUserCanEndPendingVisit() throws Throwable {
		refreshPrescriptions();
		click(pendingTab, "Clicking on pending tab");
		click(pendingVisitPatients, "Clicking on pending visit patient from the list");
		click(visitDetailsEndVisitButton, "Clicking on end visit button");
		waitForVisibility(feedbackPageTitle);
		if (isDisplayed(feedbackPageTitle) && isDisplayed(feedbackPageGiveFeedbackTextArea)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether visit is ended and feedback form is opened");
			System.out.println("Visit is ended and feedback form is opened");
			return true;
		}
		return false;
	}

}
