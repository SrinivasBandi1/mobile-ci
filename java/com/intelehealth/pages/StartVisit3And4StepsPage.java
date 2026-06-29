package com.intelehealth.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StartVisit3And4StepsPage extends BaseTest {

	// Visit Reason Section
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_sub_title")
	private WebElement visitReasonHeader;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question_counter'])[last()]")
	private WebElement questionsNumbersOfHypertensionScreening;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question'])[last()]")
	private WebElement questionsOfHypertensionScreening;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name'])[1]")
	private WebElement oneOfSixQuestionsYesButton;

	/*
	 * @AndroidFindBy(id =
	 * "(//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_question\"])[last()]")
	 * private WebElement firstTimeDiagnosedQuestion;
	 */

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name'])[3]")
	private WebElement twoOfSixQuestionsYesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Age More than 40']")
	private WebElement ageMoreThan40Option;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2 of 8 questions']")
	private WebElement twoOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Do you do physical exercises?']")
	private WebElement physicalExerciseQuestion;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/tv_name\"])[2]")
	private WebElement twoOfSixQuestionsNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3 of 8 questions']")
	private WebElement threeOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Do you have the following in your diet?']")
	private WebElement dietQuestion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='[Describe your diet]']")
	private WebElement describeYourDietQuestion;

	@AndroidFindBy(id = "org.intelehealth.app:id/actv_reasons")
	private WebElement otherDescriptionInput;

	@AndroidFindBy(xpath = "(//android.widget.Button[@resource-id='org.intelehealth.app:id/btn_submit'])[last()]")
	private WebElement submitButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='4 of 8 questions']")
	private WebElement fourOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='High fat']")
	private WebElement dietHighInFatQuestion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Other [Describe]']")
	private WebElement fourOfSixQuestionsOtherOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='5 of 8 questions']")
	private WebElement fiveOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='High salt']")
	private WebElement dietHighInSaltQuestion;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='Chips for Reason Item TextView'])[4]")
	private WebElement fiveOfEightQuestionsNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='6 of 8 questions']")
	private WebElement sixOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Fast food']")
	private WebElement dietHasFastFoodQuestion;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='Chips for Reason Item TextView'])[4]")
	private WebElement sixOfEightQuestionsNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='7 of 8 questions']")
	private WebElement sevensOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Have you recently taken/are taking any of the following medication?']")
	private WebElement medicationQuestion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None']")
	private WebElement noneOption;

	@AndroidFindBy(accessibility = "Question Node Submit Button")
	private WebElement sevenOfEightQuestionsSubmitButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='8 of 8 questions']")
	private WebElement eightOfEightQuestions;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Visit Reason Input Text Skip Button\"]")
	private WebElement eightOfEightQuestionsSkipButton;

	// Physical Examination Section
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3/4 Physical Examination']")
	private WebElement physicalExamHeader;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Hypertension screening']")
	private WebElement hyperTensionScreening;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement visitReasonNextButton;
	// confirm Visit
	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
	private WebElement confirmVisitPopupTitle;
	@AndroidFindBy(id = "org.intelehealth.app:id/dialog_subtitle")
	private WebElement confirmVisitPopupSubTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_name")
	private WebElement lblSelectedReasonInConfirmVisitPopup;

	@AndroidFindBy(id = "org.intelehealth.app:id/negative_btn")
	private WebElement noButton;
	@AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
	private WebElement yesButton;
	@AndroidFindBy(accessibility = "Visit Creation Refresh ImageButton")
	private WebElement refreshButton;

	// Associated symptom
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_no']")
	private List<WebElement> associatedSymptomNoButton;

	// 1 of 6 questions
	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question_counter'])[last()]")
	private WebElement QuestionsNumbersInPhysicalExamination;

	@AndroidFindBy(accessibility = "//androidx.recyclerview.widget.RecyclerView[@resource-id='org.intelehealth.app:id/rcv_questions']/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.ImageView")
	private WebElement oneOfSixQuestionsImage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='No']")
	private WebElement oneOfSixQuestionsNoOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Yes']")
	private WebElement oneOfSixQuestionsYesOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Take a picture']")
	private WebElement oneOfSixQuestionsTakePictureOption;

	@AndroidFindBy(id = "org.intelehealth.app:id/btn_1st_capture")
	private WebElement imageCaptureButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload an image from gallery or take a picture']")
	private WebElement captureImageText;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/title_tv' and @text='Take Photo']")
	private WebElement takePhoto;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose from Gallery']")
	private WebElement chooseFromGallery;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
	private WebElement cancel;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='org.intelehealth.app:id/tv_check_status'])[1]")
	private WebElement camera;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/utils_take_picture']")
	private WebElement photoCaptureButton;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text='Picture taken']")
	private WebElement pictureTakenMsg;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='org.intelehealth.app:id/iv_image'])[1]")
	private WebElement capturedImage;

	@AndroidFindBy(accessibility = "Image Item Wired UI Add ImageView")
	private WebElement addImage;
	@AndroidFindBy(accessibility = "Visit Image Capture View Submit Button")

	private WebElement uploadButton;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/flash_switch_iv']")
	private WebElement flashIcon;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
	private WebElement cancelOption;

	@AndroidFindBy(accessibility = "Shutter")
	private WebElement shutterButton;
	// 2 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2 of 6 questions']")
	private WebElement twoOfSixQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='General exams : Is there pallor?*']")
	private WebElement isTherePallorQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Normal'][last()]")
	private WebElement normal;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Pale']")
	private WebElement pale;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Take a picture'])[last()]")
	private WebElement twoOfQuestionsTakeAPictureOption;
	// 3 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3 of 6 questions']")
	private WebElement threeOfSixQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='General exams : Pinch skin*']")
	private WebElement pinchSkinQuestion;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Normal'])[last()]")
	private WebElement threeOfSixQuestionsNormalOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Slow']")
	private WebElement slow;
	// 4 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='4 of 6 questions']")
	private WebElement fourOfSixQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='General exams : Is there any nail abnormality?*']")
	private WebElement nailAbnormalityQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Nails are normal']")
	private WebElement nailsAreNormal;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clubbing']")
	private WebElement clubbing;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Spoon Nails']")
	private WebElement spoonNails;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Discolored']")
	private WebElement discolored;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Take a picture'])[last()]")
	private WebElement fourOfSixQuestionsTakeAPictureOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose from Gallery']")
	private WebElement chooseFromGalleryOption;

	@AndroidFindBy(accessibility = "Photo taken on Nov 28, 2023 10:28:00 AM")
	private WebElement photo;
	// 5 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='5 of 6 questions']")
	private WebElement fiveOfSixQuestions;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Nails are normal'])[last()]")
	private WebElement fiveOfSixQuestionsNailsAreNormalOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Nails are pale']")
	private WebElement nailsArePale;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Take a picture'])[last()]")
	private WebElement fiveOfSixQuestionsTakeAPictureOption;
	// 6 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='6 of 6 questions']")
	private WebElement sixOfSixQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No oedema']")
	private WebElement noOedema;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='In left']")
	private WebElement inLeft;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='In right']")
	private WebElement inRight;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Both']")
	private WebElement both;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Take a picture'])[last()]")
	private WebElement sixOfSixQuestionsTakeAPictureOption;

	@AndroidFindBy(xpath = "org.intelehealth.app:id/btn_submit")
	private WebElement confirmButton;

	// 3/4 Physical examination summary
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3/4. Physical examination summary']")
	private WebElement physicalExaminationSummaryHeader;
	@AndroidFindBy(accessibility = "Physical Examination Summary Fragment Cancel Button")
	private WebElement physicalExaminationSummaryBackButton;
	@AndroidFindBy(accessibility = "Main Row Item Complaint Edit TextView")
	private WebElement physicalExaminationSummaryChangeIcon;
	@AndroidFindBy(accessibility = "Physical Examination Summary Fragment Submit Button")
	private WebElement physicalExaminationSummaryConfirmButton;

	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@text='100.0']")
	private WebElement thirdProgressBar;
	// 4/4 medical history
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='4/4. Medical history: Patient history']")
	private WebElement medicalHistoryHeader;

	// Medical History 1 of 6 questions
	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_question_counter'])[last()]")
	private WebElement medicalHistoryOneToEightQuestions;

	@AndroidFindBy(xpath = "org.intelehealth.app:id/tv_question")
	private WebElement hasYourChildVaccinatedQuestion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Incomplete']")
	private WebElement hasYourChildVaccinatedIncompleteOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Pregnancy status']")
	private WebElement pregnancyStatusQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private WebElement pregnancyStatusNoOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes']")
	private WebElement medicalHistoryFirstYesOption;

	// 2 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2 of 6 questions']")
	private WebElement twoOfSixMedicalQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you have any allergies?']")
	private WebElement allergyQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No known allergies']")
	private WebElement noKnownAllergies;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_skip")
	private WebElement btnSkip;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Do not Chew']")
	private WebElement doNotChewOptionForTobacco;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes [Describe]']")
	private WebElement yesDescribe;
	// 3 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3 of 6 questions']")
	private WebElement threeOfSixMedicalQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Alcohol use']")
	private WebElement alcoholQuestion;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='No'])[last()]")
	private WebElement medicalHistoryThirdQuestionNoOption;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Yes'])[last()]")
	private WebElement medicalHistoryThirdQuestionYesOption;
	// 4 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='4 of 6 questions']")
	private WebElement fourOfSixMedicalQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Smoking history']")
	private WebElement smokingQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Never-smoker']")
	private WebElement neverSmoker;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Denied']")
	private WebElement denied;

	// 5 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='5 of 6 questions']")
	private WebElement fiveOfSixMedicalQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Have you recently taken any kind of medicine (including ayurvedic/homeopathic/unani/herbal)?']")
	private WebElement MedicineQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No'][last()]")
	private WebElement medicalHistoryFifthQuestionNoOption;
	@AndroidFindBy(accessibility = "Question Node Skip Button")
	private WebElement fiveOfSixSkipButton;
	// 6 of 6 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='6 of 6 questions']")
	private WebElement sixOfSixMedicalQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you have a history of any of the following?']")
	private WebElement historyQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None']")
	private WebElement historyNoneOption;

	@AndroidFindBy(accessibility = "Question Node Submit Button")
	private WebElement historySubmitButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='4/4. Medical history: Family history']")
	private WebElement familyHistoryHeader;
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_question")
	private WebElement familyHistoryQuestion;
	@AndroidFindBy(accessibility = "Visit Creation Refresh ImageButton")
	private WebElement refresh;
	@AndroidFindBy(id = "org.intelehealth.app:id/img_btn_cancel")
	private WebElement cancelButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Heart Disease']")
	private WebElement heartDisease;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other [Describe]']")
	private WebElement otherDescribe;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='High BP']")
	private WebElement highBP;
	// 4/4 medical history :family history
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='[Describe relation]']")
	private WebElement otherOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Who has it?']")
	private WebElement whoHasIt;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Father']")
	private WebElement father;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None of the above']")
	private WebElement noneOfTheAbove;

	@AndroidFindBy(id = "org.intelehealth.app:id/actv_reasons")
	private WebElement textBox;
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
	private WebElement submit;
	@AndroidFindBy(accessibility = "Family History Fragment Submit Button")
	private WebElement familyHistoryConfirmButton;
	@AndroidFindBy(accessibility = "Medical History Summary Fragment Subtitle TextView")
	private WebElement medicalHistorySummaryScreenHeader;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Main Row Item Complaint Edit TextView\"])[1]")
	private WebElement patientHistoryChangeButton;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Change'])[last()]")
	private WebElement FamilyHistoryChangeButton;
	@AndroidFindBy(accessibility = "Family History Fragment Cancel Button")
	private WebElement SummaryScreenBackButton;
	@AndroidFindBy(accessibility = "Medical History Summary Fragment Submit Button")
	private WebElement SummaryScreenConfirmButton;

	// Visit summay
	@AndroidFindBy(id = "org.intelehealth.app:id/toolbar_title")
	private WebElement visitSummaryTitle;

	// Patient history dropdowns
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Duration Type']")
	private WebElement durationDropdown;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Weeks']")
	private WebElement weeks;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name' and @text='Since']")
	private WebElement sinceOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Number']")
	private WebElement numberDropdown;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='3']")
	private WebElement three;

	// calender

	@AndroidFindBy(id = "(//androidx.recyclerview.widget.RecyclerView[@resource-id='org.intelehealth.app:id/rcv_nested_container'])[last()]//android.widget.TextView[@resource-id='org.intelehealth.app:id/tv_name']")
	private List<WebElement> medicationsOptions;

	@AndroidFindBy(id = "android:id/month_view")
	private WebElement calender;
	@AndroidFindBy(id = "android:id/prev")
	private WebElement previousMonthArrow;
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'1')]")
	private WebElement date;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'28')]")
	private WebElement toDate;
	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/btn_view_date'])[last()]")
	private WebElement selectedDate;
	// image
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Gallery']")
	private WebElement gallery;

	@AndroidFindBy(xpath = "(//android.view.ViewGroup)[2]")
	private WebElement image;

	@AndroidFindBy(accessibility = "Visit Summary Speciality Spinner")
	private WebElement specialitySpinner;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Pediatrician']")
	private WebElement pediatrician;

	@AndroidFindBy(accessibility = "Visit Summary Send Visit Button")
	private WebElement sendVisitButton;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Positive (Yes) Button")
	private WebElement sendVisitPopupYesButton;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text='Uploading...']")
	private WebElement uploading;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Visit successfully sent!']")
	private WebElement successMsg;

	@AndroidFindBy(accessibility = "Medical History Fragment Submit Button")
	private WebElement patientHistoryConfirmButton;

