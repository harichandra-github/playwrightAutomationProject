package com.qa.saucedemo.tests;

import com.aventstack.extentreports.Status;
import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.pages.HomePage;
import com.qa.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {





    @Test
    public void loginToApp() throws InterruptedException {


        Log.info("Trying to login");
       // ExtentTestManager.getTest().log(Status.INFO, "Logging steps1 in reports");
        logger.log(Status.INFO, "Logging steps1 in reports");


        LoginPage loginPage=new LoginPage(page);
        HomePage homePage=new HomePage(page);
        //logger.info("Logging to app");


       // loginPage.loginToApp("standard_user","secret_sauce");
        loginPage.loginToApp(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertEquals(homePage.getPageTitle(), AppConstants.title);
        Assert.assertEquals(homePage.getPageURL(),AppConstants.url);
        Log.info("Login Success");
        //logger.log(Status.PASS,"Test Pass");
        //ExtentTestManager.getTest().log(Status.PASS, "Step1 completed successfully");
        logger.log(Status.PASS, "Step1 completed successfully");


    }

    @Test
    public void navigateToHome(){
        LoginPage loginPage=new LoginPage(page);
        HomePage homePage=new HomePage(page);
        //logger.info("Logging to app");
        //ExtentTestManager.getTest().log(Status.INFO, "Logging steps2 in reports");
        logger.log(Status.INFO, "Logging steps2 in reports");


        loginPage.loginToApp(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertEquals(homePage.getPageURL(),AppConstants.url);
        //logger.log(Status.PASS,"Test Pass");
       // ExtentTestManager.getTest().log(Status.PASS, "Step2 completed successfully");
        logger.log(Status.PASS, "Step2 completed successfully");

    }






}
