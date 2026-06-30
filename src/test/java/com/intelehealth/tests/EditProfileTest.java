package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.EditProfilePage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

public class EditProfileTest extends BaseTest {
	AppSetupPage appSetupPage;
	LoginMenuPage loginMenuPage;
	EditProfilePage editProfilePage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		TestUtils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	  //  clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		loginMenuPage = new LoginMenuPage();
		editProfilePage = new EditProfilePage();
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
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
	//	appSetupPage.refreshUIAndWait();
		// Click on the hamburger menu in the login menu page

		loginMenuPage.clickOnHamburgerMenu();

	}

	@Test(priority = 1, description = "Verify that Edit profile navigates to My Profile page", enabled = true)
	public void IDA4_1987_verifyMyProfilePage() throws InterruptedException {

		Assert.assertTrue(editProfilePage.verifyMyProfilePage());

	}

	@Test(priority = 2, description = "Verify that user can change the profile photo by taking photo", enabled = true)
	public void IDA4_1993_changeProfileByTakingPhotoAndVerify() throws InterruptedException {

		Assert.assertTrue(editProfilePage.changeProfilePhotoByCapturingPhoto());

	}
//=======================================================================================================================================================
//	NEED TO DO SOME R&D TO AUTOMATE THE UPLOAD PHOTO FUNCTIONALITY FROM ALBUM AS IT IS NOT WORKING PROPERLY IN APPIUM, SO COMMENTED FOR NOW
//=======================================================================================================================================================
	@Test(priority = 3, description = "Verify that user can change the profile photo by uploading photo", enabled = true)
	public void IDA4_1994_uploadAPhotoAndVerify() throws InterruptedException {
		Assert.assertTrue(editProfilePage.uploadProfilePhotoAndVerify());

	}

	@Test(priority = 4, description = "Verify that user can save the updated details", enabled = true)
	public void IDA4_1992_verifyUserCanSaveUpdatedDetails() throws InterruptedException {
		Assert.assertTrue(editProfilePage.verifyUserCanSaveTheUpdatedDetails(
				appData.getJSONObject("validUser").getString("updatedMobileNumber"),
				appData.getJSONObject("validUser").getString("updatedEmail")));
		;
	}

	@Test(priority = 5, description = "Verify if user can change password", enabled = true)
	public void IDA4_1997_VerifyChangePassword() throws InterruptedException {
		editProfilePage.verifyUserAbleToLoginWithNewPassword(appData.getJSONObject("validUser").getString("password"),
				appData.getJSONObject("validUser").getString("newPassword"));

		Assert.assertTrue(loginMenuPage.verifyLocationIsDisplayed());

		editProfilePage.resetThePasswordToOldPassword(appData.getJSONObject("validUser").getString("password"),
				appData.getJSONObject("validUser").getString("newPassword"));

		Assert.assertTrue(loginMenuPage.verifyLocationIsDisplayed());
	}

	@Test(priority = 6, description = "Verify user can able to see the please contact adminstrator when clicks on non editable fields ", enabled = true)
	public void IDA4_1998_verifyNonEditableFields() throws InterruptedException {
		Assert.assertEquals(editProfilePage.verifyNonEditableFields(),
				expectedAssertProp.getProperty("non.editable.fields.message"));
	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}
}