//	// Scroll to the element with the text "Hypertension"
//	public WebElement scrollToHyperTension() {
//		return getDriver().findElement(AppiumBy.androidUIAutomator(
//				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Hypertension\").instance(0))"));
//
//	}
	public WebElement scrollToHyperTensionScreening() {

		return getDriver().findElement(new AppiumBy.ByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0))" + ".scrollIntoView(new UiSelector()"
						+ ".textMatches(\"" + "Hypertension screening" + "\").instance(0))"));

//		return getDriver().findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
//						+ "new UiSelector().description(\"Hypertension\").instance(0));"));

	}

	// Select the element representing Hypertension
	public void selectHyperTension() throws InterruptedException {
		click(hyperTensionScreening, "Selected HyperTension");
	}

	// Select Hypertension and confirm visit reason
	public void selectAndConfirmVisitReason() throws InterruptedException {
		click(hyperTensionScreening);
		click(visitReasonNextButton);
		isDisplayed(confirmVisitPopupTitle);
		isDisplayed(confirmVisitPopupSubTitle);
		isDisplayed(lblSelectedReasonInConfirmVisitPopup);
		click(yesButton);
	}

	// Handle a series of questions related to Hypertension visit
	public void handleHypertensionVisitQuestions() throws InterruptedException {

		isDisplayed(visitReasonHeader);
		isDisplayed(questionsNumbersOfHypertensionScreening);
		isDisplayed(questionsOfHypertensionScreening);
		click(oneOfSixQuestionsYesButton);
		// 2nd qtn
		isDisplayed(questionsOfHypertensionScreening);
		click(twoOfSixQuestionsYesButton);

		/*
		 * int maxRetries = 5; for (int i = 0; i < maxRetries; i++) { try {
		 * isDisplayed(ageMoreThan40Option); } catch (NoSuchElementException |
		 * StaleElementReferenceException e) { // Log or handle the exception
		 * e.printStackTrace(); } }
		 */

		isDisplayed(ageMoreThan40Option);
		click(ageMoreThan40Option);
		isDisplayed(questionsNumbersOfHypertensionScreening);

		// 3rd qn
		isDisplayed(physicalExerciseQuestion);
		click(twoOfSixQuestionsNoButton);

		isDisplayed(questionsNumbersOfHypertensionScreening);
		click(dietQuestion);
		isDisplayed(describeYourDietQuestion);
		click(describeYourDietQuestion);
		isDisplayed(otherDescriptionInput);
		isDisplayed(dietHighInFatQuestion);
		isDisplayed(dietHighInSaltQuestion);
		isDisplayed(dietHasFastFoodQuestion);
		sendKeys(otherDescriptionInput, "Testing");
		click(submitButton);

		// 4th qtn
		isDisplayed(questionsNumbersOfHypertensionScreening);
		click(fourOfSixQuestionsOtherOption);
		sendKeys(otherDescriptionInput, "Testing");
		click(submitButton);

		// 5th
		isDisplayed(questionsNumbersOfHypertensionScreening);
		click(noneOption);

		// click(fiveOfEightQuestionsNoButton);
		// 6th
		isDisplayed(questionsNumbersOfHypertensionScreening);
		sendKeys(otherDescriptionInput, "Testing");
		click(submitButton);

	}

	// Select the 'No' option for associated symptoms

	public void selectAssociatedSymptomFirstNoButton() {
		scrollDown();
		for (int i = 0; i < associatedSymptomNoButton.size(); i++) {
			click(associatedSymptomNoButton.get(i));
		}
	}

	// Verify the display of the first question set
	public void verifyOneOfSixQuestions() throws InterruptedException {

		isDisplayed(QuestionsNumbersInPhysicalExamination, "One of Six Questions is displayed");
		isDisplayed(oneOfSixQuestionsImage, "Image for One of Six Questions is displayed");
		isDisplayed(oneOfSixQuestionsYesOption, "Yes option for One of Six Questions is displayed");
		isDisplayed(oneOfSixQuestionsNoOption, "No option for One of Six Questions is displayed");
		setImplicitWait();

		isDisplayed(oneOfSixQuestionsTakePictureOption, "Take Picture option for One of Six Questions is displayed");

	}

	// Verify options for the first question and take a picture
	public void verifyOneOfSixQuestionOption() throws InterruptedException {
		click(oneOfSixQuestionsNoOption, "Clicked on No option for One of Six Questions");
		int maxRetries = 5;
		for (int i = 0; i < maxRetries; i++) {
			try {
				click(oneOfSixQuestionsTakePictureOption, "Clicked on Take Picture option for One of Six Questions");
				break;
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// Log or handle the exception
				e.printStackTrace();
			}
		}

		// click(oneOfSixQuestionsTakePictureOption);
		isDisplayed(imageCaptureButton, "Image Capture button is displayed");
		// click(imageCaptureButton, "Clicked on Image Capture Button ");
	}

