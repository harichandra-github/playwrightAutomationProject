package com.qa.saucedemo.logger;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger(Log.class);
    private static ExtentTest test;  // Your ExtentTest instance

    // Pass the ExtentTest instance to the Log class
    public static void setExtentTest(ExtentTest testInstance) {
        test = testInstance;
    }

    public static void startTestCase(String testCaseName){
        Log.info("****************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+testCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.info("****************************************************************************************");
    }

    public static void endTestCase(){
        Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
        Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

    public static void info(String message) {
        logger.info(message);

    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Exception e) {
        logger.error(message, e);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }



}
