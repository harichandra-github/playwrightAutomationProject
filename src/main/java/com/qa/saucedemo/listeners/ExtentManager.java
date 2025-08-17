package com.qa.saucedemo.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.saucedemo.utilities.CommonFunctions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExtentManager implements ITestListener {

    private static ExtentReports extent;

    private static String fileSeparator = "./Reports/";
    private static String reportFilepath = fileSeparator + "TestReport/";

    private static String timestamp = CommonFunctions.getTimeStamp();
    private static String reportFileName = timestamp + "_platform_report_" + ".html";
    private static String reportFileLocation = System.getProperty("user.dir") + "/Reports/TestReport/" + reportFileName;


  
    
    
    // Method to create an ExtentReports instance
    private static ExtentReports createInstance() {
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
                System.out.println("Reports directory created!");
            } else {
                System.out.println("Failed to create Reports directory.");
                return System.getProperty("user.dir");
            }
        }

        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("TestReport directory created!");
                return reportFileLocation;
            } else {
                System.out.println("Failed to create TestReport directory.");
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }

    // TestNG listener methods
    @Override
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " ending ***");
        ExtentManager.getInstance().flush();  // Ensure flushing happens only once at the end of the test suite
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("*** Running test method " + result.getMethod().getMethodName() + "...");
        ExtentTestManager.startTest(result.getMethod().getMethodName());

       // ExtentTestManager.getTest().log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within success percentage " + result.getMethod().getMethodName() + "...");
    }
}