//Verify the display of the second question set
	public void verifyTwoOfSixQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Two of Six Questions is displayed");
	}

//Verify taking a picture option
	public void verifyTakeAPictureOption() throws InterruptedException {
		click(oneOfSixQuestionsTakePictureOption, "Clicked on Take a Picture option");
		isDisplayed(imageCaptureButton, "Image Capture button is displayed");
		isDisplayed(captureImageText, "Upload an image from gallery or take a picture is displayed");
	}

	public void selectOneOfSixquestionsNoOption() throws InterruptedException {
		click(oneOfSixQuestionsNoOption, "Selected 'No' option for One of Six Questions");
	}

// Verify upload options after clicking on take a picture
	public void verifyUploadIcon() throws InterruptedException {
		click(oneOfSixQuestionsTakePictureOption, "Clicked on 'Take a Picture' option");
		click(imageCaptureButton, "Clicked on Image Capture button");
		isDisplayed(takePhoto, "Take Photo option is displayed");
		isDisplayed(chooseFromGallery, "Choose from Gallery option is displayed");
		isDisplayed(cancel, "Cancel option is displayed");
	}

//Verify the display of the second question set with options
	public void verifyTwoOfSixQuestionIsDisplayedWithOptions() throws InterruptedException {
		click(oneOfSixQuestionsNoOption, "Clicked on 'No' option for the first question");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Second question is displayed");
		isDisplayed(isTherePallorQuestion, "Is there pallor question is displayed");

		isDisplayed(normal, "'Normal' option is displayed");
		isDisplayed(pale, "'Pale' option is displayed");
		isDisplayed(twoOfQuestionsTakeAPictureOption, "Take a picture option is displayed");
	}

	// Verify two of six questions have normal and pale options
	public void verifyTwoOfSixQuestionsNormalPaleOption() throws InterruptedException {
		click(normal, "Clicked on 'Normal' option");
		click(pale, "Clicked on 'Pale' option");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Third question is displayed");
	}

	// Verify three questions are displayed with options, including normal, pinch
	// skin, and three of six questions
	public void verifyThreeOfQuestionsIsDisplayedWithOptions() throws InterruptedException {

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Third question is displayed");
		isDisplayed(pinchSkinQuestion, "Pinch Skin question is displayed");
		isDisplayed(threeOfSixQuestionsNormalOption, "'Normal' option is displayed");
		click(normal, "Clicked on 'Normal' option");
		isDisplayed(slow, "'Slow' option is displayed");
	}

	// Verify three of six questions have normal and slow options
	public void verifyThreeOfSixQuestionsNormalSlowOption() throws InterruptedException {
		click(threeOfSixQuestionsNormalOption, "Clicked on 'Normal' option");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Fourth question is displayed");
		click(slow, "Clicked on 'Slow' option");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Fourth question is still displayed");
	}

	// Verify four of six questions are displayed with options, including nail
	// abnormality, normal nails, clubbing, spoon nails, and discolored nails
	public void verifyFourOfSixQuestionIsDisplayedWithOptions() throws InterruptedException {
		isDisplayed(nailAbnormalityQuestion, "Nail Abnormality question is displayed");
		isDisplayed(nailsAreNormal, "'Nails are Normal' option is displayed");
		isDisplayed(clubbing, "'Clubbing' option is displayed");
		isDisplayed(spoonNails, "'Spoon Nails' option is displayed");
		isDisplayed(discolored, "'Discolored' option is displayed");
		isDisplayed(fourOfSixQuestionsTakeAPictureOption, "'Take a Picture' option is displayed");
	}

	// Verify four of six questions have options including normal nails, clubbing,
	// spoon nails, and discolored nails
	public void verifyFourOfSixQuestionsOptions() throws InterruptedException {
		click(nailsAreNormal, "Clicked on 'Nails are Normal'");
		// isDisplayed(QuestionsNumbersInPhysicalExamination, "Five of Six Questions is
		// displayed");

		click(clubbing, "Clicked on 'Clubbing'");
		// isDisplayed(QuestionsNumbersInPhysicalExamination, "Five of Six Questions is
		// displayed");

		click(spoonNails, "Clicked on 'Spoon Nails'");
		// isDisplayed(QuestionsNumbersInPhysicalExamination, "Five of Six Questions is
		// displayed");

		click(discolored, "Clicked on 'Discolored'");
		click(submitButton);
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Five of Six Questions is displayed");
	}

	// Verify five of six questions are displayed
	public void verifyFiveOfSixQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(fiveOfSixQuestions, "Five of Six Questions is displayed");
	}

	// Verifies the five of
	public void verifyFiveOfSixQuestionsOptionsAreDisplayed() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "One of Six Questions is displayed");
		click(oneOfSixQuestionsNoOption, "Clicked No option for One of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Two of Six Questions is displayed");
		click(normal, "Clicked Normal button for Two of Eight Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Three of Six Questions is displayed");
		click(threeOfSixQuestionsNormalOption, "Clicked Normal option for Three of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Four of Six Questions is displayed");
		click(nailsAreNormal, "Clicked Nails are Normal option for Four of Six Questions");
		click(submitButton);

		isDisplayed(QuestionsNumbersInPhysicalExamination,
				"Five of Six Questions - Nails are Normal option is displayed");
		isDisplayed(nailsArePale, "Nails are Pale option is displayed");
		isDisplayed(fiveOfSixQuestionsTakeAPictureOption, "Take a Picture option is displayed");
	}

	public void verifyFiveOfSixQuestionsNailsAreNormal_NailsArePaleOption() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "One of Six Questions is displayed");
		click(oneOfSixQuestionsYesOption, "Clicked Yes option for One of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Two of Six Questions is displayed");
		click(normal, "Clicked Normal button for Two of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Three of Six Questions is displayed");
		click(threeOfSixQuestionsNormalOption, "Clicked Normal option for Three of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Four of Six Questions is displayed");
		click(nailsAreNormal, "Clicked Nails are Normal option for Four of Six Questions");
		click(submitButton);

		isDisplayed(QuestionsNumbersInPhysicalExamination,
				"Five of Six Questions - Nails are Normal option is displayed");
		isDisplayed(nailsArePale, "Nails are Pale option is displayed");
		isDisplayed(fiveOfSixQuestionsTakeAPictureOption, "Take a Picture option is displayed");
		click(nailsAreNormal, "Clicked Nails are Normal option");
		click(fiveOfSixQuestionsTakeAPictureOption, "Clicked Take a Picture option");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Six of Six Questions is displayed");
		click(nailsArePale, "Clicked Nails are Pale option");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Six of Six Questions is displayed");
	}

	public void verifySixOfSixQuestionsIsDisplayedWithOptions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "One of Six Questions is displayed");
		click(oneOfSixQuestionsYesOption, "Clicked Yes option for One of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Two of Six Questions is displayed");
		click(normal, "Clicked Normal button for Two of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Three of Six Questions is displayed");
		click(threeOfSixQuestionsNormalOption, "Clicked Normal option for Three of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Four of Six Questions is displayed");
		click(nailsAreNormal, "Clicked Nails are Normal option for Four of Six Questions");
		click(submitButton);

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Five of Six Questions is displayed");
		click(nailsArePale, "Clicked Nails are Pale option for Five  of Six Questions");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "Six of Six Questions is displayed");
		isDisplayed(noOedema, "No Oedema option is displayed");
		isDisplayed(inLeft, "In Left option is displayed");
		isDisplayed(inRight, "In Right option is displayed");
		isDisplayed(both, "Both option is displayed");
		isDisplayed(sixOfSixQuestionsTakeAPictureOption, "Take a Picture option is displayed");
	}

	public void verifySixOfSixQuestionsOptions() throws InterruptedException {
		click(sixOfSixQuestionsTakeAPictureOption, "Clicked Take a Picture option for Six of Six Questions");
		click(inLeft, "Clicked In Left option");

		/// No lacators for Physical Examination Summary screen
		isDisplayed(physicalExaminationSummaryHeader, "Physical Examination Summary Header is displayed");
		click(physicalExaminationSummaryBackButton, "Clicked Back button in Physical Examination Summary");

		click(inRight, "Clicked In Right option");
		// clcik on confirm button
		click(confirmButton);

		isDisplayed(physicalExaminationSummaryHeader, "Physical Examination Summary Header is displayed");
	}

	public void verifyPhysicalExaminationChangeButton() throws InterruptedException {
		click(physicalExaminationSummaryChangeIcon, "Clicked on Physical Examination Change button");
		isDisplayed(physicalExamHeader, "Physical Examination Header is displayed");
	}

	public void answerPhysicalExaminationQuestions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "One of Six Questions is displayed");
		click(oneOfSixQuestionsYesOption, "Clicked on Yes option for One of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Two of Six Questions is displayed");
		click(normal, "Clicked on Normal option for Two of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Three of Six Questions is displayed");
		click(threeOfSixQuestionsNormalOption, "Clicked on Normal option for Three of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Four of Six Questions is displayed");
		click(nailsAreNormal, "Clicked on Nails are Normal option for Four of Six Questions");
		click(submitButton);

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Five of Six Questions is displayed");
		click(fiveOfSixQuestionsNailsAreNormalOption, "Clicked on Nails are Normal option for Five of Six Questions");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "Six of Six Questions is displayed");
		click(noOedema, "Clicked on No Oedema option for Six of Six Questions");
		click(confirmButton);

		isDisplayed(physicalExaminationSummaryHeader, "Physical Examination Summary Header is displayed");
	}

	public void updateThePhysicalExaminationQuestions() throws InterruptedException {
		isDisplayed(sixOfSixQuestions, "Six of Six Questions is displayed");
		click(inRight, "Clicked on 'In Right' option");

	}

	public void verifyBackButton() throws InterruptedException {
		click(physicalExaminationSummaryBackButton, "Clicked on Back Button");
		isDisplayed(physicalExamHeader, "Physical Exam Header is displayed");
	}

	public void verifyConfirmButton() throws InterruptedException {
		click(physicalExaminationSummaryConfirmButton, "Clicked on Confirm Button");
		if (isDisplayed(thirdProgressBar, "Third step is complete")) {
			// Additional action or log statement if needed
		}
		isDisplayed(thirdProgressBar, "Third progress bar is displayed");
	}

	public void answerMedicalHistoryQuestions() throws InterruptedException {

		/// no locator for confirm button
		click(physicalExaminationSummaryConfirmButton, "Clicked on Confirm Button");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 1 is displayed");
		isDisplayed(hasYourChildVaccinatedQuestion, "Pregnancy status question is displayed");
		click(hasYourChildVaccinatedIncompleteOption, "Pregnancy status question is displayed");

		isDisplayed(medicalHistoryOneToEightQuestions, "Pregnancy status question is displayed");
		click(pregnancyStatusNoOption, "Clicked on 'No' for pregnancy status");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 2 is displayed");

		// 3rd
		isDisplayed(medicalHistoryOneToEightQuestions, "History question is displayed");
		click(historyNoneOption, "Clicked on 'None' for history");
		click(submitButton, "Clicked on submit");

		// 4rth
		isDisplayed(medicalHistoryOneToEightQuestions, "Medicine question is displayed");
		click(medicalHistoryFifthQuestionNoOption, "Clicked on 'No' for medicine history");

		// 5th
		isDisplayed(medicalHistoryOneToEightQuestions, "Allergy question is displayed");
		click(noKnownAllergies, "Clicked on 'No Known Allergies'");

		// 6th
		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 3 is displayed");
		click(doNotChewOptionForTobacco);

		// 7th
		isDisplayed(medicalHistoryOneToEightQuestions, "Smoking question is displayed");
		click(neverSmoker, "Clicked on 'Never Smoker'");

		// 8
		isDisplayed(medicalHistoryOneToEightQuestions, "Alcohol consumption question is displayed");
		click(medicalHistoryThirdQuestionNoOption, "Clicked on 'No' for alcohol consumption");
	}

	// Verifies the visibility of key UI elements on the Family History screen.

	public void verifyFamilyHistoryScreenUi() throws InterruptedException {

		isDisplayed(familyHistoryHeader, "Family History Header is displayed");

		isDisplayed(refresh, "Refresh button is displayed");

		isDisplayed(cancelButton, "Cancel button is displayed");

		isDisplayed(familyHistoryQuestion, "Family History Question is displayed");
	}

	// Validates the selection of multiple options in the Family History screen.

	public void validateMultipleOptionsInFamilyHistory() throws InterruptedException {

		isDisplayed(familyHistoryHeader, "Family History Header is displayed");

		click(highBP, "Clicked on High Blood Pressure option");

		click(heartDisease, "Clicked on Heart Disease option");
	}

	// Verifies the display of the describe text box and related elements after
	// selecting the 'Other' option on the Family History screen.

	public void verifyDescribeTextBoxIsDisplayed() throws InterruptedException {

		isDisplayed(familyHistoryHeader, "Family History Header is displayed");

		click(otherOption, "Clicked on 'Other' option");

		isDisplayed(textBox, "Describe Text Box is displayed");

		isDisplayed(submit, "Submit button is displayed");
	}

	// Enters data in the text box and submits the form after selecting the 'Other'
	// option on the Family History screen.

	public void enterDataInTextBoxAndSubmit() throws InterruptedException {

		click(otherOption, "Clicked on 'Other' option");

		int maxRetries = 5;
		for (int i = 0; i < maxRetries; i++) {
			try {
				isDisplayed(textBox);
				sendKeys(textBox, "Testing");
				break; // Break out of the loop if the text box is displayed
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// Log or handle the exception
				e.printStackTrace();
			}
		}

		click(submit, "Clicked on Submit button");
	}

