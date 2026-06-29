package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class FollowUpVisitPage extends BaseTest {
	JSONObject appData;

	@AndroidFindBy(id = "org.intelehealth.app:id/toolbar_title")
	private WebElement followupTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/searchview_received")
	private WebElement inpSearchPatient;

	@AndroidFindBy(id = "org.intelehealth.app:id/filter_im")
	private WebElement icnFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/sort_im")
	private WebElement icnSort;

	@AndroidFindBy(id = "org.intelehealth.app:id/today_title")
	private WebElement lblToday;

	@AndroidFindBy(id = "org.intelehealth.app:id/tomorrow_title")
	private WebElement lblTomorrow;

	@AndroidFindBy(id = "org.intelehealth.app:id/others_title")
	private WebElement lblOthers;

	@AndroidFindBy(id = "org.intelehealth.app:id/today_nodata")
	private WebElement lblTodayNoData;

	@AndroidFindBy(id = "org.intelehealth.app:id/week_nodata")
	private WebElement lblTomorrowNoData;

	@AndroidFindBy(id = "org.intelehealth.app:id/fu_patname_txtview")
	private WebElement lstPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/openmrs_id_tv")
	private WebElement lstPatientID;

	@AndroidFindBy(id = "org.intelehealth.app:id/search_gender")
	private WebElement lstPatientGender;

	@AndroidFindBy(id = "org.intelehealth.app:id/fu_date_txtview")
	private WebElement lstPatientFollowupDate;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_date")
	private WebElement btnDateInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/button_range")
	private WebElement btnDateRangeInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_date")
	private WebElement inpDateInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/apply_date_bt")
	private WebElement btnApplyDateInFilter;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"android:id/month_view\"]//android.view.View")
	private WebElement datePickerDayOneInFilter;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement btnOkayInCalendarInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/month_nodata")
	private WebElement lblNoFollowUpDataAfterApplyingDateFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_start_date")
	private WebElement inpStartDateInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_end_date")
	private WebElement inpEndDateInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/apply_range_bt")
	private WebElement btnApplyDateRangeInFilter;

	@AndroidFindBy(id = "org.intelehealth.app:id/label")
	private WebElement hdrPatientDetails;

	@AndroidFindBy(id = "org.intelehealth.app:id/name_txtview")
	private WebElement lblPatientNameInDetails;

	public FollowUpVisitPage() throws IOException {
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

//give me the action methods for the above elements
//write single method and get list of strings from this methods	getFollowUpVisitTitle,getTodayLabel,getTomorrowLabel,getOthersLabel,getTodayNoDataMessage,getTomorrowNoDataMessage,getFirstPatientNameInList,getFirstPatientIDInList,getFirstPatientGenderInList,getFirstPatientFollowupDateInList,
	public List<String> getFollowUpVisitDetails() {
		List<String> details = new ArrayList<>();
		details.add(getText(lblToday, "lblToday"));
		details.add(getText(lblTomorrow, "lblTomorrow"));
		details.add(getText(lblOthers, "lblOthers"));

		return details;
	}

	public boolean isDisplayedFollowUpVisitDetails(String followupTitles) {
		return getText(followupTitle, "").contains(followupTitles) && isDisplayed(lstPatientName)
				&& isDisplayed(lstPatientID) && isDisplayed(lstPatientGender) && isDisplayed(lstPatientFollowupDate)
				&& isDisplayed(icnFilter) && isDisplayed(icnSort);
	}

	public String getFollowUpVisitTitle() {
		return getText(followupTitle, "followupTitle");
	}

	public void enterSearchPatient(String patientName) {
		sendKeys(inpSearchPatient, patientName, "inpSearchPatient");
		inpSearchPatient.sendKeys(Keys.ENTER);
	}

	public void clickFilterIcon() {
		click(icnFilter, "icnFilter");
	}

	public void clickSortIcon() {
		click(icnSort, "icnSort");
	}

	public String getTodayLabel() {
		return getText(lblToday, "lblToday");
	}

	public String getTomorrowLabel() {
		return getText(lblTomorrow, "lblTomorrow");
	}

	public String getOthersLabel() {
		return getText(lblOthers, "lblOthers");
	}

	public String getTodayNoDataMessage() {
		return getText(lblTodayNoData, "lblTodayNoData");
	}

	public String getTomorrowNoDataMessage() {
		return getText(lblTomorrowNoData, "lblTomorrowNoData");
	}

	public String getFirstPatientNameInList() {
		return getText(lstPatientName, "lstPatientName");
	}

	public String getFirstPatientIDInList() {
		return getText(lstPatientID, "lstPatientID");
	}

	public String getFirstPatientGenderInList() {
		return getText(lstPatientGender, "lstPatientGender");
	}

	public String getFirstPatientFollowupDateInList() {
		try {
			return getText(lstPatientFollowupDate, "lstPatientFollowupDate");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public boolean isNoFollowUpDataMessageDisplayedAfterApplyingDateFilter() {
		return isDisplayed(lblNoFollowUpDataAfterApplyingDateFilter, "lblNoFollowUpDataAfterApplyingDateFilter");
	}

	public boolean areTheVisitsDisplayedAfterApplyingDateFilter(String selectedDate, String followupDateOnCard) {
		try {
			if (followupDateOnCard.contains(selectedDate)) {
				return true;
			} else {
				return isNoFollowUpDataMessageDisplayedAfterApplyingDateFilter();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return isNoFollowUpDataMessageDisplayedAfterApplyingDateFilter();
		}
	}

	public void searchFollowupvisitByname() {
		sendKeys(inpSearchPatient, getFirstPatientNameInList());
	}

	public void searchFollowupvisitByOpenMRSID() {
		sendKeys(inpSearchPatient, getFirstPatientIDInList());
	}

	public boolean isDisplayedDateFilterOptions() {
		return isDisplayed(btnDateInFilter) && isDisplayed(inpDateInFilter) && isDisplayed(btnApplyDateInFilter);
	}

	public boolean isDisplayedDateRangeFilterOptions() {
		return isDisplayed(btnDateRangeInFilter) && isDisplayed(inpStartDateInFilter) && isDisplayed(inpEndDateInFilter)
				&& isDisplayed(btnApplyDateRangeInFilter);
	}

	public void clickOnDateRangeFilterOption() {
		click(btnDateRangeInFilter, "btnDateRangeInFilter");
	}

	public void clickOnDateFilterOption() {
		click(btnDateInFilter, "btnDateInFilter");
	}

	public String selectDateFromCalendar() {
		click(inpDateInFilter, "inpDateInFilter");
		click(datePickerDayOneInFilter, "datePickerDayOneInFilter");
		click(btnOkayInCalendarInFilter, "btnOkayInCalendarInFilter");
		return getText(inpDateInFilter, "inpDateInFilter");
	}

	public void clickApplyDateInFilter() {
		click(btnApplyDateInFilter, "btnApplyDateInFilter");
	}

	public String selectStartDateFromCalendar() {
		click(inpStartDateInFilter, "inpStartDateInFilter");
		click(datePickerDayOneInFilter, "datePickerDayOneInFilter");
		click(btnOkayInCalendarInFilter, "btnOkayInCalendarInFilter");
		return getText(inpStartDateInFilter, "inpStartDateInFilter");
	}

	public String selectEndDateFromCalendar() {
		click(inpEndDateInFilter, "inpEndDateInFilter");
		click(datePickerDayOneInFilter, "datePickerDayOneInFilter");
		click(btnOkayInCalendarInFilter, "btnOkayInCalendarInFilter");
		return getText(inpEndDateInFilter, "inpEndDateInFilter");
	}

	public void clickApplyDateRangeInFilter() {
		click(btnApplyDateRangeInFilter, "btnApplyDateRangeInFilter");
	}

	public boolean isFollowupDateInSelectedRange(String startDate, String endDate) {
		try {
			String followupDate = getFirstPatientFollowupDateInList();
			// Assuming the date format is "dd MMM yyyy", you may need to adjust this based
			// on your actual date format
			if (followupDate.isEmpty()) {
				return isNoFollowUpDataMessageDisplayedAfterApplyingDateFilter();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			try {
				Date followup = sdf.parse(followupDate);
				Date start = sdf.parse(startDate);
				Date end = sdf.parse(endDate);
				return followup.compareTo(start) >= 0 && followup.compareTo(end) <= 0;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return isNoFollowUpDataMessageDisplayedAfterApplyingDateFilter();

		}
	}

	public void clickOnFirstPatientInList() {
		click(lstPatientName, "lstPatientName");
	}

	public String getPatientDetailsScreenHeader() {
		// Assuming the patient details screen has a unique element with id
		// "patient_details_title"
		return getText(hdrPatientDetails, "hdrPatientDetails");
	}

	public String getPatientNameFromPatientDetailsScreen() {
		return getText(lblPatientNameInDetails, "lblPatientNameInDetails");
	}
}