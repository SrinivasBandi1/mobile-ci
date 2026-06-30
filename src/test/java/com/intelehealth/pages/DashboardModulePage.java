package com.intelehealth.pages;

import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.tests.AddNewPatientTest;
import com.intelehealth.tests.VisitSummaryTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DashboardModulePage extends BaseTest {

	// JSON object to store test data
	JSONObject appData;

	AddNewPatientTest addNewPatientTest;

	VisitSummaryTest visitSummaryTest;
	AddNewPatientPage addNewPatientPage;
	AppSetupPage appSetupPage;

	public DashboardModulePage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		addNewPatientTest = new AddNewPatientTest();

		addNewPatientPage = new AddNewPatientPage();
		appSetupPage = new AppSetupPage();
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

	@AndroidFindBy(id = "org.intelehealth.app:id/et_username")
	private WebElement username;

	@AndroidFindBy(id = "org.intelehealth.app:id/et_password")
	private WebElement password;

	@AndroidFindBy(id = "org.intelehealth.app:id/autotv_select_location")
	private WebElement dropdown;
	@AndroidFindBy(id = "org.intelehealth.app:id/text1")
	private List<WebElement> lstTelemedicineclinic;
	@AndroidFindBy(id = "//android.widget.TextView[@text='Telemedicine Clinic 1']")
	private WebElement location;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_setup")
	private WebElement setupScreeenSetupButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement locationName;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement refreshButton;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc='Setup Screen Parent RelativeLayout']/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]")
	private WebElement selectlocation;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement addedLocation;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_app_sync_time")
	private WebElement appSyncTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_close_visit_no")
	private WebElement numberOfOpenVisits;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_received_no")
	private WebElement numberOfPrescriptions;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView5")
	private WebElement numberOfAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView6")
	private WebElement numberOfFollowUps;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_notifications_home")
	private WebElement notificationIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Notification']")
	private WebElement notificationPageNotificationTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/textlayout_find_patient")
	private WebElement findPatientTextfield;

	@AndroidFindBy(id = "org.intelehealth.app:id/search_txt_enter")
	private WebElement searchPatientTextfield;

	@AndroidFindBy(id = "org.intelehealth.app:id/all_patients_tv")
	private WebElement allPatientsPageText;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_add_patient")
	private WebElement addPatientIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Patients']")
	private WebElement addPatientsText;

	@AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[@resource-id='org.intelehealth.app:id/addpatient_cardview']/android.view.ViewGroup/android.widget.ImageView[2]")
	private WebElement addPatientArrowIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_prescriptions")
	private WebElement homePagePrescriptionText;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_received_no")
	private WebElement prescriptionReceivedCount;

	@AndroidFindBy(id =  "org.intelehealth.app:id/textview_prescriptions")
	private WebElement homeScreenPrescriptionArrowIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/label")
	private WebElement prescriptionPageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Received')]")
	private WebElement receivedTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Pending')]")
	private WebElement pendingTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'unclosed visits')]")
	private WebElement countOfUnclosedVisits;

	@AndroidFindBy(id = "org.intelehealth.app:id/label")
	private WebElement openVisitScreenTitle;

	@AndroidFindBy(accessibility = "upcoming count textview in Todays Appointments")
	private WebElement upcomingTabCount;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageView")
	private WebElement openAppointments;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My Appointments']")
	private WebElement myAppointmentsPageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Upcoming')]")
	private WebElement homeScreenNoOfUpcomingAppointments;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Pending')]")
	private WebElement homeScreenNoOfPendingFollowUps;

	@AndroidFindBy(id = "org.intelehealth.app:id/toolbar_title")
	private WebElement followUpPageTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/bottom_nav_achievements")
	private WebElement homeScreenAchievements;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement myAchievementsPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/navigation_bar_item_small_label_view' and @text='Help']")
	private WebElement homeScreenHelp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Help center']")
	private WebElement helpCenterPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/navigation_bar_item_small_label_view' and @text='Add Patients']")
	private WebElement homeScreenAddPatients;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Privacy Policy']")
	private WebElement privacyPolicyPageTitle;

	@AndroidFindBy(accessibility = "Permission Required Dialog Okay Button")
	private WebElement permissionRequiredPopupOkayButton;

	@AndroidFindBy(accessibility = "Intelehealth")
	private WebElement intelehealthUsageAccess;

	@AndroidFindBy(id = "android:id/switch_widget")
	private WebElement permitUsageAccessToggleButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_level")
	private WebElement myAchievementsLevelText;

	By todaysUpcomingAppointmentPatientName = By.xpath(
			"//android.widget.TextView[@content-desc=\"upcoming title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]");

	By byPermissionRequiredPopupOkayButton = By
			.xpath("//android.widget.Button[@content-desc=\"Permission Required Dialog Okay Button\"]");

	// Perform login
	public void login(String un, String pw) throws InterruptedException {
		// Click on the dropdown to open the menu
		click(dropdown);
		getDriver().setSetting("enableTopmostWindowFromActivePackage", true);
		// selectFromDropdown("org.intelehealth.app:id/autotv_select_location",
		// "Telemedicine Clinic 1");
		click(lstTelemedicineclinic.get(0));
		getDriver().setSetting("enableTopmostWindowFromActivePackage", false);
		// Click on the location element to select a location
		//click(selectlocation);
		// Enter the username into the username field
		sendKeys(username, un);
		// Enter the password into the password field
		sendKeys(password, pw);
		// Click on the setup screen setup button to complete the login process
		click(setupScreeenSetupButton);
		Thread.sleep(5000);
		isDisplayed(locationName);
		
	}

	public void performLogin() throws InterruptedException {
		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");

		System.out.println(originalPassword);
		System.out.println(originalUserName);
		// Encrypt the password
		String encryptedUserName = encrypt(originalUserName);
		System.out.println("Encrypted UserName: " + encryptedUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		System.out.println("Decrypted UserName: " + decryptedUserName);
		String encryptedPassword = encrypt(originalPassword);
		System.out.println("Encrypted Password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		System.out.println("Decrypted Password: " + decryptedPassword);
		login(decryptedUserName, decryptedPassword);
		appSetupPage.locationIsDisplayed();
	}

	public void setUp() throws InterruptedException {
		appSetupPage.handlePermissions();
		// Click on the "Next" button on the app setup page
		appSetupPage.clickOnNextButton();

		// Click on the "Skip" button on the app setup page
		appSetupPage.clickOnSkipButton();

		// Click on the checkbox on the app setup page
		appSetupPage.clickOnCheckBox();

		// Click on the "Setup" button on the app setup page
		appSetupPage.clickOnSetupButton();

	}

	// Verifies that the added location is displayed on the page
	public boolean verifyThatAddedLocationIsDisplayOnTopOfThePage() throws Throwable {
		click(dropdown, "Clicking on dropdown");
		getDriver().setSetting("enableTopmostWindowFromActivePackage", true);
		// selectFromDropdown("org.intelehealth.app:id/autotv_select_location",
		// "Telemedicine Clinic 1");
		click(lstTelemedicineclinic.get(0));
		getDriver().setSetting("enableTopmostWindowFromActivePackage", false);
	//	click(selectlocation, "Selecting location from the dropdown");
		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");
		String encryptedUserName = encrypt(originalUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		String encryptedPassword = encrypt(originalPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		sendKeys(username, decryptedUserName);
		sendKeys(password, decryptedPassword);
	//	waitForVisibility(location);
		String SelectedLocation = dropdown.getText();
		click(setupScreeenSetupButton, "Clicking on setup button");
		waitForVisibility(addedLocation);
		String LocationAdded = addedLocation.getText();
		if (LocationAdded.equals(SelectedLocation)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added Location is displayed on top of the page");
			System.out.println("Added Location is displayed on top of the page");
			return true;
		} else {
			throw new Exception("Added Location is not displayed on top of the page");
		}
	}

	// Verifies that the last synced time and date display on the page
	public boolean verifyThatLastSyncedTimeAndDateDisplayOnTopOfThePage() throws InterruptedException {
		performLogin();
		ExtentReport.getTest().log(Status.INFO,
				"Verifying whether app synced time and date is displayed on top of the page");
		return isDisplayed(appSyncTime);
	}

	// Verifies the functionality of the sync icon
	public boolean verifyThatSyncIconFunctionality() throws Throwable {
		performLogin();
		waitForVisibility(appSyncTime);
		String beforeSyncTime = appSyncTime.getText();
		waitForVisibility(numberOfOpenVisits);
		waitForVisibility(numberOfPrescriptions);
		waitForVisibility(numberOfAppointments);
		waitForVisibility(numberOfFollowUps);
		int maxAttempts = 2;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton, "Clicking on the sync icon");
				attempt++;

			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		waitForVisibility(appSyncTime);
		String afterSyncTime = appSyncTime.getText();
		waitForVisibility(numberOfOpenVisits);
		String afterSyncUnclosedVisits = numberOfOpenVisits.getText();
		waitForVisibility(numberOfPrescriptions);
		String afterSyncPrescriptions = numberOfPrescriptions.getText();
		waitForVisibility(numberOfAppointments);
		String afterSyncNumberOfAppointments = numberOfAppointments.getText();
		waitForVisibility(numberOfFollowUps);
		String afterSyncNumberOfFollowUps = numberOfFollowUps.getText();
		if (!beforeSyncTime.equals(afterSyncTime)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether app is synced");
			System.out.println("App is synced");
			return true;
		}
		/*
		 * if (!beforeSyncUnclosedVisits.equals(afterSyncUnclosedVisits) ||
		 * !beforeSyncPrescriptions.equals(afterSyncPrescriptions) ||
		 * !beforeSyncNumberOfAppointments.equals(afterSyncNumberOfAppointments) ||
		 * !beforeSyncNumberOfFollowUps.equals(afterSyncNumberOfFollowUps)) {
		 * ExtentReport.getTest().log(Status.INFO,
		 * "Verifying whether app data is synced");
		 * System.out.println("App data is synced"); return true; } else { throw new
		 * Exception("App data is not synced"); }
		 */
;return false;
	}

	// Verifies that clicking the notification icon navigates to the notification
	// page
	public String verifyThatNotificationIconNavigateToNotificationPage() throws Throwable {
		performLogin();
		click(notificationIcon, "Clicking on notification icon");
		if (isDisplayed(notificationPageNotificationTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to notification page");
			System.out.println("User is navigated to notification page");
		}
		return getText(notificationPageNotificationTitle, "NotificationTitle is displayed in notificationPage ");
	}

	// Verifies the display and click functionality of the Find Patient search box
	public boolean verifyThatFindPatientSearchBoxDisplayedAndClickFunctionality() throws Throwable {
		performLogin();
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		click(findPatientTextfield, "Clicking on search patient textfield");
		if (isDisplayed(searchPatientTextfield) && getText(allPatientsPageText,"").equals("All Patients")) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the user is navigated to 'All patients' page along with Search textfield");
			System.out.println("The user is navigated to 'All patients' page along with Search texfield");
			return true;
		}
		return false;
	}

	// Verifies the display of the Add Patient section along with the arrow icon
	public boolean verifyThatAddPatientSectionDisplayedWithArrowIcon() throws Throwable {
		performLogin();
		if (isDisplayed(addPatientIcon) && getText(addPatientsText,"Add patient text is displayed").equals("Add Patients") && isDisplayed(addPatientArrowIcon)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether add patient section is displayed on the page with arrow icon");
			System.out.println("Add patient section is displayed on the page with arrow icon");
			return true;
		}
		return false;
	}

	// Verifies that the Prescription section displays the count of received
	// prescriptions out of total prescriptions
	public boolean verifyThatPrescriptionSectionShouldDisplayTheReceivedPrescriptionsOutOfTotalPrecriptions()
			throws Throwable {
		performLogin();
		isDisplayed(homePagePrescriptionText);
		if (isDisplayed(numberOfPrescriptions) && isDisplayed(prescriptionReceivedCount)
				) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether received prescription out of total prescription is displayed");
			System.out.println("Received prescription out of total prescription is displayed");
			return true;
		}
		return false;
	}

	// Verifies the functionality when clicking on the Prescriptions arrow navigates
	// to prescriptions page
	public boolean verifyClickingOnPrescriptionsArrow() throws Throwable {
		performLogin();
		click(homeScreenPrescriptionArrowIcon, "Clicking on prescriptions arrow icon");
		if (getText(prescriptionPageText,"prescription page text is shown").equals("Prescriptions") && isDisplayed(receivedTab) && isDisplayed(pendingTab)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to prescriptions page");
			System.out.println("User is navigated to prescriptions page");
			return true;
		}
		return false;
	}

	// Verifies that the Close Visits section displays the number of unclosed visits
	public boolean verifyThatCloseVisitsSectionDisplayTheNumberOfUnclosedVisit() throws Throwable {
		performLogin();
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		if (isDisplayed(numberOfOpenVisits)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the count of unclosed visits is displayed");
			System.out.println("The count of unclosed visits is displayed");
			return true;
		}
		return false;
	}

	// Verifies that the user can navigate to the Close Visits page
	public boolean verifyThatUserCanNavigateToCloseVisitsPage() throws Throwable {
		performLogin();
		click(numberOfOpenVisits, "Clicking on close visits");
		if (getText(openVisitScreenTitle,"").equals("Open Visits")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to close visits page");
			System.out.println("User is navigated to close visits page");
			return true;
		}
		return false;
	}

	// Verifies that the Appointments section displays the upcoming appointments
	public boolean verifyAppointmentsSectionShouldDisplayTheUpcomingAppointments() throws Throwable {
		performLogin();
		if (isDisplayed(numberOfAppointments) && isDisplayed(homeScreenNoOfUpcomingAppointments)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether number of upcoming appointments are displayed");
			System.out.println("Number of upcoming appointments are displayed");
			return true;
		}
		return false;
	}

	// Verifies that the user can navigate to the My Appointments page
	public boolean verifyThatUserCanNavigateToMyAppointmentsPage() throws Throwable {
		performLogin();
		click(openAppointments, "Clicking on appointments");
		if (isDisplayed(myAppointmentsPageText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to 'My appointments' page");
			System.out.println("User is navigated to 'My appointments' page");
			return true;
		}
		return false;
	}

	// Verifies that the Follow-Up Visits section displays the number of follow-up
	// visits
	public boolean verifyThatFollowUpVisitsSectionDisplayTheNumberOfFollowUpVisits() throws Throwable {
		performLogin();
		if (isDisplayed(numberOfFollowUps) ) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether number of pending follow up visits are displayed");
			System.out.println("Number of pending follow up visits are displayed");
			return true;
		}
		return false;
	}

	// Verifies that selecting Follow-Up Visits navigates to the Follow-Up Visits
	// page
	public boolean verifyThatFollowUpVisitsShouldNavigateToFollowUpVisitsPage() throws Throwable {
		performLogin();
		click(numberOfFollowUps, "Clicking on number of follow ups");
		if (isDisplayed(followUpPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to 'Follow-up visits' page");
			System.out.println("User is navigated to 'Follow-up visits' page");
			return true;
		}
		return false;
	}

	// Verifies that selecting Achievements on the bottom navigation navigates to My
	// Achievements page
	public boolean verifyThatAchievementsOnBottomNavigateToMyAchievementsPage() throws Throwable {
		performLogin();
		click(homeScreenAchievements, "Clicking on achievements");
		//boolean permissionPopup = isDisplayed2(byPermissionRequiredPopupOkayButton);
		//	click(permissionRequiredPopupOkayButton, "Clicking on okay button in the permission required popup");
			//scrollToElementByText("Intelehealth");
		//	click(intelehealthUsageAccess, "Clicking on intelehealth in usage access screen");
		//	click(permitUsageAccessToggleButton, "Clicking on permit usage access toggle button");d
			if (isDisplayed(myAchievementsLevelText) && myAchievementsPageTitle.isDisplayed()) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether user is navigated to 'My Achievements' page");
				System.out.println("User is navigated to 'My Achievements' page");
				return true;
			}
			return false;
		
	}

	private void scrollToElementByText(String string) {
		// TODO Auto-generated method stub

	}

	// Verifies that selecting Help on the bottom navigation navigates to the Help
	// Center page
	public boolean verifyThatHelpOnBottomNavigateToHelpCenterPage() throws Throwable {
		performLogin();
		click(homeScreenHelp, "Clicking on help");
		if (isDisplayed(helpCenterPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether User is navigated to 'Help center' page");
			System.out.println("User is navigated to 'Help center' page");
			return true;
		}
		return false;
	}

	// Verifies that selecting Add Patient on the bottom navigation navigates to the
	// Privacy Policy page
	public boolean verifyThatAddPatientOnBottomNavigateToPrivacyPolicyPage() throws Throwable {
		performLogin();
		click(homeScreenAddPatients, "Clicking on add patients icon");
		if (isDisplayed(privacyPolicyPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to 'Privacy Policy' page");
			System.out.println("User is navigated to 'Privacy Policy' page");
			return true;
		}
		return false;
	}
}
