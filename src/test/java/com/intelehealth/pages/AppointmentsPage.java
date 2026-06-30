package com.intelehealth.pages;

import java.awt.Robot;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppointmentsPage extends BaseTest {
	@AndroidFindBy(accessibility = "Home Fragment Appointment Icon ImageView")
	private WebElement appointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement MyAppointmentsTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Upcoming')]")
	private WebElement tabUpcomingAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private WebElement completedPatient;

	@AndroidFindBy(id = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_date_appointment_todays\"]")
	private List<WebElement> lstAppointmentTime;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement appointmentsDetailsScreenTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Past')]")
	private WebElement tabPastAppointments;

	@AndroidFindBy(accessibility = "upcoming appointments count textview in Appointments Fragment")
	private WebElement upcomingAppointmentsCount;
	@AndroidFindBy(accessibility = "completed appointments count textview in Appointments Fragment")
	private WebElement completedAppointmentsCount;
	@AndroidFindBy(accessibility = "cancelled appointment count textview in Appointments Fragment")
	private WebElement cancelledAppointmentsCount;
	@AndroidFindBy(accessibility = "filter imageview in Appointments Fragment")
	private WebElement filter;
	@AndroidFindBy(accessibility = "All Appointment Filter Completed RadioButton")
	private WebElement completedRadioButton;
	@AndroidFindBy(xpath = "//android.widget.CompoundButton[@text='Completed appointments']")
	private WebElement completedAppointmentResult;
	@AndroidFindBy(accessibility = "completed appointments title with count textview in Appointments Fragment")
	private WebElement completedAppointmentTitle;
	@AndroidFindBy(accessibility = "cancelled todays appointments linearlayout in Todays Appointments")
	private WebElement cancelledTab;

	@AndroidFindBy(accessibility = "cancelled count textview in Todays Appointments")
	private WebElement cancelledCount;
	@AndroidFindBy(accessibility = "cancelled title textview in Todays Appointments")
	private WebElement cancelledTitelText;
	@AndroidFindBy(accessibility = "completed count textview in Todays Appointments")
	private WebElement completedCount;
	@AndroidFindBy(accessibility = "completed title textview in Todays Appointments")
	private WebElement completedTitelText;

	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Upcoming\")")
	private WebElement upcomingTitleText;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private WebElement upcomingPatient;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_cancel_appointment")
	private WebElement appointmentCancelButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_dialog_image")
	private WebElement cancelAppointmentDialog;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_title_book_app")
	private WebElement cancelAppointmentDialogSubtitle;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_yes_appointment")
	private WebElement yesButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/button_no_appointment")
	private WebElement noButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/titleTv_new")
	private WebElement reasonDialogTitle;
	@AndroidFindBy(id = "org.intelehealth.app:id/rb_no_doctor")
	private WebElement doctorRadioButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/rb_no_patient")
	private WebElement noPatientRadioButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/rb_other_ask")
	private WebElement othersRadioButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_save_ask")
	private WebElement saveButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_cancel_ask")
	private WebElement cancelButton;

	@AndroidFindBy(accessibility = "Common Toolbar Refresh ImageView")
	private WebElement refresh;

	@AndroidFindBy(id = "org.intelehealth.app:id/vector")
	private WebElement backArrow;
	@AndroidFindBy(xpath = "//android.widget.Toast[@text='The appointment was canceled successfully!']")
	private WebElement appointmentCanceledSuccessMsg;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"])[1]")
	private WebElement cancelledPatient;
	@AndroidFindBy(accessibility = "Appointment Details Schedule Button")
	private WebElement appointmentScheduleButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Schedule appointment']")
	private WebElement scheduleAppointmentTitle;
	@AndroidFindBy(accessibility = "upcoming todays appointments linearlayout in Todays Appointments")
	private WebElement upcomingTab;
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"cancelled appointments recyclerview in Todays Appointments\"]/(//android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"])")
	private List<WebElement> apponimentsCancelledPatients;
//	@AndroidFindBy(accessibility ="All")
//	private WebElement all;

