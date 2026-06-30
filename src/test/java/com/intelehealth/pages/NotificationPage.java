package com.intelehealth.pages;

import static org.testng.Assert.fail;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.api.APIServices;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NotificationPage extends BaseTest {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Notification']")
	private WebElement notificationHeader;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_text")
	private WebElement patients;
	@AndroidFindBy(id = "android:id/alertTitle")
	private WebElement prescriptionNotificationTitle;
	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_notifications_home")
	private WebElement notificationIcon;
	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;
	@AndroidFindBy(accessibility = "Notifications Refresh ImageButton")
	private WebElement notificationsRefreshButton;

	private StartVisit3And4StepsPage startVisit3And4StepsPage;
	private VisitSummaryPage visitSummaryPage;

	private AddNewPatientPage addNewPatientPage;
	JSONObject appData;

	public NotificationPage() throws Throwable {
		// Initialize AnotherPageClass object in the constructor
		startVisit3And4StepsPage = new StartVisit3And4StepsPage();
		addNewPatientPage = new AddNewPatientPage();
		visitSummaryPage = new VisitSummaryPage(driver);
		InputStream datais = null;
		try {
			// Load test data from JSON fil
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

	By notificationPatients = By
			.xpath("(//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/search_name\"])[1]");

	public boolean verifyUserAbleToViewPrescription(String notification) throws InterruptedException {
		Thread.sleep(25000);
		click(notificationIcon, "Clicked on Notification Icon");
		waitForVisibility(notificationHeader);
		String input = notificationHeader.getText();
		boolean notificationText = false;
		if (input.equalsIgnoreCase(notification)) {
			notificationText = true;
		}
		click(patients, "Clicked On Patient");
		return notificationText && isDisplayed(prescriptionNotificationTitle, "User able to view Prescription");

	}

	public void verifyDataIsSyncedToWeb() throws Throwable {
		addNewPatientPage.registerAPatient(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("personalDetails").getString("phoneNumber"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		String openMRSID = addNewPatientPage.getOpenMRSID();
		addNewPatientPage.clickOnStartVisitButton();
		addNewPatientPage.clickOnContinueButton();
		addNewPatientPage.clickOnAcceptButtonInTeleConsultation();
		visitSummaryPage.completing4OutOf4Steps();

		APIServices.searchPatient(doctor_request, openMRSID);
	}

	// Verifies that the user can view the most recent received prescription
	// notification
	public boolean verifyThatUserCanViewTheRecentReceivedPrescriptionNotificationOnTop() throws Exception {
		click(homeScreenRefreshButton, "Clicking on app sync icon");
		ExtentReport.getTest().log(Status.INFO, "Sharing the prescription");
		click(notificationIcon, "Clicked on Notification Icon");
		getDriver().navigate().back();
		click(homeScreenRefreshButton, "Clicking on app sync icon");
		Thread.sleep(10000);
		click(notificationIcon, "Clicked on Notification Icon");
		if (isDisplayed2(notificationPatients)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is able to view the recent prescription received notification");
			System.out.println("Able to view the recent prescription received notification");
			return true;
		} else {
			throw new Exception("Not able to view the recent prescription received notification");
		}
		// int numberOfNotifications = patients.size();
		/*
		 * //String notifications = String.valueOf(numberOfNotifications);
		 * waitForVisibility(notificationHeader); String prescReceived =
		 * notificationHeader.getText(); String count = extractBefore(prescReceived,
		 * "prescriptions received"); //if (notifications.equals(count)) {
		 * ExtentReport.getTest().log(Status.INFO,
		 * "Verifying whether the count of prescriptions received is correct");
		 * System.out.println("The count of prescriptions received is correct"); } else
		 * { throw new Exception("Not displaying the correct count of prescription"); }
		 */
	}
}