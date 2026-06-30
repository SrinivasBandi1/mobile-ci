/**
 * Author: Shweta Naik 
 * Description: Test class for verifying App Setup functionalities.
 */
package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
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

@Epic("App Setup Module")
@Feature("Introductory Screens and Language  Selection")
@Owner("Srinivas Bandi")
public class AppSetupTest extends BaseTest {
	AppSetupPage appSetupPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
	    ExtentReport.startTest("beforeMethod",
	        "Setup for: " + m.getName());
	    TestUtils.log().info("****** Starting test: " + m.getName() + " ******");

	    // 1. Verify the driver session is alive before doing anything.
	    if (!SessionHealthManager.isSessionAlive(getDriver())) {
	        throw new IllegalStateException(
	            "Driver session is dead before test: " + m.getName());
	    }
	//    clearLogcat();
	    // 2. Reset and wait for app to be fully ready.
	    resetApp();

	    // 3. Initialise Page Object AFTER driver is confirmed alive.
	    appSetupPage = new AppSetupPage();

	    // 4. Load test data.
	    try (InputStream datais = getClass().getClassLoader()
	            .getResourceAsStream("data/appData.json")) {
	        appData = new JSONObject(new JSONTokener(datais));
	    }

	    // 5. Brief stabilisation wait — prefer explicit element wait over Thread.sleep.
	    Thread.sleep(2000);

