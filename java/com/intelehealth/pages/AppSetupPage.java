package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.intelehealth.base.BaseTest;
import com.intelehealth.base.CustomElementNotFoundException;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppSetupPage extends BaseTest {
	// Elements on the App Setup Page
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_logo")
	public WebElement logoIntelehealth;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_title")
	public WebElement txtIntelehealthTagline;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='English']")
	public WebElement rdoEnglish;

//	@AndroidFindBy(id = "org.intelehealth.app:id/btn_next_to_intro")
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_next")
	private WebElement nextButton;

	// Introductory Screen Elements
	@AndroidFindBy(id = "org.intelehealth.app:id/tvIntroTitle")
	private WebElement whoWeAre;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Take patient visits']")
	private WebElement takePatientVisits;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Provide prescriptions']")
	private WebElement providePrescriptions;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_skip")
//	@AndroidFindBy(id = "org.intelehealth.app:id/btn_skip_intro")
	private WebElement skipButton;

	// Ayu Intro Screen Elements
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_hello_ayu")
	private WebElement helloIamAyu;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_hello_ayu_subtitle")
	private WebElement ayuIntroScreenSubtitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/checkbox_privacy_policy")
	private WebElement checkBox;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_setup")
	private WebElement setupButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/enterDetailsTV")
	private WebElement lblPleaseEnterDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_location_title")
	private WebElement lblLocation;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_password_title")
	private WebElement lblPassword;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_username_title")
	private WebElement lblUsername;

	// @AndroidFindBy(accessibility = "Setup Screen Setup Title TextView")
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_setup")
	private WebElement lblSetUpHeader;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_setup_note")
	private WebElement lblSetUpDescription;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_privacy_notice_link_1")
	private WebElement termsAndCondition;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_privacy_notice_link_1")
	private WebElement lblTermsAndPolicy;

	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP TextView 4")
	private WebElement privacyPolicy;

	// Terms and Conditions Screen Elements
	// @AndroidFindBy(accessibility = "Terms and Conditions Title TextView")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Terms & Conditions\"]")
	private WebElement termsAndConditionTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/webview")
	private WebElement termsAndConditionDescription;

