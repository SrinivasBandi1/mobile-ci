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
import com.intelehealth.pages.ForgotPasswordPage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginMenuTest extends BaseTest {
	// TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	LoginMenuPage loginMenuPage;
	EditProfilePage editProfilePage;
	ForgotPasswordPage forgotPasswordPage;

	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
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
		editProfilePage = new EditProfilePage();
		forgotPasswordPage = new ForgotPasswordPage();

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
		appSetupPage.refreshUIAndWait();
		// Click on the hamburger menu in the login menu page
		loginMenuPage.clickOnHamburgerMenu();
	}

	@Test(priority = 1, description = "Verify if the 'Welcome Back' title is displayed on the login screen.", enabled = true)
	public void IDA4_1949_verifyWelcomeBackTitle() throws InterruptedException {
		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();
		// Pause execution for 2000 milliseconds (2 seconds)
		Thread.sleep(2000);
		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();
		// Verify if the "Welcome Back" title is displayed on the login screen after
		// logging
		Assert.assertTrue(loginMenuPage.verifyLoginScreenElements());

	}

	@Test(priority = 2, description = "Verify that user is able to navigate to setup page when the terms & conditions box is checked", enabled = true)
	public void IDA4_1943_verifyForgotUsernameDialogBox() throws InterruptedException {
		loginMenuPage.clickOnLogOut();
		// Pause execution for 2000 milliseconds (2 seconds)
		Thread.sleep(2000);

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();
		appSetupPage.clicKOnForgotUserNameLink();

		Assert.assertEquals(appSetupPage.getForgotUserNameDialogTitle(),
				expectedAssertProp.getProperty("forgot.username.dialog.box.title"));

		Assert.assertEquals(appSetupPage.getForgotUserNameDialogMessage(),
				expectedAssertProp.getProperty("forgot.username.dialog.box.message"));

		appSetupPage.clickOkInForgotUserNameDialog();
	}

	@Test(priority = 3, description = "Verify Forgot Password UI elements are displayed (positive)", enabled = true)
	@Description("Verifies that all UI elements on the Forgot Password screen are displayed correctly.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password UI Verification")
	public void verifyForgotPasswordUIElements() throws InterruptedException {
		loginMenuPage.clickOnLogOut();
		// Pause execution for 2000 milliseconds (2 seconds)
		Thread.sleep(2000);

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();
		appSetupPage.clicKOnForgotPasswordLink();

		Assert.assertTrue(forgotPasswordPage.isForgotPasswordLabelDisplayed());
		Assert.assertEquals(forgotPasswordPage.getForgotPasswordLabelText(),
				expectedAssertProp.getProperty("forgot.password.screen.label"));
		Assert.assertTrue(forgotPasswordPage.isForgotPasswordDescriptionDisplayed());
		Assert.assertEquals(forgotPasswordPage.getForgotPasswordDescriptionText(),
				expectedAssertProp.getProperty("forgot.password.screen.description"));
		Assert.assertTrue(forgotPasswordPage.isForgotPasswordImageDisplayed());
		Assert.assertTrue(forgotPasswordPage.isUsernameButtonDisplayed());
		Assert.assertTrue(forgotPasswordPage.isMobileNumberButtonDisplayed());
		Assert.assertTrue(forgotPasswordPage.isContinueButtonDisplayed());
	}

	@Test(priority = 4, description = " Verify if a user will be able to login with a valid username and valid password.", enabled = true)
	public void IDA4_1950_verifyLoginWithValidUsernameAndPassword() throws InterruptedException {

		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();
		// Pause execution for 2000 milliseconds (2 seconds)
		Thread.sleep(2000);

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();

		// Log back in using valid user credentials from appData
		loginMenuPage.login();
		Thread.sleep(5000);
		Assert.assertTrue(loginMenuPage.verifyLocationIsDisplayed());

	}

	@Test(priority = 5, description = "Verify if clicking on the Hamburger menu open the menu page", enabled = true)
	public void IDA4_1957_verifyHamburgerMenu() throws InterruptedException {

		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();

		// Log back in using valid user credentials from appData
		loginMenuPage.login();
		loginMenuPage.clickOnHamburgerMenu();
		// Verify the elements on the menu page to ensure successful login
		Assert.assertTrue(loginMenuPage.verifyMenuPageElements());

	}

	@Test(priority = 6, description = "Verify that user profile is displayed on top of the menu page", enabled = true)
	public void IDA4_1959_verifyUserProfile() throws InterruptedException {

		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();

		// Log back in using valid user credentials from appData
		loginMenuPage.login();

		loginMenuPage.clickOnHamburgerMenu();

		// Verify the user profile details on the menu page after logging back in
		Assert.assertTrue(loginMenuPage.verifyUserProfileDetails());

	}

//	logout and reset app 
	@Test(priority = 7, description = "Verify the User can logout from app", enabled = true)
	public void IDA4_2050_verifyLogoutFunctionality() throws InterruptedException {
		Assert.assertTrue(loginMenuPage.verifyLogout());
	}

	@Test(priority = 8, description = "Verify if user can Reset the App", enabled = true)
	public void IDA4_2052_verifyResetApp() throws InterruptedException {
		loginMenuPage.verifyResetAppFunctionality();
	}

//	@Test(priority = 6, description = "Verify if a user is able to login with a new password only after he/she has changed the password", enabled = true)
//	public void IDA4_2052_verifyUserAbleToLoginWithNewPassword() throws InterruptedException {
//		editProfilePage.verifyUserAbleToLoginWithNewPassword();
//		editProfilePage.resetThePasswordToOldPassword();
//	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}
}
