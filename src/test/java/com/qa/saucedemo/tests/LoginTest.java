package com.qa.saucedemo.tests;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.pages.HomePage;
import com.qa.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {





    @Test
    public void loginToApp() throws InterruptedException {

        LoginPage loginPage=new LoginPage(page,logger);
        HomePage homePage=new HomePage(page,logger);


       // loginPage.loginToApp("standard_user","secret_sauce");
        loginPage.loginToApp(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertEquals(homePage.getPageTitle(), AppConstants.title);
        Assert.assertEquals(homePage.getPageURL(),AppConstants.url);

    }

    @Test
    public void navigateToHome(){
        HomePage homePage=new HomePage(page, logger);
        Assert.assertEquals(homePage.getPageTitle(),AppConstants.homePageTitle);
        Assert.assertEquals(homePage.getPageURL(),AppConstants.homePageUrl);

    }






}
