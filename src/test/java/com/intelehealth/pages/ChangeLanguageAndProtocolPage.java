package com.intelehealth.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChangeLanguageAndProtocolPage extends BaseTest {
	
	@AndroidFindBy(id = "org.intelehealth.app:id/lang_spinner_txt")
	private WebElement languageSpinner;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='org.intelehealth.app:id/rb_choose_language' and @text='हिंदी']")
	private WebElement hindi;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement btnYes;

	@AndroidFindBy(id = "org.intelehealth.app:id/negative_btn")
	private WebElement btnNo;

	@AndroidFindBy(id = "org.intelehealth.app:id/iv_hamburger")
	private WebElement mnuHamburger;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/reset_btn")
	private WebElement btnReset;

	// android.widget.CheckedTextView[@text='Change language & protocol']
//
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Change language & protocol']")
	private WebElement changeLangProtocol;
//
	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement changeLangTitleInDialog;
	

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_subtitle")
	private WebElement changeLangSubTitleInDialog;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement icnSync;

	@AndroidFindBy(id = "org.intelehealth.app:id/lang_spinner_txt")
	private WebElement txtSelectedLanguage;

	@AndroidFindBy(id = "org.intelehealth.app:id/update_protocols_btn")
	private WebElement btnUpdateProtocolInChangeLanguage;

	@AndroidFindBy(id = "org.intelehealth.app:id/licenseurl")
	private WebElement txtServerURL;

	@AndroidFindBy(id = "org.intelehealth.app:id/licensekey")
	private WebElement txtLicenseKey;

	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement btnUpdateProtocol;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and @text='Settings']")
	private WebElement lftPnlSettings;


	@AndroidFindBy(id = "org.intelehealth.app:id/refresh")
	private WebElement icnSyncInChangeLanguage;
	
//	@AndroidFindBy(accessibility = )
//	private WebElement languageSpinner;

	/**
	 * Updated by @SrinivasBandi
	 * @throws InterruptedException
	 */
	public void verifyUserCanSelectLanguage() throws InterruptedException {
		click(mnuHamburger, "Clicked on Hamburger Menu");
		Thread.sleep(2000);
		click(changeLangProtocol, "Clicked on Change Language & Protocol");
		click(languageSpinner);
		click(hindi);
	}

	/**
	 * @author @SrinivasBandi
	 * @throws InterruptedException
	 */
	public void navigateToChangeLanguageScreen() {
		click(mnuHamburger, "Clicked on Hamburger Menu");
		click(lftPnlSettings, "\"Clicked on Settings");
		//click(changeLangProtocol, "Clicked on Change Language & Protocol");
	}

	public void selectHindiLanguage() {
		click(languageSpinner);
		click(hindi);
	}

	public List<String> getchangeLanguageConfirmationDialogueLabels() {
		return getElementsText(changeLangSubTitleInDialog, changeLangTitleInDialog, btnYes, btnNo);
	}

	public void clickOnYes() {
		click(btnYes);
	}

	public void clickOnSync() {
		click(icnSync);
	}
	public void clickOnSyncInChangeLanguage() {
		click(icnSyncInChangeLanguage);
	}
	
	public void clickOnResetButton() {
		click(btnReset);
	}

	public String selectedLanguageTextInDropdown() {
		return txtSelectedLanguage.getText();
	}

	public void clickOnUpdateProtocolInChangeLanguage() {
		click(btnUpdateProtocolInChangeLanguage);
	}

	public void enterServerURL(String serverURL) throws InterruptedException {
		sendKeys(txtLicenseKey, serverURL);
	}

	public void enterLicenseKey(String licenseKey) throws InterruptedException {
		sendKeys(txtLicenseKey, licenseKey);
	}

	public void clickOnUpdateProtocolButton() {
		click(btnUpdateProtocol);
	}

}