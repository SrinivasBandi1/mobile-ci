package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class AchievementsPage extends BaseTest {
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement lblachievementsHeader;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_level")
	private WebElement achievementsLevel;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Overall']")
	private WebElement tabOverall;
	
	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Daily']")
	private WebElement tabDaily;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Date Range']")
	private WebElement tabDateRange;

	

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_start_date")
	private WebElement icnStartDate;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"1\")")
	private WebElement datePickerDay1;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement btnOkayInCalendar;	
	
	
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_end_date")
	private WebElement icnEndDate;

	JSONObject appData;

	public AchievementsPage() throws IOException {
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
	
	public void clickOverallTab() {
		click(tabOverall);
	}
	public void clickDailyTab() {
		click(tabDaily);
	}
	public void clickDateRangeTab() {
		click(tabDateRange);
	}
	public void clickStartDate() {
		click(icnStartDate);
	}
	public void clickEndDate() {
		click(icnEndDate);
	}
	public void selectDateFromCalendar() {
		click(datePickerDay1);
		click(btnOkayInCalendar);
	}
	public String getAchievementsHeaderText() {
		return getText(lblachievementsHeader, "Achievements header is displayed");
	}
	public String getAchievementsLevelText() {
		return getText(achievementsLevel, "Achievements level is displayed");
	}
	
	public boolean clickTabAndVerifySelected(String tabName) {
	    String tabXpath = "//android.widget.TextView[@text='%s']";

	    String xpath = String.format(tabXpath, tabName);


	    WebElement tabElement = driver.get().findElement(By.xpath(xpath));

        // Click tab
        tabElement.click();

        // Return selected status
        return tabElement.getAttribute("selected").equalsIgnoreCase("true");	}

}
