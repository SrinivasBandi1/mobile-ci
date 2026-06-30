package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.AppointmentsPage;
import com.intelehealth.pages.DashboardModulePage;
import com.intelehealth.pages.EndToEndPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.pages.PrescriptionsPage;
import com.intelehealth.pages.VisitSummaryPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class EndToEndTest extends BaseTest {

	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	// JSON object to store test data
	JSONObject appData;
	EndToEndPage endToEndPage;

	Faker faker = new Faker(new Locale("en-IND"));
	String lastName = faker.name().lastName();

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, Throwable {
		// Log information about the test
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// Launch the app and initialize necessary screens
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
//	    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		setImplicitWait();
		appSetupPage = new AppSetupPage();
		endToEndPage = new EndToEndPage(driver);
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
		appSetupPage.handlePermissions();	
		appSetupPage.completeSetup();
				

	}
	
	@Test(priority = 1, description = "Login, Add patient, Patient register", enabled = true)
	public void IDA4_2153_loginAddPatientPatientRegister() throws Throwable {
		endToEndPage.loginAddPatientPatientRegister();
	}

	@Test(priority = 2, description = "Login, Add patient, Patient register, Update patient details", enabled = true)
	public void IDA4_2154_logiAddPatientPatientRegisterUpdatePatientDetails() throws Throwable {
		endToEndPage.logiAddPatientPatientRegisterUpdatePatientDetails();
	}

	@Test(priority = 3, description = "Login, Add patient, Patient register, Start visit, Send visit", enabled = true)
	public void IDA4_2155_loginAddPatientPatientRegisterStartVisitSendVisit() throws Throwable {
		endToEndPage.loginAddPatientPatientRegisterStartVisitSendVisit();
	}

	@Test(priority = 4, description = "Login, Find patient, Book appointment [For already added patient] profile", enabled = true)
	public void IDA4_2156_loginFindPatientBookAppointmentForAlreadyAddedPatientProfile() throws Throwable {
		endToEndPage.loginFindPatientBookAppointmentForAlreadyAddedPatientProfile();
	}

	@Test(priority = 5, description = "Login, Find patient, Start visit, Send visit [No visit created earlier]", enabled = true)
	public void IDA4_2157_LoginFindPatientStartVisitSendVisitNoVisitCreatedEarlier() throws Throwable {
		endToEndPage.loginFindPatientStartVisitSendVisitNoVisitCreatedEarlier();
	}

	@Test(priority = 6, description = "Login, Prescriptions, Received/pending, Call to patient", enabled = true)
	public void IDA4_2158_LoginPrescriptionsReceivedCallToPatient() throws Throwable {
		endToEndPage.loginPrescriptionsReceivedCallToPatient();
	}

	@Test(priority = 7, description = "Login, Prescriptions, Received/Pending, Whatsapp to patient", enabled = true)
	public void IDA4_2159_LoginPrescriptionsReceivedWhatsappToPatient() throws Throwable {
		endToEndPage.loginPrescriptionsReceivedWhatsappToPatient();
	}

	@Test(priority = 8, description = "Login, Prescriptions, Received, Visit summary, Print", enabled = true)
	public void IDA4_2160_LoginPrescriptionsReceivedVisitSummaryPrint() throws Throwable {
		endToEndPage.loginPrescriptionsReceivedVisitSummaryPrint();
	}

	@Test(priority = 9, description = "Login, Prescriptions, Received, Visit summary, Share", enabled = true)
	public void IDA4_2161_LoginPrescriptionsReceivedVisitSummaryShare() throws Throwable {
		endToEndPage.loginPrescriptionsReceivedVisitSummaryShare();
	}

	@Test(priority = 10, description = "Login, Prescriptions, Received, Visit summary, Home", enabled = true)
	public void IDA4_2162_LoginPrescriptionsReceivedVisitSummaryHome() throws Throwable {
		endToEndPage.loginPrescriptionsReceivedVisitSummaryHome();
	}
	
	@AfterMethod
	public void afterMethod() {
		closeApp();
		System.gc();
	}

}