//	@AndroidFindBy(accessibility ="All")
//	private WebElement all;
//	@AndroidFindBy(accessibility ="All")
//	private WebElement all;
//	@AndroidFindBy(accessibility ="All")
//	private WebElement all;

	// calender

	@AndroidFindBy(accessibility = "calendar icon imageview in Appointments Fragment")
	private WebElement calender;
	@AndroidFindBy(accessibility = "All Appointment Filter By Date 'From Date' TextView")
	private WebElement fromDateCalenderIcon;
	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Month Spinner")
	private WebElement monthSpinner;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='October']")
	private WebElement fromMonth;
	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Year Spinner")
	private WebElement yearSpinner;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2019']")
	private WebElement fromYear;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1']")
	private WebElement date;
	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Okay Button")
	private WebElement okayButton;

	@AndroidFindBy(accessibility = "All Appointment Filter By Date 'To Date' TextView")
	private WebElement toDateCalenderIcon;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='November']")
	private WebElement toMonth;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2020']")
	private WebElement toYear;
	@AndroidFindBy(xpath = "//android.widget.CompoundButton[@text='1 Oct - 1 Nov']")
	private WebElement dateRange;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView4")
	private WebElement openAppointments;

	@AndroidFindBy(accessibility = "Today's")
	private WebElement todaysTab;

	@AndroidFindBy(accessibility = "Common Toolbar Back Arrow ImageView")
	private WebElement todayAppointmentBackArrow;

	@AndroidFindBy(accessibility = "Appointment Details Current Appointment Date TextView")
	private WebElement appointmentsDate;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]")
	private WebElement todayAppointments;

	@AndroidFindBy(accessibility = "completed todays appointments linearlayout in Todays Appointments")
	private WebElement completedTab;

	@AndroidFindBy(accessibility = "upcoming count textview in Todays Appointments")
	private WebElement upcomingTabCount;

	@AndroidFindBy(accessibility = "cancelled count textview in Todays Appointments")
	private WebElement cancelledTabCount;

	@AndroidFindBy(accessibility = "completed count textview in Todays Appointments")
	private WebElement completedTabCount;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private WebElement upcomingAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private WebElement todayUpcomingAppointmentslatest;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"cancelled title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]")
	private WebElement todayCancelledAppointments;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"completed title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]")
	private WebElement todayCompletedAppointments;

	@AndroidFindBy(accessibility = "upcoming title textview in Todays Appointments")
	private WebElement upcomingSection;

	@AndroidFindBy(accessibility = "cancelled title textview in Todays Appointments")
	private WebElement cancelledSection;

	@AndroidFindBy(accessibility = "completed title textview in Todays Appointments")
	private WebElement completedSection;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement appointmentDetailsPageTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"completed title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"])[last()]")
	private WebElement todayCompletedAppointmentsLast;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_call_patient_app")
	private WebElement appointmentDetailsCallIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_whatsapp_patient_app")
	private WebElement appointmentDetailsWhatsappIcon;

	@AndroidFindBy(accessibility = "dial")
	private WebElement dialIcon;

	@AndroidFindBy(accessibility = "More options")
	private WebElement callMoreOptions;

	@AndroidFindBy(id = "com.android.dialer:id/digits")
	private WebElement phoneNumberDigits;

	@AndroidFindBy(accessibility = "backspace")
	private WebElement callBackSpace;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_drawer_visit_summary")
	private WebElement appointmentDetailsVisitSummaryForwardArrow;

	@AndroidFindBy(id = "org.intelehealth.app:id/toolbar_title")
	private WebElement visitSummaryPageTitle;

	@AndroidFindBy(accessibility = "Visit Summary Item Card Details 1 TextView")
	private WebElement visitSummaryPageDetailsText;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Height (cm)\")")
	private WebElement visitSummaryPageHeight;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Weight (kg)\")")
	private WebElement visitSummaryPageWeight;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"BMI\")")
	private WebElement visitSummaryPageBMI;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"BP\")")
	private WebElement visitSummaryPageBP;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Pulse\")")
	private WebElement visitSummaryPagePulse;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_temp_faren")
	private WebElement visitSummaryPageTemp;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SpO2 (%)\")")
	private WebElement visitSummaryPageSpO2;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_respiratory")
	private WebElement visitSummaryPageRespRate;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement turnOnSyncNoThanks;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement turnOnSyncAddAccount;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_reschedule_appointment")
	private WebElement appointmentDetailsRescheduleButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_title_book_app")
	private WebElement rescheduleAppointmentText;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_info_dialog_app")
	private WebElement areUSureRescheduleText;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_no_appointment")
	private WebElement appointmentNoButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_yes_appointment")
	private WebElement appointmentYesButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/titleTv_new")
	private WebElement pleaseSelectYourRescheduleReasonText;

	@AndroidFindBy(id = "org.intelehealth.app:id/rb_no_doctor")
	private WebElement doctorIsNotAvailableRadioButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/rb_no_patient")
	private WebElement patientIsNotAvailableRadioButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_cancel_ask")
	private WebElement rescheduleReasonCancelButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_save_ask")
	private WebElement rescheduleReasonSaveButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement scheduleAppointmentText;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_book_appointment")
	private WebElement bookAppointmentButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.intelehealth.app:id/parent_time_slot\").instance(0)")
	private WebElement appointmentFirstTime;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Confirm appointment?']")
	private WebElement confirmAppointmentText;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement bookingAppointmentText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Are you sure, patient want to book the appointment on')]")
	private WebElement confirmAppointmentPopupText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Appointment booked successfully!']")
	private WebElement appointmentBookedSuccessfullyText;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement myAppointmentsPageText;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private WebElement todayUpcomingAppointmentPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private List<WebElement> lstUpcomingAppointmentPatientName;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"upcoming title textview in Todays Appointments\"]//..//android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"])[last()]")
	private WebElement todayUpcomingAppointmentPatientNameAdded;

	@AndroidFindBy(accessibility = "Today Appointment Item Patient Name TextView")
	private WebElement todayAppointmentPatientName;

	@AndroidFindBy(accessibility = "Appointment Details Cancel Button")
	private WebElement appointmentDetailsCancelButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"cancelled title textview in Todays Appointments\"]//..//android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"])[1]")
	private WebElement firstCancelledAppointmentPatientName;

	@AndroidFindBy(accessibility = "Appointment Details Schedule Button")
	private WebElement scheduleAppointmentButton;

	@AndroidFindBy(id = "com.android.chrome:id/terms_accept")
	private WebElement chromeAcceptAndContinue;

	@AndroidFindBy(id = "org.intelehealth.app:id/et_search")
	private WebElement inpSearchInPastAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/filter_im")
	private WebElement icnFilterInPastAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/sort_im")
	private WebElement icnSortInPastAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_patient_name_todays")
	private List<WebElement> appointmentCards;

	@AndroidFindBy(id = "org.intelehealth.app:id/status_tv")
	private List<WebElement> statusLabels;

	Robot robot;
	VisitSummaryPage visitSummaryPage;

	By byAppointmentBookedSuccessfullyText = By
			.xpath("//android.widget.TextView[@text='Appointment booked successfully!']");

	By todaysUpcomingAppointmentPatientName = By.xpath(
			"//android.widget.TextView[@content-desc=\"upcoming title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]");

	By todaysCancelledAppointmentPatientName = By.xpath(
			"//android.widget.TextView[@content-desc=\"cancelled title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]");

	By todaysCompletedAppointmentPatientName = By.xpath(
			"//android.widget.TextView[@content-desc=\"completed title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]");

	By todaysAppointmentPatientName = By
			.xpath("//android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"]");

	By todayAppointmentPatients = By.xpath(
			"(//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"])/android.widget.RelativeLayout");

	By byChromeAcceptAndContinue = By.id("com.android.chrome:id/terms_accept");

	@AndroidFindBy(id = "org.intelehealth.app:id/patname_txt")
	private WebElement lblPatientNameInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/openmrsID_txt")
	private WebElement lblOpenMRSIDInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/gender_age_txt")
	private WebElement lblGenderAndAgeInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/chief_complaint_txt")
	private WebElement lblChiefComplaintInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/visitID_appointment")
	private WebElement lblVisitIDInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/dr_speciality_appointment")
	private WebElement lblDoctorSpecialityInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/visitDetails_text")
	private WebElement lblVisitDetailsInAppointmentDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/completed_tv")
	private WebElement lblCompletedInFilters;

	@AndroidFindBy(id = "org.intelehealth.app:id/cancelled_tv")
	private WebElement lblCancelledInFilters;

	@AndroidFindBy(id = "org.intelehealth.app:id/missed_tv")
	private WebElement lblMissedInFilters;

	@AndroidFindBy(id = "org.intelehealth.app:id/upcoming_tv")
	private WebElement lblUpcomingInFilters;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Completed')]")
	private WebElement lblCompletedInPastAppointmentsAfterFilter;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Cancelled')]")
	private WebElement lblCancelledInPastAppointmentsAfterFilter;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Missed')]")
	private WebElement lblMissedInPastAppointmentsAfterFilter;

	// Constructor
	public AppointmentsPage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		visitSummaryPage = new VisitSummaryPage(driver);
		robot = new Robot();
	}

	// Verifies that the appointment details screen is displayed
	public boolean verifyAppointmentDetailsScreenIsDisplayed() throws Throwable {
		refreshAppointments();
		waitForVisibility(MyAppointmentsTitle);
		isDisplayed(MyAppointmentsTitle, "MyAppointment Screen is Displayed");
		click(tabUpcomingAppointments, "Clicked on Completed Tab");

		try {
			if (isDisplayed(completedPatient, "Completed patient is Displayed")) {
				click(completedPatient, "Clicked on Completed Patient ");
			}
		} catch (Exception e) {
			// Handle the exception if the element is not found or any other error occurs
		}

		return isDisplayed(appointmentsDetailsScreenTitle, "Appointments Details Screen is Displayed");

	}

	public boolean verifyAppointmentDetailsScreenIsDisplayedFromPastAppointments() throws Throwable {
		refreshAppointments();
		waitForVisibility(MyAppointmentsTitle);
		isDisplayed(MyAppointmentsTitle, "MyAppointment Screen is Displayed");
		click(tabPastAppointments, "Clicked on Completed Tab");

		try {
			if (isDisplayed(completedPatient, "Completed patient is Displayed")) {
				click(completedPatient, "Clicked on Completed Patient ");
			}
		} catch (Exception e) {
			// Handle the exception if the element is not found or any other error occurs
		}

		return isDisplayed(appointmentsDetailsScreenTitle, "Appointments Details Screen is Displayed");

	}

	// Verifies the functionality of the "All" tab
	public void verifyAllTab() throws Throwable {
		refreshAppointments();
		// click(all, "Clicked on All Tab");
		click(tabPastAppointments, "Clicked on All Tab");

		try {
			// Check if elements are displayed before getting counts
			if (isDisplayed(upcomingAppointmentsCount, "Upcoming Appointment Count is Displayed")) {
				int appointmentsCount = Integer.parseInt(upcomingAppointmentsCount.getText().toString());
				ExtentReport.getTest().log(Status.INFO, appointmentsCount == 0 ? "No upcoming appointments"
						: "Upcoming appointments count: " + appointmentsCount);
				System.out.println(appointmentsCount == 0 ? "No upcoming appointments"
						: "Upcoming appointments count: " + appointmentsCount);
			} else {
				ExtentReport.getTest().log(Status.INFO, "Upcoming Appointments count element is not displayed");
				System.out.println("Upcoming Appointments count element is not displayed");
			}

			if (isDisplayed(completedAppointmentsCount, "Completed Appointment Count is Displayed")) {
				int completedCount = Integer.parseInt(completedAppointmentsCount.getText());
				ExtentReport.getTest().log(Status.INFO, completedCount == 0 ? "No completed appointments"
						: "Completed appointments count: " + completedCount);
				System.out.println(completedCount == 0 ? "No completed appointments"
						: "Completed appointments count: " + completedCount);
			} else {
				ExtentReport.getTest().log(Status.INFO, "Completed Appointments count element is not displayed");
				System.out.println("Completed Appointments count element is not displayed");
			}

			if (isDisplayed(cancelledAppointmentsCount, "Cancelled Appointment Count is Displayed")) {
				int cancelledCount = Integer.parseInt(cancelledAppointmentsCount.getText());
				ExtentReport.getTest().log(Status.INFO, cancelledCount == 0 ? "No cancelled appointments"
						: "Cancelled appointments count: " + cancelledCount);
				System.out.println(cancelledCount == 0 ? "No cancelled appointments"
						: "Cancelled appointments count: " + cancelledCount);
			} else {
				ExtentReport.getTest().log(Status.INFO, "Cancelled Appointments count element is not displayed");
				System.out.println("Cancelled Appointments count element is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle exceptions appropriately
		}
	}

	// Verifies the functionality of the "Appointments" radio button
	public void verifyAppointmentsRadioButton() throws Throwable {
		refreshAppointments();
		click(tabPastAppointments, "Clicked on All");
		click(filter, "Clicked on Filter");
		click(completedRadioButton, "Clicked on Completed Radio Button");

		isDisplayed(completedAppointmentResult, "Completed Appointment Result is Displayed");

		isDisplayed(completedAppointmentTitle, "Completed Appointment Title is Displayed");

	}

	// Verifies that the user can select "From" and "To" dates
	public void verifyUserCanSelectFromAndToDate() throws Throwable {
		refreshAppointments();
		click(tabPastAppointments, "Clicked on All");
		click(calender, "Clicked on Calendar");

		// Select From Date
		click(fromDateCalenderIcon, "Clicked on From Date Calendar Icon");
		click(monthSpinner, "Clicked on Month Spinner");
		click(fromMonth, "Clicked on From Month");
		click(yearSpinner, "Clicked on Year Spinner");
		click(fromYear, "Clicked on From Year");
		click(date, "Clicked on Date");
		click(okayButton, "Clicked on Okay Button for From Date");

		// Select To Date
		click(toDateCalenderIcon, "Clicked on To Date Calendar Icon");
		click(monthSpinner, "Clicked on Month Spinner");
		click(toMonth, "Clicked on To Month");
		click(yearSpinner, "Clicked on Year Spinner");
		click(toYear, "Clicked on To Year");
		click(date, "Clicked on Date");
		click(okayButton, "Clicked on Okay Button for To Date");

		isDisplayed(dateRange, "Date Range is Displayed");

	}

	// Verifies the count of appointments in the "Cancelled" tab
	public void verifyCountOfCancelledTab() throws Throwable {
		refreshAppointments();
		click(cancelledTab, "Clicked on cancelled Tab");
		String cc = cancelledCount.getText();
		String ct = cancelledTitelText.getText();
		int extractedCT = 0;
		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(ct);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			extractedCT = Integer.parseInt(numberString);

			System.out.println("Extracted number: " + extractedCT);
		} else {
			System.out.println("No number found in the input string.");
		}
		System.out.println("Cancelled count: " + cc);
		System.out.println("Extracted number: " + extractedCT);
		if (Integer.parseInt(cc) == extractedCT) {
			ExtentReport.getTest().log(Status.INFO, "Verified the count of the Cancelled tab..");
			System.out.println("Verified the count of the Cancelled tab..");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification of count of cancelled failed; count does not match");
			System.out.println("Verification of count of cancelled failed; count does not match");
		}

	}

	// Verifies the functionality of the "Cancelled" tab
	public void verifyCancelledTabFunctionality() throws Throwable {
		refreshAppointments();
		click(cancelledTab, "Clicked on cancelled Tab");
		try {
			if (isDisplayed(cancelledTitelText, "Cancelled TitleText is Displayed")) {
				ExtentReport.getTest().log(Status.INFO, "Cancelled section present.");
				System.out.println("Cancelled section present.");
			} else {
				ExtentReport.getTest().log(Status.INFO, "Cancelled section not present.");
				System.out.println("Cancelled section not present.");
			}

			if (isDisplayed(completedTitelText, "Completed Title Text is Displayed")) {
				ExtentReport.getTest().log(Status.INFO, "Completed section present.");
				System.out.println("Completed section present.");
			} else {
				ExtentReport.getTest().log(Status.INFO, "Completed section not  present.");
				System.out.println("Completed section not present.");
			}

			if (isDisplayed(upcomingTitleText, "Upcoming Title Text is Displayed")) {
				ExtentReport.getTest().log(Status.INFO, "Upcoming section present.");
				System.out.println("Upcoming section present.");
			} else {
				ExtentReport.getTest().log(Status.INFO, "Upcoming section  not present.");
				System.out.println("Upcoming section not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String ct = completedTitelText.getText();
		String cc = completedCount.getText();
		int extractedCT = 0;
		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(ct);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			extractedCT = Integer.parseInt(numberString);
			System.out.println("Extracted number: " + extractedCT);
		} else {
			System.out.println("No number found in the input string.");
		}
		if (Integer.parseInt(cc) == extractedCT) {
			ExtentReport.getTest().log(Status.INFO, "Verified the count of the completed tab..");
			System.out.println("Verified the count of the completed tab..");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification of count of completed failed; count does not match");
			System.out.println("Verification of count of completed failed; count does not match");
		}

	}

	// Verifies the functionality of the "Cancel" button
	public boolean verifyCancelButton() throws Throwable {
		refreshAppointments();
		Thread.sleep(2000);
		int number = 0;
		String uvalue = upcomingTitleText.getText();

		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(uvalue);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			number = Integer.parseInt(numberString);
			System.out.println("Extracted number: " + number);
		} else {
			System.out.println("No number found in the input string.");
		}

		if (number > 0) {

			click(upcomingPatient, "Clicked on Upcoming Patient");
			click(appointmentCancelButton, "Clicked on Appointment Cancel Button");

			return isDisplayed(cancelAppointmentDialog, "Cancel Appointment Dialog is Displayed")
					&& isDisplayed(cancelAppointmentDialogSubtitle, "Cancel Appointment Dialog Subtitle is Displayed")
					&& isDisplayed(yesButton, "Yes Button is Displayed")
					&& isDisplayed(noButton, "No Button is Displayed");
		} else {
			// Throw an exception or use an assertion to fail the test case
			throw new AssertionError("No upcoming patient found. Test case failed.");
		}
	}

	// Verifies the functionality of the "Yes" button
	public boolean verifyYesButton(String cancelReason) throws Throwable {
		refreshAppointments();
		// click(backArrow, "Clicked on Back Arrow");
		Thread.sleep(5000);
		// click(appointments, "Clicked on Appointments");
//		for(int i=0; i<3;i++) {
//		click(refresh);
//		}
		// click(refresh);

		int number = 0;
		String uvalue = upcomingTitleText.getText();
		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(uvalue);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			number = Integer.parseInt(numberString);
			System.out.println("Extracted number: " + number);
		} else {
			System.out.println("No number found in the input string.");
		}

		if (number > 0) {
			click(upcomingPatient, "Clicked on Upcoming Patient");
			click(appointmentCancelButton, "Clicked on Appointment Cancel Button");

			isDisplayed(cancelAppointmentDialog, "Cancel Appointment Dialog is Displayed");
			isDisplayed(cancelAppointmentDialogSubtitle, "Cancel Appointment Dialog Subtitle is Displayed");

			click(yesButton, "Clicked on Yes Button");

			return getText(reasonDialogTitle, "Reason Dialog Title is Displayed").equals(cancelReason)
					&& isDisplayed(doctorRadioButton, "Doctor Radio Button is Displayed")
					&& isDisplayed(noPatientRadioButton, "No Patient Radio Button is Displayed")
					&& isDisplayed(othersRadioButton, "Others Radio Button is Displayed")
					&& isDisplayed(cancelButton, "Cancel Button is Displayed")
					&& isDisplayed(saveButton, "Save Button is Displayed");
		} else {

			throw new AssertionError("No upcoming patient found. Test case failed.");

		}

	}

	// Verifies the Completed tab functionality
	public boolean verifyPastAppointmentsTab() throws Throwable {
		refreshAppointments();
		click(tabPastAppointments, "Clicked On Completed Tab");

		int number = 0;
		String countOfPastAppointments = tabPastAppointments.getText();

		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(countOfPastAppointments);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			number = Integer.parseInt(numberString);
			System.out.println("Extracted number: " + number);
		} else {
			System.out.println("No number found in the input string.");
		}
		return isDisplayed(icnFilterInPastAppointments) && isDisplayed(icnSortInPastAppointments)
				&& isDisplayed(inpSearchInPastAppointments);
	}

	// Verifies the functionality of the Save button on the appointment screen
	public boolean verifyAppointmentSaveButton() throws Throwable {
		refreshAppointments();
//		click(backArrow,"Clicked on Back Arrow");
//		Thread.sleep(10000);
//		click(appointments,"Clicked onAppointments");
//	for(int i=0; i<3;i++) {
//	click(refresh);
//	}
//		click(refresh,"Clicked On Refresh ");
		/*
		 * int number = 0; String uvalue = upcomingTitleText.getText(); // Define a
		 * regular expression pattern to match the numeric part Pattern pattern =
		 * Pattern.compile("\\d+");
		 * 
		 * // Create a Matcher object Matcher matcher = pattern.matcher(uvalue);
		 * 
		 * // Check if a match is found if (matcher.find()) { // Extract and print the
		 * matched number String numberString = matcher.group(); number =
		 * Integer.parseInt(numberString); System.out.println("Extracted number: " +
		 * number); } else { System.out.println("No number found in the input string.");
		 * }
		 */
		int number = getAppointmentCountFromTab(upcomingTitleText);
		if (number > 0) {
			String patientNameBeforeCancellation = todayUpcomingAppointmentPatientName.getText();
			click(upcomingPatient, "Clicked on Upcoming Patient");
			click(appointmentCancelButton, "Clicked on Appointment Cancel Button");

			isDisplayed(cancelAppointmentDialog, "Cancel Appointment Dialog is Displayed");

			click(yesButton, "Clicked on Yes Button");
			click(noPatientRadioButton, "Clicked on No Patient Radio Button");
			click(saveButton, "Clicked on Save Button");waitFor(3000);
			for (int i = 0; i < lstUpcomingAppointmentPatientName.size(); i++) {
				waitFor(2000);
				if (getText(lstUpcomingAppointmentPatientName.get(i), "Today's Upcoming Appointment Patient Name")
						.equals(patientNameBeforeCancellation)) {
					return false;
				}
			}
			/*
			 * try { return isDisplayed(appointmentCanceledSuccessMsg,
			 * "Appointment Canceled Success Message is Displayed"); } catch (Exception e) {
			 * // TODO: handle exception }
			 */
			return isDisplayed(MyAppointmentsTitle, "My Appointments Title is Displayed")
					&& number - 1 == getAppointmentCountFromTab(tabUpcomingAppointments);

		} else {

			throw new AssertionError("No upcoming patient found. Test case failed.");

		}
	}

	public int getAppointmentCountFromTab(WebElement element) throws Throwable {
		int number = 0;
		String uvalue = element.getText();
		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(uvalue);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			number = Integer.parseInt(numberString);
			return number;
		} else {
			return 0;
		}
	}

	// Verifies that the Schedule Appointment page is displayed
	public void verifySchedulaAppointmentPageIsDisplayed() throws Throwable {
		refreshAppointments();
		click(backArrow, "Clicked on Back Arrow");
		Thread.sleep(10000);
		click(appointments, "Clicked onAppointments");
//	for(int i=0; i<3;i++) {
//	click(refresh);
//	}
		click(refresh, "Clicked on Refresh");
		// click(cancelledTab, "Clicked on cancelled Tab");
		int number = 0;
		String uvalue = cancelledTitelText.getText();
		// Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(uvalue);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			number = Integer.parseInt(numberString);
			System.out.println("Extracted number: " + number);
		} else {
			System.out.println("No number found in the input string.");
		}

		if (number > 0) {
			click(upcomingPatient, "Clicked on Upcoming Patient");
			click(appointmentScheduleButton, "Clicked on Appointment Schedule Button");

			isDisplayed(scheduleAppointmentTitle, "Schedule Appointment Title is Displayed");
		}
	}

	// Verifies the Cancelled tab functionality
	public void verifyCancelledTab() throws Throwable {
		refreshAppointments();
		click(backArrow, "Clicked on Back Arrow");
		Thread.sleep(10000);
		click(appointments, "Clicked on Appointments");
		Thread.sleep(2000);
		click(cancelledTab, "Clicked on cancelled Tab");
		String count;
		count = cancelledCount.getText();
		click(upcomingTab, "Clicked on Upcoming Tab");
		String uCount = upcomingTitleText.getText();
		int number = 0;
//Define a regular expression pattern to match the numeric part
		Pattern pattern = Pattern.compile("\\d+");

		// Create a Matcher object
		Matcher matcher = pattern.matcher(uCount);

		// Check if a match is found
		if (matcher.find()) {
			// Extract and print the matched number
			String numberString = matcher.group();
			number = Integer.parseInt(numberString);
			System.out.println("Extracted number: " + number);
		} else {
			System.out.println("No number found in the input string.");
		}
//click(upcomingTab);
		if (number > 0) {
			click(upcomingPatient, "Clicked on Upcoming Patient");
			click(cancelButton, "Clicked on Cancel Button");
			click(yesButton, "Clicked on Yes Button");
			click(noPatientRadioButton, "Clicked on No Patient Radio Button");
			click(saveButton, "Clicked on Save Button");
			click(cancelledTab, "Clicked on Cancelled Tab");
			String newCount = cancelledCount.getText();

			if (Integer.parseInt(count) > Integer.parseInt(newCount)) {
				System.out.println("Cancelled count increased successfully");

			}

		}
	}

	public void verifyCancelledSection() throws Throwable {
		refreshAppointments();
		// Click on Back Arrow
		click(backArrow, "Clicked on Back Arrow");

		// Wait for some time (you may replace this with a more robust wait strategy)
		Thread.sleep(10000);

		// Click on Appointments again
		click(appointments, "Clicked on Appointments");

		// Wait for some time (you may replace this with a more robust wait strategy)
		Thread.sleep(2000);

		// Click on Cancelled Tab
		click(cancelledTab, "Clicked on Cancelled Tab");

		// Get the count of cancelled appointments from the UI
		String countInBrackets = cancelledTitelText.getText();

		// Extract the numeric count from the string using regular expressions
		Matcher matcher = Pattern.compile("\\d+").matcher(countInBrackets);
		int countFromUI = matcher.find() ? Integer.parseInt(matcher.group()) : 0;

		// Verify that it shows the correct count
		if (countFromUI == 0) {
			System.out.println("Verification: Cancelled Tab shows Cancelled (0)");
		} else {
			System.out.println("Verification: Cancelled Tab shows Cancelled (" + countFromUI + ")");
		}

		// Get the list of cancelled appointments
		List<WebElement> cancelledAppointments = apponimentsCancelledPatients;

		// Check if the list is empty
		if (cancelledAppointments.isEmpty()) {

			throw new AssertionError("No cancelled patients found. Test case failed.");

		} else {
			ExtentReport.getTest().log(Status.INFO, "Cancelled patients found");
			System.out.println("Cancelled patients found");

		}
	}

	// Clicking on app sync icon and Clicking on appointments
	public void refreshAppointments() {
		int maxAttempts = 3;
		int attempt = 0;

		while (attempt < maxAttempts) {
			try {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

				}
				click(homeScreenRefreshButton, "Clicking on app sync icon");
				click(openAppointments, "Clicking on appointments");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
	}

	/*
	 * Gets the last character of the provided string and verifies whether No. of
	 * 'Upcoming' appointments are displayed
	 */
	public char doGetLastOneCharUpcoming(WebElement e) throws InterruptedException {
		waitForVisibility(e);
		String text = e.getText();
		char Upcoming = text.charAt(9);
		char Upcoming1 = text.charAt(10);
		if (Character.isDigit(Upcoming) || Character.isDigit(Upcoming1)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether No. of 'Upcoming' appointments are displayed");
			System.out.println("No. of 'Upcoming' appointments are displayed");
		}
		return Upcoming;
	}

	/*
	 * Gets the last character of the provided string and verifies whether No. of
	 * 'Cancelled' appointments are displayed
	 */
	public char doGetLastOneCharCancelled(WebElement e) throws InterruptedException {
		waitForVisibility(e);
		String text = e.getText();
		char Cancelled = text.charAt(10);
		char Cancelled1 = text.charAt(11);
		if (Character.isDigit(Cancelled) || Character.isDigit(Cancelled1)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether No. of 'cancelled' appointments are displayed");
			System.out.println("No. of 'Cancelled' appointments are displayed");
		}
		return Cancelled;
	}

	/*
	 * Gets the last character of the provided string and verifies whether No. of
	 * 'Completed' appointments are displayed
	 */
	public char doGetLastOneCharCompletedSection(WebElement e) throws InterruptedException {
		waitForVisibility(e);
		String text = e.getText();
		char Completed = text.charAt(10);
		char Completed1 = text.charAt(11);
		if (Character.isDigit(Completed) || Character.isDigit(Completed1)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether No. of 'completed' appointments are displayed");
			System.out.println("No. of 'Completed' appointments are displayed");
		}

		return Completed;
	}

	// Verifies if only today's date appointments are showing under the "Today's"
	// tab
	public void verifyIfOnlyTodaysDateAppointmentsAreShowingUnderTodaysTab() throws Throwable {
		refreshAppointments();
		click(todaysTab, "Clicking on today's tab");
		click(upcomingTab, "Clicking on upcoming tab");
		boolean There = isDisplayed2(todaysAppointmentPatientName);
		if (There == true) {
			List<WebElement> patientNames = getElements(todayAppointmentPatients);
			int allPatients = patientNames.size();
			int maxRetries = allPatients; // Maximum number of retries
			// Retry loop
			for (int attempt = 0; attempt < maxRetries; attempt++) {
				List<WebElement> allPatientNames = getElements(todayAppointmentPatients);
				for (Iterator<WebElement> iterator = allPatientNames.iterator(); iterator.hasNext();) {
					WebElement webElement = (WebElement) iterator.next();
					click(webElement, "Clicking on today's appointments");
					try {
						waitForVisibility(appointmentsDate);
						String appointmentsDateText = appointmentsDate.getText();
						click(todayAppointmentBackArrow);
						System.out.println("Appointment Date: " + appointmentsDateText);
						// Get the current date text to compare against
						CharSequence currentFormattedDate = doGetFormattedCurrentDDMonYYYY();
						if (appointmentsDateText.equals(currentFormattedDate)) {
							ExtentReport.getTest().log(Status.INFO,
									"Verifying whether current date's appointment is displayed");
							System.out.println("Current date's appointment is displayed");
						} else {
							throw new Exception("The appointment displayed is not for the current date");
						}
						// Break out of the retry loop if successful
						break;
					} catch (StaleElementReferenceException e) {
						// Element is stale, retry
						System.out.println("Stale element exception. Retrying...");
					}
				}
			}
		}

		if (There == false) {
			ExtentReport.getTest().log(Status.INFO, "There are no appointments in the current date");
			System.out.println("There are no appointments in the current date");
		}
	}

	// Verifies that the cancelled section is displayed
	public void cancelledSectionDisplayed() throws InterruptedException {
		ExtentReport.getTest().log(Status.INFO, "Verifying whether cancelled section is displayed");
		isDisplayed(cancelledSection);
	}

	// Verifies that the completed section is displayed
	public void completedSectionDisplayed() throws InterruptedException {
		ExtentReport.getTest().log(Status.INFO, "Verifying whether completed section is displayed");
		isDisplayed(completedSection);
	}

	// Verifies the functionality of the 'Upcoming' tab
	public void verifyTheFunctionalityOfUpcomingTab() throws Throwable {
		refreshAppointments();
		ExtentReport.getTest().log(Status.INFO, "Verifying whether upcoming section is displayed");
		isDisplayed(upcomingSection);
		doGetLastOneCharUpcoming(upcomingSection);
		scrollToElementByDescription("cancelled title textview in Todays Appointments");
		cancelledSectionDisplayed();
		doGetLastOneCharCancelled(cancelledSection);
		scrollToElementByDescription("completed title textview in Todays Appointments");
		completedSectionDisplayed();
		doGetLastOneCharCompletedSection(completedSection);
		boolean patients = isDisplayed2(todaysAppointmentPatientName);
		if (patients == true) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether there are patients in the current date");
			System.out.println("There are patients in the current date");
		}
		if (patients == false) {
			ExtentReport.getTest().log(Status.INFO, "There are no patients in the current date");
			System.out.println("There are no patients in the current date");
		}
	}

	// Verifies the presence of the cancelled section under the 'Upcoming' tab
	public void verifyCancelledSectionUnderUpcomingTab() throws Throwable {
		refreshAppointments();
		waitForVisibility(upcomingSection);
		scrollToElementByDescription("cancelled title textview in Todays Appointments");
		cancelledSectionDisplayed();
		doGetLastOneCharCancelled(cancelledSection);
		boolean Patients = isDisplayed2(todaysCancelledAppointmentPatientName);
		if (Patients == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether there are cancelled appointments in the current date");
			System.out.println("There are 'cancelled' appointments in the current date");
		}
		if (Patients == false) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether there are no cancelled appointments in the current date");
			System.out.println("There are no 'cancelled' appointments in the current date");
		}
	}

	// Verifies the presence of the completed section under the 'Upcoming' tab
	public void verifyCompletedSectionUnderUpcomingTab() throws Throwable {
		refreshAppointments();
		scrollToElementByDescription("completed title textview in Todays Appointments");
		completedSectionDisplayed();
		doGetLastOneCharCompletedSection(completedSection);
		boolean Patients = isDisplayed2(todaysCompletedAppointmentPatientName);
		if (Patients == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether there are completed appointments in the current date");
			System.out.println("There are 'completed' appointments in the current date");
		}
		if (Patients == false) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether there are no completed appointments in the current date");
			System.out.println("There are no 'completed' appointments in the current date");
		}
	}

	// Verifies the count of patients in the 'Upcoming' tab
	public void verifyTheCountOfUpcomingTab() throws Throwable {
		refreshAppointments();
		click(todaysTab);
		boolean upcomingThere = isDisplayed2(todaysUpcomingAppointmentPatientName);
		if (upcomingThere == true) {
			List<WebElement> upcomingPatientNames = getElements(todaysUpcomingAppointmentPatientName);
			int totalUpcomingPatients = upcomingPatientNames.size();
			waitForVisibility(upcomingSection);
			String upcomnigCount = upcomingSection.getText();
			CharSequence intUpcomnigCount = String.valueOf(totalUpcomingPatients);
			if (upcomnigCount.contains(intUpcomnigCount)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether displaying the correct count of upcoming patients");
				System.out.println("Displaying the correct count of upcoming patients");
			}
		}
		if (upcomingThere == false) {
			ExtentReport.getTest().log(Status.INFO, "There are no patients in the 'upcoming' section");
			System.out.println("There are no patients in the 'upcoming' section");
		}
		scrollToElementByDescription("cancelled title textview in Todays Appointments");
		cancelledSectionDisplayed();
		boolean cancelledThere = isDisplayed2(todaysCancelledAppointmentPatientName);
		if (cancelledThere == true) {
			List<WebElement> cancelledPatientNames = getElements(todaysCancelledAppointmentPatientName);
			int totalCancelledPatients = cancelledPatientNames.size();
			String cancelledCount = cancelledSection.getText();
			String cancelledPatients = String.valueOf(totalCancelledPatients);
			if (cancelledCount.contains(cancelledPatients)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether displaying the correct count of cancelled patients");
				System.out.println("Displaying the correct count of cancelled patients");
			}
		}
		if (cancelledThere == false) {
			ExtentReport.getTest().log(Status.INFO, "There are no patients in the cancelled section");
			System.out.println("There are no patients in the 'cancelled' section");
		}
		scrollToElementByDescription("completed title textview in Todays Appointments");
		completedSectionDisplayed();
		boolean completedThere = isDisplayed2(todaysCompletedAppointmentPatientName);
		if (completedThere == true) {
			List<WebElement> completedPatientNames = getElements(todaysCompletedAppointmentPatientName);
			int TotalCompletedPatients = completedPatientNames.size();
			waitForVisibility(completedSection);
			String completedCount = completedSection.getText();
			String completedPatients = String.valueOf(TotalCompletedPatients);
			if (completedCount.contains(completedPatients)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether displaying the correct count of completed patients");
				System.out.println("Displaying the correct count of completed patients");
			}
		}
		if (cancelledThere == false) {
			ExtentReport.getTest().log(Status.INFO, "There are no patients in the 'completed' section");
			System.out.println("There are no patients in the 'completed' section");
		}

	}

	/*
	 * Verifies the patient visit details displayed after clicking on upcoming
	 * appointment from 'Upcoming' section
	 */
	public void verifyThePatientVisitDetailsInUpcomingSection() throws Throwable {
		refreshAppointments();
		waitForVisibility(todayUpcomingAppointmentslatest);
		click(todayUpcomingAppointmentslatest, "Clicking on newly added upcoming appointment");
		if (isDisplayed(appointmentDetailsPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to appointment details page");
		} else {
			throw new Exception("User is not navigated to appointment details page");
		}

	}

	/*
	 * Verifies the functionality of clicking on the call and WhatsApp icons on the
	 * Appointment Details page
	 */
	public boolean verifyClickingOnCallAndWhatsappIconAnAppointmentDetailsPageWhenNumberIsProvided() throws Throwable {
		verifyThePatientVisitDetailsInUpcomingSection();

		if (isDisplayed(appointmentDetailsCallIcon) && isDisplayed(appointmentDetailsWhatsappIcon)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether Call Icon and Whatsapp Icon are displayed");
			System.out.println("Call Icon and Whatsapp Icon are displayed");
		}
		click(appointmentDetailsWhatsappIcon, "Clicking on whatsapp icon");
		boolean isChromeAppOpened = isAppOpened("com.android.chrome");
		getDriver().navigate().back();
		Thread.sleep(3000);
		click(appointmentDetailsCallIcon, "Clicking on call icon");

		boolean isDialerOpened = isAppOpened("dialer");

		return isChromeAppOpened && isDialerOpened;

		/*
		 * boolean acceptAndContine = isDisplayed2(byChromeAcceptAndContinue); if
		 * (acceptAndContine == true) { boolean isChromeAppOpened =
		 * isAppOpened("com.android.chrome"); // click(chromeAcceptAndContinue); } if
		 * (isDisplayed(turnOnSyncNoThanks) && isDisplayed(turnOnSyncAddAccount)) {
		 * ExtentReport.getTest().log(Status.INFO,
		 * "There is no whatsapp application in the device");
		 * System.out.println("There is no whatsapp application in the device"); for
		 * (int i = 0; i < 2; i++) { ExtentReport.getTest().log(Status.INFO,
		 * "Navigating back"); getDriver().navigate().back(); } }
		 * click(appointmentDetailsCallIcon, "Clicking on call icon"); if
		 * (isDisplayed(callMoreOptions) && isDisplayed(phoneNumberDigits) &&
		 * isDisplayed(callBackSpace)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verifying whether Dial pad is opened"); isAppOpened("dialer");
		 * System.out.println("Dial pad is opened"); click(dialIcon,
		 * "Clicking on dial icon"); }
		 */

	}

	/*
	 * Verifies that clicking the arrow next to the Visit Summary on the AppointmentnDetails page navigates the user to the Visit Summary page with all thedetails displayed
	 */
	public boolean verifyUserClickingOnArrowNextToVisitSummaryOnAppointmentDetailsPage() throws Throwable {
		refreshAppointments();
		click(upcomingAppointments, "Clicking on upcoming appointment");
		click(appointmentDetailsVisitSummaryForwardArrow, "Clicking on arrow next to visit summary");
		if (isDisplayed(visitSummaryPageTitle)
				&& isDisplayed(visitSummaryPageHeight) && isDisplayed(visitSummaryPageWeight)
				&& isDisplayed(visitSummaryPageBMI) && isDisplayed(visitSummaryPageBMI)
				&& isDisplayed(visitSummaryPageBP) && isDisplayed(visitSummaryPagePulse)
				&& isDisplayed(visitSummaryPageTemp) && isDisplayed(visitSummaryPageSpO2)
				&& isDisplayed(visitSummaryPageRespRate)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether visit summary page is displayed with all the details");
			System.out.println("Visit summary page is displayed with all the details");
			return true;
		}
		return false;
	}

	// Verifies the functionality of the 'Reschedule' feature on the Appointment
	// Details page
	public boolean verifyTheFunctionalityOfRescheduleInAppointmentDetailsPage() throws Throwable {
		refreshAppointments();
		waitForVisibility(upcomingAppointments);
		click(upcomingAppointments, "Clicking on upcoming appointment");
		click(appointmentDetailsRescheduleButton, "Clicking on reschedule button");

		if (isDisplayed(rescheduleAppointmentText) && isDisplayed(areUSureRescheduleText)
				&& isDisplayed(appointmentNoButton) && isDisplayed(appointmentYesButton)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether reschedule appointment popup is displayed with proper text");
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether reschedule appointment popup is displayed with 'Yes' and 'No' button");
			System.out
					.println("Reschedule appointment popup is displayed with proper text,  with 'Yes' and 'No' button");
			return true;
		}
		return false;
	}

	/*
	 * Verifies that the user receives the 'Select Reschedule Reason' popup by
	 * clicking 'Yes' when attempting to reschedule an appointment on the
	 * Appointment Details page
	 */
	public boolean verifyUserGetsTheSelectRescheduleReasonPopupByClickingYes() throws Throwable {
		verifyTheFunctionalityOfRescheduleInAppointmentDetailsPage();
		click(appointmentYesButton, "Clicking on 'Yes' button");
		if (isDisplayed(pleaseSelectYourRescheduleReasonText) && isDisplayed(doctorIsNotAvailableRadioButton)
				&& isDisplayed(patientIsNotAvailableRadioButton) && isDisplayed(othersRadioButton)
				&& isDisplayed(rescheduleReasonCancelButton) && isDisplayed(rescheduleReasonSaveButton)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether select reason popup is displayed with all the radio buttons");
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether 'Cancel' and 'Save' buttons are displayed in the select your reason popup");
			return true;
			// System.out.println("Select reason popup is displayed with all the radio
			// buttons");
		}
		return false;
	}

	/*
	 * Verifies the functionality of clicking on the 'Save' button after selecting a
	 * reason in the 'Reschedule Reason' popup during the appointment rescheduling
	 * process
	 */
	public boolean verifyClickingOnSaveButtonBySelectingReasonInRescheduleReasonPopup() throws Throwable {
		verifyUserGetsTheSelectRescheduleReasonPopupByClickingYes();
		click(patientIsNotAvailableRadioButton, "Clicking on patient is not available radio button");
		click(rescheduleReasonSaveButton, "Clicking on 'Save' button");
		boolean scheduleAppointment = getText(scheduleAppointmentText, "Schedule appointment")
				.equals("Schedule appointment");
		boolean bookAppointment = getText(bookAppointmentButton, "Schedule appointment").equals("BOOK APPOINTMENT");

		// boolean bookAppointment = isDisplayed(bookAppointmentButton);
		if (scheduleAppointment == true && bookAppointment == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to schedule appointment screen");
			System.out.println("User is navigated to schedule appointment screen");
			return true;
		}
		return false;
	}

	// Verifies the process of selecting any available time slot and clicking on the
	// 'Book Appointment' button
	public List<String> verifySelectingAnyTimeSlotsAndClickingOnBookAppointment() throws Throwable {
		verifyClickingOnSaveButtonBySelectingReasonInRescheduleReasonPopup();
		List<String> lblsConfirmAppointmentDialog = new ArrayList<>();
		click(appointmentFirstTime, "Selecting the time slot");
		click(bookAppointmentButton, "Clicking on 'Book Appointment' button");

		lblsConfirmAppointmentDialog.add(getText(confirmAppointmentText, "Confirm appointment text"));
		lblsConfirmAppointmentDialog.add(getText(appointmentYesButton, "Yes button text"));
		lblsConfirmAppointmentDialog.add(getText(appointmentNoButton, "No button text"));
		return lblsConfirmAppointmentDialog;

	}

	public boolean isDisplayedconfirmAppointmentPopupText(String rescheduleConfirmText) throws Throwable {
		return getText(confirmAppointmentPopupText, "Confirm appointment popup text").contains(rescheduleConfirmText);
	}

	// Verifies the appearance and functionality of the 'Confirm Appointment' popup
	public boolean verifyTheConfirmAppointmentPopup() throws Throwable {
		verifySelectingAnyTimeSlotsAndClickingOnBookAppointment();
		if (isDisplayed(confirmAppointmentText) && isDisplayed(confirmAppointmentPopupText)
				&& isDisplayed(appointmentNoButton) && isDisplayed(appointmentYesButton)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether confirm appointment popup should be displayed with the proper text");
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether confirm appointment popup is displayed with 'Yes' and 'No' button");
			System.out.println("Confirm appointment popup should be displayed with the proper text");
			return true;
		}
		return false;

	}

	// Verifies the behavior when the user clicks 'Yes' in the 'Confirm Appointment'
	// popup
	public boolean verifyWhenUserClicksYesInConfirmAppointmentPopup() throws Throwable {
		click(appointmentYesButton, "Clicking on 'Yes' button in the confirm popup");
		boolean isDisplayedBookingAppointmentText = false;
		if (isDisplayed(bookingAppointmentText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether booking appointment popup is displayed");
			System.out.println("Booking appointment popup is displayed");
			isDisplayedBookingAppointmentText = true;
		}
		return isDisplayedBookingAppointmentText;

	}

	// Verifies that a rescheduled appointment is displayed under the 'Today's' tab
	// in the 'Upcoming' section
	public boolean verifyRescheduledAppointmentDisplaysUnderTodaysTabOfUpcomingSection() throws Throwable {
		refreshAppointments();
		waitForVisibility(todayUpcomingAppointmentPatientName);
		String reschedulingPatientName = todayUpcomingAppointmentPatientName.getText();
		Thread.sleep(3000);
		click(upcomingAppointments, "Clicking on upcoming appointment");
		click(appointmentDetailsRescheduleButton, "Clicking on reshedule button");
		click(appointmentYesButton, "Clicking on yes button");
		click(patientIsNotAvailableRadioButton, "Clicking on patient is not available radio button");
		click(rescheduleReasonSaveButton, "Clicking on save button");
		click(appointmentFirstTime, "Selecting the time slot");
		click(bookAppointmentButton, "Clicking on book appointment button");
		click(appointmentYesButton, "Clicking on yes button");
		// waitForVisibility(todayUpcomingAppointmentPatientNameAdded);
		String rescheduledPatientName = upcomingAppointments.getText();
		Thread.sleep(6000);
		if (rescheduledPatientName.equals(reschedulingPatientName)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether rescheduled appointment is reflected under Today's tab of Upcoming section");
			System.out.println("Rescheduled appointment is reflected under Today's tab of Upcoming section");
			return true;
		}
		if (!rescheduledPatientName.contains(reschedulingPatientName)) {
			return false;
		}
		return false;

	}

	// Verifies that the user can successfully schedule an appointment
	public void verifyUserCanScheduleTheAppointmentSuccessfully() throws Throwable {
		refreshAppointments();
		click(todaysTab);
		boolean cancelledPatients = isDisplayed2(todaysCancelledAppointmentPatientName);
		if (cancelledPatients == true) {
			waitForVisibility(upcomingTabCount);
			String countBeforeSchedulingCancelled = upcomingTabCount.getText();
			System.out.println(countBeforeSchedulingCancelled);
		}
		if (cancelledPatients == false) {
			boolean IfThere = isDisplayed2(todaysUpcomingAppointmentPatientName);
			if (IfThere == true) {
				waitForVisibility(todayUpcomingAppointmentPatientName);
				String reschedulingPatientName = todayUpcomingAppointmentPatientName.getText();
				System.out.println(reschedulingPatientName);
				click(upcomingAppointments);
				click(appointmentDetailsCancelButton);
				click(appointmentYesButton);
				click(patientIsNotAvailableRadioButton);
				click(rescheduleReasonSaveButton);
			}
			if (IfThere == false) {
				getDriver().navigate().back();
				visitSummaryPage.verifyIfBookedAppointmentIsReflectingInAppointments();
				waitForVisibility(todayUpcomingAppointmentPatientName);
				String reschedulingPatientName = todayUpcomingAppointmentPatientName.getText();
				System.out.println(reschedulingPatientName);
				click(upcomingAppointments);
				click(appointmentDetailsCancelButton);
				click(appointmentYesButton);
				click(patientIsNotAvailableRadioButton);
				click(rescheduleReasonSaveButton);
			}
		}
		ExtentReport.getTest().log(Status.INFO,
				"Getting the count of upcoming patients before scheduling the cancelled appointment");
		waitForVisibility(upcomingTabCount);
		String countBeforeSchedulingCancelled = upcomingTabCount.getText();
		waitForVisibility(firstCancelledAppointmentPatientName);
		String cancelledPatient = firstCancelledAppointmentPatientName.getText();
		click(firstCancelledAppointmentPatientName);
		click(scheduleAppointmentButton);
		click(appointmentFirstTime);
		click(bookAppointmentButton);
		click(appointmentYesButton);
		if (isDisplayed(bookingAppointmentText)) {
			System.out.println("Booking appointment popup is displayed");
		}
		if (isDisplayed(myAppointmentsPageText)) {
			System.out.println("User is landed on 'My appointments' page");
		}
		ExtentReport.getTest().log(Status.INFO,
				"Getting the count of upcoming patients after scheduling the cancelled appointment");
		waitForVisibility(upcomingTabCount);
		String countAfterSchedulingCancelled = upcomingTabCount.getText();
		waitForVisibility(todayUpcomingAppointmentPatientName);
		String upcomingPatients = todayUpcomingAppointmentPatientName.getText();
		if (upcomingPatients.contains(cancelledPatient)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the booked appointment is reflected in 'upcoming' appointment section");
			System.out.println("The booked appointment is reflected in 'upcoming' appointment section");
		}
		if (!countBeforeSchedulingCancelled.equals(countAfterSchedulingCancelled)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the count has been changed");
			System.out.println("Count has been changed");
		}
	}

	public List<LocalDateTime> getAppointmentDateTimes() {

		List<LocalDateTime> actualList = new ArrayList<>();

		for (WebElement element : lstAppointmentTime) {

			String text = element.getText().trim();

			actualList.add(parseAppointmentTime(text));
		}

		return actualList;
	}

	public void verifySortingOrder(boolean ascending, By timeLocator) {

		// 1️⃣ Get actual UI order converted to LocalDateTime
		List<LocalDateTime> actual = getAppointmentDateTimes();

		// 2️⃣ Create expected sorted copy
		List<LocalDateTime> expected = new ArrayList<>(actual);

		Collections.sort(expected);

		if (!ascending) {
			Collections.reverse(expected);
		}

		// 3️⃣ Compare
		Assert.assertEquals(actual, expected, "Sorting order is incorrect!");
	}

	public LocalDateTime parseAppointmentTime(String text) {
		LocalDateTime now = LocalDateTime.now();

		// 1) Relative like: "In 2 hours 34 minutes at 8:30 PM" OR "In 20 minutes at
		// 2:00 PM"
		Pattern relPattern = Pattern.compile(
				"In\\s+(?:(\\d+)\\s+hours?)?\\s*(?:(\\d+)\\s+minutes?)?\\s*at\\s*(\\d{1,2}:\\d{2}\\s*[APMapm]{2})");
		Matcher m = relPattern.matcher(text);
		if (m.find()) {
			String hoursStr = m.group(1);
			String minsStr = m.group(2);
			String timePart = m.group(3);

			int addHours = hoursStr != null ? Integer.parseInt(hoursStr) : 0;
			int addMins = minsStr != null ? Integer.parseInt(minsStr) : 0;

			DateTimeFormatter tf = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
			LocalTime parsedTime = LocalTime.parse(timePart.toUpperCase(), tf);

			// Build tentative date-time: start from now, add relative offsets,
			// but finally ensure the time matches parsedTime (keeps consistent with UI
			// phrasing).
			LocalDateTime candidate = now.plusHours(addHours).plusMinutes(addMins);
			candidate = candidate.withHour(parsedTime.getHour()).withMinute(parsedTime.getMinute()).withSecond(0)
					.withNano(0);

			// If the candidate is in the past (rare edge), move to next day
			if (candidate.isBefore(now))
				candidate = candidate.plusDays(1);

			return candidate;
		}

		// 2) Absolute like: "25-02-2026, 14:00"
		DateTimeFormatter absFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
		try {
			return LocalDateTime.parse(text, absFmt);
		} catch (DateTimeParseException e) {
			// fallback: try other formats or throw
		}

		throw new IllegalArgumentException("Unknown appointment time format: " + text);
	}

	public void clickOnSortIcon() {
		click(icnSortInPastAppointments, "Clicking on sort icon");
	}

	public void clickOnSearch(String patientName) {
		click(inpSearchInPastAppointments, "Clicking on search icon");
		sendKeys(inpSearchInPastAppointments, patientName, "Entering patient name in search");
	}

	public String isDisplayedSearchedPatient() {
		return getText(upcomingPatient, "Getting the name of the searched patient");
	}

	public void NavigateToPastAppointments() {
		refreshAppointments();
		click(tabPastAppointments, "Clicking on today's tab");

	}

	public boolean verifyAppointmentDetailsScreen() throws Throwable {

		// click(upcomingAppointments, "Clicking on upcoming appointment");
		if (isDisplayed(appointmentDetailsPageTitle) && isDisplayed(lblPatientNameInAppointmentDetails)
				&& isDisplayed(lblOpenMRSIDInAppointmentDetails) && isDisplayed(lblGenderAndAgeInAppointmentDetails)
				&& isDisplayed(lblChiefComplaintInAppointmentDetails) && isDisplayed(lblVisitIDInAppointmentDetails)
				&& isDisplayed(lblDoctorSpecialityInAppointmentDetails)
				&& isDisplayed(lblVisitDetailsInAppointmentDetails)) {

			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether appointment details screen is displayed with all the details");
			System.out.println("Appointment details screen is displayed with all the details");
			return true;
		}
		return false;
	}

	public String getOpenMRSIDFromAppointmentDetailsScreen() {
		click(upcomingAppointments, "Clicking on upcoming appointment");
		// waitForVisibility(appointmentDetailsOpenMRSID);

		return getText(lblOpenMRSIDInAppointmentDetails, "Getting the OpenMRS ID from appointment details screen");
	}

	public boolean verifyFiltersInPastAppointments() {
		refreshAppointments();
		click(tabPastAppointments, "Clicking on past appointments tab");
		click(icnFilterInPastAppointments, "Clicking on filter icon in past appointments");
		if (isDisplayed(lblCompletedInFilters) && isDisplayed(lblCancelledInFilters)
				&& isDisplayed(lblMissedInFilters)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether all filter options are displayed");
			System.out.println("All filter options are displayed");
			return true;
		}
		return false;
	}

	public void applyFilterInPastAppointments(String filterName) {

		click(tabPastAppointments, "Clicking on past appointments tab");
		click(icnFilterInPastAppointments, "Clicking on filter icon in past appointments");
		switch (filterName.toLowerCase()) {
		case "completed":
			click(lblCompletedInFilters, "Applying 'Completed' filter");
			break;
		case "cancelled":
			click(lblCancelledInFilters, "Applying 'Cancelled' filter");
			break;
		case "missed":
			click(lblMissedInFilters, "Applying 'Missed' filter");
			break;
		case "upcoming":
			click(lblUpcomingInFilters, "Applying 'Upcoming' filter");
			break;
		default:
			throw new IllegalArgumentException("Invalid filter name: " + filterName);
		}
	}

	public boolean verifyFilteredAppointments(String expectedStatus) {
		refreshAppointments();
		applyFilterInPastAppointments(expectedStatus);

		// Case 1: No appointments found
		if (appointmentCards == null || appointmentCards.isEmpty()) {

			ExtentReport.getTest().log(Status.INFO, "No appointments found for filter: " + expectedStatus);

			System.out.println("No appointments displayed for filter: " + expectedStatus);

			return true;
		}

		// Case 2: Appointments exist → verify all contain correct status
		for (WebElement statusLabel : statusLabels) {

			String actualStatus = statusLabel.getText().trim();

			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {

				ExtentReport.getTest().log(Status.FAIL, "Expected: " + expectedStatus + " but found: " + actualStatus);
				return false;
			}
			if (verifyLabelAfterApplyingfilter(expectedStatus) == false) {
				ExtentReport.getTest().log(Status.FAIL,
						"Expected label for filter: " + expectedStatus + " is not displayed");
				return false;
			}
		}

		ExtentReport.getTest().log(Status.PASS, "All appointments correctly filtered by: " + expectedStatus);
		return true;
	}

	public boolean verifyLabelAfterApplyingfilter(String filterName) {
	//	click(tabPastAppointments, "Clicking on past appointments tab");
		//click(icnFilterInPastAppointments, "Clicking on filter icon in past appointments");
		switch (filterName) {
		case "Completed":
			return isDisplayed(lblCompletedInPastAppointmentsAfterFilter);
			

		case "Cancelled":
			return isDisplayed(lblCancelledInPastAppointmentsAfterFilter);
			

		case "Missed":
			return isDisplayed(lblMissedInPastAppointmentsAfterFilter);
		default:
			throw new IllegalArgumentException("Invalid filter name: " + filterName);
		}
	}
}
