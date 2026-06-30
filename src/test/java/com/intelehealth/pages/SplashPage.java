package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class SplashPage extends BaseTest {
	JSONObject appData;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivAppLogo")
	private WebElement logoIH;

	@AndroidFindBy(id = "org.intelehealth.app:id/txtProgressTitle")
	private WebElement hwWelcome;

	@AndroidFindBy(id = "org.intelehealth.app:id/txtProgressContent")
	private WebElement lblLoadingContentDescription;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/txtProgress")
	private WebElement loadingProgressPercentage;

	@AndroidFindBy(id = "org.intelehealth.app:id/txtProgressTask")
	private WebElement txtSyncing;
	
	
	
	
	

	public SplashPage() throws IOException {
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
	
	
	public boolean isLogoDisplayed() {
		return isDisplayed(logoIH);
	}
	
	public String isWelcomeTextDisplayed() {
		return getText(hwWelcome, "welcomeText");
	}
	public String isLoadingContentDescriptionDisplayed() {
		return getText(lblLoadingContentDescription, "loadingContentDescription");
	}
	public boolean isLoadingProgressPercentageDisplayed() {
		return isDisplayed(loadingProgressPercentage, "loadingProgressPercentage");
	}
	
	public String getSyncingText() {
		return getText(txtSyncing, "txtSyncing");
	}
}
