package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class HelpPage extends BaseTest {
	JSONObject appData;

	// Elements on the App Setup Page
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement helpHeader;

	// This needs to be changed
	@AndroidFindBy(xpath = "(//android.webkit.WebView[@resource-id=\"org.intelehealth.app:id/youtubeVideo_WV\"])[1]")
	private WebElement iconYoutubeVideo;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Frequently asked questions\"]")
	private WebElement lblFrequentlyAskedQuestions;

	public HelpPage() throws IOException {
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

	// write assertion helper methods here for the elements in the help page
	public String getHelpHeaderText() {
		return helpHeader.getText();
	}

	public boolean isYoutubeVideoIconAndFrequentlyAskedQuestionsDisplayed() {
		return isDisplayed(iconYoutubeVideo) && isDisplayed(lblFrequentlyAskedQuestions);
	}

}
