package com.qa.saucedemo.tests;

import com.microsoft.playwright.Page;
import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.factory.PlaywrightFactory;
import com.qa.saucedemo.pages.HomePage;
import com.qa.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {





    @Test
    public void loginToApp() throws InterruptedException {



        LoginPage loginPage=new LoginPage(page);
        HomePage homePage=new HomePage(page);


        loginPage.loginToApp("standard_user","secret_sauce");
        Assert.assertEquals(homePage.getPageTitle(),"Swag Labs");
        Assert.assertEquals(homePage.getPageURL(),"https://www.saucedemo.com/inventory.html");

    }

//    @Test
//    public void navigateToHome(){
//        LoginPage loginPage=new LoginPage(page);
//        HomePage homePage=new HomePage(page);
//
//
//        loginPage.loginToApp("standard_user","secret_sauce");
//        Assert.assertEquals(homePage.getPageTitle(),"Products");
//        Assert.assertEquals(homePage.getPageURL(),"https://www.saucedemo.com/inventory.html");
//
//    }






}