	    appSetupPage.handlePermissions();
	    appSetupPage.clickOnEnglishLanguageRadioButton();
	    appSetupPage.clickOnNextButton();
	}

	 @Test(priority = 1, description = "Verify user can select any of the language and click Next", enabled = true)
	@Description("Verifies that the user can select any language from the available options and navigate through the introductory screen.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Language Selection and Navigation")
	public void IDA4_1939_verifySelectLanguage() throws InterruptedException {

		ExtentReport.startTest("IDA4_1939_verifySelectLanguage",
				"Verify user can select any of the language and click Next");

		Assert.assertTrue(appSetupPage.verifyIntroductoryScreen());

		Assert.assertTrue(appSetupPage.skipButtonIsDisplayed());

		// Assert.assertEquals(appSetupPage.getIntroductoryScreenText(),

		// Arrays.asList(expectedAssertProp.getProperty("introductory.screen.whoarewetext"),
		// expectedAssertProp.getProperty("introductory.screen.takepatientvisitstext"),
		// expectedAssertProp.getProperty("introductory.screen.provideprescriptionstext")));

	}

	 @Test(priority = 2, description = " Verify clicking on Skip on theintroductory screens", enabled = true)
	public void IDA4_1940_verifySkipOnTheIntroductoryScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_1939_verifySelectLanguage",
				" Verify clicking on Skip on the introductory screens");

		appSetupPage.clickOnSkipButton();

		// Verify elements on Ayu screen are displayed
		Assert.assertTrue(appSetupPage.verifyAyuScreen());

		/**
		 * remove list and make it as a signle string when we execute as there is a
		 * single xpath
		 */
		// Assert.assertEquals(appSetupPage.getPolicyTexts(),
		// Arrays.asList(expectedAssertProp.getProperty("ayu.screen.termsandconditionstext"),
		// expectedAssertProp.getProperty("ayu.screen.privacypolicytext")));

		Assert.assertEquals(appSetupPage.getAyuScreenText(),
				Arrays.asList(expectedAssertProp.getProperty("ayu.screen.title"),
						expectedAssertProp.getProperty("ayu.screen.subtitle"),
						expectedAssertProp.getProperty("ayu.screen.setup.text")));

	}

	@Test(priority = 3, description = "Verify that user is able to navigate to setup page when the terms & conditions box is checked", enabled = true)
	public void IDA4_1943_verifyTermsAndConditionCheckBox() throws InterruptedException {

		// Click on the "Skip" button to bypass initial setup
		appSetupPage.clickOnSkipButton();
		/**
		 * Needed one attribute For status checked
		 */
		Assert.assertFalse(appSetupPage.isEnabledSetUpButton());
		// Click on the checkbox to agree to terms and conditions
		appSetupPage.clickOnCheckBox();
		Assert.assertTrue(appSetupPage.isEnabledSetUpButton());

		// Click on the "Setup" button to proceed with the application setup
		appSetupPage.clickOnSetupButton();

	}

	/**
	 * Commented this due to only one locator for terms and conditions and privacy
	 * policy.. Hence required one more locator to automate this testcase
	 * 
	 * @throws InterruptedException
	 */
	// @Test(priority = 4, description = "Verify clicking on Terms and
	// Conditions/Privacy Policy link on the checkbox text", enabled = true)
	public void IDA4_1941_verifyTermsAndPrivacyPolicyScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_1941_verifyTermsAndPrivacyPolicyScreen",
				"Verify clicking on Terms and Conditions/Privacy Policy link on the checkbox text");
		// Click on the "Skip" button to bypass the initial setup
		appSetupPage.clickOnSkipButton();

		// Click on the link to view the Terms and Conditions
		appSetupPage.clickOnTermsAndCondition();

		// Verify that the Terms and Conditions screen is displayed
		Assert.assertTrue(appSetupPage.verifyTermsAndConditionScreen());

		Assert.assertEquals(appSetupPage.getTermsAndConditionsScreenElementsText(),
				Arrays.asList(expectedAssertProp.getProperty("terms.and.conditions.screen.title"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.description"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.accept.button"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.decline.button")));

		// Navigate back from the Terms and Conditions screen
		appSetupPage.clickOnTermsAndConditionsBackArrow();
		/**
		 * Needed separate element locator for terms and conditions and privacy policy
		 */
		// Click on the link to view the Privacy Policy
		appSetupPage.clickOnPrivacyPolicy();

		// Verify that the Privacy Policy screen is displayed
		Assert.assertTrue(appSetupPage.verifyPrivacyPolicyScreen());

		Assert.assertEquals(appSetupPage.getPrivacyPolicyScreenElementsText(),
				Arrays.asList(expectedAssertProp.getProperty("privacy.policy.screen.title"),
						expectedAssertProp.getProperty("privacy.policy.screen.description"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.accept.button"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.decline.button")));

	}

	@Test(priority = 5, description = "Verify that user is able to navigate to setup page when the terms & conditions box is checked", enabled = true)
	public void IDA4_1943_verifyForgotUsernameDialogBox() throws InterruptedException {

		// Click on the "Skip" button to bypass initial setup
		appSetupPage.clickOnSkipButton();
		/**
		 * Needed one attribute For status checked
		 * 
		 */
		Assert.assertFalse(appSetupPage.isEnabledSetUpButton());
		// Click on the checkbox to agree to terms and conditions
		appSetupPage.clickOnCheckBox();
		
		Assert.assertTrue(appSetupPage.isEnabledSetUpButton());
		
		// Click on the "Setup" button to proceed with the application setup
		appSetupPage.clickOnSetupButton();

		appSetupPage.clicKOnForgotUserNameLink();

		Assert.assertEquals(appSetupPage.getForgotUserNameDialogTitle(),
				expectedAssertProp.getProperty("forgot.username.dialog.box.title"));

		Assert.assertEquals(appSetupPage.getForgotUserNameDialogMessage(),
				expectedAssertProp.getProperty("forgot.username.dialog.box.message"));

		appSetupPage.clickOkInForgotUserNameDialog();
	}

	@Test(priority = 6, description = "Verify that UI of set up screen", enabled = true)
	public void IDA4_1942_verifySetUpScreenUIElements() throws InterruptedException {
		ExtentReport.startTest("verifySetUpScreenUIElements", "Verify that UI of set up screen");
		// Click on the "Skip" button to bypass initial setup
		appSetupPage.clickOnSkipButton();
		// Click on the checkbox to agree to terms and conditions
		appSetupPage.clickOnCheckBox();
		// Click on the "Setup" button to proceed with the application setup
		appSetupPage.clickOnSetupButton();

		Assert.assertEquals(appSetupPage.getSetUpScreenLabels(),
				Arrays.asList(expectedAssertProp.getProperty("app.setup.screen.header"),
						expectedAssertProp.getProperty("app.setup.screen.please.enter.details"),
						expectedAssertProp.getProperty("app.setup.screen.location"),
						expectedAssertProp.getProperty("app.setup.screen.username"),
						expectedAssertProp.getProperty("app.setup.screen.password")));
	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}

}
