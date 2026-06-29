# Intelehealth-Mobile -Automation 

Appium mobile test automation framework with Page Object Model design using Java + Maven + TestNG.
Framework follows many of the industry best practices and supports Android.

##Project overview
 - Total Testcases - 680
 - Regression Testcases - 373
 - High Priority Regression Testcases (Automated) - 243

##Introduction on intelehealth app

  - Intelehealth is an Medical domain Non Profitable Organization Intelehealth leverages open-source technology for 
    Governments, NGO’s and Hospitals to seamlessly connect hard to reach population with high quality primary healthcare! 
    
Technologies/Tools used in building the framework
===============================================
- Eclipse - IDE
- Appium - Mobile Automation library
- Maven - Build automation tool
- Java - Programming language
- TestNG - Test Management library
- Log4J - Logging framework
- Extent Reports - Reporting framework
- JSON - Test Data
- XML - Static text


 Appium Installation Steps
===========================

Important setup notes

-> Be an admin on your Windows/Mac
-> Use latest Windows/MacOS operating system
-> Office machine? make sure anti-virus and company policies are not blocking installation of Appium and associated softwares
"-> If practicing using an Android emulator, use a powerful processor and sufficient RAM"
-> Avoid using phone from Chinese manufacturers that may restrict Appium due to their security limitations


Install Appium Command Line Interface (CLI) server
==================================================
-> Commands to check if node and NPM (Node Package Manager) are installed:
node -v
npm -v
-> Install node.js (NPM is included) from link - https://nodejs.org/en/download/
Important note: Use the LTS and not current version.
-> Command to install Appium using npm: npm install -g appium@next
Note: @next will not be required once Appium 2.0 stable release is out to market.
-> Command to install specific version: npm install -g appium@<verion_number>
-> Command to start Appium: appium
-> Command to get installation location: where appium
-> Command to uninstall Appium: npm uninstall -g appium


Install UiAutomator2 driver (using Appium CLI)
==============================================
Get help: appium driver --help (or -h)
Get list of officially supported drivers: appium driver list
Install driver: appium driver install uiautomator2
Install driver with specific version: appium driver install uiautomator2@<version_number>


Install Appium Inspector
========================
-> Download and install from https://github.com/appium/appium-inspector/releases


Install JAVA JDK and configure environment variables
====================================================
-> Command to check if JAVA is already installed: java -version
-> JAVA JDK download link: https://www.oracle.com/technetwork/java/javase/downloads/index.html
Important note: Please use Java 8/11/15 for now. Don't use Java 16 or higher. The current Appium Java Client 8.x.x is not compatible with Java 16+. You may use Java 16+ once Appium Java client becomes compatible.
-> Create JAVA_HOME system environment variable and set it to JDK path (without bin folder). 
Edit PATH system environment variable and add %JAVA_HOME%\bin
Note: Usually JDK path is "C:\Program Files\Java\<your_jdk_version>"


Install Android Studio and configure environment variables
==========================================================
-> Android Studio download link: https://developer.android.com/studio
-> Create ANDROID_HOME system environment variable and set it to SDK path. 
"Edit PATH system environment variable and add below,"
%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\cmdline-tools


Verify installation using appium-doctor
=======================================
Command to install appium-doctor: npm install -g appium-doctor
Command to get help: appium-doctor --help
Command to check Android setup: appium-doctor --android 


Emulator Setup: Accelerate Performance
======================================
Launch Android Studio -> SDK Manager -> SDK Tools
Intel processor: Check "Intel x86 Emulator Accelerator (HAXM Installer)" and Apply
AMD processor: Check "Android Emulator Hypervisor Driver for AMD Processors (installer)" and Apply


Emulator Setup: Create AVD and start it 
=======================================
Important note: AVDs are resource hungry! Please use a laptop with powerful processor (that supports Intel HAXM/AMD hypervisor) and sufficient RAM.
Open Android Studio -> Configure -> Virtual Device Manager -> Create Virtual Device -> 
Select Model -> Download Image for desired OS version if not already downloaded 
-> Start AVD


Emulator Setup: Create Driver Session using Appium CLI
===============================================
Download link for dummy app:
https://github.com/appium/appium/tree/master/packages/appium/sample-code/apps
[Also available for download from the lecture's resources section]


Real Device Setup: Enable USB debugging on Android mobile
===============================================
Note: Steps can differ based on the phone manufacturer!
-> Settings -> System -> About Phone -> Click Build Number 7-8 times
-> Settings -> Developer Options -> Enable USB Debugging
-> Permission pop-up: Check the box and press Allow to recognise the computer
-> run "adb devices" in CMD prompt to check if device is recognised
-> USB drivers:
Google: https://developer.android.com/studio/run/win-usb
OEMs: https://developer.android.com/studio/run/oem-usb



Project Structure
===============================================

 Intelehealth-Mobile-Automation/
|-- src/
|   |-- main/
|   |   |-- resources/
|   |       |-- config.properties
|   |       |-- log4j.properties
|   |       
|   |          
|   |           
|   |               
|   |-- test/
|       |-- java/
|       |   |-- base/
|       |   |   |-- BaseTest.java
|       |   |-- test/
|       |   |   |-- AppSetupTest.java
                |-- ... (more test classes)          
|       |   |-- utils/
|       |   |   |-- TestUtils.java
|       |   
|       |   |-- report/
|       |   |   |-- ExtentReport.java
|       |   |-- pages/
|       |   |   |-- AppSetupPage.java
|       |       |-- ... (more page classes)
|       |   |-- listeners/
|       |       |-- TestListener.java
|       |-- resources/
|           |-- app/
|           |   |-- Intelehealth.apk
|           |-- data/
|               |-- appData.json
|-- target/
|-- test-output/
|-- pom.xml
|-- testng.xml
|-- README.md
|-- .gitignore

## Setup

1. Clone the repository:
   git clone https://github.com/Intelehealth/QA-Automation-MobileApp
2. Update the Appium installation path and Node installation path
3. Update the device details in XML file.


##Framework Details
 - Takes screenshots on test failures
 - Record the videos of Test execution
 - Extent reports is used for Reporting
 
 ##Test Script Design:
 - All the Regression testcases with High priority are selected for Automation.
 - All the Locators are stored in Page File as per the Module using @AndroidFindBy and By   class with variable name
 - Methods are created as per the Testcase and the Modules.
 - Test scripts are stored in a separate class files based on Modules.
 - Each testcases are stored in TestNG annotations @Test methods.
 - @BeforeMethod and @AfterMethod will store the line of code for prerequisite and postrequsite.
 
## Test Data Management

- The `data` folder contains a JSON file (`appData.json`) that includes the required test data.
- The framework utilizes an API for test data generation to ensure the availability of up-to-date and relevant data for testing purposes.

 
## Test Execution Plan

1. **Run Appium Server Manually:**
   - Start the Appium server manually (in case the appium server fails to run programmatically).

2. **Execute Test Cases Using XML File:**
   - Utilize the XML file (`testng.xml`) to execute all the test cases. Ensure that the necessary configurations are set in the XML file.

3. **Selective Test Execution:**
   - Modify the XML file to run specific test scripts based on modules.
     - For example, to run only Login & Dashboard module test cases, adjust the configurations in the XML file accordingly.

   
##Reporting:
 - Extent Report is  used in this framework.
 - Report contains the screenshots of each testcase(fail).
 - Also display the reason for failure testcases.
   
   