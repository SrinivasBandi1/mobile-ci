package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginMenuPage extends BaseTest {
	// Elements on the Login Menu Page
	@AndroidFindBy(id = "org.intelehealth.app:id/et_username_login")
	private WebElement loginScreenUserName;
	@AndroidFindBy(id = "org.intelehealth.app:id/et_password_login")
	private WebElement loginScreenPassword;
	@AndroidFindBy(id = "org.intelehealth.app:id/button_login")
	private WebElement loginButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_welcome_back")
	private WebElement loginScreenWelcomeBackTitle;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_logo")
	private WebElement IHLogo;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_login_title")
	private WebElement loginScreenLoginTitle;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_title")
	private WebElement lblUsername;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_password_title")
	private WebElement lblPassword;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_forgot_username")
	private WebElement lnkforgotUsernameLink;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_forgot_password1")
	private WebElement lnkForgotPasswordLink;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_hamburger")
	private WebElement menu;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_edit_profile")
	private WebElement profile;
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and contains(@text,'Achievements ')]")
	private WebElement myAchievements;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and @text='Settings']")
	private WebElement lftPnlSettings;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and @text='Call Logs']")
	private WebElement lftPnlcallLogs;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and @text='About us']")
	private WebElement aboutUs;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and @text='Log-out']")
	private WebElement logOut;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reset app']")
	private WebElement resetApp;

	@AndroidFindBy(accessibility = "Book Appointment Dialog Title TextView")
	private WebElement logOutPopup;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_yes_appointment")
	private WebElement yesButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_loggedin_username")
	private WebElement name;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_userid")
	private WebElement healthworkerID;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_edit_profile")
	private WebElement editProfileLink;
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_profile_icon")
	private WebElement image;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement locationName;
	@AndroidFindBy(id = "org.intelehealth.app:id/txtProgressTask")
	private WebElement synchingApp;
	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement resetAppYesButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement syncSuccessful;
	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement resetAppPopup;

	JSONObject appData;

	public LoginMenuPage() throws IOException {
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
	// Methods for Page Actions

	// Click on the hamburger menu icon
	public void clickOnHamburgerMenu() throws InterruptedException {

		click(menu);
	}

	// Click on the Log Out button
	public void clickOnLogOut() throws InterruptedException {
		click(logOut, "Clicked on the Log Out button");
	}

	// Verify if the "Welcome Back" title is displayed on the login screen
	public void verifyWelcomeBackTitle() throws InterruptedException {
		isDisplayed(loginScreenWelcomeBackTitle, "Welcome Back title is displayed on the login screen");
	}

	// Perform login with the provided username and password
	public void login() throws InterruptedException {
		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");

		// Encrypt the password
		String encryptedUserName = encrypt(originalUserName);
		TestUtils.log().info(encryptedUserName);
		String decryptedUserName = decrypt(encryptedUserName);

		String encryptedPassword = encrypt(originalPassword);
		TestUtils.log().info(encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword);

		// Enter the username into the username field
		sendKeys(loginScreenUserName, decryptedUserName, "Entered username: " + decryptedUserName);

		// Enter the password into the password field
		sendKeys(loginScreenPassword, decryptedPassword, "Entered password: " + decryptedPassword);
		ExtentReport.getTest().log(Status.INFO, "Entered the username and password");
		// Click the login button to submit the credentials
		click(loginButton, "Clicked on the Login button");

	}

	public void loginWithNewPassword(String pw) {
		String originalUserName = appData.getJSONObject("validUser").getString("username");
		System.out.println(originalUserName);
		// Encrypt the password
		String encryptedUserName = encrypt(originalUserName);
		System.out.println("Encrypted UserName: " + encryptedUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		System.out.println("Decrypted Password: " + decryptedUserName);
		sendKeys(loginScreenUserName, decryptedUserName, "Entered username: " + decryptedUserName);
		sendKeys(loginScreenPassword, pw, "Entered password: " + pw);
		click(loginButton, "Clicked on the Login button");
	}

	// Verify elements on the Menu Page
	public boolean verifyMenuPageElements() throws InterruptedException {
		return isDisplayed(profile, "Profile element is displayed on the Menu Page")
				&& isDisplayed(myAchievements, "My Achievements element is displayed on the Menu Page")
				&& isDisplayed(lftPnlSettings, "Settings element is displayed on the Menu Page")
				&& isDisplayed(lftPnlcallLogs, "CallLogs element is displayed on the Menu Page")
				&& isDisplayed(aboutUs, "About Us element is displayed on the Menu Page")
				&& isDisplayed(resetApp, "Reset App element is displayed on the Menu Page")
				&& isDisplayed(logOut, "Log Out element is displayed on the Menu Page");
	}

	// Verify user profile details on the Profile Page
	public boolean verifyUserProfileDetails() throws InterruptedException {
		return isDisplayed(name, "User's name is displayed on the Profile Page") &&
		isDisplayed(healthworkerID, "Healthworker ID is displayed on the Profile Page") &&
		isDisplayed(editProfileLink, "Edit Profile link is displayed on the Profile Page") &&
		isDisplayed(image, "User's profile image is displayed on the Profile Page");
	}

	// Click on the "Yes" button
	public void clickOnYes() throws InterruptedException {
		click(yesButton, "Clicked on the Yes button");
	}

	/**
	 * Updated by Srinivas
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	// Verify if the location name is displayed
	public boolean verifyLocationIsDisplayed() throws InterruptedException {
		return isDisplayed(locationName, "Location name is displayed");
	}

	// Verify logout functionality
	public boolean verifyLogout() throws InterruptedException {
		// Click on the logout button
		click(logOut, "Clicked on the Logout button");

		// Click on the "Yes" button to confirm logout
		click(yesButton, "Confirmed logout by clicking on Yes button");

		// Introducing a brief delay for the UI to update (use of Thread.sleep should be
		// minimized)
		Thread.sleep(2000);

		return isDisplayed(loginScreenWelcomeBackTitle, "Verified the display of the login screen's welcome back title");
	}

	// Verify reset app functionality
	public void verifyResetAppFunctionality() throws InterruptedException {
		// Click on the reset app button
		click(resetApp, "Clicked on the Reset App button");

		// Click on the "Yes" button to confirm app reset
		click(resetAppYesButton, "Confirmed app reset by clicking on Yes button");

		//return isDisplayed(synchingApp, "Verified that the app is synchronizing");
		

	}
	
	//write a method for asserting the login screen elements
	public boolean verifyLoginScreenElements() throws InterruptedException {
		return isDisplayed(IHLogo, "Intelehealth logo is displayed on the login screen") &&
				isDisplayed(loginScreenLoginTitle, "Login title is displayed on the login screen") &&
				isDisplayed(lblUsername, "Username label is displayed on the login screen") &&
				isDisplayed(lblPassword, "Password label is displayed on the login screen") &&
				isDisplayed(lnkforgotUsernameLink, "Forgot Username link is displayed on the login screen") &&
				isDisplayed(lnkForgotPasswordLink, "Forgot Password link is displayed on the login screen") &&
				isDisplayed(loginScreenUserName, "Username input field is displayed on the login screen") &&
				isDisplayed(loginScreenPassword, "Password input field is displayed on the login screen") &&
				isDisplayed(loginButton, "Login button is displayed on the login screen");
	}

}