//	@AndroidFindBy(accessibility = "Terms and Conditions Back Arrow ImageView")
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_back_arrow_terms")

	private WebElement termsAndConditionsBackArrow;

	@AndroidFindBy(accessibility = "Terms and Conditions Decline Button")
	private WebElement termsAndConditionDeclineButton;

	@AndroidFindBy(accessibility = "Terms and Conditions Accept Button")
	private WebElement termsAndConditionAcceptButton;

	// Privacy Policy Screen Elements
	@AndroidFindBy(accessibility = "Privacy Policy Title TextView")
	private WebElement privacyPolicyTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/webview")
	private WebElement privacyPolicyContent;

	@AndroidFindBy(accessibility = "Privacy Policy Back Arrow ImageView")
	private WebElement privacyPolicyBackArrow;

	@AndroidFindBy(accessibility = "Privacy Policy Decline Button")
	private WebElement privacyPolicyDeclineButton;

	@AndroidFindBy(accessibility = "Privacy Policy Accept Button")
	private WebElement privacyPolicyAcceptButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_forgot_username")
	private WebElement lnkForgotUserName;

	@AndroidFindBy(id = "org.intelehealth.app:id/alertTitle")
	private WebElement forgotUserNameDialogTitle;

	@AndroidFindBy(id = "android:id/message")
	private WebElement forgotUserNameDialogMessage;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement btnOkInForgotUserNameDialog;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_forgot_password1")
	private WebElement lnkForgotPassword;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	// xpath = "//android.widget.Button[@text='While using the app']")
	public WebElement whileUsingAppButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_all_button']")
	public WebElement btnAllowAll;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	// xpath = "//android.widget.Button[@text='Allow']")
	public WebElement allowButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
	public WebElement pDAllowButton;

	@AndroidFindBy(id = "android:id/checkbox")
	public WebElement toggle;

	@AndroidFindBy(accessibility = "Back")
	public WebElement back;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to take pictures and record video?']")
	public WebElement takePicturesTextView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to access your contacts?']")
	public WebElement accessContactsTextView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to make and manage phone calls?']")
	public WebElement managePhoneCallsTextView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to access photos and media on your device?']")
	public WebElement accessPhotosTextView;

	// xpath =
	// "//android.widget.Button[@resource-id=\"org.intelehealth.app:id/btn_next_to_intro\"]"
	/*
	 * @AndroidFindBy(xpath =
	 * "//android.widget.Button[@resource-id='org.intelehealth.app:id/btnNextToIntro']")
	 * private WebElement nextButton;
	 */

	// @AndroidFindBy(id = "org.intelehealth.app:id/textInputUsername")
	@AndroidFindBy(id = "org.intelehealth.app:id/et_username")
	private WebElement username;

	// @AndroidFindBy(accessibility = "Setup Screen Password Edittext")
	// @AndroidFindBy(id = "org.intelehealth.app:id/textInputPassword")
	@AndroidFindBy(id = "org.intelehealth.app:id/et_password")
	private WebElement password;

	// @AndroidFindBy(accessibility = "Show dropdown menu")
	@AndroidFindBy(id = "org.intelehealth.app:id/autotv_select_location")
	private WebElement dropdown;

	// @AndroidFindBy(id = "//android.widget.TextView[@text='Telemedicine Clinic
	// 1']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Telemedicine Clinic 1']")
	private WebElement location;

	// @AndroidFindBy(accessibility = "Setup Screen Setup Button")
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_setup")
	private WebElement setupScreeenSetupButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement locationName;

	// @AndroidFindBy(id = "org.intelehealth.app:id/ivSyncIcon")
	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement refreshButton;

	@AndroidFindBy(accessibility = "Intelehealth")
	private WebElement intelehealth;
	@AndroidFindBy(id = "android:id/switch_widget")
	private WebElement toggleButton;

	@AndroidFindBy(accessibility = "Navigate up")
	private WebElement navigateBack;
	@AndroidFindBy(id = "org.intelehealth.app:id/text1")
	private List<WebElement> lstTelemedicineclinic;
	// @AndroidFindBy(accessibility = "Setup Screen Enter Details TextView")

	JSONObject appData;

	public AppSetupPage() throws IOException {
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

	// Methods for Page Actions

	// Select language
	public boolean selectLanguage() {
		return isSelected(rdoEnglish);

	}

	// Handles the permission prompts during app installation
	public void handlePermissions() throws InterruptedException {
		for (int j = 0; j < 2; j++) {
			try {
				click(whileUsingAppButton);
				click(whileUsingAppButton);

				for (int i = 0; i < 4; i++) {
					click(allowButton);
				}
				break;
			} catch (TimeoutException e) {
				getDriver().navigate().back();
			}
			// click(allowButton);
			// click(allowButton);
		}
		// click(btnAllowAll);

	}

	public boolean isDisplayedAppLogoAndTaglineText() throws InterruptedException {

		return isDisplayed(logoIntelehealth, "App Logo is displayed")
				&& txtIntelehealthTagline.getText().replace("\r", "").replace("\n", " ").trim()
						.equals("Deliver quality healthcare where there is no doctor");
	}

	public void clickOnEnglishLanguageRadioButton() throws InterruptedException {
		if (setupLanguageScreenIsDisplayed() && selectLanguage())
			click(rdoEnglish);
	}

	// Click on Next button
	public void clickOnNextButton() throws InterruptedException {
		Thread.sleep(5000);
		if (isDisplayed(rdoEnglish)) {
			waitForVisibility(nextButton);
			click(nextButton);
			// "Clicked on Next Button");
		}
	}

	// Click on Skip button
	/*
	 * public void clickOnSkipButton() throws InterruptedException {
	 * click(skipButton); }
	 */

	public void clickOnSkipButton() {
		for (int i = 0; i < 2; i++) {
			try {
				waitForVisibility(skipButton);
				click(skipButton);
				return;
			} catch (TimeoutException e) {
				if (i == 0) {

					click(nextButton);
				} else {
					throw new RuntimeException("Continue button not clickable after retry", e);
				}
			}
		}
	}

	// Click on CheckBox
	public void clickOnCheckBox() throws InterruptedException {
		click(checkBox);
	}

	// Click on Setup button
	public void clickOnSetupButton() throws InterruptedException {
		click(setupButton);
	}

	// Click on Terms and Conditions Back Arrow
	public void clickOnTermsAndConditionsBackArrow() throws InterruptedException {
		click(termsAndConditionsBackArrow, "Clicked on Terms and Conditions Back Arrow");
	}

	// Click on Terms and Conditions
	public void clickOnTermsAndCondition() throws InterruptedException {
		click(termsAndCondition, "Clicked on Terms and Conditions");
	}

	// Click on Privacy Policy
	public void clickOnPrivacyPolicy() throws InterruptedException {
		click(privacyPolicy, "Clicked on Privacy Policy");
	}

	// Click on Privacy Policy Back Arrow
	public void clickOnPrivacyPolicyBackArrow() throws InterruptedException {
		click(privacyPolicyBackArrow, "Clicked on Privacy Policy Back Arrow");
	}

	// Verify elements on Introductory Screen
	/**
	 * @author @SrinivasBandi
	 * 
	 */
	public boolean verifyIntroductoryScreen() throws InterruptedException {
		return isDisplayed(whoWeAre, "Who We Are is displayed on the Introductory Screen")
				&& isDisplayed(takePatientVisits, "Take Patient Visits is displayed on the Introductory Screen")
				&& isDisplayed(providePrescriptions, "Provide Prescriptions is displayed on the Introductory Screen");
	}

	public List<String> getIntroductoryScreenText() {
		return getElementsText(whoWeAre, takePatientVisits, providePrescriptions);
	}

	// Verify Skip button is displayed
	/**
	 * public void skipButtonIsDisplayed() throws InterruptedException {
	 * isDisplayed(skipButton, "Skip button is displayed"); }
	 * 
	 * @throws CustomElementNotFoundException
	 */
	public boolean skipButtonIsDisplayed() throws InterruptedException {
		if (isDisplayed2(skipButton, "Skip button is displayed")) {
			return true;
		} else {
			return false;
		}
	}

	// Verify elements on Ayu Intro Screen
	public boolean verifyAyuScreen() throws InterruptedException {
		return isDisplayed(helloIamAyu, "Hello, I am Ayu text is displayed")
				&& isDisplayed(ayuIntroScreenSubtitle, "Ayu Intro Screen Subtitle is displayed")
				&& isDisplayed(checkBox, "Checkbox is displayed")
				&& isDisplayed(setupButton, "Setup Button is displayed");
	}

	/**
	 * need to check
	 * 
	 * @return
	 */
	public List<String> getPolicyTexts() {
		return getElementsText(termsAndCondition, privacyPolicy);
	}

	public String getTermsAndPolicyText() {
		return lblTermsAndPolicy.getText();
	}

	public List<String> getAyuScreenText() {
		;
		return getElementsText(helloIamAyu, ayuIntroScreenSubtitle, setupButton);
	}

	public List<String> getTermsAndConditionsScreenElementsText() {
		return getElementsText(termsAndConditionTitle, termsAndConditionDescription, termsAndConditionAcceptButton,
				termsAndConditionDeclineButton);
	}

	public List<String> getPrivacyPolicyScreenElementsText() {
		return getElementsText(privacyPolicyTitle, privacyPolicyContent, privacyPolicyAcceptButton,
				privacyPolicyDeclineButton);
	}

// check accept and decline button locator and functionality 
	// Verify elements on Terms and Conditions Screen
	public boolean verifyTermsAndConditionScreen() throws InterruptedException {
		return isDisplayed(termsAndConditionTitle, "Terms and Condition Title is displayed")
				&& isDisplayed(termsAndConditionDescription, "Terms and Condition Description is displayed")
				&& isDisplayed(termsAndConditionDeclineButton, "Decline Button is displayed")
				&& isDisplayed(termsAndConditionsBackArrow, "Back Arrow is displayed")
				&& isDisplayed(termsAndConditionAcceptButton, "Accept Button is displayed");
	}

	// Verify elements on Privacy Policy Screen

	public boolean verifyPrivacyPolicyScreen() throws InterruptedException {

		return isDisplayed(privacyPolicyTitle, "Privacy Policy Title is displayed")
				&& isDisplayed(privacyPolicyContent, "Privacy Policy Content is displayed")
				&& isDisplayed(privacyPolicyDeclineButton, "Privacy Policy Decline Button is displayed")
				&& isDisplayed(privacyPolicyAcceptButton, "Privacy Policy Accept Button is displayed");
	}

	public boolean isEnabledSetUpButton() {
		return setupButton.isEnabled();
	}

	public List<String> getSetUpScreenLabels() {
		return getElementsText(lblSetUpHeader, lblPleaseEnterDetails, lblLocation, lblUsername, lblPassword);
	}

	public void selectFromDropdown(String spinnerResourceId, String optionText) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
		AndroidDriver androidDriver = (AndroidDriver) driver.get();

		// Step 1: Capture main window handle BEFORE clicking anything
		// This is the fix — grab it before the popup spawns
		String mainHandle = androidDriver.getWindowHandle();
		System.out.println("Main window handle: " + mainHandle);

		// Step 2: Click spinner to open popup
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id(spinnerResourceId))).click();

		Thread.sleep(1500);

		// Step 3: Get all handles after popup opens
		Set<String> allHandles = androidDriver.getWindowHandles();
		System.out.println("Total handles after click: " + allHandles.size());
		allHandles.forEach(h -> System.out.println("  Handle: " + h));

		// Step 4: Identify popup handle — it's the one that's NOT the main
		String popupHandle = null;
		for (String handle : allHandles) {
			if (!handle.equals(mainHandle)) {
				popupHandle = handle;
				break;
			}
		}

		if (popupHandle != null) {
			System.out.println("Popup handle found: " + popupHandle);

			// Step 5: Switch to popup window
			androidDriver.switchTo().window(popupHandle);

			// Step 6: Find and click option — resource-id confirmed from your XML
			WebElement option = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.xpath("//*[@resource-id='org.intelehealth.app:id/text1']" + "[@text='" + optionText + "']")));
			option.click();
			System.out.println("Clicked option: " + optionText);

			// Step 7: Switch back to main window
			androidDriver.switchTo().window(mainHandle);
			System.out.println("Switched back to main window");

		} else {
			// Step 8: Fallback — handles still show 1 (popup not a separate handle)
			System.out.println("Only 1 handle found — trying coordinate tap fallback");
			selectByCoordinates(optionText);
		}
	}

	// ─── Coordinate fallback ─────────────────────────────────────────────────────
	// Coordinates taken directly from your dropdownAppSource.xml bounds
	private void selectByCoordinates(String optionText) {
		AndroidDriver androidDriver = (AndroidDriver) driver.get();

		Map<String, int[]> coords = new LinkedHashMap<>();
		coords.put("Telemedicine Clinic 1", new int[] { 540, 1221 });
		coords.put("Telemedicine Clinic 2", new int[] { 540, 1346 });
		coords.put("Telemedicine Clinic 3", new int[] { 540, 1470 });

		int[] xy = coords.get(optionText);

		if (xy == null) {
			throw new IllegalArgumentException("No coordinates defined for option: " + optionText);
		}

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), xy[0], xy[1]));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		androidDriver.perform(Collections.singletonList(tap));

		System.out.println("Coordinate tap done for: " + optionText + " at [" + xy[0] + "," + xy[1] + "]");
	}

	// Perform login
	public void login(String un, String pw) throws InterruptedException {
		// Click on the dropdown to open the menu
		click(dropdown);

		getDriver().setSetting("enableTopmostWindowFromActivePackage", true);
		// selectFromDropdown("org.intelehealth.app:id/autotv_select_location",
		// "Telemedicine Clinic 1");
		click(lstTelemedicineclinic.get(0));
		getDriver().setSetting("enableTopmostWindowFromActivePackage", false);

		// For selecting Telemedicine 1

		// Click on the location element to select a location

		/*
		 * String main = driver.get().getWindowHandle(); for (String h :
		 * driver.get().getWindowHandles()) { driver.get().switchTo().window(h); if
		 * (driver.get().getPageSource().contains("Your Location")) {
		 * driver.get().findElement(By.
		 * xpath("//*[contains(@text,'Telemedicine Clinic 1')]")).click(); break; } }
		 */
		// click(location);
		// selectDropdownOption(76, 1007, 1004, 1142);
		// System.out.println(driver.get().getPageSource());
//		driver.get()
//				.findElement(AppiumBy
//						.androidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\"))"
//								+ ".scrollIntoView(new UiSelector().text(\"Telemedicine Clinic 1\"))"))
//				.click();
		// Enter the username into the username field

		AndroidDriver androidDriver = (AndroidDriver) driver.get();

//		// Press down + enter
		// androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
//		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
//		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
//		Thread.sleep(2000);
//		androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));//Select Telemedicine 1
//
		/*
		 * androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select
		 * Telemedicine 2 androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));//
		 * Select Telemedicine 3 androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		 * androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		 * androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));// Select
		 * Telemedicine 3 androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
		 */
		/*
		 * List<WebElement> items =
		 * getDriver().findElements(AppiumBy.id("android:id/autotv_select_location"));
		 * 
		 * 
		 * for (WebElement item : items) { System.out.println(item.getText()); }
		 * WebElement el = getDriver().findElement(AppiumBy.className(
		 * "android.widget.AutoCompleteTextView")); System.out.println(el.getText());
		 */
		sendKeys(username, un);
		// Enter the password into the password field

		sendKeys(password, pw);
		// Click on the setup screen setup button to complete the login process
		click(setupScreeenSetupButton);

		// Handling display over other apps
		/*
		 * try { if (isDisplayed(navigateBack)) { click(intelehealth);
		 * click(toggleButton); click(navigateBack); click(navigateBack);
		 * 
		 * } } catch (Exception e) { // Properly handle the exception, e.g., log the
		 * error
		 * 
		 * }
		 */
		// Check if locationName is displayed after the actions
		Thread.sleep(15000);
		// isDisplayed(locationName);

	}

	public void refreshUIAndWait() throws InterruptedException {
		click(refreshButton);
		// Thread.sleep(26000);
	}

	/**
	 * Updated by Srinivas
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	// Verify if the location name is displayed
	public boolean locationIsDisplayed() throws InterruptedException {
		return isDisplayed(locationName);
	}

	// Check if the setup language screen is displayed
	public boolean setupLanguageScreenIsDisplayed() throws InterruptedException {
		return isDisplayed(rdoEnglish, "Setup Language Screen is displayed");
	}

	// Perform a series of actions to complete the setup process
	public void completeSetup() throws InterruptedException {
		// Click the "Next" button
		clickOnNextButton();

		// Click the "Skip" button
		clickOnSkipButton();

		// Click the checkbox
		clickOnCheckBox();

		// Click the "Setup" button
		clickOnSetupButton();

		// Perform login with the provided username and password

		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");

		System.out.println(originalPassword);
		System.out.println(originalUserName);
		// Encrypt the password
		String encryptedUserName = encrypt(originalUserName);
		System.out.println("Encrypted UserName: " + encryptedUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		System.out.println("Decrypted UserName: " + decryptedUserName);
		String encryptedPassword = encrypt(originalPassword);
		System.out.println("Encrypted Password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		System.out.println("Decrypted Password: " + decryptedPassword);
		login(decryptedUserName, decryptedPassword);

	}

	public void clicKOnForgotUserNameLink() throws InterruptedException {
		click(lnkForgotUserName);
	}

	public String getForgotUserNameDialogTitle() {
		return forgotUserNameDialogTitle.getText();
	}

	public String getForgotUserNameDialogMessage() {
		return forgotUserNameDialogMessage.getText();
	}

	public void clickOkInForgotUserNameDialog() throws InterruptedException {
		click(btnOkInForgotUserNameDialog);
	}

	public void clicKOnForgotPasswordLink() throws InterruptedException {
		click(lnkForgotPassword);
	}
}
