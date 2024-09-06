package com.qa.saucedemo.logintests;

import com.microsoft.playwright.Page;
import com.qa.saucedemo.factory.PlaywrightFactory;
import com.qa.saucedemo.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest {
    Page page;
    @Test
    public void loginToApp(){

        PlaywrightFactory playwrightFactory=new PlaywrightFactory();
        page=playwrightFactory.initBrowser("chrome");

        LoginPage loginPage=new LoginPage(page);
        loginPage.loginToApp("standard_user","secret_sauce");


    }
}
