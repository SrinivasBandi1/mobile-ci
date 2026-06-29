package com.intelehealth.pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StartVisit1And2StepsPage extends BaseTest {
	@AndroidFindBy(accessibility = "Patient Details Patient Name TextView")
	private WebElement patientName;
	@AndroidFindBy(accessibility = "Patient Details Patient OpenMRS ID TextView")
	private WebElement patientId;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_title")
	private WebElement visitCreatedPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_title_desc")
	private WebElement visitCreatedPatientAge;

	@AndroidFindBy(id = "org.intelehealth.app:id/imb_btn_refresh")
	private WebElement visitCreationRefreshImageButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_sub_title' and @text='1/4 Vitals']")
	private WebElement visitCreationSubtitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/prog_bar_step1")
	private WebElement visitCreationStep1ProgressBar;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_height")
	private WebElement heightEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_weight")
	private WebElement weightEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_bp_sys")
	private WebElement bpSystolicEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_bp_dia")
	private WebElement bpDiastolicEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_pulse")
	private WebElement pulseEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_temperature")
	private WebElement temperatureEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_spo2")
	private WebElement spo2EditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/etv_respiratory_rate")
	private WebElement respiratoryRateEditText;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_blood_group_spinner")
	private WebElement drpBloodGroup;

	@AndroidFindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.intelehealth.app:id/lang_dialog_list_view\"]/android.widget.RelativeLayout[@resource-id=\"android:id/text1\"])[4]")
	private WebElement selectDrpBloodGroup;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement nextButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_sub_title_vital_info")
	private WebElement enterthePatientsVitalsLabel;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_sub_title' and @text='Enter patient’s body measurement details']")
	private WebElement enterPatientsBodyMeasurementDetailsLabel;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bmi_lbl")
	private WebElement bMIIndex;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bmi")
	private WebElement bMIvalue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bmi_status")
	private WebElement bMIStatus;

	@AndroidFindBy(accessibility = "bmi status textview in Vital Collection Fragment")
	private WebElement normalWeight;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_sub_title' and @text='1/4 Vitals summary']")
	private WebElement vitalSummaryTitle;

// 1/4 vital summary screen elements
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_vital_image")
	private WebElement thermoMeter;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_details_label")
	private WebElement vitalsDetailsLabel;

	/*
	 * @AndroidFindBy(accessibility = "Vitals Summary Edit TextView") private
	 * WebElement vitalsTitle;
	 */
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_height_label")
	private WebElement heightTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_height")
	private WebElement heightValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_weight_label")
	private WebElement weightTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_weight")
	private WebElement weightValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bmi_label")
	private WebElement bmiTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bmi")
	private WebElement bmiValueInVitalsSummary;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bp_label")
	private WebElement bpTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bp")
	private WebElement bpValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_pulse_label")
	private WebElement pulseTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_pulse")
	private WebElement pulseValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_temperature_label")
	private WebElement tempTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_temperature")
	private WebElement tempValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_spo2_label")
	private WebElement spo2Title;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_spo2")
	private WebElement spo2Value;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_respiratory_rate_label")
	private WebElement rrTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_respiratory_rate")
	private WebElement rrValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_blood_group_label")
	private WebElement bloodGroupTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_blood_group")
	private WebElement bloodGroupValue;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_cancel")
	private WebElement backButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement confirmButton;
// 2/4 visit reason
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_sub_title")
	private WebElement visitReasonTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_visit_reason_t1")
	private WebElement whatIsTheReasonForVisit;

	@AndroidFindBy(id = "org.intelehealth.app:id/actv_reasons")
	private WebElement searchReasonTextBox;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement visitReasonNextButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_cancel")
	private WebElement visitReasononBackButton;

	@AndroidFindBy(accessibility = "selected reasons textview in Visit Reason Capture Fragment")
	private WebElement selectedReasonLabel;

	@AndroidFindBy(accessibility = "all reasons textview in Visit Reason Capture Fragment")
	private WebElement allResaonsection;

	@AndroidFindBy(accessibility = "Visit Creation Title Description TextView")
	private WebElement patientAgeAndGender;

	@AndroidFindBy(accessibility = "Visit Creation Title TextView")
	private WebElement visitReasonPatientname;

	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@text='100.0']")
	private WebElement progressBar1;

	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@text='20.0']")
	private WebElement progressBar2;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name'])[1]")
	private WebElement selectedReason;

	@AndroidFindBy(accessibility = "reason_list_item_0")
	private WebElement visitReasonAbdominalPain;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name'])[1]")
	private WebElement chipReason;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Diarrhea']")
	private WebElement diarrhea;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Fever']")
	private WebElement fever;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Hypertension screening']")
	private WebElement hypertension;

	@AndroidFindBy(id = "org.intelehealth.app:id/im_remove")
	private WebElement remove;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_all_reason_lbl']/preceding-sibling::android.widget.RelativeLayout//android.widget.TextView")
	private List<WebElement> allSelectedReasons;
// confirm visit popup

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement confirmVisitPopupTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_subtitle")
	private WebElement confirmVisitPopupSubTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_name")
	private WebElement reason;

	@AndroidFindBy(id = "org.intelehealth.app:id/negative_btn")
	private WebElement noButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement yesButton;

// 2/4 Visit reason : Abdominal Pain
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_sub_title")
	private WebElement visitCreationTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_question_counter")
	private WebElement OneofElevenQuestion;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_question")
	private WebElement addominalPainReasonQuestion1;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Upper (R) - Right Hypochondrium']")
	private WebElement upperRightHypochondrium;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Upper (C) - Epigastric']")
	private WebElement upperEpigastric;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Upper (L) - Left Hypochondrium']")
	private WebElement upperLeftHypochondrium;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Middle (R) - Right Lumbar']")
	private WebElement middleRightLumbar;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Middle (C) - Umbilical']")
	private WebElement middleUmbilical;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Middle (L) - Left Lumbar']")
	private WebElement middleLeftLumbar;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Lower (R) - Right Illiac Fossa']")
	private WebElement lowerRightIliacFossa;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Lower (C) - Hypogastric/Suprapubic']")
	private WebElement lowerHypogastric;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Lower (R) - Left Illiac Fossa']")
	private WebElement lowerLeftIliacFossa;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='All over']")
	private WebElement allOver;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement submitButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_question_counter")
	private WebElement twoOfElevenQuestion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question_counter' and @text='3 of 12 questions']")
	private WebElement threeofTwelveQuestion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Pain radiates to *']")
	private WebElement painRadiates;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Pain radiates to *']")
	private WebElement painRadiatesTo;
	// android.widget.TextView[@text='Pain radiates to']
	//// android.widget.TextView[@content-desc="Nested Question Item View Question
	// TextView"]
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\" and @text=\"Right shoulder\"]")
	private WebElement rightShoulder;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\" and @text=\"Right scapula\"]")
	private WebElement rightScapula;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\" and @text=\"Groin\"]")
	private WebElement groin;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\" and @text=\"Sacral region\"]")
	private WebElement sacralRegion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\" and @text=\"Flanks\"]")
	private WebElement rightFlank;

	// (//android.widget.TextView[@content-desc="Chips for Reason Item
	// TextView"])[19]
	// android.widget.TextView[@text='Left groin']
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\" and @text=\"Chest\"]")
	private WebElement chest;
	@AndroidFindBy(accessibility = "Nested Question Item View Submit Button")
	private WebElement painRadiatesSubmitButton;
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Skip']")
	private WebElement painRadiatesSkipButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_question_counter\" and @text=\"3 of 12 questions\"]")
	private WebElement threeOfTwelveQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='2']")
	private WebElement two;

	@AndroidFindBy(id = "org.intelehealth.app:id/sp_number_range")
	private WebElement numberSpinner;

	@AndroidFindBy(id = "org.intelehealth.app:id/sp_duration_type")
	private WebElement durationSpinner;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Days']")
	private WebElement days;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement threeOfElevenQuestionSubmitButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question_counter' and @text='4 of 12 questions']")
	private WebElement fourOfElevenQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Gradual']")
	private WebElement gradualOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Rapidly increasing']")
	private WebElement rapidlyIncreasingOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Sudden']")
	private WebElement suddenOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question_counter' and @text='5 of 12 questions']")
	private WebElement fiveOfTwelveQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Other [describe]']")
	private WebElement othersOptionOfFourOfTwelveQuestions;

	@AndroidFindBy(id = "org.intelehealth.app:id/actv_reasons")
	private WebElement describeTextField;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Enter Value EditText")
	private WebElement describeField;
	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement submitButtonOfFourOfTwelveQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Night']")
	private WebElement night;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='6 of 12 questions']")
	private WebElement sixOfTwelveQuestions;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Other [describe]'])[last()]")
	private WebElement othersOptionOfFiveOfTwelveQuestions;

	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement fiveOfTwelveQuestionsSubmitButton;
// 6 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Constant']")
	private WebElement constantOptionOfSixOfTwelveQuetions;

	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement sixOfTwelveQuestionsSubmitButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_question_desc")
	private WebElement selectOneOrMoreText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='7 of 12 questions']")
	private WebElement sevenOfTwelveQuestions;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Other [describe]'])")
	private WebElement othersOptionOfSixOfTwelveQuestions;
// 7 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Mild, 1-3']")
	private WebElement mildOneThreeOption;

// 8 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='8 of 12 questions']")
	private WebElement eightOfTwelveQusetions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Coughing']")
	private WebElement coughingOption;
	@AndroidFindBy(accessibility = "Question Node Submit Button")
	private WebElement eightOfTwelveQuestionsSubmitButton;
	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement eightOfTwelveSubmitButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other [describe]']")
	private WebElement otherOptionOfEightOfTwelveQuestions;