//	  Verifies the behavior of the Family History screen confirm button.
//	 Clicks the confirm button and checks if the Medical History Summary screen header is displayed.

	public void verifyFamilyHistoryScreenConfirmButton() throws InterruptedException {
///no locator
		isDisplayed(medicalHistorySummaryScreenHeader, "Medical History Summary Screen Header is displayed");
	}

	// Verifies the behavior of the "Change" button on the Patient History screen.
	// Clicks the "Change" button and checks if the Medical History Header is
	// displayed.

	public void verifyPatientHistoryChangeButton() throws InterruptedException {
///no locator
		click(patientHistoryChangeButton, "Clicked on Patient History Change Button");
		isDisplayed(medicalHistoryHeader, "Medical History Header is displayed");
	}

	// Verifies the behavior of the "Change" button on the Family History
	// screen.Clicks the "Change" button and checks if the Medical History Header is
	// displayed. Add additional verification or actions as needed.

	public void verifyFamilyHistoryChangeButton() throws InterruptedException {
		/// no locator

		click(FamilyHistoryChangeButton, "Clicked on Family History Change Button");
		isDisplayed(familyHistoryHeader, "Medical History Header is displayed");

	}

	public void verifyMedicalHistorySummaryScreenBackButton() throws InterruptedException {
		/// no locator
		click(SummaryScreenBackButton, "Clicked on Summary Screen Back Button");
		isDisplayed(medicalHistoryHeader, "Medical History Header is displayed");

	}

	public void verifyMedicalHistorySummaryScreenConfirmButton() throws InterruptedException {

		isDisplayed(medicalHistorySummaryScreenHeader, "Medical History Summary Screen Header is displayed");

		click(SummaryScreenConfirmButton, "Clicked on Summary Screen Confirm Button");

		isDisplayed(visitSummaryTitle, "Visit Summary Title is displayed");

	}

	public void verifyUserAbleToSelectOneOrMoreOption() throws InterruptedException {

		click(physicalExaminationSummaryConfirmButton, "Clicked on Physical Examination Summary Confirm Button");

		isDisplayed(medicalHistoryOneToEightQuestions, "First Medical Question is displayed");

		// isDisplayed(pregnancyStatusQuestion, "Pregnancy Status Question is
		// displayed");

		click(pregnancyStatusNoOption, "Clicked on 'No' for Pregnancy Status");

		isDisplayed(medicalHistoryOneToEightQuestions, "Second Medical Question is displayed");

	}

	public void verifySkipButtonOnPatientScreen() throws InterruptedException {
		click(physicalExaminationSummaryConfirmButton, "Clicked on Physical Examination Summary Confirm Button");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 1 is displayed");
		isDisplayed(hasYourChildVaccinatedQuestion, "Has Your Child Vaccinated Question is dispalyed");
		click(hasYourChildVaccinatedIncompleteOption, "Clicked on Incompleted option");

		isDisplayed(medicalHistoryOneToEightQuestions, "Pregnancy status question is displayed");
		click(pregnancyStatusNoOption, "Clicked on 'No' for pregnancy status");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 2 is displayed");

		// 3rd
		isDisplayed(medicalHistoryOneToEightQuestions, "History question is displayed");
		click(historyNoneOption, "Clicked on 'None' for history");
		click(submitButton, "Clicked on submit");

		// 4rth
		isDisplayed(medicalHistoryOneToEightQuestions, "Medicine question is displayed");
		click(medicalHistoryFifthQuestionNoOption, "Clicked on 'No' for medicine history");

		// 5th
		isDisplayed(medicalHistoryOneToEightQuestions, "Allergy question is displayed");
		click(noKnownAllergies, "Clicked on 'No Known Allergies'");

		// 6th
		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 3 is displayed");
		click(btnSkip);

		// 7th
		isDisplayed(medicalHistoryOneToEightQuestions, "Smoking question is displayed");
		click(neverSmoker, "Clicked on 'Never Smoker'");

		// 8
		isDisplayed(medicalHistoryOneToEightQuestions, "Alcohol consumption question is displayed");
		click(medicalHistoryThirdQuestionNoOption, "Clicked on 'No' for alcohol consumption");
		isDisplayed(sixOfSixMedicalQuestions, "Sixth Medical Question is displayed");
	}

	public boolean verifyPatientHistorySubmitButtonFunctionality() throws InterruptedException {
		click(physicalExaminationSummaryConfirmButton, "Clicked on Physical Examination Summary Confirm Button");
		isDisplayed(hasYourChildVaccinatedQuestion, "Has Your Child Vaccinated Question is dispalyed");
		click(hasYourChildVaccinatedIncompleteOption, "Has Your Child Vaccinated Incomplete Option");
		isDisplayed(medicalHistoryOneToEightQuestions, "First Medical Question is displayed");
		// isDisplayed(pregnancyStatusQuestion, "Pregnancy Status Question is
		// displayed");
		click(pregnancyStatusNoOption, "Clicked on 'No' for Pregnancy Status");
		isDisplayed(medicalHistoryOneToEightQuestions, "Medical Question is displayed");
		click(otherDescribe, "Clicked on 'Yes' for describing the medical history");
		sendKeys(textBox, "Testing");
		click(submit, "Clicked on Submit button for medical history");
		return isDisplayed(medicalHistoryOneToEightQuestions, "Third Medical Question is displayed");

	}

	public void verifyPatientHistoryOtherDescribeTextBox() throws InterruptedException {
		click(physicalExaminationSummaryConfirmButton, "Clicked on Physical Examination Summary Confirm Button");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 1 is displayed");
		isDisplayed(hasYourChildVaccinatedQuestion, "Has Your Child Vaccinated Question is dispalyed");
		click(hasYourChildVaccinatedIncompleteOption, "Clicked on Incompleted option");

		isDisplayed(medicalHistoryOneToEightQuestions, "Pregnancy status question is displayed");
		click(pregnancyStatusNoOption, "Clicked on 'No' for pregnancy status");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 2 is displayed");

		// 3rd
		isDisplayed(medicalHistoryOneToEightQuestions, "History question is displayed");
		click(historyNoneOption, "Clicked on 'None' for history");
		click(otherDescribe, "Clicked on 'Yes' for describing the medical history");
		sendKeys(textBox, "Testing");
		click(submit, "Clicked on Submit button for medical history");

		// 4rth
		isDisplayed(medicalHistoryOneToEightQuestions, "Medicine question is displayed");

	}

	public void verifyPatientHistoryDropdowns() throws InterruptedException {
		click(physicalExaminationSummaryConfirmButton, "Clicked on Physical Examination Summary Confirm Button");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 1 is displayed");
		isDisplayed(hasYourChildVaccinatedQuestion, "Has Your Child Vaccinated Question is dispalyed");
		click(hasYourChildVaccinatedIncompleteOption, "Clicked on Incompleted option");

		isDisplayed(medicalHistoryOneToEightQuestions, "Pregnancy status question is displayed");
		click(pregnancyStatusNoOption, "Clicked on 'No' for pregnancy status");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 2 is displayed");

		// 3rd
		isDisplayed(medicalHistoryOneToEightQuestions, "History question is displayed");
		click(historyNoneOption, "Clicked on 'None' for history");
		click(submitButton, "Clicked on submit");

		// 4rth
		isDisplayed(medicalHistoryOneToEightQuestions, "Medicine question is displayed");
		click(medicalHistoryFifthQuestionNoOption, "Clicked on 'No' for medicine history");

		// 5th
		isDisplayed(medicalHistoryOneToEightQuestions, "Allergy question is displayed");
		click(noKnownAllergies, "Clicked on 'No Known Allergies'");

		// 6th
		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 3 is displayed");
		click(btnSkip);
		// 7th
		isDisplayed(medicalHistoryOneToEightQuestions, "Smoking question is displayed");
		click(neverSmoker, "Clicked on 'Never Smoker'");
		// 8th
		isDisplayed(medicalHistoryOneToEightQuestions, "Alcohol Question is displayed");
		click(medicalHistoryThirdQuestionYesOption, "Clicked on 'Yes' for Alcohol Question");
		click(sinceOption, "Clicked on Since option");
		click(numberDropdown, "Clicked on Number Dropdown");
		click(three, "Selected 'Three' from Number Dropdown");
		click(durationDropdown, "Clicked on Duration Dropdown");
		click(weeks, "Selected 'Weeks' from Duration Dropdown");
		isDisplayed(three, "Number 'Three' is displayed");
		isDisplayed(weeks, "Duration 'Weeks' is displayed");
		click(submit);

	}

	public void calenderSelectionOption() throws InterruptedException {
		click(physicalExaminationSummaryConfirmButton, "Clicked on Physical Examination Summary Confirm Button");
		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 1 is displayed");
		isDisplayed(hasYourChildVaccinatedQuestion, "Has Your Child Vaccinated Question is dispalyed");
		click(hasYourChildVaccinatedIncompleteOption, "Clicked on Incompleted option");

		isDisplayed(medicalHistoryOneToEightQuestions, "Pregnancy status question is displayed");
		click(pregnancyStatusNoOption, "Clicked on 'No' for pregnancy status");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medical history question 2 is displayed");

		// 3rd
		isDisplayed(medicalHistoryOneToEightQuestions, "History question is displayed");
		click(historyNoneOption, "Clicked on 'None' for history");
		click(submitButton, "Clicked on submit");

		isDisplayed(medicalHistoryOneToEightQuestions, "Medicine question is displayed");
		click(medicalHistoryFirstYesOption, "Clicked on 'yes' for medicine history");
		
		boolean medicationText = true;
		for (int i = 0; i < medicationsOptions.size(); i++) {
			 String s = medicationsOptions.get(i).getText();
			 if (!s.equalsIgnoreCase("Medication name "+(i+1))) 
			 {
				 medicationText = false;
			}
			
		}
		click(medicationsOptions.get(0));
		sendKeys(textBox, "paracetomal");
		click(submit);
		isDisplayed(calender);

		// Click on the previous month arrow and select a date
		click(previousMonthArrow, "Clicked on Previous Month Arrow");
		click(date, "Clicked on a Date");

		// Get the selected date from the UI
		String selectedDateText = selectedDate.getText();

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Get the first day of the previous month
		LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);

		// Format the date as "dd/MMM/yyyy"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		String formattedDate = firstDayOfPreviousMonth.format(formatter);

		// Print the result
		System.out.println("First Day of Previous Month: " + formattedDate);

		// Assert that the selected date matches the expected first day of the previous
		// month
		Assert.assertEquals(selectedDateText, formattedDate,
				"Selected date matches the first day of the previous month");
		click(submit);
		
		//to
		click(previousMonthArrow, "Clicked on Previous Month Arrow");
		click(toDate, "Clicked on a Date");

		// Get the selected date from the UI
		String selectedToDateText = selectedDate.getText();

		// Get the current date
		LocalDate currentDate1 = LocalDate.now();

		// Get the first day of the previous month
		LocalDate lastDayOfPreviousMonth = currentDate1.minusMonths(1).withDayOfMonth(28);

		// Format the date as "dd/MMM/yyyy"
		String formattedToDate = lastDayOfPreviousMonth.format(formatter);

		// Print the result
		System.out.println("First Day of Previous Month: " + formattedToDate);

		// Assert that the selected date matches the expected first day of the previous
		// month
		Assert.assertEquals(selectedToDateText, formattedToDate,
				"Selected date matches the first day of the previous month");
		click(submit);
		
	}

	public WebElement scrollToOneOfSixQuestions() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"2 of 6 questions\").instance(0))"));

	}

	public WebElement scrollToAnkle() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Ankle\").instance(0))"));

	}

	public WebElement scrollToFourOfSixQuestions() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"5 of 6 questions\").instance(0))"));

	}
