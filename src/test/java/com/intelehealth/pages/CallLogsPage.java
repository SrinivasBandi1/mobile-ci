package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class CallLogsPage extends BaseTest {
	JSONObject appData;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call Logs']")
	private WebElement callLogsHeader;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='org.intelehealth.app:id/design_menu_item_text' and @text='Call Logs']")
	private WebElement callLogsMenuItem;

	public CallLogsPage() throws IOException {
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

	public void clickOnCallLogsMenuItem() {
		click(callLogsMenuItem);
	}

	public String getCallLogsHeaderText() {
		return getText(callLogsHeader, "Call Logs header is displayed");
	}

}
