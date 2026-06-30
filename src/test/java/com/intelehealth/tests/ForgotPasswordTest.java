package com.intelehealth.tests;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.ForgotPasswordPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.SessionHealthManager;
import com.intelehealth.utils.TestUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

@Epic("Forgot Password Module")
@Feature("Forgot Password Functionality")
@Owner("Srinivas Bandi")
public class ForgotPasswordTest extends BaseTest {
	ForgotPasswordPage forgotPasswordPage;
	AppSetupPage appSetupPage;
	JSONObject appData;
	Properties expectedAssertProp;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		ExtentReport.startTest("beforeMethod", "Setup for: " + m.getName());
		TestUtils.log().info("\n****** starting test:" + m.getName() + "******\n");
		// 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	//    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();
		appSetupPage = new AppSetupPage();
		forgotPasswordPage = new ForgotPasswordPage();
		InputStream datais = null;
		expectedAssertProp = new Properties();
		try {
			String dataFileName = "data/appData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			appData = new JSONObject(tokener);
			// Load expected assertions
			FileInputStream expectedAssertionsStream = new FileInputStream(
					"src/test/resources/data/expected-assertions.properties");
			expectedAssertProp.load(expectedAssertionsStream);
			expectedAssertionsStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		appSetupPage.handlePermissions();
		appSetupPage.clickOnNextButton();

		// Navigate to Forgot Password screen
		appSetupPage.clickOnSkipButton();
		appSetupPage.clickOnCheckBox();
		appSetupPage.clickOnSetupButton();
		appSetupPage.clicKOnForgotPasswordLink();
		// Assume there is a button to open Forgot Password, click it
		// Replace with actual method if different
		// appSetupPage.clickOnForgotPasswordLink();
	}

	@Test(priority = 1, description = "Verify Forgot Password UI elements are displayed (positive)", enabled = true)
	@Description("Verifies that all UI elements on the Forgot Password screen are displayed correctly.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password UI Verification")
	public void verifyForgotPasswordUIElements() throws InterruptedException {
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
		forgotPasswordPage.clickMobileNumberButton();
		Assert.assertTrue(forgotPasswordPage.isCountryCodeDisplayed());
	}

	@Test(priority = 2, description = "Verify Forgot Password with valid username (positive)", enabled = true)
	@Description("Verifies Forgot Password functionality with a valid username.")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Forgot Password Positive Flow - Username")
	public void verifyForgotPasswordWithValidUsername() throws InterruptedException {
		System.out.println(appData.getJSONObject("validUser").getString("username"));
		forgotPasswordPage.clickUsernameButton();
		String validUsername = appData.getJSONObject("validUser").getString("username");

		forgotPasswordPage.enterUsername(validUsername);
		forgotPasswordPage.clickContinueButton();
		Assert.assertEquals(forgotPasswordPage.getEnterVerificationCodeLabelText(),
				expectedAssertProp.getProperty("forgot.password.enter.verification.code.label"));
		// Assert expected success message or navigation
		// Assert.assertEquals(forgotPasswordPage.getSuccessMessage(),
		// expectedAssertProp.getProperty("forgot.password.success.username"));
	}

	@Test(priority = 3, description = "Verify Forgot Password with invalid username (negative)", enabled = true)
	@Description("Verifies Forgot Password functionality with an invalid username.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password Negative Flow - Invalid Username")
	public void verifyForgotPasswordWithInvalidUsername() throws InterruptedException {
		forgotPasswordPage.clickUsernameButton();
		forgotPasswordPage.enterUsername("invaliduser");
		forgotPasswordPage.clickContinueButton();
		// Assert expected error message
		// Assert.assertEquals(forgotPasswordPage.getErrorMessage(),
		// expectedAssertProp.getProperty("forgot.password.error.invalid.username"));
	}

	// verify the ui of forgot password screen after entering the correct username
	// or mobile number and clicking on continue button write test case for this

