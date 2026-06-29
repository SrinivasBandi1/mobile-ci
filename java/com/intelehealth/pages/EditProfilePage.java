package com.intelehealth.pages;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EditProfilePage extends BaseTest {

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_edit_profile")
	private WebElement editProfile;
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_hamburger")
	private WebElement menu;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_screen_title_common")
	private WebElement myProfileTitle;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_change_photo_profile")
	private WebElement changePhoto;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/title_tv' and @text='Take Photo']")
	private WebElement takePhoto;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/title_tv' and @text='Choose from Gallery']")
	private WebElement chooseFromGallery;
	@AndroidFindBy(id = "org.intelehealth.app:id/utils_take_picture")
	private WebElement photoCaptureButton;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/camera_switch_iv']")
	private WebElement camera;
	@AndroidFindBy(accessibility = "Shutter")
	private WebElement shutterButton;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='com.sec.android.gallery3d:id/thumbnail'])[1]")
	private WebElement galleryAlbum;

	@AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[5]/android.view.View[2]/android.view.View[2]/android.view.View")
	private WebElement imageInAlbum;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_profilePic")
	private WebElement profilePhoto;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_points")
	private WebElement changePassword;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Fingerprint Lock']")
	private WebElement fingerPrintLock;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Username']")
	private WebElement userNameLabel;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_username_profile")
	private WebElement userNameTextField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='First name']")
	private WebElement firstNameLabel;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_first_name_profile")
	private WebElement firstNameTextField;
	/*
	 * NOt present currently
	 * 
	 * @AndroidFindBy(accessibility =
	 * "My Profile Screen Middle Name Heading TextView") private WebElement
	 * middleNameLabel;
	 * 
	 * @AndroidFindBy(accessibility = "My Profile Screen Middle Name Edittext")
	 * private WebElement middleNameTextField;
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last name']")
	private WebElement lastNameLabel;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_last_name_profile")
	private WebElement lastNameTextField;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_gender")
	private WebElement gender;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_dob")
	private WebElement dob;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_email")
	private WebElement email;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_save_profile")
	private WebElement save;

	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Month Spinner")
	private WebElement monthSpinner;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='February']")
	private WebElement month;

	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Year Spinner")
	private WebElement yearSpinner;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1999']")
	private WebElement year;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1']")
	private WebElement date;
	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Okay Button")
	private WebElement okayButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Feb 01, 1999']")
	private WebElement selectedDOB;
	@AndroidFindBy(id = "org.intelehealth.app:id/et_mobile_no_profile")
	private WebElement phoneNumberTextField;

	@AndroidFindBy(id = "org.intelehealth.app:id/et_email_profile")
	private WebElement emailID;
	@AndroidFindBy(id = "org.intelehealth.app:id/et_current_password")
	private WebElement currentPwdTextBox;
	@AndroidFindBy(id = "org.intelehealth.app:id/et_new_password_change")
	private WebElement newPwdTextBox;
	@AndroidFindBy(id = "org.intelehealth.app:id/et_new_password_confirm")
	private WebElement reEnterNewPwdTextBox;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_save_change")
	private WebElement changePasswordSaveButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/snackbar_text")
	private WebElement contactAdminstratorMessage;

	private LoginMenuPage loginMenuPage;
	String generatedPassword;

	public EditProfilePage() throws IOException {
		loginMenuPage = new LoginMenuPage();
	}

	public WebElement scrollToYear() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"2000\").instance(0))"));

	}

//Scroll to the element with the text "Date of birth"
	public WebElement scrollToDateOfBirth() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Date of birth\").instance(0))"));

	}

	// Verifies the elements on the My Profile page
	public boolean verifyMyProfilePage() throws InterruptedException {

		boolean isDisplayedEditprofile = isDisplayed(editProfile, "Edit Profile is Displayed");
		Thread.sleep(10000);
		click(editProfile, "Clicked on Edit Profile");
		boolean myProfileElements = isDisplayed(myProfileTitle, "My Profile Title Displayed")
				&& isDisplayed(changePhoto, "Change Photo Displayed")
				&& isDisplayed(changePassword, "Change Password Displayed")
				&& isDisplayed(fingerPrintLock, "FingerPrint Lock displayed")
				&& isDisplayed(userNameLabel, "Username Label Displayed")
				&& isDisplayed(userNameTextField, "Username Text Field is Displayed")
				&& isDisplayed(firstNameLabel, "First Name Label Displayed")
				&& isDisplayed(firstNameTextField, "First Name Text Field Displayed") &&
				// isDisplayed(middleNameLabel, "Middle Name Label Displayed");
				// isDisplayed(middleNameTextField, "Middle Name Text Field Displayed");
				isDisplayed(lastNameLabel, "Last Name Label Displayed")
				&& isDisplayed(lastNameTextField, "Last Name Text Field Displayed");
		scrollDown();
		return isDisplayedEditprofile && myProfileElements && isDisplayed(gender, "Gender Displayed")
				&& isDisplayed(dob, "DOB Displayed") && isDisplayed(email, "Email Displayed")
				&& isDisplayed(save, "Save Button Displayed");

	}

//Changes the profile photo by capturing a new photo
	public boolean changeProfilePhotoByCapturingPhoto() throws InterruptedException {
		waitForVisibility(editProfile);
		click(editProfile, "Clicked on edit profile");
		Thread.sleep(7000);
		click(changePhoto, "Clicked on change photo");
		click(takePhoto, "Clicked on Take photo");
		// click(camera, "Clicked on Camera");
		click(photoCaptureButton, "Capture the photo");
		return isDisplayed(profilePhoto, "Profile photo is displayed");
	}

//Uploads a profile photo and verifies its display
	public boolean uploadProfilePhotoAndVerify() throws InterruptedException {
		// launchCamera();
		// click(shutterButton, "Clicked on capture button");
		// activateIntelehealth();
		click(editProfile, "Clicked on edit profile");
		Thread.sleep(5000);
		click(changePhoto, "Clicked on change photo");
		click(chooseFromGallery, "Clicked on Choose from gallery");
		// click(galleryAlbum, "Clicked on photos");
		click(imageInAlbum, "Selects a image");
		return isDisplayed(profilePhoto, "profile photo is displayed");
	}

//Verifies that the user can save the updated details
	public boolean verifyUserCanSaveTheUpdatedDetails(String mobileNumber, String txtEmailID)
			throws InterruptedException {
		waitForVisibility(editProfile);
		click(editProfile, "Clicked on edit profile");
		Thread.sleep(2000);
		scrollDown();
		// scrollToDateOfBirth();
		/*
		 * Fields non editable click(dob, "Clicked on DOB Field"); click(monthSpinner,
		 * "Clicked on month spinner"); click(month, "Selects a month");
		 * click(yearSpinner, "Clicked on year spinner"); scrollToYear(); click(year,
		 * "Selects a year"); click(date, "Selects a date"); click(okayButton,
		 * "Clicked on okay button");
		 */
		clear(phoneNumberTextField);
		sendKeys(phoneNumberTextField, mobileNumber, "Entered a phone number");
		clear(emailID);
		sendKeys(emailID, txtEmailID, "Entered email");
		click(save, "Clicked On save button ");
		click(menu, "Clicked on menu");
		click(editProfile, "Clicked on edit profile");
		Thread.sleep(2000);
		scrollDown();
		String updatedEmailID = emailID.getText();
		String updatedMobileNumber = phoneNumberTextField.getText();

		if (updatedEmailID.equalsIgnoreCase(txtEmailID) && updatedMobileNumber.equalsIgnoreCase(mobileNumber)) {
			return true;
		} else {
			return false;
		}
	}
