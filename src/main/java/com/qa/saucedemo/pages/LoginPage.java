package com.qa.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import com.qa.saucedemo.logger.Log;

public class LoginPage {

    Page page;
    ExtentTest logger;
    //Locators
    private final String username = "//input[@id='user-name']";
    private final String password = "//input[@id='password']";
    private final String loginButton = "//input[@id='login-button']";
    private final String errorMessage = "h3[data-test='error']";


    public LoginPage(Page page, ExtentTest logger) {
        this.page = page;
        this.logger = logger;
    }

    public void loginToApp(String userName, String pass) {
        try {
            Log.info("Going to login to the Application for " + userName);
            logger.info("Going to login to the Application for " + userName);

            enterUserName(userName);
            enterPassword(pass);
            clickLoginButton();
            Log.info("Login Successful");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPageTitle() {
        try {
            String pagetitle = page.title();
            Log.info("Page title is : " + pagetitle);
            logger.info("Page title is : " + pagetitle);
            return pagetitle;
        } catch (Exception e) {
            Log.error("Failed to get page title: " + e.getMessage());
            return null;
        }
    }

    public String getPageURL() {
        try {
            String PageURL = page.url();
            Log.info("Page URL is : " + PageURL);
            logger.info("Page URL is : " + PageURL);
            return PageURL;
        } catch (Exception e) {
            Log.error("Failed to get page URL: " + e.getMessage());
            return null;
        }
    }


    public void enterUserName(String userName) {
        try {
            Log.info("Entering username: " + userName);
            logger.info("Entering username: " + userName);
            page.locator(username).fill(userName);
            Log.info("Username entered successfully");

        } catch (Exception e) {
            Log.error("Failed to enter username: " + e.getMessage());
        }


    }

    public void enterPassword(String password) {
        try {
            Log.info("Entering password");

            logger.info("Entering password");
            page.locator(this.password).fill(password);
            Log.info("Password entered successfully");
        } catch (Exception e) {
            Log.error("Failed to enter password: " + e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            Log.info("Clicking on login button");
            logger.info("Clicking on login button");
            page.click(loginButton);
            Log.info("Login button clicked successfully");
        } catch (Exception e) {
            Log.error("Failed to click on login button: " + e.getMessage());
        }

    }

    public String getErrorMessage() {
        try {
            String errorMsg = page.locator(errorMessage).innerText();
            Log.info("Error message displayed: " + errorMsg);
            logger.info("Error message displayed: " + errorMsg);
            return errorMsg;
        } catch (Exception e) {
            Log.error("Failed to get error message: " + e.getMessage());
            return null;
        }
    }

    public String loginWithCredentials(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        return getErrorMessage();
    }

}