//		return getDriver().findElement(AppiumBy.androidUIAutomator(
//
//				("new UiScrollable(new UiSelector()).scrollIntoView(text(\"4 of 6 questions\"));")));

	public void updatePhysicalExaminationQuestionsAndVerify() throws InterruptedException {

		/// locators are not present
		click(physicalExaminationSummaryChangeIcon, "Clicked on Physical Examination Summary Change Icon");

		isDisplayed(physicalExamHeader, "Physical Examination Header is displayed");

		scrollUp();
		// click(oneOfSixQuestionsYesOption, "Clicked on 'Yes' option for One of Six
		// Questions");
		click(pale, "Clicked on 'Pale'");
		click(slow, "Clicked on 'Slow'");
		click(clubbing, "Clicked on 'Clubbing'");
		click(nailsArePale, "Clicked on 'Nails are Pale'");
		click(inLeft, "Clicked on 'In Left'");

		// isDisplayed(oneOfSixQuestionsYesOption, "One of Six Questions - 'Yes' option
		// is displayed");
		isDisplayed(pale, "'Pale' option is displayed");
		isDisplayed(slow, "'Slow' option is displayed");
		isDisplayed(clubbing, "'Clubbing' option is displayed");

		scrollDown();
		isDisplayed(nailsArePale, "'Nails are Pale' option is displayed");
		isDisplayed(inLeft, "'In Left' option is displayed");
	}

	public void verifyChangesUpdatedInMedicalHistorySummaryScreen() throws InterruptedException {
		// Retry clicking the "Patient History Change" button up to 5 times
		int maxRetries = 5;
		for (int i = 0; i < maxRetries; i++) {
			try {
				click(patientHistoryChangeButton, "Clicked on Patient History Change Button");
				break; // Break out of the loop if the click is successful
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// Log or handle the exception
				e.printStackTrace();
			}
		}

		// click(patientHistoryChangeButton, "Clicked on Patient History Change
		// Button");

		// isDisplayed(medicalHistorySummaryScreenHeader, "Medical History Summary
		// Screen Header is displayed");

		// Scroll to four of six questions and update options
		scrollToFourOfSixQuestions();
		click(denied, "Clicked on 'Denied'");
		click(patientHistoryConfirmButton, "Clicked on Confirm Button");
		click(noneOfTheAbove, "Clicked on 'None of the Above'");
		click(familyHistoryConfirmButton, "Clicked on Confirm Button");
		isDisplayed(denied, "Denied is displayed");
		isDisplayed(noneOfTheAbove, "None of the Above is Displayed");

		// click(FamilyHistoryChangeButton, "Clicked on Confirm Button");
	}

	public void verifyFamilyHistoryDescribeSection() throws InterruptedException {
		// Verify the display of family history header and question
		isDisplayed(familyHistoryHeader, "Family History Header is displayed");
		isDisplayed(familyHistoryQuestion, "Family History Question is displayed");

		// Click on the 'High BP' option
		click(highBP, "Clicked on 'High BP'");
		
		// Verify the display of 'Father' option and click on it
		isDisplayed(father, "'Father' is displayed");
		click(father, "Clicked on 'Father'");

		// Click on 'Other' option, enter text in the text box, and submit
		click(otherOption, "Clicked on 'Other' option");
		sendKeys(textBox, "Testing");
		click(submit, "Clicked on Submit");
	}

	public void verifyClickingPhotoInOneOfSixQuestions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsTakePictureOption, "Clicked on Take a picture ");
		click(imageCaptureButton, "Clicked on + icon ");
		click(takePhoto, "Clicked on Take a photo");
		click(camera, "Clicked on camera icon");
		click(photoCaptureButton, "Clicked on photo capture Button");