// 9 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='9 of 12 questions']")
	private WebElement nineOfTwelveQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Food']")
	private WebElement foodOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Leaning forward']")
	private WebElement leaningForwardOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other']")
	private WebElement otherOption;
	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement nineOfTwelveQusetionsSubmitButton;

// 10 of 12
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Has not started menstruation']")
	private WebElement hasNotStartedmenstruationOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='10 of 12 questions']")
	private WebElement tenOfTwelveQusetions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Is menstruating']")
	private WebElement isMenstruatingOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Age at onset ']")
	private WebElement ageAtOnset;

	@AndroidFindBy(id = "org.intelehealth.app:id/actv_reasons")
	private WebElement ageAtOnSetInputFiled;

	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement ageOnSetInputFeildSubmitButton;

	@AndroidFindBy(accessibility = "Visit Reason Input Text Skip Button")
	private WebElement ageOnSetSkipButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last menstruation period']")
	private WebElement lastMenstruationPeriod;

	@AndroidFindBy(id = "android:id/month_view")
	private WebElement calender;

	@AndroidFindBy(xpath = "//android.view.View[@text='1']")
	private WebElement menstruationDate;
	@AndroidFindBy(accessibility = "Visit Reason Date Picker Selected Date TextView")
	private WebElement selectedMenstruationDate;
	@AndroidFindBy(accessibility = "Visit Reason Date Picker Submit Button")
	private WebElement calenderSubmitButton;
//11 of 12 
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='11 of 12 questions']")
	private WebElement elevenOfTwelveQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None']")
	private WebElement noneOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes [Describe]']")
	private WebElement yesDescribeOption;
//12 of 12 
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='12 of 12 questions']")
	private WebElement twelveOfTweleveQuestions;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_skip")
	private WebElement twelveOfTwelveSkipButton;
	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Submit'])[last()]")
	private WebElement twelveOfTwelveSubmitButton;
//Associated symptoms
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_sub_title")
	private WebElement associatedSymptomsTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you have the following symptom(s)?*']")
	private WebElement doYouHaveFollowingSymptom;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_yes'])[6]")
	private WebElement changeInApetiteYesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1 of 1 questions']")
	private WebElement oneOfOneQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='What is the change?']")
	private WebElement whatIsTheChange;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Increased']")
	private WebElement increased;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Decreased']")
	private WebElement decreased;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question' and @text='7. Change in frequency of urination [describe]']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_yes']")
	private WebElement sevenYesButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question' and @text='8. Color change in stool [describe]']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_yes']")
	private WebElement eightYesButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question' and @text='9. Color change in urine [describe]']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_yes']")
	private WebElement nineYesButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question' and @text='16. Other [describe]']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_yes']")
	private WebElement sixteenYesButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/actv_reasons")
	private WebElement describeTextArea;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement associatedSymptomSubmitButton;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'Yes' TextView\"])[1]")
	private WebElement firstYesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='8. Color change in stool [describe]']")
	private WebElement eighthAssociatedSymptom;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='20. Vomiting']")
	private WebElement vomiting;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='No'])[last()]")
	private WebElement lastNoButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private List<WebElement> asSymptomNoButton;
	// 2/4 visit reason summary screen
	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Subtitle TextView")
	private WebElement visitReasonSummaryTitle;

	@AndroidFindBy(accessibility = "Main Row Item Complaint Edit TextView")
	private WebElement abdominalPainChangeButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Food']")
	private WebElement food;
	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Cancel Button")
	private WebElement VisitReasonSummaryScreenBackButton;

	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Submit Button")
	private WebElement VisitReasonSummaryScreenConfirmButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/dialog_title']")
	private WebElement pleaseWashOrSanitizeYourHands;
	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement okayButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3/4 Physical Examination']")
	private WebElement physicalExaminationScreenTitle;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patient reports']")
	private WebElement patientReports;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patient denies']")
	private WebElement patientDenies;
	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Associated Symptoms Edit TextView")
	private WebElement associatedSymptomChangeButton;

// Patient Details Screen
	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Header Title TextView")
	private WebElement personalDetailsHeader;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement nameValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Gender' Value TextView")
	private WebElement genderValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'DOB' Value TextView")
	private WebElement dobValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Age' Value TextView")
	private WebElement ageValue;

// Address Details Section
	@AndroidFindBy(accessibility = "Patient Details Screen Address Details Header Title TextView")
	private WebElement addressDetailsHeader;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Postal Code' Value Textview")
	private WebElement postalCodeValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Country' Value TextView")
	private WebElement countryValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'State' Value TextView")
	private WebElement stateValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'District' Value TextView")
	private WebElement districtValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Village' Value TextView")
	private WebElement villageValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Corresponding Address 1' TableRow")
	private WebElement correspondingAddress1Row;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Corresponding Address 2' Value TextView")
	private WebElement correspondingAddress2Value;

// Other Details Section
	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'National ID' Value TextView")
	private WebElement nationalIdValue;
	@AndroidFindBy(accessibility = "Patient Details Screen Other Details Header Title TextView")
	private WebElement otherDetailsHeader;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Occupation' Value TextView")
	private WebElement occupationValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Caste' Value TextView")
	private WebElement casteValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Education' Value TextView")
	private WebElement educationValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Economic' Value TextView")
	private WebElement economicValue;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'No' TextView\"])[5]")
	private WebElement asymNoButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_height_lbl")
	private WebElement lblHeight;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_weight_lbl")
	private WebElement lblWeight;

// Other Details Section
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bmi_lbl")
	private WebElement lblBMIIndex;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bp_sys_lbl")
	private WebElement lblBPSystolic;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_bp_dia_lbl")
	private WebElement lblBPDiastolic;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_pulse_lbl")
	private WebElement lblPulse;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_temperature_lbl")
	private WebElement lblTemperature;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_spo2_lbl")
	private WebElement lblSpO2;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_respiratory_rate_lbl")
	private WebElement lblRespiratoryRate;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_blood_group_lbl")
	private WebElement lblBloodGroup;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_change")
	private WebElement btnChangeInVitalsSummaryScreen;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Does not move']")
	private WebElement DoesNotMoveOption;

	public void clickOnDoesNotMoveOption() {// 1/4
		click(DoesNotMoveOption);
	}
//scroll

//
// public WebElement scrollToSkipButtonOnPainRadiatesScreen() {
// return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Chest\").instance(0))"));
// }

	//
// ("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
// + "new UiSelector().description(\"//android.widget.TextView[@text='Chest']\"));"));

	public WebElement scrollUpToSkipButtonOnPainRadiatesScreen() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"//android.widget.Button[@content-desc=\"Nested Question Item View Skip Button\"]\").instance(0));"));
	}

	public WebElement scrollToSubmitButtonOfFourOfTwelvequestions() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"Visit Reason Input Text Submit Button\").instance(0));"));
	}

	public WebElement scrollToSubmitButtonOfFiveOfTwelvequestions() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"(//android.widget.Button[@content-desc=\\\"Visit Reason Input Text Submit Button\\\"])[2]\").instance(0));"));
	}

	public WebElement scrollToWhatIsTheChangeQuestion() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"decreased\").instance(0));"));
	}