//Verifies that the user is able to login with the new password

	public void verifyUserAbleToLoginWithNewPassword(String password,String newPassword) throws InterruptedException {
		// Generate and store a 7-character password
		generatedPassword = generatePassword(); // Corrected: initialize generatedPassword
		System.out.println("Generated Password: " + generatedPassword);
		isDisplayed(editProfile, "Edit Profile is Displayed");
		Thread.sleep(7000);
		click(editProfile, "Clicked on Edit Profile");
		click(changePassword, "Clicked on Change Password");
		sendKeys(currentPwdTextBox, password, "Entered Current Password");
		sendKeys(newPwdTextBox, newPassword, "Entered New Password");
		sendKeys(reEnterNewPwdTextBox, newPassword, "Re-Entered New Password");
		click(changePasswordSaveButton, "Clicked on Save Button");
		loginMenuPage.loginWithNewPassword(newPassword);

	}

//Resets the password to the old password
	public void resetThePasswordToOldPassword(String password,String newPassword) throws InterruptedException {
		loginMenuPage.clickOnHamburgerMenu();
		isDisplayed(editProfile, "Edit Profile is Displayed");
		Thread.sleep(7000);
		click(editProfile, "Clicked on Edit Profile");
		click(changePassword, "Clicked on Change Password");
		sendKeys(currentPwdTextBox, newPassword, "Entered Current Password");
		sendKeys(newPwdTextBox, password, "Entered New Password");
		sendKeys(reEnterNewPwdTextBox, password, "Re-Entered New Password");
		click(changePasswordSaveButton, "Clicked on Save Button");
		loginMenuPage.login();
	}

//Generates a password with a specific pattern

	public static String generatePassword() {
		StringBuilder password = new StringBuilder();
		Random random = new Random();

		// Ensure the first letter is an uppercase letter
		char firstChar = (char) (random.nextInt(26) + 'A');
		password.append(firstChar);

		// Generate the remaining characters (letters and at least one digit)
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		// Append 6 characters (length - 1) without the first character
		for (int i = 1; i < 7; i++) {
			char randomChar = characters.charAt(random.nextInt(characters.length()));
			password.append(randomChar);
		}

		// Append at least one digit
		password.append(characters.charAt(random.nextInt(10) + 52)); // Digits start at index 52

		return password.toString();
	}

	public String verifyNonEditableFields() {
		click(editProfile, "Clicked on edit profile");
		click(dob, "Clicked on DOB Field");
		click(firstNameTextField, "Clicked on first name field");
		return contactAdminstratorMessage.getText();
	}
}