//		int maxRetries = 5;
//		for (int i = 0; i < maxRetries; i++) {
//		    try {
//		    	isDisplayed(pictureTakenMsg, "Picture Taken Msg is Displayed");
//		    } catch (NoSuchElementException | StaleElementReferenceException e) {
//		        // Log or handle the exception
//		        e.printStackTrace();
//		    }
//		}

		isDisplayed(capturedImage, "Captured Image is Displayed");
		isDisplayed(addImage, "Add Image Field is Displayed");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, " 2 of 6 Questions is Displayed");

	}

	public void verifyClickingPhotoInTwoOfSixQuestions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No ");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(twoOfQuestionsTakeAPictureOption, "Clicked on Take a photo");
		click(imageCaptureButton, "Clicked on + icon ");
		click(takePhoto, "Clicked on Take a photo");
		click(camera, "Clicked on camera icon");
		click(photoCaptureButton, "Clicked on photo capture button");
//		int maxRetries = 5;
//		for (int i = 0; i < maxRetries; i++) {
//		    try {
//		    	isDisplayed(pictureTakenMsg, "Picture Taken Msg is Displayed");
//		    } catch (NoSuchElementException | StaleElementReferenceException e) {
//		        // Log or handle the exception
//		        e.printStackTrace();
//		    }
//		}
		isDisplayed(capturedImage, "Captured Image is Displayed");
		isDisplayed(addImage, "Add Image Field is Displayed");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, " 3 of 6 Questions is Displayed");
	}

	public void verifyClickingPhotoInFourOfSixQuestions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");

		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(normal, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");
		click(threeOfSixQuestionsNormalOption, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "4 of 6 Questons is Displyed");
		click(fourOfSixQuestionsTakeAPictureOption, "Clicked on Take a picture");
		click(imageCaptureButton, "Clicked on + icon ");
		click(takePhoto, "Clicked on Take a photo");
		isDisplayed(camera, "Camera option is Displayed");
		// click(photoCaptureButton,"Clicked on photo capture button");
		isDisplayed(flashIcon, "Flash Icon is Displayed");
		isDisplayed(cancelOption, " Cancel is Displyed");

	}

	public void verifyClickingPhotoInFiveOfSixQuestions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(normal, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");
		click(threeOfSixQuestionsNormalOption, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "4 of 6 Questons is Displyed");
		click(nailsAreNormal, "Clicked  on nails are normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "5of 6 Questons is Displyed");
		click(fiveOfSixQuestionsTakeAPictureOption, "Clicked on Take a picture");
		click(imageCaptureButton, "Clicked on + icon ");
		click(takePhoto, "Clicked on Take a photo");
		click(camera, "Clicked on camera icon");
		click(photoCaptureButton, "Clicked on photo capture button");
		isDisplayed(capturedImage, "Captured Image is Displayed");
		isDisplayed(addImage, "Add Image Field is Displayed");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "6 of 6 Questons is Displyed");

	}

	public void verifyClickingPhotoInSixOfSixQuestions() throws InterruptedException {
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(normal, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");
		click(threeOfSixQuestionsNormalOption, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "4 of 6 Questons is Displyed");
		click(nailsAreNormal, "Clicked  on nails are normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "5 of 6 Questons is Displyed");
		click(fiveOfSixQuestionsNailsAreNormalOption, "Clicked  on nails are normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "6 of 6 Questons is Displyed");
		click(sixOfSixQuestionsTakeAPictureOption, "Clicked on Take a picture");
		click(imageCaptureButton, "Clicked on + icon ");
		click(takePhoto, "Clicked on Take a photo");
		click(camera, "Clicked on camera icon");
		click(photoCaptureButton, "Clicked on photo capture button");
		isDisplayed(capturedImage, "Captured Image is Displayed");
		isDisplayed(addImage, "Add Image Field is Displayed");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(physicalExaminationSummaryHeader, "3/4. Physical examination summary screen is Displayed");

	}

	public void verifyPictureUploadedFromGalleryInOneOfSix() throws InterruptedException {
		/*
		 * launchCamera(); click(shutterButton, "Clicked on capture button");
		 * activateIntelehealth();
		 */
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsTakePictureOption, "Clicked on Take a picture ");
		click(imageCaptureButton, "Clicked on + icon ");
		click(chooseFromGallery, "Clicked on Choose from gallery");
		click(gallery, "Clicked on galleery");
		click(image, "Selected the image");
		isDisplayed(capturedImage, "Image is added ");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
	}

	public void verifyPictureUploadedFromGalleryInTwoOfSix() throws InterruptedException {
		/*
		 * launchCamera(); click(shutterButton, "Clicked on capture button");
		 * activateIntelehealth();
		 */
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(twoOfQuestionsTakeAPictureOption, "Clicked on Take a photo");
		click(imageCaptureButton, "Clicked on + icon ");
		click(chooseFromGallery, "Clicked on Choose from gallery");
		click(gallery, "Clicked on photos");
		click(image, "Selected the image");
		isDisplayed(capturedImage, "Image is added ");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");

	}

	public void verifyPictureUploadedFromGalleryInFourOfSix() throws InterruptedException {
		/*
		 * launchCamera(); click(shutterButton, "Clicked on capture button");
		 * activateIntelehealth();
		 */
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(normal, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");
		click(threeOfSixQuestionsNormalOption, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "4 of 6 Questons is Displyed");
		click(fiveOfSixQuestionsTakeAPictureOption, "Clicked on Take a picture");
		click(imageCaptureButton, "Clicked on + icon ");
		click(chooseFromGallery, "Clicked on Choose from gallery");
		click(gallery, "Clicked on photos");
		click(image, "Selected the image");
		isDisplayed(capturedImage, "Image is added ");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "5 of 6 Questons is Displyed");
	}

	public void verifyPictureUploadedFromGalleryInFiveOfSix() throws InterruptedException {
		/*
		 * launchCamera(); click(shutterButton, "Clicked on capture button");
		 * activateIntelehealth();
		 */
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(normal, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");
		click(threeOfSixQuestionsNormalOption, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "4 of 6 Questons is Displyed");
		click(nailsAreNormal, "Clicked  on nails are normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "5of 6 Questons is Displyed");
		click(fiveOfSixQuestionsTakeAPictureOption, "Clicked on Take a picture");
		click(imageCaptureButton, "Clicked on + icon ");
		click(chooseFromGallery, "Clicked on Choose from gallery");
		click(gallery, "Clicked on photos");
		click(image, "Selected the image");
		isDisplayed(capturedImage, "Image is added ");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "6 of 6 Questons is Displyed");
	}

	public void verifyPictureUploadedFromGalleryInSixOfSix() throws InterruptedException {
		/*
		 * launchCamera(); click(shutterButton, "Clicked on capture button");
		 * activateIntelehealth();
		 */
		isDisplayed(QuestionsNumbersInPhysicalExamination, "1 of 6 questions is Displayed");
		click(oneOfSixQuestionsNoOption, "Clicked on No");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "2 of 6 Questons is Displyed");
		click(normal, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "3 of 6 Questons is Displyed");
		click(threeOfSixQuestionsNormalOption, "Clicked on normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "4 of 6 Questons is Displyed");
		click(nailsAreNormal, "Clicked  on nails are normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "5of 6 Questons is Displyed");
		click(fiveOfSixQuestionsNailsAreNormalOption, "Clicked  on nails are normal");
		isDisplayed(QuestionsNumbersInPhysicalExamination, "6 of 6 Questons is Displyed");
		click(sixOfSixQuestionsTakeAPictureOption, "Clicked on Take a picture");
		click(imageCaptureButton, "Clicked on + icon ");
		click(chooseFromGallery, "Clicked on Choose from gallery");
		click(gallery, "Clicked on photos");
		click(image, "Selected the image");
		isDisplayed(capturedImage, "Image is added ");
		click(uploadButton, "Clicked on Upload button");
		isDisplayed(physicalExaminationSummaryHeader, "3/4. Physical examination summary screen is Displayed");
	}

	public void choosePhotoFrommGallery() {
		
		click(chooseFromGallery, "Clicked on Choose from gallery");
		click(gallery, "Clicked on photos");
		click(image, "Selected the image");
		isDisplayed(capturedImage, "Image is added ");
		click(uploadButton, "Clicked on Upload button");
	}
	
	public WebElement scrollToAdditionalDocuments() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Add additional documents (0)\").instance(0))"));

	}

	public void sendVisit() throws InterruptedException {
		handleHypertensionVisitQuestions();
		answerPhysicalExaminationQuestions();
		answerMedicalHistoryQuestions();

		enterDataInTextBoxAndSubmit();
		click(familyHistoryConfirmButton, "Clicked on confirm button");
		scrollToAdditionalDocuments();
		click(specialitySpinner, "Clicked on Speiality Spinner");
		click(pediatrician, "Selects a Specialization");
		click(sendVisitButton, "Clicked on Send Visit Button");
		click(sendVisitPopupYesButton, "Selects Yes ");
		isDisplayed(uploading, "Uploading Msg is Displayed");
		isDisplayed(successMsg, "Visit Sent Successfully Msg is Displayed");

	}
}