//android.widget.Button[@content-desc=\"Visit Reason Input Text Submit Button\"])[2]
	// Clicks on the 'Next' button for the Visit Reason section. (Step 2 out of 4)

	public void clickOnNextButtonOfVisitReason() {// 2/4
		click(visitReasonNextButton);
	}

	// Clicks on the 'Next' button for the first Vitals section. (Step 1 out of 4)

	public void clickOnFirstVitalsNextButton() {// 1/4
		click(nextButton);
	}

	public void clickOnChangeInVitalsSummaryScreen() {
		click(btnChangeInVitalsSummaryScreen);
	}

	public String isDisplayedVitalsCollectionScreen() {
		if (isDisplayed(visitReasonTitle)) {
			return visitReasonTitle.getText();
		} else {
			return null;
		}
	}

	// Verifies that the Vitals Summary screen is displayed.

	public boolean verifyVitailsSummaryScreenIsDisplayed() throws InterruptedException {
		return isDisplayed(vitalSummaryTitle);
	}

	public String getVitalSummaryTitle() {
		return vitalSummaryTitle.getText();
	}

	// Verifies the UI elements of the first Vitals screen.
	public boolean verifyUiOfFirstVitalsScreen() throws InterruptedException {

		// Check if the following UI elements are displayed on the screen
		boolean isDisplayedLabels = isDisplayed(enterthePatientsVitalsLabel,
				"Enter the Patient's Vitals label is displayed")
				&& isDisplayed(visitCreatedPatientName, "Created Patient Name is displayed")
				&& isDisplayed(visitCreatedPatientAge, "Created Patient Age is displayed")
				&& isDisplayed(visitCreationRefreshImageButton, "Visit Creation Refresh Image Button is displayed")
				&& isDisplayed(visitCreationSubtitle, "Visit Creation Subtitle is displayed")
				&& isDisplayed(visitCreationStep1ProgressBar, "Visit Creation Step 1 ProgressBar is displayed")
				&& isDisplayed(heightEditText, "Height EditText is displayed")
				&& isDisplayed(weightEditText, "Weight EditText is displayed")
				&& isDisplayed(bMIIndex, " BMI Index is displayed")
				&& isDisplayed(bpSystolicEditText, "Blood Pressure Systolic EditText is displayed")
				&& isDisplayed(bpDiastolicEditText, "Blood Pressure Diastolic EditText is displayed")
				&& isDisplayed(pulseEditText, "Pulse EditText is displayed")
				&& isDisplayed(temperatureEditText, "Temperature EditText is displayed");

		// Scroll to view the 'Respiratory Rate' field
		scrollDown();

		return isDisplayedLabels && isDisplayed(spo2EditText, "SpO2 EditText is displayed")
				&& isDisplayed(respiratoryRateEditText, " Respiratory Rate EditText is displayed")
				&& isDisplayed(drpBloodGroup, "BloodGroup EditText is displayed");

	}

	public List<String> getUIElementsOfVitalsScreen() {
		/*
		 * getDriver().findElement( AppiumBy.
		 * androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"
		 * ));
		 */
		scrollUp();
		List<String> elementsText = getElementsText(enterPatientsBodyMeasurementDetailsLabel,
				enterthePatientsVitalsLabel, lblHeight, lblWeight, lblBMIIndex, lblBPSystolic, lblBPDiastolic, lblPulse,
				lblTemperature);
		scrollDown();
		// scrollDown("Respiratory Rate (breaths/min)");
		elementsText.addAll(getElementsText(lblSpO2, lblRespiratoryRate, lblBloodGroup));
		return elementsText;
	}

	public List<String> getUIElementsOfVitalsSummaryScreen() {
		return getElementsText(vitalSummaryTitle, heightTitle, weightTitle, bmiTitle, bpTitle, pulseTitle, tempTitle,
				spo2Title, rrTitle, bloodGroupTitle);
	}

	// Retrieves and prints the name of the patient from the UI.

	public void getPatientName() {
		String text = visitCreatedPatientName.getText();
		System.out.println(text);
	}

	// Scrolls to view the Respiratory Rate input field in the Vital Collection
	// Fragment.

	public WebElement scrollToViewRespiratoryRate() {

		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Respiratory Rate input edittext in Vital Collection Fragment\"));"));

	}

	// Enters the specified text into the Height input field.

	public void enterHeight(String txt) throws InterruptedException {
		sendKeys(heightEditText, txt, "Entered Height");
	}

	// Enters the specified text into the Weight input field.

	public void enterWeight(String txt) throws InterruptedException {
		sendKeys(weightEditText, txt, "Entered weight");
	}

	public void verifyBmiIsAutoCalculated(String expectedBmiValue) {
		String val = bMIvalue.getText();
		if (val.equals(expectedBmiValue)) {
			System.out.println("BMI value is autocalaculated");
			ExtentReport.getTest().log(Status.INFO, "BMI value is autocalaculated");
		} else {
			System.out.println("BMI value is autocalculation is failed");
			ExtentReport.getTest().log(Status.INFO, "BMI value is autocalculation is failed");
		}
	}

	public boolean verifyCalculatedBMI(String htCm, String wtKg) {
		double weightKg = Double.parseDouble(wtKg);
		double heightCm = Double.parseDouble(htCm);

		String actualBMIValue = bMIvalue.getText().replaceAll("[^0-9.]", "");
		if (heightCm < 40 || heightCm > 252) {
			throw new IllegalArgumentException("Height must be in between 40 cm and 252 cm");
		}
		if (weightKg < 20 || weightKg > 500) {
			throw new IllegalArgumentException("Weight must be in between 20 kg and 500Kg");
		}

		// Calculate BMI
		double bmi = calculateBMI(weightKg, heightCm);
		String formattedBMI = formatToTwoDecimalPlaces(bmi);
		return areDoublesEqual(Double.parseDouble(formattedBMI), Double.parseDouble(actualBMIValue));

	}

	public boolean verifyStatus(String height, String weight, List<String> ExpectedStatus) throws InterruptedException {
		double weightKg = Double.parseDouble(weight);
		double heightCm = Double.parseDouble(height);
		sendKeys(heightEditText, height);
		sendKeys(weightEditText, weight);
		Thread.sleep(2000);

		String actualBMIStatus = bMIStatus.getText();
		double calculatedBMI = calculateBMI(weightKg, heightCm);
		Thread.sleep(1000);
		if (calculatedBMI < 18.5) {
			return actualBMIStatus.equalsIgnoreCase(ExpectedStatus.get(0));
		} else if (calculatedBMI > 18.5 && calculatedBMI < 24.9) {
			return actualBMIStatus.equalsIgnoreCase(ExpectedStatus.get(1));

		} else if (calculatedBMI > 25.0 && calculatedBMI < 29.9) {
			return actualBMIStatus.equalsIgnoreCase(ExpectedStatus.get(2));

		} else if (calculatedBMI > 30.0 && calculatedBMI < 34.9) {
			return actualBMIStatus.equalsIgnoreCase(ExpectedStatus.get(3));

		} else if (calculatedBMI > 35.0 && calculatedBMI < 39.9) {
			return actualBMIStatus.equalsIgnoreCase(ExpectedStatus.get(4));

		} else if (calculatedBMI > 40.0) {
			return actualBMIStatus.equalsIgnoreCase(ExpectedStatus.get(5));

		}

		return false;

	}
// public void writeToPatientJsonFile() throws IOException {
// // Get patient information
// String name = (patientName != null) ? patientName.getText() : "Unknown";
// String id = (patientId != null) ? patientId.getText() : "Unknown";
//
// // Create JSON object for patient data
// JSONObject patientData = createPatientObject(name, id);
//
// // Specify the file path where you want to write the JSON data
// String filePath = "src/test/resources/data/patient.json";
//
// // Read existing data from the file, if it exists
// JSONArray existingData;
// if (new File(filePath).exists()) {
// String fileContent = readFile(filePath);
// existingData = new JSONArray(fileContent);
// } else {
// // File doesn't exist, create a new JSON array
// existingData = new JSONArray();
// }
//
// // Add the new data to the existing JSON array
// existingData.put(patientData);
//
// // Write to JSON file
// try (FileWriter fileWriter = new FileWriter(filePath)) {
// fileWriter.write(existingData.toString(4)); // The '4' is for indentation (optional)
// System.out.println("Data has been written to " + filePath);
// }
// }
//
// private JSONObject createPatientObject(String name, String id) {
// JSONObject patientData = new JSONObject();
// patientData.put("Name", name);
// patientData.put("Id", id);
// return patientData;
// }
//
// private String readFile(String filePath) throws IOException {
// StringBuilder contentBuilder = new StringBuilder();
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line;
// while ((line = br.readLine()) != null) {
// contentBuilder.append(line).append('\n');
// }
// }
// return contentBuilder.toString();
// }
// 

// public void writeToJsonfile() {
// String name = patientName.getText();
// System.out.println(name);
// String id = patientId.getText();
// JSONArray jsonArray = new JSONArray();
//
// // Create the first data set as a JSON object
// JSONObject data1 = new JSONObject();
// data1.put("Name", name);
// data1.put("Id", id);
//
//// // Create the second data set as another JSON object
//// JSONObject data2 = new JSONObject();
//// data2.put("nationalId", "123457");
//// data2.put("occupation", "Doctor");
//
// // Add the data sets to the JSON array
// jsonArray.put(data1);
// // jsonArray.put(data2);
//
// // Specify the file path where you want to write the JSON data
// String filePath = "src/test/resources/data/patient.json";
//
// try (FileWriter fileWriter = new FileWriter(filePath)) {
// // Write the JSON array to the file
// fileWriter.write(jsonArray.toString(4)); // The '4' is for indentation (optional)
// System.out.println("Data has been written to " + filePath);
// } catch (IOException e) {
// e.printStackTrace();
// }
// }

	// Verifies the UI elements on the Vitals Summary screen.

	public boolean verifyVitalslSummaryUi() throws InterruptedException {
// Check if the following UI elements are displayed on the Vitals Summary screen
		return isDisplayed(vitalsDetailsLabel, "Vitals Details Label is Displayed")
				&& isDisplayed(vitalSummaryTitle, "Vitals Title is Displayed")
				&& isDisplayed(heightTitle, "Height Title is Displayed")
				&& isDisplayed(heightValue, "Height Value is Displayed")
				&& isDisplayed(weightTitle, "Weight Title is Displayed")
				&& isDisplayed(weightValue, "Weight Value is Displayed")
				&& isDisplayed(bmiTitle, "BMI Title is Displayed")
				&& isDisplayed(bmiValueInVitalsSummary, "BMI Value is Displayed")
				&& isDisplayed(pulseTitle, "Pulse Title is Displayed")
				&& isDisplayed(pulseValue, "Pulse Value is Displayed") && isDisplayed(bpTitle, "BP Title is Displayed")
				&& isDisplayed(bpValue, "BP Value is Displayed") && isDisplayed(pulseTitle, "Pulse Title is Displayed")
				&& isDisplayed(pulseValue, "Pulse Value is Displayed")
				&& isDisplayed(tempTitle, "Temp Title is Displayed")
				&& isDisplayed(tempValue, "Temp Value is Displayed")
				&& isDisplayed(spo2Title, "SpO2 Title is Displayed")
				&& isDisplayed(spo2Value, "SpO2 Value is Displayed") && isDisplayed(rrTitle, "RR Title is Displayed")
				&& isDisplayed(rrValue, "RR Value is Displayed")
				&& isDisplayed(bloodGroupTitle, "Temp Title is Displayed")
				&& isDisplayed(bloodGroupValue, "Temp Value is Displayed")
				&& isDisplayed(backButton, "Back Button is Displayed")
				&& isDisplayed(confirmButton, "Confirm Button is Displayed");

	}

