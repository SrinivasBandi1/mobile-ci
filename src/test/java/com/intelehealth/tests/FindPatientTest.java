package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class FindPatientTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	FindPatientPage findPatientPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	//    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		findPatientPage = new FindPatientPage();
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
		//grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
        appSetupPage.completeSetup();;
        Thread.sleep(6000);
     // Click on the "Find Patient" button to initiate the patient search
     		findPatientPage.clickOnFindPatient();
     		

	}

	@Test(priority = 1, description = " Verify the Search functionality by entering valid patient name in the search box", enabled = true)
	public void IDA4_2221_verifyValidPatientSearch() throws InterruptedException {
		
		Thread.sleep(1000);
		// Enter the valid patient name into the search box
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("validPatient").getString("patientName"));
		// Enter valid patient name and verify the search result
		Assert.assertTrue(findPatientPage.isDisplayedSearchedPatient());
		
		Assert.assertEquals(findPatientPage.verifySearchedPatient(), expectedAssertProp.getProperty("searched.patient.name"));
	}

	@Test(priority = 2, description = "Verify the Search functionality by entering invalid patient name in the search box", enabled = true)
	public void IDA4_2222_verifyInvalidPatientSearch() throws JSONException, InterruptedException {
		

		// Enter invalid patient name and verify the search result
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("inValidPatient").getString("patientName"));
		// Verify that the system correctly handles an invalid patient search
		Assert.assertTrue(findPatientPage.verifyInvalidPatientSearch());
		
		Assert.assertEquals(findPatientPage.getInvalidPatientSearch(),

				Arrays.asList(expectedAssertProp.getProperty("findpatient.nopatientfound.text"),
						expectedAssertProp.getProperty("findpatient.noPatientFoundSubTitle.text"),
						expectedAssertProp.getProperty("findpatient.addnewpatient.text")));
		
		

	}

	@Test(priority = 3, description = " Verify the Search functionality by entering invalid patient id in the search box", enabled = true)
	public void IDA4_2226_verifyInvalidPatientIdSearch() throws JSONException, InterruptedException {
		
		// Enter invalid patient id and verify the search resultfindPatientPage.getInvalidPatientSearch
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("invalidPatientID").getString("Id"));
		// Verify that the system correctly handles an invalid patient search
		Assert.assertTrue(findPatientPage.verifyInvalidPatientSearch());
		
		Assert.assertEquals(findPatientPage.getInvalidPatientSearch(),

				Arrays.asList(expectedAssertProp.getProperty("findpatient.nopatientfound.text"),
						expectedAssertProp.getProperty("findpatient.noPatientFoundSubTitle.text"),
						expectedAssertProp.getProperty("findpatient.addnewpatient.text")));

	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}
}
