package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BaseTest {
	JSONObject appData;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement lblUserLocation;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_app_sync_time")
	private WebElement lblAppLastSyncTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/textlayout_find_patient")
	private WebElement inpSearchPatient;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_prescriptions")
	private WebElement lblPrescriptions;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_received_no")
	private WebElement lblPrescriptionsCount;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_close_visit")
	private WebElement lblOpenVisit;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_close_visit_no")
	private WebElement lblCloseVisitCount;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView4")
	private WebElement lblAppointments;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView5")
	private WebElement lblAppointmentsCount;

	@AndroidFindBy(id = "org.intelehealth.app:id/txtFollowUpVisits")
	private WebElement lblFollowUpVisits;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView6")
	private WebElement lblFollowUpVisitsCount;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement icnSync;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_notifications_home")
	private WebElement icnNotifications;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_hamburger")
	private WebElement icnHambergerMenu;

	@AndroidFindBy(id = "org.intelehealth.app:id/bottom_nav_achievements")
	private WebElement tabAchievements;

	@AndroidFindBy(id = "org.intelehealth.app:id/bottom_nav_help")
	private WebElement tabHelp;

	@AndroidFindBy(id = "org.intelehealth.app:id/bottom_nav_add_patient")
	private WebElement tabAddPatient;

	@AndroidFindBy(id = "org.intelehealth.app:id/txtFollowUpVisits")
	private WebElement tabFollowUpVisits;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"About us\")")
	private WebElement mnuHambergerAboutUs;

	public HomePage() throws IOException {
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
	}
//write assertion helper methods like getText using from BAsetest can you write for the above methods
// can you convert below all methods to single method by passing element and element name as parameter and use that method in all below methods to get text or attribute or check if element is displayed

	public List<String> getHomePageUI() {
		List<String> homePageDetails = new ArrayList<>();
		homePageDetails.add(getText(inpSearchPatient, "Search Patient Placeholder"));
		homePageDetails.add(getText(lblPrescriptions, "Prescriptions Label"));
		homePageDetails.add(getText(lblOpenVisit, "Close Visit Label"));
		homePageDetails.add(getText(lblAppointments, "Appointments Label"));
		homePageDetails.add(getText(lblFollowUpVisits, "Follow Up Visits Label"));

		return homePageDetails;
	}

	public boolean isDisplayedHomePageIcons() {
		return isDisplayed(icnSync, "Sync Icon") && isDisplayed(lblUserLocation, "Sync Icon")
				&& isDisplayed(lblAppLastSyncTime, "Sync Icon") && isDisplayed(lblPrescriptionsCount, "Sync Icon")
				&& isDisplayed(lblCloseVisitCount, "Sync Icon") && isDisplayed(lblFollowUpVisitsCount, "Sync Icon")
				&& isDisplayed(lblAppointmentsCount, "Notifications Icon")
				&& isDisplayed(icnNotifications, "Notifications Icon")
				&& isDisplayed(icnHambergerMenu, "Hamburger Menu Icon")
				&& isDisplayed(tabAchievements, "Achievements Tab") && isDisplayed(tabHelp, "Help Tab")
				&& isDisplayed(tabAddPatient, "Add Patient Tab");
	}

	public void clickHamburgerMenu() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(icnHambergerMenu, "Hamburger Menu Icon");
	}

	public void clickAchievementsTab() {
		click(tabAchievements, "Achievements Tab");
	}

	public void clickHelpTab() {
		click(tabHelp, "Help Tab");
	}

	public void clickAddPatientTab() {
		click(tabAddPatient, "Add Patient Tab");
	}

	public void clickFollowUpVisitsTab() {
		click(tabFollowUpVisits, "Follow Up Visits Tab");
	}

	public void clickAboutUsInHamburgerMenu() {
		click(mnuHambergerAboutUs, "About Us Menu Item in Hamburger Menu");
	}

}