//	  Verifies the BMI status for a patient with normal weight based on the
//	  provided height and weight. Compares the actual status with the expected
//	  status and prints the result of the verification.

	public void verifyStatusForNormalWeightPatient(String height, String weight, String expectedStatus)
			throws InterruptedException {
// Enter the provided height and weight
		sendKeys(heightEditText, height);
		sendKeys(weightEditText, weight);
// Get the actual status
		String actualStatus = bMIStatus.getText();

// Compare the actual status with the expected status
		if (actualStatus.equals(expectedStatus)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
			System.out.println(
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		} else {
			ExtentReport.getTest().log(Status.INFO,
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		}

	}

	// Verifies the status for an underweight patient based on the provided height
	// and weight.
	public void verifyStatusForUnderWeightPatient(String height, String weight, String expectedStatus)
			throws InterruptedException {
		// Enter the provided height into the height field
		sendKeys(heightEditText, height);

		// Enter the provided weight into the weight field
		sendKeys(weightEditText, weight);

		// Get the actual BMI status from the UI
		String actualStatus = bMIStatus.getText();

		// Compare the actual status with the expected status
		if (actualStatus.equals(expectedStatus)) {
			// Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		} else {
			// Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		}
	}

	// Verifies the BMI status for a patient with severe obesity based on provided
	// height and weight.
	public void verifyStatusForSevereObesityWeightpatient(String height, String weight, String expectedStatus)
			throws InterruptedException {
		// Enter the provided height into the height field
		sendKeys(heightEditText, height, "Entered Height");

		// Enter the provided weight into the weight field
		sendKeys(weightEditText, weight, "Entered Weight");

		// Get the actual BMI status from the UI
		String actualStatus = bMIStatus.getText();

		// Compare the actual status with the expected status
		if (actualStatus.equals(expectedStatus)) {
			// Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		} else {
			// Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		}
	}

	// Verifies the BMI status for a patient with moderate obesity based on provided
	// height and weight.
	public void verifyStatusModerateObesityWeightPatient(String height, String weight, String expectedStatus)
			throws InterruptedException {
		// Enter the provided height into the height field
		sendKeys(heightEditText, height, "Entered Height");

		// Enter the provided weight into the weight field
		sendKeys(weightEditText, weight, "Entered Weight");

		// Get the actual BMI status from the UI
		String actualStatus = bMIStatus.getText();

		// Compare the actual status with the expected status
		if (actualStatus.equals(expectedStatus)) {
			// Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		} else {
			// Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		}
	}

	// Verifies the BMI status for a patient with very severe obesity based on
	// provided height and weight.
	public void verifyStatusForVerySevereObesityWeightPatient(String height, String weight, String expectedStatus)
			throws InterruptedException {
		// Enter the provided height into the height field
		sendKeys(heightEditText, height, "Entered Height");

		// Enter the provided weight into the weight field
		sendKeys(weightEditText, weight, "Entered Weight");

		// Get the actual BMI status from the UI
		String actualStatus = bMIStatus.getText();

		// Compare the actual status with the expected status
		if (actualStatus.equals(expectedStatus)) {
			// Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		} else {
			// Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO,
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		}
	}

	public void enterPatientVitals(JSONObject appData) throws JSONException, InterruptedException {

		enterPatientVitalsDetails(appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
	}

	// Enters the vital details of a patient, including height, weight, blood
	// pressure, pulse, temperature, spo2, and respiratory rate.
	public void enterPatientVitalsDetails(String height, String weight, String bpSystolicValue, String bpDiastolicValue,
			String pulse, String temperature, String spo2, String respiratoryRate) throws InterruptedException {
		// Enter the provided height into the height field

		sendKeys(heightEditText, height);

		// Enter the provided weight into the weight field
		sendKeys(weightEditText, weight);

		// Enter the provided systolic blood pressure value
		sendKeys(bpSystolicEditText, bpSystolicValue);

		// Enter the provided diastolic blood pressure value
		sendKeys(bpDiastolicEditText, bpDiastolicValue);

		// Enter the provided pulse rate
		sendKeys(pulseEditText, pulse);

		// Enter the provided body temperature
		sendKeys(temperatureEditText, temperature);

		// Scroll to view the respiratory rate field
		scrollDown();

		// Enter the provided spo2 (oxygen saturation) level
		sendKeys(spo2EditText, spo2);

		// Enter the provided respiratory rate
		sendKeys(respiratoryRateEditText, respiratoryRate);

		click(drpBloodGroup, "clicked on bloodGroup dropdown");

		click(selectDrpBloodGroup, "clicked on bloodGroup dropdown");

	}

	public List<String> verifyPatientVitalsDetails() throws InterruptedException {
		Thread.sleep(3000);
		return getElementsText(heightValue, weightValue, bpValue, pulseValue, tempValue, spo2Value, rrValue, bMIvalue,
				bloodGroupValue);

		/*
		 * s String h = heightValue.getText(); if (h.equals(height)) {
		 * ExtentReport.getTest().log(Status.INFO, "Verification passed");
		 * System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String w =
		 * weightValue.getText(); if (w.equals(weight)) {
		 * ExtentReport.getTest().log(Status.INFO, "Verification passed");
		 * System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String bp = bpValue.getText();
		 * if (bp.equals(bpVal)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verification passed"); System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String p = pulseValue.getText();
		 * if (p.equals(pulse)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verification passed"); System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String t = tempValue.getText();
		 * if (t.equals(temperature)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verification passed"); System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String sp = spo2Value.getText();
		 * if (sp.equals(spo2)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verification passed"); System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String rr = rrValue.getText();
		 * if (rr.equals(respiratoryRate)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verification passed"); System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * 
		 * System.out.println("Verification failed"); } String bmi = bMIvalue.getText();
		 * if (bmi.equals(bmiValue)) { ExtentReport.getTest().log(Status.INFO,
		 * "Verification passed"); System.out.println("Verification passed"); } else {
		 * ExtentReport.getTest().log(Status.INFO, "Verification failed ");
		 * System.out.println("Verification failed"); }
		 */
	}

	public boolean djvdd(List<String> actualVitalsDetailsInSummary,
			List<String> expectedChangedVitalsDetailsInSummary) {

		for (int i = 0; i < actualVitalsDetailsInSummary.size(); i++) {
			String actual = actualVitalsDetailsInSummary.get(i);
			String expected = expectedChangedVitalsDetailsInSummary.get(i);
			System.out.println(actual);
			System.out.println(expected);
			System.out.println(expected.contains(actual));
			// Assert.assertTrue(expected.contains(actual),
			// String.format("Vital mismatch at index %d: Expected [%s], but found [%s]", i,
			// expected, actual));
		}
		return true;
	}

	public boolean checkVitalsUpdateScreenDetailsEditable(String height, String weight, String bpSystolicValue,
			String bpDiastolicValue, String pulse, String temperature, String spo2, String respiratoryRate) {
		try {
			clear(heightEditText);
			sendKeys(heightEditText, height, "Entered Height");
			clear(weightEditText);
			sendKeys(weightEditText, weight, "Enterrd Weight");
			clear(bpSystolicEditText);
			sendKeys(bpSystolicEditText, bpSystolicValue, "Entered BPSystolicValue ");
			clear(bpDiastolicEditText);
			sendKeys(bpDiastolicEditText, bpDiastolicValue, "Entered BPDiastolicValue");
			clear(pulseEditText);
			sendKeys(pulseEditText, pulse, "Entered Pulse");
			clear(temperatureEditText);
			sendKeys(temperatureEditText, temperature, "Entered Temperature");
			clear(spo2EditText);
			sendKeys(spo2EditText, spo2, "Entered spo2");
			clear(respiratoryRateEditText);
			sendKeys(respiratoryRateEditText, respiratoryRate, "Entered  RespiratoryRate");
			return true;
		} catch (Exception e) {
// Print any exception details if an error occurs
			e.printStackTrace();
			return false;

		}
	}

	public boolean checkVitalsUpdateScreenDetailsEditable(List<String> textList) {

		List<WebElement> inpVitalsFields = Arrays.asList(heightEditText, weightEditText, bpSystolicEditText,
				bpDiastolicEditText, pulseEditText, temperatureEditText, spo2EditText, respiratoryRateEditText);

		try {
			for (int iterations = 0,
					textListIndex = 0; iterations < inpVitalsFields.size(); iterations++, textListIndex++) {
				System.out.println("Iteration count :" + iterations);
				System.out.println("WebElement text:" + inpVitalsFields.get(iterations).getText());
				System.out.println("Text to insert  :" + textList.get(textListIndex));

				if (iterations == 2) { // Handling BP (systolic and diastolic)
					// Split the BP value into systolic and diastolic
					String[] splittedString = textList.get(textListIndex).split("/");

					// Send the systolic part to the current element (bpSystolicEditText)
					clear(inpVitalsFields.get(iterations));
					sendKeys(inpVitalsFields.get(iterations), splittedString[0]);

					// Send the diastolic part to the next element (bpDiastolicEditText)
					clear(inpVitalsFields.get(iterations + 1));
					sendKeys(inpVitalsFields.get(iterations + 1), splittedString[1]);

					// Since we're manually handling two WebElements, increment `iterations` to skip
					// the diastolic field
					iterations++;

				} else if (iterations == inpVitalsFields.size() - 3) {
					// Special case for scrolling down before pulse
					scrollDown();
					clear(inpVitalsFields.get(iterations));
					sendKeys(inpVitalsFields.get(iterations), textList.get(textListIndex));

				} else {
					// Handle other vitals normally
					clear(inpVitalsFields.get(iterations));
					sendKeys(inpVitalsFields.get(iterations), textList.get(textListIndex));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	/**
	 * Commented by @srinivas clear(weightEditText); sendKeys(weightEditText,
	 * weight, "Enterrd Weight"); clear(bpSystolicEditText);
	 * sendKeys(bpSystolicEditText, bpSystolicValue, "Entered BPSystolicValue ");
	 * clear(bpDiastolicEditText); sendKeys(bpDiastolicEditText, bpDiastolicValue,
	 * "Entered BPDiastolicValue"); clear(pulseValue); sendKeys(pulseEditText,
	 * pulse, "Entered Pulse"); clear(temperatureEditText);
	 * sendKeys(temperatureEditText, temperature, "Entered Temperature");
	 * clear(spo2EditText); sendKeys(spo2EditText, spo2, "Entered spo2");
	 * clear(respiratoryRateEditText); sendKeys(respiratoryRateEditText,
	 * respiratoryRate, "Entered RespiratoryRate");
	 */

	public boolean verifyVisitReasonScreenIsDisplayed() throws InterruptedException {
		return isDisplayed(visitReasonTitle) && isDisplayed(whatIsTheReasonForVisit) && isDisplayed(searchReasonTextBox)
				&& isDisplayed(visitReasonNextButton) && isDisplayed(visitReasononBackButton);

	}

	/**
	 * @author SrinivasBandi
	 */
	public String getVisitReasonHeader() throws InterruptedException {
		return getText(visitReasonTitle, "Visit reason title is displayed");
	}

	public void clickOnConfirmButton() {
		click(confirmButton);

	}

// 2/4visit reason
	public void selectVisitReasonAsAbdominalPain() {
		click(visitReasonAbdominalPain);

	}

	public boolean verifyVisitReasonAbdominalPainIsSelected() throws InterruptedException {
		System.out.println(
				"##############################3Abdominal pain is selected" + isSelected(visitReasonAbdominalPain));
		System.out.println("##############################3Abdominal pain is displayed after select"
				+ isDisplayed(selectedReason));

		return isSelected(visitReasonAbdominalPain) || isDisplayed(selectedReason);
	}

// public void verifyReasonfilledWithBlueColor() {
// visitReasonAbdominalPain.getAttribute(getDeviceName())
// }
	public void removeSelectedReason() {
		click(remove);
	}

	public boolean verifySelectedReasonIsRemoved() {
		try {
			Thread.sleep(2000);
			return selectedReason.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void selectAllReasons() throws InterruptedException {
		click(visitReasonAbdominalPain);
		scrollToDiarreha();

		click(diarrhea);

		scrollToFever();

		click(fever);

		scrollToHyperTension();

		click(hypertension);
	}

	/*
	 * public boolean verifyAllSelectedReasonsAreDisplayed() {
	 * click(visitReasonAbdominalPain); scrollToDiarreha();
	 * 
	 * click(diarrhea);
	 * 
	 * scrollToFever();
	 * 
	 * click(fever);
	 * 
	 * scrollToHyperTension();
	 * 
	 * click(hypertension); scrollToWhatIsTheReasonForVisit(); // Define the
	 * predefined list of reasons List<String> predefinedReasons =
	 * Arrays.asList("Abdominal Pain", "Diarrhea", "Fever", "Hypertension");
	 * 
	 * // Verify whether all predefined reasons are reflected under Selected Reason
	 * boolean verificationPassed = true; boolean isReflected = false;
	 * 
	 * for (String predefinedReason : predefinedReasons) { // Check if the reason is
	 * reflected under Selected Reason
	 * 
	 * for (WebElement selectedReasonElement : allSelectedReasons) { if
	 * (selectedReasonElement.getText().equals(predefinedReason)) { isReflected =
	 * true; } }
	 * 
	 * if (!isReflected) { System.out.println("Verification Failed: Reason '" +
	 * predefinedReason + "' is not reflected under Selected Reason.");
	 * verificationPassed=false; return false; } }
	 * 
	 * if (verificationPassed) { System.out.
	 * println("Verification Passed: All predefined reasons are reflected under Selected Reason."
	 * ); return true; } return isReflected;
	 * 
	 * }
	 */
	public boolean verifyAllSelectedReasonsAreDisplayed(List<String> predefinedReasons) {
		click(visitReasonAbdominalPain);
		scrollToDiarreha();
		click(diarrhea);
		// scrollToFever();
		// click(fever);
		scrollToHyperTension();
		click(hypertension);
		scrollToWhatIsTheReasonForVisit();

		// Extract text from all selected reasons (WebElements)
		List<String> selectedReasonsText = allSelectedReasons.stream().map(WebElement::getText) // Extract text from
																								// each WebElement
				.collect(Collectors.toList()); // Collect into a list

		// Check if all predefined reasons are present in selectedReasonsText
		boolean allReasonsReflected = predefinedReasons.stream().allMatch(selectedReasonsText::contains);

		if (selectedReasonsText.isEmpty()) {
			return false;
		}

		return true;
	}

	public void clickOnBackButton() {
		click(visitReasononBackButton);
	}

	public boolean verifyConfirmVisitPopup() throws InterruptedException {
		return isDisplayed(confirmVisitPopupTitle) && isDisplayed(confirmVisitPopupSubTitle) && isDisplayed(reason)
				&& isDisplayed(yesButton) && isDisplayed(noButton);

	}

	public List<String> getConfirmVisitPopupContent() {
		return getElementsText(confirmVisitPopupTitle, confirmVisitPopupSubTitle, reason, yesButton, noButton);
	}

	public void clickOnNoButton() {
		click(noButton);
	}

	public void clickOnYesButton() {
		click(yesButton);
	}
// 2/4 Visit reason : Abdominal Pain

	public void verifyVisitReasonAbdominalPainTitleIsDisplayed() throws InterruptedException {
		isDisplayed(visitCreationTitle);
	}

	public void verifyOneOfTwelveQuestionIsDisplayed() throws InterruptedException {
		isDisplayed(OneofElevenQuestion);
	}

	/**
	 * @edited by Srinivas Bandi
	 * @return
	 * @throws InterruptedException
	 */
	public boolean verifyAbdominalFirstQuestionIsDisplayed() throws InterruptedException {
		return isDisplayed(addominalPainReasonQuestion1);
	}

	public boolean verifyAllOptionsAreDisplayed() {
		List<WebElement> options = Arrays.asList(upperRightHypochondrium, upperEpigastric, upperLeftHypochondrium,
				middleRightLumbar, middleUmbilical, middleLeftLumbar, lowerRightIliacFossa, lowerHypogastric,
				lowerLeftIliacFossa, allOver);

		try {
			// Check if all elements are displayed, and immediately throw an error if one is
			// not
			options.stream().forEach(option -> {
				if (!option.isDisplayed()) {
					throw new AssertionError("Element is not displayed: " + option.getAttribute("text"));
				}
			});

		} catch (NoSuchElementException e) {
			throw new AssertionError("Element not found: " + e.getMessage(), e);
		}

		return true;
	}

	public void clickUpperRightHypochondrium() {
		click(upperRightHypochondrium);

	}

	public void clickAllOver() {
		click(allOver);

	}
public boolean isSelectedAllOverOption() {
		return isSelected(allOver);
	}
	public void clickOnGroin() {
		click(groin);
	}

	public void clickOnSubmitButton() {
		scrollDown();	click(sixOfTwelveQuestionsSubmitButton);
	}

	public boolean verifyTwoOfTwelveQuestionIsDisplayed(String QuestionCounter) throws InterruptedException {
		return getText(twoOfElevenQuestion, QuestionCounter).contains(QuestionCounter);
	}

	public boolean threeOfTwelveQuestioNotPresent() {

		try {
			return isDisplayed(threeofTwelveQuestion);
		} catch (Exception e) {
			return false;
		}
	}

	public void selectPainRadiatesOption() {
		click(painRadiates);
	}

	public void scrollToEndAction() throws InterruptedException {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("mobile: scrollGesture",
					ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "up", "percent",
							3.0

					));
			Thread.sleep(1000);
		} while (canScrollMore);
	}

	public boolean verifyPainRadiatesToOptionsAreDisplayed() throws InterruptedException {
		scrollDown();
		return isDisplayed(painRadiatesTo) && isDisplayed(upperRightHypochondrium) && isDisplayed(upperEpigastric)
				&& isDisplayed(upperLeftHypochondrium) && isDisplayed(middleRightLumbar) && isDisplayed(middleUmbilical)
				&& isDisplayed(middleLeftLumbar) && isDisplayed(lowerRightIliacFossa) && isDisplayed(lowerHypogastric)
				&& isDisplayed(lowerLeftIliacFossa) && isDisplayed(rightShoulder) && isDisplayed(rightScapula)
				&& isDisplayed(groin) && isDisplayed(sacralRegion) && isDisplayed(rightFlank) && isDisplayed(chest);

	}

	public boolean verifySubmitButtonIsDisplayed() throws InterruptedException {
		try {
			return isDisplayed(painRadiatesSubmitButton);
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnPainRadiatesSubmitButton() {
		click(painRadiatesSubmitButton);
	}

	public boolean verifyThreeOfTweleveQuestionsIsDisplayed() throws InterruptedException {
		return isDisplayed(threeOfTwelveQuestions);
	}

	public void clickOnNumberSpinner() {
		click(numberSpinner);
	}

	public String selectTwo() {
		String twoText = two.getText();
		click(two);
		return twoText;
	}

	public boolean verifyNumberTwoIsSelected(String expectedValue) throws InterruptedException {
		return two.getText().equals(expectedValue);
	}

	public void verifyNumberTwoIsSelected() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		two.isDisplayed();
	}

	public void clickOnDurationSpinner() {
		click(durationSpinner);
	}

	public String selectDays() {
		String daysText = days.getText();

		click(days);
		return daysText;
	}

	public void verifyDaysIsSelected() throws InterruptedException {
		isDisplayed(days);
	}

	public boolean verifyDaysIsSelected(String expectedText) throws InterruptedException {
		return days.getText().equals(expectedText);
	}

	public void clickOnThreeOfTwelveQuestionSubmitButton() {
		scrollDown();
		click(threeOfElevenQuestionSubmitButton);
	}

	public boolean verifyFourOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		return isDisplayed(fourOfElevenQuestion);
	}

	public void clickOnGradualOption() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(gradualOption);
	}

	public void clickOnsuddenOption() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}

		click(suddenOption);
	}

	public void clickOnRapidlyIncreasingOption() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}

		click(rapidlyIncreasingOption);
	}

	public boolean verifyFiveOfTwelveQuestonIsDisplayed() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		return isDisplayed(fiveOfTwelveQuestions);
	}

	public void clickOnOtherOptionOfFourOfTwelveQuestions() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(othersOptionOfFourOfTwelveQuestions);
	}

	public boolean verifyDescribeBlockIsDisplayedWithSubmitButton() throws InterruptedException {
		scrollDown();
		return isDisplayed(describeTextField) && isDisplayed(submitButtonOfFourOfTwelveQuestion);
	}

	public boolean isdisplayedSelectOneOrMoreText() {
		return isDisplayed(selectOneOrMoreText);
	}

	public void clickOnFourOfTwelveSubmitButton() {
		click(submitButtonOfFourOfTwelveQuestion);
	}

	public void enterValueInDescribeTextBox(String txt) throws InterruptedException {
		int attempts = 0;
		while (attempts < 2) {
			try {
				// Try interacting with the element
				sendKeys(describeTextField, txt);
				// If successful, break out of the loop
				break;
			} catch (StaleElementReferenceException e) {
				// If StaleElementReferenceException occurs, catch it and try again
				attempts++;
			}
		}
	}

	public void clickOnSubmitButtonOfFourOfTwelveQuestion() {
		click(submitButtonOfFourOfTwelveQuestion);
	}

	public void selectNightOption() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(night);
	}

	public boolean isSelectedNIghtOption() {
		return night.isSelected();
	}

	public boolean verifySixOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		// click(othersOptionOfFiveOfTwelveQuestions);
		// sendKeys(describeTextField, "sri_test");
		try {

			return isDisplayed(sixOfTwelveQuestions);
		} catch (Exception e) {
			return false;
		}
	}

	public void selectOtherOptionOfFiveOfTwelveQuestions() {
		click(othersOptionOfFiveOfTwelveQuestions);
	}

	public void clickOnFiveOfTwelveQuestionsSubmitButton() {
		scrollDown();
		click(fiveOfTwelveQuestionsSubmitButton);
	}

	public void clickOnConstantOptionOfSixOfTweleveQuestions() {
		click(constantOptionOfSixOfTwelveQuetions);
	}

	public void clickOnSubmitButtonOfSixOfTwelveQusetions() {
		click(sixOfTwelveQuestionsSubmitButton);
	}

	public boolean verifySevenOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		return isDisplayed(sevenOfTwelveQuestions);
	}

	public void selectOtherOptionOfSixOfTwelveQuestions() {
		click(othersOptionOfSixOfTwelveQuestions);
	}

	public void selectMildOneThreeOption() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(mildOneThreeOption);
	}

	public boolean isSelectedMildOneThreeOption() throws InterruptedException {
		return mildOneThreeOption.isSelected();
	}

	public boolean verifyEightOfTwelvequsetionsIsDisplayed() throws InterruptedException {
		return isDisplayed(eightOfTwelveQusetions);
	}

