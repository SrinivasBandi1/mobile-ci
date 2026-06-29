package com.intelehealth.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FindPatientPage extends BaseTest {
	
	// Page elements using AndroidFindBy annotation
	@AndroidFindBy(id = "org.intelehealth.app:id/textlayout_find_patient")
	private WebElement findPatients;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/search_txt_enter")
	private WebElement findPatientSearchBar;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/icon_search")
	private WebElement findPatientSearchIcon;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/search_name'])[1]")
	private WebElement automationOnePatient;

	@AndroidFindBy(id = "org.intelehealth.app:id/search_pat_not_found_txt")
	private WebElement patientNotFound;

	@AndroidFindBy(id = "org.intelehealth.app:id/search_pat_hint_txt")
	private WebElement noPatientFoundSubTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Add New Patient']")
	private WebElement addNewPatientCTAButton;

	@AndroidFindBy(xpath = "(//android.widget.FrameLayout[@resource-id='org.intelehealth.app:id/fu_cardview_item'])[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]")
	private WebElement lblPatientCard;
	
//	@AndroidFindBy(accessibility = "No Patient Found Placeholder Icon ImageView")
//	private WebElement findPatient;

	// Method to click on the "Find Patient" element
	public void clickOnFindPatient() {
		click(findPatients);
	}
	public void clickOnFirstPatientCard() {
		click(lblPatientCard);
	}


// Method to enter a value in the search box and press Enter
	public void enterValueInSearchBox(String txt) throws InterruptedException {
		Thread.sleep(4000);
//	sendKeys(findPatientSearchBar,"Automation Rana");
		click(findPatientSearchBar);
		
//   Thread.sleep(2000);
//executeCommand("adb shell input keyevent 84");
		 //sendKeys(findPatientSearchBar,((PressesKey) getDriver()).pressKey(new
		// KeyEvent(AndroidKey.ENTER)));

		// click(findPatientSearchBar);
		//String textToEnter = "AutomationRana";
		String adbCommand = String.format("adb shell input text %s", txt);
		executeCommand(adbCommand);
		executeCommand("adb shell input keyevent 66");
	//	executeCommand("adb shell input keyevent 84");
		findPatientSearchBar.sendKeys(txt + "\n");
		//getDriver().findElement(findPatientSearchBar).sendKeys(txt + "\n");
		new Actions(driver.get()).sendKeys(Keys.ENTER).perform();
		((PressesKey) getDriver()).pressKey(new
				KeyEvent(AndroidKey.ENTER));
//	//((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.TAB));
//	((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));

		// ((PressesKey) getDriver()).pressKey(new
		// KeyEvent().withKey(AndroidKey.SEARCH));
		// pressEnter(findPatientSearchBar);
	}

	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// Method to verify the presence of the searched patient
	public String verifySearchedPatient() {
			return automationOnePatient.getText();
	}
	
	public boolean isDisplayedSearchedPatient() {
		return isDisplayed(automationOnePatient, "Patient is Displayed");
		
		
	}
	

// Method to enter an invalid patient name in the search box and press Enter
	public void enterInvalidPatienTName(String txt) throws InterruptedException {
		sendKeys(findPatientSearchBar, txt);

	}

//Method to verify the elements when an invalid patient is searched
	public boolean verifyInvalidPatientSearch() {
		return isDisplayed(patientNotFound, "Patient not founf msg is displayed")
	&&	isDisplayed(noPatientFoundSubTitle, "")
		&& isDisplayed(addNewPatientCTAButton, "");	
	}

	public List<String> getInvalidPatientSearch() {
		return getElementsText(patientNotFound, noPatientFoundSubTitle, addNewPatientCTAButton);
	}
	
// Method to verify if the search box is visible
	public boolean verifySearchBoxIsVisible() throws InterruptedException {
	return	isDisplayed(findPatientSearchBar);
	}
}
