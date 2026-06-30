package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.api.APIServices;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.pages.NotificationPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class NotificationTest extends BaseTest {
	AppSetupPage appSetupPage;
	LoginMenuPage loginMenuPage;
	NotificationPage notificationPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws Throwable {
		TestUtils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	//    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		loginMenuPage = new LoginMenuPage();
		notificationPage = new NotificationPage();
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
		/**
		 * GOing forward we will be using these variables for authorization for CHW and
		 * Doctor
		 */
		hw_request = buildRequestWithNurseAuthorization();
		doctor_request = buildRequestWithDoctorAuthorization();
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		// Click on the hamburger menu in the login menu page
		appSetupPage.refreshUIAndWait();
	}

	@Test(priority = 1, description = "Verify that user is able to view the prescription", enabled = true)
	public void IDA4_2142_verifyUserAbleToViewPrescription() throws InterruptedException {
		Assert.assertTrue(notificationPage
				.verifyUserAbleToViewPrescription(expectedAssertProp.getProperty("notification.title.text")));
	}

	/**
	 * Needed more steps for it and also need some clarity on how to verify the
	 * visit is synced to Webapp
	 * 
	 * @throws Throwable
	 */
	@Test(priority = 2, description = "Verify once internet is available, the data should be synced to web portal automatically", enabled = true)
	public void IDA4_2150_verifyDataIsSyncedToWebApp() throws Throwable {
		notificationPage.verifyDataIsSyncedToWeb();
	}

	@Test(priority = 3, description = "Verify that user can view the recent received prescription notification on top", enabled = true)
	public void IDA4_2140_verifyThatUserCanViewTheRecentReceivedPrescriptionNotificationOnTop() throws Exception {

		/**
		 * GOing forward we will be using these methods for visit creation as CHW,
		 */
		APIServices.createVisitUsingRestAssured(hw_request);
		APIServices.startVisitUsingRestAssured(doctor_request);
		APIServices.signAndSubmitUsingRestAssured(doctor_request);

		Assert.assertTrue(notificationPage.verifyThatUserCanViewTheRecentReceivedPrescriptionNotificationOnTop());

	}

}