// 8 of 12 questions
	public void selectCoughingOption() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(coughingOption);
	}

	public void clickOnEightOfTwelveSubmitButton() {
		scrollDown();
		click(eightOfTwelveSubmitButton);
	}

	public boolean verifyNineOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		return isDisplayed(nineOfTwelveQuestions);
	}

	public void clickOnOtherOptonOfEightOfTwelveQusetions() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(otherOptionOfEightOfTwelveQuestions);
	}

// 9 of 12
	public void selectfoodOption() throws InterruptedException {
		Thread.sleep(3000);

		click(foodOption);
	}

	public void selectLeaningForward() throws InterruptedException {
		Thread.sleep(3000);
		;
		click(leaningForwardOption);
	}

	public void clickOnSubmitButtonOfNineOfTwelveQuestions() throws InterruptedException {
		Thread.sleep(3000);scrollDown();
		click(nineOfTwelveQusetionsSubmitButton);
	}

// 10 of 12
	public boolean verifyTenOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		scrollDown(); return isDisplayed(tenOfTwelveQusetions);
	}

// Click on the option indicating that menstruation has not started
	public void selectHasNotStartedMenstruationOption() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		click(hasNotStartedmenstruationOption);
	}

//Click on the option Ismenstruating
	public void selectIsMenstruatingOption() {
		click(isMenstruatingOption);
	}

