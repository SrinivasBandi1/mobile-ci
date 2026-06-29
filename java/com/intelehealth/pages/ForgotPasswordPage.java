package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ForgotPasswordPage extends BaseTest {
	JSONObject appData;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_forgot_password")
	public WebElement lblForgotPassword;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_please_continue")
	public WebElement lblForgotPasswordDescription;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_image_icon")
	public WebElement imgForgotPassword;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_username")
	public WebElement btnUsername;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_mobile_number")
	public WebElement btnMobileNumber;

	@AndroidFindBy(id = "org.intelehealth.app:id/edittext_username")
	public WebElement txtUsername;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_continue")
	public WebElement btnContinue;

	@AndroidFindBy(id = "org.intelehealth.app:id/edittext_mobile_number")
	public WebElement txtMobileNumber;

	@AndroidFindBy(id = "org.intelehealth.app:id/textView_selectedCountry")
	public WebElement rdoBtnCountryCode;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_enter_mobileno")
	public WebElement lblEnterVerificationCode;
	@AndroidFindBy(id = "org.intelehealth.app:id/textview_mobile_no_note")
	public WebElement lblEnterVerificationCodeDescription;

	@AndroidFindBy(id = "org.intelehealth.app:id/textview_no_otp")
	public WebElement btnResendOTP;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_help_info_otp")
	public WebElement icnHelpInOTPVerification;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_verify_otp")
	public WebElement btnCOntinueInOTPVerification;

	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'org.intelehealth.app:id/et_pin')]")
	public List<WebElement> lstTxtOTPFields;

	public ForgotPasswordPage() throws IOException {
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

	// Assertion for lblForgotPassword
	public boolean isForgotPasswordLabelDisplayed() throws InterruptedException {
		return isDisplayed(lblForgotPassword, "Forgot Password label is displayed");
	}

	// Getter for lblForgotPassword
	public String getForgotPasswordLabelText() {
		return lblForgotPassword.getText();
	}

	// Assertion for lblForgotPasswordDescription
	public boolean isForgotPasswordDescriptionDisplayed() throws InterruptedException {
		return isDisplayed(lblForgotPasswordDescription, "Forgot Password description is displayed");
	}

	// Getter for lblForgotPasswordDescription
	public String getForgotPasswordDescriptionText() {
		return lblForgotPasswordDescription.getText();
	}

	// Action methods
	public void clickUsernameButton() throws InterruptedException {
		click(btnUsername, "Clicked Username button");
	}

	public void clickMobileNumberButton() throws InterruptedException {
		click(btnMobileNumber, "Clicked Mobile Number button");
	}

	public void enterUsername(String username) {
		sendKeys(txtUsername, username);
	}

	public void enterMobileNumber(String mobileNumber) {
		sendKeys(txtMobileNumber, mobileNumber);
	}

	public void clickContinueButton() throws InterruptedException {
		click(btnContinue, "Clicked Continue button");
	}
//is enabled mrthod for continue button can you write a method to check if the continue button is enabled or not?
	public boolean isContinueButtonEnabled() throws InterruptedException {
		return isEnabled(btnContinue, "Continue button is enabled");
	}
	public void clickCountryCode() throws InterruptedException {
		click(rdoBtnCountryCode, "Clicked Country Code");
	}

	// Getter methods for input fields
	public String getUsernameFieldText() {
		return txtUsername.getText();
	}

	public String getMobileNumberFieldText() {
		return txtMobileNumber.getText();
	}

	public boolean isForgotPasswordImageDisplayed() throws InterruptedException {
		return isDisplayed(imgForgotPassword, "Forgot Password image is displayed");
	}

	public boolean isUsernameButtonDisplayed() throws InterruptedException {
		return isDisplayed(btnUsername, "Username button is displayed");
	}

	public boolean isMobileNumberButtonDisplayed() throws InterruptedException {
		return isDisplayed(btnMobileNumber, "Mobile Number button is displayed");
	}

	public boolean isContinueButtonDisplayed() throws InterruptedException {
		return isDisplayed(btnContinue, "Continue button is displayed");
	}
	public boolean isContinueButtonInOTPVerificationDisplayed() throws InterruptedException {
		return isDisplayed(btnCOntinueInOTPVerification, "Continue button is displayed");
	}
	public boolean isCountryCodeDisplayed() throws InterruptedException {
		return isDisplayed(rdoBtnCountryCode, "Country code is displayed");
	}

	// Assertion for lblEnterVerificationCode
	public boolean isEnterVerificationCodeLabelDisplayed() throws InterruptedException {
		return isDisplayed(lblEnterVerificationCode, "Enter Verification Code label is displayed");
	}

	// Getter for lblEnterVerificationCode
	public String getEnterVerificationCodeLabelText() {
		return lblEnterVerificationCode.getText();
	}

	// Assertion for lblEnterVerificationCodeDescription
	public boolean isEnterVerificationCodeDescriptionDisplayed() throws InterruptedException {
		return isDisplayed(lblEnterVerificationCodeDescription, "Enter Verification Code description is displayed");
	}

	// Getter for lblEnterVerificationCodeDescription
	public String getEnterVerificationCodeDescriptionText() {
		return lblEnterVerificationCodeDescription.getText();
	}

	// Assertion for btnResendOTP
	public boolean isResendOTPButtonDisplayed() throws InterruptedException {
		return isDisplayed(btnResendOTP, "Resend OTP button is displayed");
	}

	// Assertion for icnHelpInOTPVerification
	public boolean isHelpIconInOTPVerificationDisplayed() throws InterruptedException {
		return isDisplayed(icnHelpInOTPVerification, "Help icon in OTP Verification is displayed");
	}

	// Assertion for btnCOntinueInOTPVerification
	public boolean isContinueInOTPVerificationButtonDisplayed() throws InterruptedException {
		return isDisplayed(btnCOntinueInOTPVerification, "Continue button in OTP Verification is displayed");
	}

	// Assertion for lstTxtOTPFields (checks if all OTP fields are displayed)
	public boolean areAllOTPFieldsDisplayed() throws InterruptedException {
		// Taken the size of lstTxtOTPFields as 6 based on the 6 OTP digit fields.
		for (int i = 0; i < 6; i++) {
			if (!isDisplayed(lstTxtOTPFields.get(i), i+" OTP field is displayed")) {
				return false;
			}
		}
		return true;
	}

}
