package com.qa.saucedemo.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.qa.saucedemo.factory.PlaywrightFactory;
import com.qa.saucedemo.listeners.ExtentTestManager;
import com.qa.saucedemo.logger.Log;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Properties;

import static com.qa.saucedemo.listeners.ExtentManager.getInstance;

public class BaseTest {
    PlaywrightFactory playwrightFactory;
    protected static ExtentReports extent;
    protected static ExtentTest logger;
    protected Page page;
   protected Properties prop;


    private static void initializeLogger() {
         extent = getInstance();
         logger=ExtentTestManager.getTest();
    }
    @BeforeSuite
    public void setup(){
        initializeLogger();


    }

    @BeforeMethod
    public void beforeFunction(Method method){
        //logger= extent.createTest(method.getName());

        playwrightFactory=new PlaywrightFactory();
        prop= playwrightFactory.init_prop();

        page=playwrightFactory.initBrowser(prop.getProperty("browser").trim());
        Log.info("Browser launched");

    }





    @AfterMethod
    public void tearDown(){
        page.context().browser().close();
        Log.info("Browser closed");

    }
}
