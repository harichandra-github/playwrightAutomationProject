package com.qa.saucedemo.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.factory.PlaywrightFactory;
import com.qa.saucedemo.listeners.ExtentTestManager;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.utilities.CommonFunctions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import static com.qa.saucedemo.listeners.ExtentManager.getInstance;

public class BaseTest {
    PlaywrightFactory playwrightFactory;
    protected static ExtentReports extent;
    protected static ExtentTest logger;
    protected Page page;
    protected static Properties testConfig;
    protected static Properties prop;


    private static Properties loadConfig() throws IOException {
        testConfig = new Properties();
        try (FileInputStream file = new FileInputStream(AppConstants.CONFIG_FILE_PATH)) {
            testConfig.load(file);
        }
        // Override with environment variables if present
        for (String key : testConfig.stringPropertyNames()) {
            String envValue = System.getenv(key);
            if (envValue != null) {
                testConfig.setProperty(key, envValue);
                Log.info("Overriding property '{}' with environment variable '{}'", key, envValue);
            }
        }
        return testConfig;
    }

    private static void initializeLogger() {
        extent = getInstance();
    }

    @BeforeSuite
    public void setUpSuite() {
        Log.info("Setting up the test suite...");
        initializeLogger();
        Log.info("Test suite setup complete.");
    }

    @AfterSuite
    public void tearDownSuite() {
        Log.info("Tearing down the test suite...");
        if (extent != null) {
            extent.flush();
        }
        Log.info("Test suite teardown complete.");
    }

    @BeforeTest
    public void setUpTest() throws IOException {
        Log.info("Setting up the test class...");
        loadConfig();
        playwrightFactory = new PlaywrightFactory();
        prop = playwrightFactory.init_prop();
        page = playwrightFactory.initBrowser(prop);
        Log.info("Browser launched");

    }


    @BeforeMethod
    public void setupMethod(Method method) {
        Log.info("Setting up the test method: " + method.getName());
        logger = extent.createTest(method.getName());
        ExtentTestManager.setTest(logger);
        logger.log(Status.INFO, "----- Starting test method: " + method.getName() + " -----");
        Log.info("-------------------Starting test method: " + method.getName() + " ------------");

    }

    @AfterTest
    public void tearDownTest() {
        try {
            if (page != null) {
                page.close();
                Log.info("Browser closed successfully");
            }
        } catch (Exception e) {
            // Log exception if logout or closing browser fails
            Log.error("Error in @AfterTest (Logout or Close): " + e.getMessage());
            if (logger != null) {
                logger.log(Status.FAIL, "Logout or Browser close failed: " + e.getMessage());
            }
            throw new RuntimeException(e);
        }
    }


    @AfterTest
    public void tearDown() {
        page.context().browser().close();
        Log.info("Browser closed");

    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        try {
            // Process test result after each test
            processTestResult(result);
        } catch (Exception e) {
            // Log exception if result processing fails
            Log.error("Error in @AfterMethod: " + e.getMessage());
            if (logger != null) {
                logger.log(Status.FAIL, "Failed to process test result: " + e.getMessage());
            }
            throw new RuntimeException(e);
        }
    }


    private void processTestResult(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.log(Status.FAIL, "Test Failed: " + result.getName());
                logger.log(Status.FAIL, result.getThrowable());

                String screenshotDirectory = System.getProperty("user.dir") + "/screenshots/";
                File directory = new File(screenshotDirectory);
                if (!directory.exists()) directory.mkdir();

                String timestamp = CommonFunctions.getTimeStamp();
                String fileName = result.getName() + "_" + timestamp + ".png";
                String screenshotPath = screenshotDirectory + fileName;

                byte[] screenshotBytes = null;
                String base64Screenshot = null;

                try {
                    // Capture screenshot and encode
                    screenshotBytes = page.screenshot(
                            new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true)
                    );
                    base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

                    // Attach to report
                    logger.fail(
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, fileName).build());

                    Log.info("Screenshot saved and attached: " + screenshotPath);
                } catch (Exception ex) {
                    // Log failure to attach screenshot
                    logger.warning("Screenshot could not be captured or attached.");
                    Log.warn("Screenshot not attached due to error: " + ex.getMessage());
                }

            } else if (result.getStatus() == ITestResult.SKIP) {
                Log.warn("-----------------Test Skipped: " + result.getName() + "--------------");
                logger.log(Status.SKIP, "Test Skipped: " + result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                Log.info("----------------Test Passed: " + result.getName() + " --------------");
                logger.log(Status.PASS, "Test Passed: " + result.getName());
            }
        } catch (Exception e) {

            Log.error("Error in processTestResult: " + e.getMessage());
            logger.log(Status.FAIL, "Error during result processing: " + e.getMessage());
        }
    }
}
