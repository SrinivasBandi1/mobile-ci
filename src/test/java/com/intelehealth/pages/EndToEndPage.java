package com.intelehealth.pages;

import java.awt.Robot;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EndToEndPage extends BaseTest {

	VisitSummaryPage visitSummaryPage;
	PrescriptionsPage prescriptionsPage;
	AppointmentsPage appointmentsPage;
	AddNewPatientPage addNewPatientPage;
	// JSON object to store test data
	JSONObject appData;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"org.intelehealth.app:id/frame_10014\"]")
	private WebElement prescriptionPatients;

	@AndroidFindBy(accessibility = "Visit Details Visit Summary Card RelativeLayout")
	private WebElement visitDetailsVisitSummary;

	@AndroidFindBy(accessibility = "Visit Summary Filter ImageButton")
	private WebElement PrescriptionVisitSummaryScreenKebabMenu;

	@AndroidFindBy(accessibility = "Notification Filter Reminder TextView")
	private WebElement kebabMenuHome;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
	private WebElement homeScreenHome;

	@AndroidFindBy(accessibility = "Visit Summary Share Button")
	private WebElement visitSummaryShareButton;

	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement visitSummaryPrintButton;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Message TextView")
	private WebElement sharePrescriptionPopupText;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Mobile Num EditText")
	private WebElement sharePrescriptionPopupMobileNumText;

	@AndroidFindBy(accessibility = "Share Prescription Dialog 'Share' Button")
	private WebElement sharePrescriptionPopupShareButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement turnOnSyncNoThanks;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement turnOnSyncAddAccount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select a printer\"]")
	private WebElement selectAPrinterText;

	@AndroidFindBy(id = "com.android.printspooler:id/title")
	private WebElement printerPageTitle;

	@AndroidFindBy(id = "com.android.printspooler:id/page_content")
	private WebElement printedFile;

	@AndroidFindBy(accessibility = "dial")
	private WebElement dialIcon;

	@AndroidFindBy(accessibility = "Home Fragment Find Patient TextView")
	private WebElement findPatientTextfield;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No visit created']")
	private WebElement recentlyAddedNoVistCreatedPatient;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(accessibility = "Patient Details Start Visit Button")
	private WebElement patientDetailsStartVisitButton;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Positive (Yes) Button")
	private WebElement patientRegisteredPopupContinueButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Prescription pending']")
	private WebElement recentlyAddedPrescriptionPendingPatient;

	@AndroidFindBy(accessibility = "Past Visit List Item Parent RelativeLayout")
	private WebElement openTheOpenVisit;

	@AndroidFindBy(accessibility = "Visit Summary Patient Name TextView")
	private WebElement visitSummaryPatientName;

	@AndroidFindBy(accessibility = "Visit Summary Appointment Button")
	private WebElement appointmentButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tvTime_new'])[1]")
	private WebElement appointmentFirstTime;

	@AndroidFindBy(accessibility = "Schedule Appointment 'Book Appointment' Button")
	private WebElement bookAppointmentButton;

	@AndroidFindBy(accessibility = "Book Appointment Dialog Positive (Yes) Button")
	private WebElement appointmentYesButton;

	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"upcoming appointments recyclerview in Todays Appointments\"]//(android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"])[last()]")
	private WebElement AddedAppointmentPatientName;

	@AndroidFindBy(accessibility = "Identification First Screen First Name EditText")
	private WebElement addedFirstName;

	@AndroidFindBy(accessibility = "Identification First Screen Last Name EditText")
	private WebElement addedLastName;

	@AndroidFindBy(accessibility = "Identification First Screen Age EditText")
	private WebElement addedPatientAge;

	@AndroidFindBy(accessibility = "Identification Second Screen Country Spinner")
	private WebElement addedCountry;

	@AndroidFindBy(accessibility = "Identification Second Screen State Spinner")
	private WebElement addedState;

	@AndroidFindBy(accessibility = "Identification Second Screen District Spinner")
	private WebElement addedDistrict;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement patientDetailsPatientName;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Age' Value TextView")
	private WebElement patientDetailsAge;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Country' Value TextView")
	private WebElement patientDetailsCountry;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'State' Value TextView")
	private WebElement patientDetailsState;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'District' Value TextView")
	private WebElement patientDetailsDistrict;

	@AndroidFindBy(accessibility = "Identification Second Screen Village EditText")
	private WebElement villageTextBox;
	
	@AndroidFindBy(accessibility = "Identification Activity Refresh ImageButton")
	private WebElement addNewPatientRefreshButton;

	By byNoTimeSlots = By
			.xpath("//android.widget.TextView[@content-desc=\"Schedule Appointment 'No Slot Available' TextView\"]");

	By patientDetailsOpenVisit = By
			.xpath("//android.widget.TextView[@content-desc=\"Patient Details Open Visits Title TextView\"]");

	By findPatientScreen = By
			.xpath("//android.widget.RelativeLayout[@content-desc=\"Find Patient Screen Parent RelativeLayout\"]");

	// Constructor
	public EndToEndPage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		visitSummaryPage = new VisitSummaryPage(driver);
		prescriptionsPage = new PrescriptionsPage();
		appointmentsPage = new AppointmentsPage(driver);
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

	// Clicking on patient from the list and clicking on visit summary
	public void prescriptionsReceivedVisitSummary() throws Throwable {
		prescriptionsPage.refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsVisitSummary, "Clicking on visit summary");
	}

	// Clicking on app sync icon and clicking on find patient textfield
	public void refreshFindPatient() throws Throwable {
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton, "Clicking on app sync icon");
				click(findPatientTextfield, "Clicking on find patient textfield");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	// Performs the login, adds a new patient, and registers the patient
	public void loginAddPatientPatientRegister() throws Throwable {
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton, "Clicking on app sync icon");
				ExtentReport.getTest().log(Status.INFO, "Clicking on Add Patient button");
				addNewPatientPage.clickOnAddPatients();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(3000);
				ExtentReport.getTest().log(Status.INFO, "Clicking on Accept button");
				addNewPatientPage.clickOnAcceptButton();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(3000);
				ExtentReport.getTest().log(Status.INFO, "Entering the first name");
				addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
				Thread.sleep(4000);
				click(addNewPatientRefreshButton);
				ExtentReport.getTest().log(Status.INFO, "Entering the last name");
				addNewPatientPage.enterLastName();

				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		waitForVisibility(addedFirstName);
		String givenFirstName = addedFirstName.getText();
		waitForVisibility(addedLastName);
		String givenLastName = addedLastName.getText();
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(3000);
				ExtentReport.getTest().log(Status.INFO, "Selecting the gender");
				addNewPatientPage.selectGender();
				scrollToElement();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clickin on DOB icon");
				addNewPatientPage.clickOnDobIcon();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on month spinner");
				addNewPatientPage.clickOnMonthSpinner();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Selecting the month");
				addNewPatientPage.selectMonth();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on year spinner");
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
				ExtentReport.getTest().log(Status.INFO, "Selecting the year");
				addNewPatientPage.selectYear();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Selecting the date");
				addNewPatientPage.selectDate();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on okay button");
				addNewPatientPage.clickOnOkayButton();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				waitForVisibility(addedPatientAge);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		String givenAge = addedPatientAge.getText();
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on next button");
				addNewPatientPage.clickOnNextButton1();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on state spinner");
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
				ExtentReport.getTest().log(Status.INFO, "Selecting the state");
				addNewPatientPage.selectState();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on district spinner");
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
				ExtentReport.getTest().log(Status.INFO, "Selecting the district");
				addNewPatientPage.selectDistrict();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				sendKeys(villageTextBox, "BHATKAL","Entered village name");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on next button");
				addNewPatientPage.clickOnNextButton2();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		while (attempt < maxAttempts) {
			try {
				ExtentReport.getTest().log(Status.INFO, "Clicking on next button");
				addNewPatientPage.clickOnNextButton3();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		waitForVisibility(patientDetailsPatientName);
		String savedPatientName = patientDetailsPatientName.getText();
		waitForVisibility(patientDetailsAge);
		String savedAge = patientDetailsAge.getText();
		if (savedPatientName.contains(givenFirstName) && savedPatientName.contains(givenLastName)
				&& givenAge.contains(savedAge)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the patient is added with the added details");
			System.out.println("The patient is added with the added details");
		}
	}

	// Performs login, adds a new patient, registers the patient, and updates
	// patient details
	public void logiAddPatientPatientRegisterUpdatePatientDetails() throws Throwable {
		visitSummaryPage.addPatients();
	}

	// Performs login, adds a new patient, registers the patient, starts a visit,
	// and sends the visit
	public void loginAddPatientPatientRegisterStartVisitSendVisit() throws Throwable {
		visitSummaryPage.verifyIfPriorityVisitIsEnabledON();
	}

	// Performs login, finds a patient, and books an appointment for an already
	// added patient profile
	public void loginFindPatientBookAppointmentForAlreadyAddedPatientProfile() throws Throwable {
//		restAssured sendPrescription = new restAssured();
//		sendPrescription.createPatientAndSharePrescription();
		refreshFindPatient();
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				scrollToTextContains_Android("Prescription received");
//				scrollToTextContains_Android("Prescription pending");
				click(recentlyAddedPrescriptionPendingPatient, "Clicking on the recently added patient");
//		        scrollToElementByDescription("Patient Details Open Visits Title TextView");
				scrollToElementByDescription("Past Visit List Item Visit Date TextView");
				boolean openVisit = isDisplayed2(patientDetailsOpenVisit);
				if (openVisit == true) {
					click(openTheOpenVisit, "Click on open visit");
					waitForVisibility(visitSummaryPatientName);
					String patientName = visitSummaryPatientName.getText();
					System.out.println(patientName);
					click(appointmentButton, "Clicking on appointment button");
					boolean noSlots = isDisplayed2(byNoTimeSlots);
					if (noSlots == true) {
						System.err.println("Appointment slot is not available for selected date and speciality!");
						throw new Exception("Appointment slot is not available for selected date and speciality!");
					}
					click(appointmentFirstTime, "Selecting the time slot");
					click(bookAppointmentButton, "Clicking on book appointment button");
					appointmentsPage.verifyWhenUserClicksYesInConfirmAppointmentPopup();
					waitForVisibility(AddedAppointmentPatientName);
					String AppointmentPatientName = AddedAppointmentPatientName.getText();
					if (patientName.equals(AppointmentPatientName)) {
						ExtentReport.getTest().log(Status.INFO,
								"Verifying whether the booked appointment is shown in Upcoming section of Appointment");
						System.out.println("The booked appointment is shown in Upcoming section of Appointment");
					} else {
						System.err.println("The booked appointment is not shown in Upcoming section of Appointment");
						ExtentReport.getTest().log(Status.INFO,
								"The booked appointment is not shown in Upcoming section of Appointment");
						throw new Exception("The booked appointment is not shown in Upcoming section of Appointment");
					}
				} else {
					System.err.println("The visit has ended");
					ExtentReport.getTest().log(Status.INFO, "The visit has ended");
					throw new Exception("The visit has ended");
				}
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	// Performs login, finds a patient, starts a visit, and sends the visit,
	// assuming no visit was created earlier
	public void loginFindPatientStartVisitSendVisitNoVisitCreatedEarlier() throws Throwable {
		refreshFindPatient();
		scrollToTextContains_Android("No visit created");
		click(recentlyAddedNoVistCreatedPatient,
				"Clicking on the recently added patient for whom no visit has been created");
		click(patientDetailsStartVisitButton, "Clicking on start visit button");
		click(patientRegisteredPopupContinueButton, "Clicking on continue button");
		visitSummaryPage.verifyBehaviorOnClickingYesInSendVisitPopup();
	}

	// Performs login and initiates a call to a patient with received prescriptions.
	public void loginPrescriptionsReceivedCallToPatient() throws Throwable {
		prescriptionsPage.verifyThatUserCanCallPatient();
		try {
			ExtentReport.getTest().log(Status.INFO, "Clicking on dial icon");
			Thread.sleep(3000);
			dialIcon.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Performs login and sends a WhatsApp message to a patient with received
	// prescriptions
	public void loginPrescriptionsReceivedWhatsappToPatient() throws Throwable {
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				prescriptionsPage.verifyThatUserCanSendWhatsappMessageToThePatient();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	// Performs login and verifies visit summary and prescription print
	// functionality for received prescriptions
	public void loginPrescriptionsReceivedVisitSummaryPrint() throws Throwable {
		prescriptionsReceivedVisitSummary();
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				click(visitSummaryPrintButton, "Clicking on print button");
				if (isDisplayed(printedFile) && isDisplayed(selectAPrinterText) || isDisplayed(printerPageTitle)) {
					ExtentReport.getTest().log(Status.INFO, "Verifying whether the visit summary is saved as pdf copy");
				}
				System.out.println("The visit summary is saved as pdf copy");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	// Performs login and verifies visit summary and prescription share
	// functionality for received prescriptions
	public void loginPrescriptionsReceivedVisitSummaryShare() throws Throwable {
		prescriptionsReceivedVisitSummary();
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				click(visitSummaryShareButton, "Clicking on share button");
				prescriptionsPage.verifyUserCanShareThroughWhatsapp();
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	// Logs in, checks prescriptions received, views visit summary, and navigates
	// back to the home page
	public void loginPrescriptionsReceivedVisitSummaryHome() throws Throwable {
		prescriptionsReceivedVisitSummary();
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				click(PrescriptionVisitSummaryScreenKebabMenu, "Clicking on kebab menu");
				click(kebabMenuHome, "Clicking on Home");
				if (isDisplayed(homeScreenHome)) {
					ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to Home page");
					System.out.println("User is navigated to Home page");
				}
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

}