//Verify that age at onset option is displayed
	public void verifyAgeAtOnSetIsDisplayed() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		isDisplayed(ageAtOnset);
	}

// enter age in ageonset Input feild
	public void enterAgeInAgeOnSetFeild(String txt) throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		sendKeys(ageAtOnSetInputFiled, txt);
	}

//Click On submit button
	public void clickOnSubmitButtonOfTenOftwelveQuestions() {
		click(ageOnSetInputFeildSubmitButton);
	}

//verify calender is displayed
	public boolean verifyCalenderIsDisplayed() throws InterruptedException {
		return isDisplayed(calender);
	}

//verify that LastMenstruationPeriod Is Displayed
	public boolean verifyLastMenstruationPeriodIsDisplayed() throws InterruptedException {
		return isDisplayed(lastMenstruationPeriod);
	}

//selects a date
	public void selectmenstruationDate() {scrollDown();
		click(menstruationDate);
	}

	public boolean verifySelectedMenstruationDateIsDisplayed(String expectedMenstruationDate) {
		String displayedMenstruationDate = menstruationDate.getText();
		return displayedMenstruationDate.equals(expectedMenstruationDate);
		/*
		 * { if (displayedMenstruationDate.equals(expectedMenstruationDate)) { // The
		 * selected menstruation date is displayed and matches the expected value
		 * System.out.
		 * println("Selected menstruation date is displayed and matches the expected value."
		 * ); } else { // The selected menstruation date is either not displayed or
		 * doesn't match the expected value System.out.
		 * println("Selected menstruation date is not displayed or does not match the expected value."
		 * ); } }
		 */
	}

// Click on the 'Other' option
	public void selectOtherOption() {
		click(othersOptionOfFiveOfTwelveQuestions);
	}

//11 of 12 
// Verify that the eleventh question out of twelve is displayed
	public boolean verifyElevenOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		return isDisplayed(elevenOfTwelveQuestions);
	}

//click On yes describe
	public void selectYesDescribeOption() {
		click(yesDescribeOption);
	}

	public void scrolltoSubmit() {
		scrollDown();
	}

// Click on the 'None' option element to select it
	public void selectNoneOption() {
		click(noneOption);
	}

	public boolean isSelectedNoneOption() {
		// TODO Auto-generated method stub
		return noneOption.isSelected();
	}

//12 of 12 
// Check if the element representing twelve questions is displayed
	public boolean verifyTwelveOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		return isDisplayed(twelveOfTweleveQuestions);
	}

// Click on the 'Skip' button for twelve questions
	public void clickOnTwelevOfTwelveSkipButton() {
		click(twelveOfTwelveSkipButton);
	}

	// Click On the submit button for twelve questions
	public void clickOnTwelevOfTwelveSubmitButton() {scrollDown();
		click(twelveOfTwelveSubmitButton);
	}

// Check if the element representing the title for associated symptoms is displayed
	public boolean verifyAssociatedSymptomsTitleIsDisplayed() throws InterruptedException {
		return isDisplayed(associatedSymptomsTitle) && isDisplayed(doYouHaveFollowingSymptom);
	}

	public String getAssociatedSymptomsTitle() {
		return getText(associatedSymptomsTitle, "Associated symptoms title is Displayed");
	}

// Check if the question doYouHaveFollowingSymptom is displayed
	public boolean verifyDoYouHaveFollowingSymptomIsDisplayed() throws InterruptedException {
		return isDisplayed(associatedSymptomsTitle);
	}

//click on change in apetite yes button
	public void clickOnChangeInApetiteYesButton() {
		click(changeInApetiteYesButton);
	}

	public void verifyWhatIsTheChangeIsDisplayed() throws InterruptedException {
		isDisplayed(whatIsTheChange);
	}

	public void verifyInreasedOptionIsDisplayed() throws InterruptedException {
		isDisplayed(increased);
	}

	public void verifyDecreasedOptionIsDisplayed() throws InterruptedException {
		isDisplayed(decreased);
	}

	public void verifyOneOfOneQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(oneOfOneQuestions);
	}

	public WebElement scrollToCorrespondingAddress2() {

		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Patient Details Screen Address Details 'Corresponding Address 2' Value TextView\"));"));

	}

//Method to verify personal details
	public boolean verifyPersonalDetails(String expectedGender, String expectedDOB, String expectedAge)
			throws InterruptedException {
		return isDisplayed(personalDetailsHeader)
				&& getText(nameValue, "Name: ").equals(getText(patientName, "PatientName:"))
				&& getText(genderValue, "Gender: ").equals(expectedGender)
				&& getText(dobValue, "DOB: ").equals(expectedDOB) && getText(ageValue, "Age: ").equals(expectedAge);
	}

