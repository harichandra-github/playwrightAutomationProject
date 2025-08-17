package com.qa.saucedemo.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.utilities.CommonFunctions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class ExtentManager implements ITestListener {

    private static ExtentReports extent;

    private static String fileSeparator = "./Reports/";
    private static String reportFilepath = fileSeparator + "TestReport/";

    private static String timestamp = CommonFunctions.getTimeStamp();
    private static String reportFileName = timestamp + "_platform_report_" + ".html";
    private static String reportFileLocation = System.getProperty("user.dir") + "/Reports/TestReport/" + reportFileName;


    int passed = 0, failed = 0, skipped = 0;




    // Method to create an ExtentReports instance
    private static ExtentReports createInstance() {
        Properties testConfig = new Properties(System.getProperties());
        try (FileInputStream fileInput = new FileInputStream(AppConstants.CONFIG_FILE_PATH)) {
            testConfig.load(fileInput);

            // Override with environment variables if present
            for (String key : testConfig.stringPropertyNames()) {
                String envValue = System.getenv(key);
                if (envValue != null) {
                    testConfig.setProperty(key, envValue);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String fileName = getReportPath(reportFilepath);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle(reportFileName);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Automation Report");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");




        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        

        // Dynamically set environment details
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Host Name", getHostName());
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser",testConfig.getProperty("browser","chrome"));
        extent.setSystemInfo("Browser",testConfig.getProperty("browser","chrome"));
        extent.setSystemInfo("Headless Mode",testConfig.getProperty("headless","false"));
        extent.setSystemInfo("Incognito Mode",testConfig.getProperty("incognito", "false"));




        return extent;
    }
    
    private static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown Host";
        }
    }
    

    // Singleton to get instance of ExtentReports
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }
    
    
    
    
    

    private static String getReportPath(String path) {
        File reportsDirectory = new File("./Reports");
        File testDirectory = new File(path);

        if (!reportsDirectory.exists()) {
            if (reportsDirectory.mkdir()) {
                Log.info("Reports directory created!");
            } else {
               Log.error("Failed to create Reports directory.");
                return System.getProperty("user.dir");
            }
        }

        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                Log.info("TestReport directory created!");
                return reportFileLocation;
            } else {
               Log.error("Failed to create TestReport directory.");
                return System.getProperty("user.dir");
            }
        } else {
            Log.info("Directory already exists: " + path);
        }
        return reportFileLocation;
    }

    // TestNG listener methods
    @Override
    public void onStart(ITestContext context) {
        Log.info("*** Test Suite " + context.getName() + " started ***");

    }

    @Override
    public void onFinish(ITestContext context) {
        int total = passed + failed + skipped;
        extent.setSystemInfo("Total Tests", String.valueOf(total));
        extent.setSystemInfo("Passed", String.valueOf(passed));
        extent.setSystemInfo("Failed", String.valueOf(failed));
        extent.setSystemInfo("Skipped", String.valueOf(skipped));
        Log.info("*** Test Suite " + context.getName() + " ending ***");
        Log.info("*** Test Suite " + context.getName() + " ending ***");
        Log.info("*** Total Tests: " + total);
        Log.info("*** Passed: " + passed);
        Log.info("** Failed: " + failed);
        Log.info("*** Skipped: " + skipped);
        ExtentManager.getInstance().flush();  // Ensure flushing happens only once at the end of the test suite
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("*** Running test method " + result.getMethod().getMethodName() + "...");
        //ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passed++;
        Log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failed++;
        Log.error("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipped++;
        Log.warn("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.warn("*** Test failed but within success percentage " + result.getMethod().getMethodName() + "...");
    }
}
