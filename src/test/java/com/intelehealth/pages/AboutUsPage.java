package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class AboutUsPage extends BaseTest {
	JSONObject appData;

	@AndroidFindBy(id = "org.intelehealth.app:id/label")
	private WebElement lblAboutUsHeader;

	@AndroidFindBy(id = "org.intelehealth.app:id/imgview_aboutus")
	private WebElement imageInAboutUs;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/info_link']")
	private WebElement lnkTermsAndConditions;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/goto_btn")
	private WebElement btnGoToWebsite;
	

	public AboutUsPage() throws IOException {
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
	
	public String getAboutUsHeaderText() {
		return lblAboutUsHeader.getText();
	}

	public boolean isAboutUsContentDisplayed() {
		return isDisplayed(imageInAboutUs)&& isDisplayed(lnkTermsAndConditions) && isDisplayed(btnGoToWebsite);
	}
	public void clickOnTermsAndConditionsLink() {
		click(lnkTermsAndConditions);
	}
	public void clickOnGoToWebsiteButton() {
		btnGoToWebsite.click();
	}
	
}
