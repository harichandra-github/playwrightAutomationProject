package com.qa.saucedemo.pages;

import com.microsoft.playwright.Page;
import com.qa.saucedemo.logger.Log;

public class LoginPage {

    Page page;
    //Locators
    private String username = "//input[@id='user-name']";
    private String password = "//input[@id='password']";
    private String loginButton = "//input[@id='login-button']";


    public LoginPage(Page page) {
        this.page = page;
    }

    public void loginToApp(String userName, String pass) {
        Log.info("Going to login to the Application for " + userName);

        page.locator(username).fill(userName);
        page.locator(password).fill(pass);
        page.click(loginButton);
        Log.info("Login Successful");
    }
}


