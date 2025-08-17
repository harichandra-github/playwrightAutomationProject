package com.qa.saucedemo.listeners;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Retrieve the current thread's ExtentTest instance
    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    // End the test
    public static synchronized void endTest() {
        extentTest.remove();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    // Start a new test for the current thread
    public static synchronized void startTest(String testName) {
        ExtentTest test = ExtentManager.getInstance().createTest(testName);
        extentTest.set(test); // Store the test in ThreadLocal for the current thread
    }
}