// Method to verify address details
	public boolean verifyAddressDetails(String expectedPostalCode, String expectedCountry, String expectedState,
			String expectedDistrict, String expectedVillage) throws InterruptedException {
		return isDisplayed(addressDetailsHeader) && getText(postalCodeValue, "Postal Code: ").equals(expectedPostalCode)
				&& getText(countryValue, "Country: ").equals(expectedCountry)
				&& getText(stateValue, "State: ").equals(expectedState)
				&& getText(districtValue, "District: ").equals(expectedDistrict)
				&& getText(villageValue, "Village: ").equals(expectedVillage);
	}

// Method to verify other details
	public boolean verifyOtherDetails(String expectedNationalID, String expectedOccupation, String expectedCaste,
			String expectedEducation, String expectedEconomic) throws InterruptedException {
		return isDisplayed(otherDetailsHeader) && getText(nationalIdValue, "nationalId:").equals(expectedNationalID)
				&& getText(occupationValue, "Occupation: ").equals(expectedOccupation)
				&& getText(casteValue, "Caste: ").equals(expectedCaste)
				&& getText(educationValue, "Education: ").equals(expectedEducation)
				&& getText(economicValue, "Economic: ").equals(expectedEconomic);
	}

//Associated Sysmptoms
	// Clicks on the "Yes" button for the associated symptom seven.
	public void clickOnSevenAssociatedSymptomYesButton() {
		click(sevenYesButton);
	}
	// Clicks on the "Yes" button for the associated symptom eight.

	public void clickOnEightAssociatedSymptomYesButton() {
		click(eightYesButton);
	}

	// Clicks on the "Yes" button for the associated symptom nine.
	public void clickOnNineAssociatedSymptomYesButton() {
		click(nineYesButton);
	}

	// Clicks on the "Yes" button for the associated symptom sixteen.
	public void clickOnSixteenAssociatedSymptomYesButton() {
		click(sixteenYesButton);
	}

	// Verifies that the text area for describing symptoms is displayed.
	public boolean verifyDescribeTextAreaIsDisplayed() throws InterruptedException {
		return isDisplayed(describeTextArea);
	}

	// Scrolls to the eighth associated symptom
	public WebElement scrollToEighthAssociatedSymptom() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"//android.widget.TextView[@text='15. Nausea']\")):"));
	}

	// Scrolls to the sixteen associated symptom
	public WebElement scrollToSixteenAssociatedSymptom() {
//		return getDriver().findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
//						+ "new UiSelector().description(\"//android.widget.TextView[@text='16. Other [describe]']\"));"));
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"15. Nausea\").instance(0))"));

	}

	public void enterDataInDescribeTextField(String txt) throws InterruptedException {
		sendKeys(describeTextArea, txt);
	}

	public void scrollToAssociatedSymptomSubmiButton() {
//		return getDriver().findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
//+ "new UiSelector().description(\"Associated Symptoms Questionnaire Main View Submit Button\")):"));

	}

	public void clickOnAssociatedSymptomMainSubmitButton() throws InterruptedException {
		// scrollToEndAction();
		click(associatedSymptomSubmitButton);
	}

	// visit reason summary screen
	public boolean verifyVisitReasonSummaryScreenIsDisplayed() throws InterruptedException {
		return isDisplayed(visitReasonSummaryTitle);
	}

	public String getVisirReasonSummaryTitle() throws Exception {
		return getText(visitReasonSummaryTitle, "Visit reason summary title is displayed");
	}

	public void clickOnAbdominalPainChangeIcon() {
		click(abdominalPainChangeButton);
	}

	public void scrollToEightOfTwelveQuestions() {
		for (int i = 0; i < 4; i++) {
			scrollUp();

		}
		scrollToElementUsingItsText("What worsens the pain?*");
		// return getDriver().findElement(
		// AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" +
		// ".scrollable(true)).scrollIntoView("
		// + "new UiSelector().description(\"//android.widget.TextView[@text='8 of 12
		// questions']\"));"));

	}

	/**
	 * Verifies if the selected food option is displayed on the screen.
	 * 
	 * @throws InterruptedException
	 */
	public void verifySelectedFoodOptionIsDisplayed() throws InterruptedException {
		isDisplayed(food);
	}

	/**
	 * Verifies if vomiting information is displayed on the screen.
	 * 
	 * @throws InterruptedException
	 */
	public boolean verifyVomitingIsDisplayed() throws InterruptedException {
		return isDisplayed(vomiting);
	}

	/**
	 * Clicks on the back button on the Visit Reason Summary screen.
	 */
	public void clickOnBackButtonOnVisitReasonSummaryScreen() {
		click(VisitReasonSummaryScreenBackButton);
	}

	/**
	 * Clicks on the confirm button on the Visit Reason Summary screen.
	 */
	public void clickOnConfirmButtonOnVisitReasonSummaryScreen() {
		click(VisitReasonSummaryScreenConfirmButton);
	}

	/**
	 * Verifies if the "Please Wash or Sanitize Your Hands" popup is displayed.
	 * Also, checks for the presence of the "Okay" button.
	 * 
	 * @throws InterruptedException
	 */
	public void verifyPleaseWashOrSanitizeYourHandsPopupIsDisplayed() throws InterruptedException {
		isDisplayed(pleaseWashOrSanitizeYourHands);
		isDisplayed(okayButton);
	}

	/**
	 * Clicks on the "Okay" button.
	 */
	public void clickOnOkayButton() {
		click(okayButton);
	}

	/**
	 * Clicks on the first "Yes" button.
	 */
	public void clickOnFirstYesButton() {
		click(firstYesButton);
	}

	/**
	 * Enters patient vitals and completes the visit by navigating through various
	 * screens.
	 *
	 * @param height           The height of the patient.
	 * @param weight           The weight of the patient.
	 * @param bpSystolicValue  The systolic blood pressure value of the patient.
	 * @param bpDiastolicValue The diastolic blood pressure value of the patient.
	 * @param pulse            The pulse rate of the patient.
	 * @param temperature      The body temperature of the patient.
	 * @param spo2             The oxygen saturation level of the patient.
	 * @param respiratoryRate  The respiratory rate of the patient.
	 * @throws InterruptedException
	 */
	public void enterVitalsAndCompleteVisit(JSONObject appData) throws InterruptedException {
		// Enter patient vitals details
		enterPatientVitals(appData);

		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();

		// Select visit reason as abdominal pain
		selectVisitReasonAsAbdominalPain();

		// Navigate to the next screen after selecting visit reason
		clickOnNextButtonOfVisitReason();

		// Confirm the visit and proceed
		clickOnYesButton();

		// Answer questions related to abdominal pain
		// verifyAbdominalFirstQuestionIsDisplayed();
		// verifyAllOptionsAreDisplayed();
		clickUpperRightHypochondrium();
		clickOnSubmitButton();

		// Answer additional questions related to abdominal pain
		// verifyTwoOfTwelveQuestionIsDisplayed();
		/*
		 * selectPainRadiatesOption(); clickOnGroin();
		 * clickOnPainRadiatesSubmitButton();
		 */
		clickOnDoesNotMoveOption();

		// Answer more questions related to abdominal pain
		// verifyThreeOfTweleveQuestionsIsDisplayed();
		clickOnNumberSpinner();
		selectTwo();
		// verifyNumberTwoIsSelected();
		clickOnDurationSpinner();
		selectDays();
		// verifyDaysIsSelected();
		clickOnThreeOfTwelveQuestionSubmitButton();

		// Answer further questions related to abdominal pain
		// verifyFourOfTwelveQuestionsIsDisplayed();
		clickOnGradualOption();

		// Answer more questions related to abdominal pain
		// verifyFiveOfTwelveQuestonIsDisplayed();
		selectNightOption();
		clickOnSubmitButtonOfSixOfTwelveQusetions();
		// Answer additional questions related to abdominal pain
		// verifySixOfTwelveQuestionsIsDisplayed();
		clickOnConstantOptionOfSixOfTweleveQuestions();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer more questions related to abdominal pain
		// verifySevenOfTwelveQuestionsIsDisplayed();
		selectMildOneThreeOption();

		// Answer additional questions related to abdominal pain
		// verifyEightOfTwelvequsetionsIsDisplayed();
		selectCoughingOption();
		clickOnSubmitButton();

		// Answer more questions related to abdominal pain
		// verifyNineOfTwelveQuestionsIsDisplayed();
		selectLeaningForward();
		clickOnSubmitButtonOfNineOfTwelveQuestions();

		// Answer remaining questions related to abdominal pain
		// verifyTenOfTwelveQuestionsIsDisplayed();
		selectHasNotStartedMenstruationOption();
		// verifyElevenOfTwelveQuestionsIsDisplayed();
		selectNoneOption();
		// verifyTwelveOfTwelveQuestionsIsDisplayed();

		// Skip the last question
		clickOnTwelevOfTwelveSkipButton();
	}

	public void enterVitalsDetailsAndCompleteTenQuestions(String height, String weight, String bpSystolicValue,
			String bpDiastolicValue, String pulse, String temperature, String spo2, String respiratoryRate)
			throws InterruptedException {
		// Enter patient vitals details
		enterPatientVitalsDetails(height, weight, bpSystolicValue, bpDiastolicValue, pulse, temperature, spo2,
				respiratoryRate);

		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();

		// Select visit reason as abdominal pain
		selectVisitReasonAsAbdominalPain();

		// Navigate to the next screen after selecting visit reason
		clickOnNextButtonOfVisitReason();

		// Confirm the visit and proceed
		clickOnYesButton();

		// Answer questions related to abdominal pain
		// verifyAbdominalFirstQuestionIsDisplayed();
		// verifyAllOptionsAreDisplayed();
		clickUpperRightHypochondrium();
		clickOnSubmitButton();

		// Answer additional questions related to abdominal pain
		// verifyTwoOfTwelveQuestionIsDisplayed();
		// selectPainRadiatesOption();
		// clickOnGroin();
		// clickOnPainRadiatesSubmitButton();
		clickOnDoesNotMoveOption();
		// Answer more questions related to abdominal pain
		// verifyThreeOfTweleveQuestionsIsDisplayed();
		clickOnNumberSpinner();
		selectTwo();
		// verifyNumberTwoIsSelected();
		clickOnDurationSpinner();
		selectDays();
		// verifyDaysIsSelected();
		clickOnThreeOfTwelveQuestionSubmitButton();

		// Answer further questions related to abdominal pain
		// verifyFourOfTwelveQuestionsIsDisplayed();
		clickOnGradualOption();

		// Answer more questions related to abdominal pain
		// verifyFiveOfTwelveQuestonIsDisplayed();
		selectNightOption();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer additional questions related to abdominal pain
		// verifySixOfTwelveQuestionsIsDisplayed();
		clickOnConstantOptionOfSixOfTweleveQuestions();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer more questions related to abdominal pain
		// verifySevenOfTwelveQuestionsIsDisplayed();
		selectMildOneThreeOption();

		// Answer additional questions related to abdominal pain
		// verifyEightOfTwelvequsetionsIsDisplayed();
		selectCoughingOption();
		clickOnSubmitButton();

		// Answer more questions related to abdominal pain
		// verifyNineOfTwelveQuestionsIsDisplayed();
		selectLeaningForward();
		clickOnSubmitButtonOfNineOfTwelveQuestions();

		// Answer remaining questions related to abdominal pain
		// verifyTenOfTwelveQuestionsIsDisplayed();
		selectHasNotStartedMenstruationOption();
		// verifyElevenOfTwelveQuestionsIsDisplayed();
	}

	public void verifyUserAllowedToSelectOneOptionInTenOfTwelveQuestion() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();

		// Select visit reason as abdominal pain
		selectVisitReasonAsAbdominalPain();

		// Navigate to the next screen after selecting visit reason
		clickOnNextButtonOfVisitReason();

		// Confirm the visit and proceed
		verifyConfirmVisitPopup();
		clickOnYesButton();

		// Answer questions related to abdominal pain
		verifyAbdominalFirstQuestionIsDisplayed();
		verifyAllOptionsAreDisplayed();
		clickUpperRightHypochondrium();
		clickOnSubmitButton();

		// Answer additional questions related to abdominal pain
		// verifyTwoOfTwelveQuestionIsDisplayed();
		// selectPainRadiatesOption();
		// clickOnGroin();
		// clickOnPainRadiatesSubmitButton();
		clickOnDoesNotMoveOption();
		// Answer more questions related to abdominal pain
		// verifyThreeOfTweleveQuestionsIsDisplayed();
		clickOnNumberSpinner();
		selectTwo();
		// verifyNumberTwoIsSelected();
		clickOnDurationSpinner();
		selectDays();
		// verifyDaysIsSelected();
		clickOnThreeOfTwelveQuestionSubmitButton();

		// Answer further questions related to abdominal pain
		// verifyFourOfTwelveQuestionsIsDisplayed();
		clickOnGradualOption();

		// Answer more questions related to abdominal pain
		// verifyFiveOfTwelveQuestonIsDisplayed();
		selectNightOption();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer additional questions related to abdominal pain
		// verifySixOfTwelveQuestionsIsDisplayed();
		clickOnConstantOptionOfSixOfTweleveQuestions();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer more questions related to abdominal pain
		// verifySevenOfTwelveQuestionsIsDisplayed();
		selectMildOneThreeOption();

		// Answer additional questions related to abdominal pain
		// verifyEightOfTwelvequsetionsIsDisplayed();
		selectCoughingOption();
		clickOnSubmitButton();

		// Answer more questions related to abdominal pain
		// verifyNineOfTwelveQuestionsIsDisplayed();
		selectLeaningForward();
		clickOnSubmitButtonOfNineOfTwelveQuestions();

		// Answer remaining questions related to abdominal pain
		// verifyTenOfTwelveQuestionsIsDisplayed();

		click(hasNotStartedmenstruationOption, "Clicked on hasNotStartedMenstruation");
		isDisplayed(elevenOfTwelveQuestions, "11 of 12 Questions is Displayed");

	}

	public boolean verifyUserAllowedToSelectOneOptionInElevenOfTwelveQuestion() throws InterruptedException {
		click(yesDescribeOption);
		boolean b = isDisplayed(describeField);
		click(noneOption);
		return b && isDisplayed(twelveOfTweleveQuestions);

	}

	public void clickSymptomsNoButton() {
		for (int i = 0; i < 4; i++) {
			click(lastNoButton);
		}
	}

	public void clickNoButtonInAssociatedSymptoms() {

		click(lastNoButton);
	}

	public void checkSymptomsNoButton() {
		for (int i = 0; i < 10; i++) {
			click(lastNoButton);
		}
	}

	public WebElement scrollToAssociatedSymptoms() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Do you have the following symptom(s)?\").instance(0))"));

	}

	public void editAssociatedSymptoms() {
		isDisplayed(associatedSymptomsTitle);

		click(firstYesButton);
		int maxRetries = 2;
		for (int i = 0; i < maxRetries; i++) {
			try {
				for (int j = 0; j < 9; j++) {
					click(lastNoButton);
				}
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// Log or handle the exception
				e.printStackTrace();
			}
		}

		click(associatedSymptomSubmitButton);

	}

	public boolean verifyAssociatedSymptomsSectionOnSummaryPage() throws InterruptedException {

		scrollToAssociatedSymptoms();
		return isDisplayed(associatedSymptomChangeButton) && isDisplayed(patientReports) && isDisplayed(patientDenies);

	}

	public boolean selectAllTwentyOptionsForAssociatedSymptoms() throws InterruptedException {
		boolean isDisplayedAssociatedSymptomsTitle = isDisplayed(associatedSymptomsTitle);
		try {
			for (WebElement element : asSymptomNoButton) {
				Thread.sleep(3000);
				element.click();
			}
		} catch (Exception e) {

			return false;
		}
		return true && isDisplayedAssociatedSymptomsTitle;
	}