	@Test(priority = 8, description = "Verify Forgot Password UI after entering valid username and clicking Continue", enabled = true)
	@Description("Verifies the UI elements on the Forgot Password screen after entering a valid username and clicking Continue.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password UI Verification after Valid Username")
	public void verifyForgotPasswordUIAfterValidUsername() throws InterruptedException {
		forgotPasswordPage.clickUsernameButton();
		String validUsername = appData.getJSONObject("validUser").getString("username");
		forgotPasswordPage.enterUsername(validUsername);
		forgotPasswordPage.clickContinueButton();
		Assert.assertTrue(forgotPasswordPage.isEnterVerificationCodeLabelDisplayed(),
				"Enter Verification Code label should be displayed after clicking Continue with valid username");
		Assert.assertEquals(forgotPasswordPage.getEnterVerificationCodeLabelText(),
				expectedAssertProp.getProperty("forgot.password.enter.verification.code.label"));

//write assertions to verify enter verification code description, OTP fields Resend button and continue button are displayed or not
		Assert.assertTrue(forgotPasswordPage.isEnterVerificationCodeDescriptionDisplayed(),
				"Enter Verification Code description should be displayed after clicking Continue with valid username");
		Assert.assertEquals(forgotPasswordPage.getEnterVerificationCodeDescriptionText(),
				expectedAssertProp.getProperty("forgot.password.enter.verification.code.description"));

		Assert.assertTrue(forgotPasswordPage.isResendOTPButtonDisplayed(),
				"Resend OTP button should be displayed after clicking Continue with valid username");
		Assert.assertTrue(forgotPasswordPage.areAllOTPFieldsDisplayed(),
				"Continue button should be displayed after clicking Continue with valid username");
		Assert.assertTrue(forgotPasswordPage.isContinueButtonInOTPVerificationDisplayed(),
				"Continue button should be displayed after clicking Continue with valid username");
	}

	@Test(priority = 4, description = "Verify Forgot Password with empty username (negative)", enabled = true)
	@Description("Verifies Forgot Password functionality with an empty username field.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password Negative Flow - Empty Username")
	public void verifyForgotPasswordWithEmptyUsername() throws InterruptedException {
		forgotPasswordPage.clickUsernameButton();
		forgotPasswordPage.enterUsername("");
		Assert.assertFalse(forgotPasswordPage.isContinueButtonEnabled(),
				"Continue button should be disabled when username is empty");
		// forgotPasswordPage.clickContinueButton();

	}

	@Test(priority = 5, description = "Verify Forgot Password with valid mobile number (positive)", enabled = true)
	@Description("Verifies Forgot Password functionality with a valid mobile number.")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Forgot Password Positive Flow - Mobile Number")
	public void verifyForgotPasswordWithValidMobileNumber() throws InterruptedException {
		forgotPasswordPage.clickMobileNumberButton();
		String validMobile = appData.getJSONObject("validUser").getString("mobile");
		forgotPasswordPage.enterMobileNumber(validMobile);
		forgotPasswordPage.clickContinueButton();
		
		Assert.assertEquals(forgotPasswordPage.getEnterVerificationCodeLabelText(),
				expectedAssertProp.getProperty("forgot.password.enter.verification.code.label"));

	}

	@Test(priority = 6, description = "Verify Forgot Password with invalid mobile number (negative)", enabled = true)
	@Description("Verifies Forgot Password functionality with an invalid mobile number.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password Negative Flow - Invalid Mobile Number")
	public void verifyForgotPasswordWithInvalidMobileNumber() throws InterruptedException {
		forgotPasswordPage.clickMobileNumberButton();
		forgotPasswordPage.enterMobileNumber("0000000000");
		forgotPasswordPage.clickContinueButton();
		// Assert expected error message
		// Assert.assertEquals(forgotPasswordPage.getErrorMessage(),
		// expectedAssertProp.getProperty("forgot.password.error.invalid.mobile"));
	}

	@Test(priority = 7, description = "Verify Forgot Password with empty mobile number (negative)", enabled = true)
	@Description("Verifies Forgot Password functionality with an empty mobile number field.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Forgot Password Negative Flow - Empty Mobile Number")
	public void verifyForgotPasswordWithEmptyMobileNumber() throws InterruptedException {
		forgotPasswordPage.clickMobileNumberButton();
		forgotPasswordPage.enterMobileNumber("");
		Assert.assertFalse(forgotPasswordPage.isContinueButtonEnabled(),
				"Continue button should be disabled when username is empty");
	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		closeApp();
	}
}