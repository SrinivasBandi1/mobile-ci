package com.intelehealth.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CloseVisitsPage extends BaseTest {

    /**
     * modified locators IDA 4.0 TO IDA 5.0 On 8th April 2025 by @SHAILAJAVEMUNOORI
     */
    @AndroidFindBy(id = "org.intelehealth.app:id/textview_close_visit")
    private WebElement lblOpenVisits;

    @AndroidFindBy(id = "org.intelehealth.app:id/backArrow")
    private WebElement backArrow;

    @AndroidFindBy(id = "org.intelehealth.app:id/you_can_add")
    private WebElement recentVisitsTitle;

    @AndroidFindBy(xpath = "(//android.widget.Button[@resource-id='org.intelehealth.app:id/end_visit_btn'])[1]")
    private WebElement closeVisitsButton;

    @AndroidFindBy(id = "org.intelehealth.app:id/dialog_title")
    private WebElement closeVisitPopupTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/this_week']/following-sibling::androidx.recyclerview.widget.RecyclerView//android.widget.Button")
    private WebElement olderVisitsCloseButton;

    @AndroidFindBy(id = "org.intelehealth.app:id/dialog_subtitle")
    private WebElement closeVisitPopupSubTitle;

    @AndroidFindBy(id = "org.intelehealth.app:id/negative_btn")
    private WebElement cancelButton;

    @AndroidFindBy(id = "org.intelehealth.app:id/positive_btn")
    private WebElement confirmButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Feedback']")
    private WebElement feedbackTitle;

    @AndroidFindBy(id = "org.intelehealth.app:id/editText_exit_survey")
    private WebElement feedbackTextField;

    @AndroidFindBy(id = "org.intelehealth.app:id/btn_submit")
    private WebElement submitButton;

    @AndroidFindBy(id = "org.intelehealth.app:id/textview_close_visit_no")
    private WebElement closedVisitsCount;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_patname_txtview'])[1]")
    private WebElement recentVisitsPatientName;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_date_txtview'])[1]")
    private WebElement date;

    @AndroidFindBy(xpath = "(//android.widget.Button[@resource-id='org.intelehealth.app:id/end_visit_btn'])[1]")
    private WebElement recentVisitCloseButton;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc='older visits recycler data']//android.widget.TextView[@content-desc='patient date and time row item']")
    private List<WebElement> olderVisitsTimeStamp;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/you_can_add']/following-sibling::androidx.recyclerview.widget.RecyclerView//android.widget.TextView[@resource-id='org.intelehealth.app:id/fu_date_txtview' ]")
    private List<WebElement> recentVisitsTimeStamp;

    @AndroidFindBy(id = "org.intelehealth.app:id/snackbar_text")
    private WebElement msgThankYouForFeedback;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"recent visits recycler data\"]//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout//android.widget.RelativeLayout//android.widget.TextView[@content-desc='patient date and time row item']")
    private WebElement firstCard;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='org.intelehealth.app:id/fu_cardview_item']")
    private List<WebElement> cards;

    @AndroidFindBy(id = "org.intelehealth.app:id/btnEndVisitRowItem")
    private WebElement btnCloseVisitInCloseVisitsScreen;

    /**
     * updated by @srinivasBandi Added return statement for displayed Method
     */
    // Verify the functionality of the Close Visit button
    public boolean verifyCloseVisitButtonFunctionality() throws InterruptedException {
        click(lblOpenVisits, "Clicked on 'Open Visits'");
        click(backArrow, "Clicked on back arrow");

        // Wait for some time to ensure the elements are loaded
        Thread.sleep(2000);

        // Click on Close Visits again
        click(lblOpenVisits, "Clicked on 'open Visits'");
        click(closeVisitsButton, "Clicked on 'Close Visits' Button");
        return isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title")
                && isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle")
                && isDisplayed(cancelButton, "Close Visit Popup is displayed - Cancel Button")
                && isDisplayed(confirmButton, "Close Visit Popup is displayed - Confirm Button");
    }

    public List<String> getCloseVisitDialogText() {
        return getElementsText(closeVisitPopupTitle, closeVisitPopupSubTitle, cancelButton, confirmButton);
    }

    // Verify functionality of the 'Confirm' button in the close visit popup.
    /**
     * updated by @srinivasBandi Removed is displayed Method as we are verifying
     * them in previous test case IDA4_2239_verifyCloseVisitButtonFunctionality ..
     * Also adding a e
     */
    public void verifyConfirmButtonFunctionality() throws InterruptedException {
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        // Click on Close Visits again
        click(closeVisitsButton, "Clicked on 'Close Visits' Button");

        click(confirmButton, "Clicked on 'Confirm' Button");
        isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
    }

    /**
     * @author @SrinivasBandi
     * @return String
     */

    public String getFeedbackTitle() {
        return feedbackTitle.getText();
    }

    /**
     * @author Srinivas
     * @return String
     */
    public String getThankYouForYourFeedbackMessage() throws Exception {
        return getText(msgThankYouForFeedback, "Message is displayed on the dashboard screen");
    }

    /**
     * 
     * @throws InterruptedException
     */

    // Enter feedback and verify the process
    public void enterFeedbackAndverify() throws InterruptedException {
        click(lblOpenVisits, "Clicked on 'Close Visits'");

        // Click on Close Visits again
        click(closeVisitsButton, "Clicked on 'Close Visits'");
        // click(closeVisitsButton, "Clicked on 'Close Visits' Button");
        click(confirmButton, "Clicked on 'Confirm' Button");
        isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");

        sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
        click(submitButton, "Clicked on 'Submit' Button");
    }

    // Scroll to the 'Older Visits' section and close visits in both recent and

    public WebElement scrollToOlderVisits() {
        return getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Older Visits\").instance(0))"));
    }

    // Close visits in both the Recent and Older Visits sections
    public boolean closeVisitsInRecentAndOlderVisitsSection() throws InterruptedException {
        // Close visits in the Recent Visits section
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        click(closeVisitsButton, "Clicked on 'Close Visits' Button");
        boolean closeVisitTitle = isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title")
                && isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

        click(confirmButton, "Clicked on 'Confirm' Button");
        boolean feedbacktitle = isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
        sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
        click(submitButton, "Clicked on 'Submit' Button");

        // Close visits in the Older Visits section
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        scrollToOlderVisits();
        click(olderVisitsCloseButton, "Clicked on 'Close' Button for Older Visits");
        boolean closevisitTitle = isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
        isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

        click(confirmButton, "Clicked on 'Confirm' Button");
        boolean feedbackTitleHeader = isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
        sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
        click(submitButton, "Clicked on 'Submit' Button");
        return closeVisitTitle && feedbacktitle && closevisitTitle && feedbackTitleHeader;
    }

    /**
     * Updated Logic by @srinivasBandi
     * 
     */
    // Verify elements in the Recent Visits section
    public boolean verifyRecentVisitsSection() throws InterruptedException {
        click(lblOpenVisits, "Clicked on 'Close Visits'");

        return isDisplayed(recentVisitsTitle, "Recent Visits Section - Title") &&

                isDisplayed(recentVisitsPatientName, "Recent Visits Section - Patient Name")
                && isDisplayed(date, "Recent Visits Section - Date")
                && isDisplayed(recentVisitCloseButton, "Recent Visits Section - Close Button");
    }

    public List<String> getRecentVisitTitle() {
        return getElementsText(recentVisitsTitle, recentVisitCloseButton);
    }

    /**
     * Updated Logic by @srinivasBandi
     * 
     */
    // Verify that the count of unclosed visits decreases after closing a visit
    public boolean verifyCloseVisitsCountIsDecreased() throws InterruptedException {
        // Wait for some time to ensure the elements are loaded
        Thread.sleep(5000);

        int decreasedClosedCount = 0;
        int closedCount = 0;

        // Display the initial closed visits count
        isDisplayed(closedVisitsCount, "Closed Visits Count is displayed");
        String inputString = closedVisitsCount.getText();

        // Use a regular expression to match numeric values
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(inputString);

        // Check if a numeric value is found
        if (matcher.find()) {
            // Extract and print the numeric value
            String numericValue = matcher.group();
            closedCount = Integer.parseInt(numericValue);
            System.out.println("Number of unclosed visits: " + closedCount);
        } else {
            System.out.println("No numeric value found in the input string.");
        }

        // Close a visit
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        // scrollToOlderVisits();
        click(closeVisitsButton, "Clicked on 'Close' Button for Older Visits");
        isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
        isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

        click(confirmButton, "Clicked on 'Confirm' Button");
        isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
        sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
        click(submitButton, "Clicked on 'Submit' Button");

        // Wait for some time to ensure the elements are loaded
        Thread.sleep(5000);

        // Display the updated closed visits count
        String dCount = closedVisitsCount.getText();

        // Use a regular expression to match numeric values
        Pattern pattern1 = Pattern.compile("\\d+");
        Matcher matcher1 = pattern.matcher(dCount);

        // Check if a numeric value is found
        if (matcher1.find()) {
            // Extract and print the numeric value
            String numericValue = matcher1.group();
            decreasedClosedCount = Integer.parseInt(numericValue);
            System.out.println("Number of unclosed visits after closing a visit: " + decreasedClosedCount);
        } else {
            System.out.println("No numeric value found in the input string.");
        }

        // Verify if the closed visit count is decreased
        if (decreasedClosedCount == closedCount - 1) {
            return true;
        } else {
            return false;
        }
    }

    // Verify that patients with visits older than seven days are displayed
    public void verifyAboveSevenDaysPatientsAreDisplayed() throws InterruptedException {
        // Step 1: Navigate to the Close Visits section
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        click(backArrow, "Clicked on back arrow");

        // Wait for some time to ensure the elements are loaded
        Thread.sleep(10000);

        // Click on Close Visits again
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        scrollToOlderVisits();

        // Counter for the number of older visits
        int olderVisitsCount = 0;

        // Step 2: Verify each patient visit date
        for (WebElement visitTimeStamp : olderVisitsTimeStamp) {
            String visitDateText = visitTimeStamp.getText();
            System.out.println("Visit Date Text: " + visitDateText);

            // Parse the visit date using the specified format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm a", Locale.ENGLISH);

            // Replace multiple spaces with a single space before parsing
            visitDateText = visitDateText.replaceAll("\\s+", " ");

            try {
                LocalDateTime visitDateTime = LocalDateTime.parse(visitDateText, formatter);

                // Extract the date part for comparison
                LocalDate visitDate = visitDateTime.toLocalDate();

                // Get the current date
                LocalDate currentDate = LocalDate.now();

                // Calculate the difference in days
                long daysDifference = ChronoUnit.DAYS.between(visitDate, currentDate);

                // Check if the visit is 7 days or more old
                if (daysDifference >= 7) {
                    System.out.println("Older visit: " + visitDateText);
                    // You can add further verification or actions as needed

                    // Increment the counter
                    olderVisitsCount++;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date: " + visitDateText);
                // Handle the case where the date cannot be parsed
            }
        }

        // Fail the test case if no older visits are found
        assert olderVisitsCount > 0 : "No visits are above 7 days.";
        System.out.println("Number of older visits: " + olderVisitsCount);
    }

    // Verify that patients with visits from the last seven days are displayed
    public void verifyLastSevenDaysPatientsAreDisplayed() throws InterruptedException {

        click(lblOpenVisits, "Clicked on 'Close Visits'");
        Thread.sleep(2000);
        // Counter for the number of recent visits
        int recentVisitsCount = 0;

        // Step 2: Verify each patient visit date in the "Recent Visits" section
        for (WebElement visitTimeStamp : recentVisitsTimeStamp) {
            String visitDateText = visitTimeStamp.getText();
            System.out.println("Visit Date Text: " + visitDateText);

            // Parse the visit date using the specified format
          //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm a", Locale.ENGLISH);
             visitDateText = visitDateText.replace("at", "").replaceAll("PM", " ").trim(); // becomes "29 Apr 16:57 PM"

            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM 'at' hh:mm a", Locale.ENGLISH);


            // Replace multiple spaces with a single space before parsing
            visitDateText = visitDateText.replaceAll("\\s+", " ");

            try {
            	//failing here 
                LocalDateTime visitDateTime = LocalDateTime.parse(visitDateText, formatter);

                // Extract the date part for comparison
                LocalDate visitDate = visitDateTime.toLocalDate();

                // Get the current date
                LocalDate currentDate = LocalDate.now();

                // Calculate the difference in days
                long daysDifference = ChronoUnit.DAYS.between(visitDate, currentDate);

                // Check if the visit is within the last 7 days
                if (daysDifference <= 7) {
                    System.out.println("Recent visit: " + visitDateText);
                    // You can add further verification or actions as needed

                    // Increment the counter
                    recentVisitsCount++;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date: " + visitDateText);
                // Handle the case where the date cannot be parsed
            }
        }

        // Fail the test case if no recent visits are found
        assert recentVisitsCount > 0 : "No visits are from the last 7 days.";
        System.out.println("Number of recent visits: " + recentVisitsCount);
    }

    /**
     * \
     * 
     * @author @SrinivasBandi
     * @throws ParseException
     */
    // Method to parse the date string into a Date object
    private Date parseDate(String dateText) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM 'at' hh:mm a", Locale.ENGLISH);
        return dateFormat.parse(dateText);
    }

    // Method to check if the extracted date is before the date 7 days ago
    public boolean isOlderThan7Days(WebElement dateElement) throws ParseException {
        Date currentDate = new Date();
        Date date7DaysAgo = new Date(currentDate.getTime() - (7L * 24 * 60 * 60 * 1000));

        String dateText = date.getText();
        Date extractedDate = parseDate(dateText);

        return extractedDate.before(date7DaysAgo);
    }

    // Method to check if all elements in the list have dates not within the last 7
    // days
    public boolean checkAllElementsForOldDates() throws ParseException {
        click(lblOpenVisits, "Clicked on 'Close Visits'");
        int scrollCount = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (!isOlderThan7Days(cards.get(i))) {
                return false; // The date is within the last 7 days, handle accordingly
            }
            if (i == cards.size() - 1) {
                i = 0;
                scrollDown();
                scrollCount++;
            }
            if (scrollCount == 3) {
                break;
            }
        }
        return true; // All dates checked are not within the last 7 days
    }
}