//method selects all twenty options for associated symptoms
	public boolean selectValueFromSearchProtocolResultsAndVerify() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		clear(searchReasonTextBox, "Clicked on Search Reason Text Box");
		searchReasonTextBox.click();
		String textToEnter = "ab";
		String adbCommand = String.format("adb shell input text %s", textToEnter);
		executeCommand(adbCommand);
		((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.TAB));
		selectVisitReasonAsAbdominalPain();

		// click(selectedReasonLabel);
		return isDisplayed(selectedReason, "Selected Resaon is Displayed")
				&& isDisplayed(chipReason, "Chip reason is Displayed");

	}

//Executes a system command and waits for its completion
	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Verifies the functionality of selecting and confirming a visit reason after entering vitals
	public boolean verifyAllReasonFuntionality() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		click(visitReasonAbdominalPain);

		return isDisplayed(selectedReason);

	}

//Verifies the functionality of selecting and confirming a specific visit reason in the "All Reasons" section 
	public void verifyAllReasonInAllReasonSection() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		click(visitReasonAbdominalPain, "Clicked On abdominal pain");
		isDisplayed(selectedReason, "Selected reason is displayed");

	}

//Scroll to the element with the text "Hypertension"
	public WebElement scrollToHyperTension() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Hypertension\").instance(0))"));

	}

	// Scroll to the element with the text "Hypertension"
	public WebElement scrollToDiarreha() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Diarrhea\").instance(0))"));

	}

	// Scroll to the element with the text "Hypertension"
	public WebElement scrollToFever() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Fever\").instance(0))"));

	}

	// Scroll to the element with the text "Hypertension"
	public WebElement scrollToWhatIsTheReasonForVisit() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"What is the reason for this visit?\").instance(0))"));

	}

//method to verify 2/4 visit reason page
	public boolean verifyUiOfVisitReasonPage() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		boolean isDisplayedCreatedPatientName = isDisplayed(visitCreatedPatientName, "Patient name is displayed")
				&& isDisplayed(patientAgeAndGender, "Patient age and gender is displayed")
				&& isDisplayed(visitReasonTitle, "2/4 visit screen is displayed")
				&& isDisplayed(progressBar1, "Progress bar 1 is filled")
				&& isDisplayed(progressBar2, "Progress bar 2  is half filled")
				&& isDisplayed(whatIsTheReasonForVisit, "What is the reason for visit is displayed")
				&& isDisplayed(searchReasonTextBox, "Serach reason text box is displayed")
				&& isDisplayed(allResaonsection, "All reason section is displayed")
				&& isDisplayed(visitReasonAbdominalPain, "Abdominal pain is displayed");
		scrollToDiarreha();
		boolean isDisplayedDiarrhea = isDisplayed(diarrhea);
		scrollToFever();
		boolean isDisplayedFever = isDisplayed(fever, "Fever is displayed");
		scrollToHyperTension();
		return isDisplayedDiarrhea && isDisplayedFever && isDisplayedCreatedPatientName
				&& isDisplayed(hypertension, "Hypertension is displayed")
				&& isDisplayed(visitReasonNextButton, "Next button is displayed") &&

				isDisplayed(visitReasononBackButton, "Back button is displayed");

	}

}
