package com.qa.saucedemo.tests;

import com.aventstack.extentreports.Status;
import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.pages.HomePage;
import com.qa.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(page, logger);
        homePage = new HomePage(page, logger);
    }

    // ----------* POSITIVE TEST CASE *----------
    //priority is set to 100 to make sure this test runs last
    @Test(description = "Verify that user is able to login with valid credentials",priority = 100)
    public void validLogin() {
        try {

            loginPage.loginToApp(prop.getProperty("username"), prop.getProperty("password"));
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(homePage.getPageTitle(), AppConstants.TITLE, "Page title does not match");
            softAssert.assertEquals(homePage.getPageURL(), AppConstants.URL, "Page URL does not match");
            softAssert.assertAll();

        } catch (Exception e) {
            Log.error("Exception occurred during validLogin: " , e);
            Assert.fail("Test failed due to exception: " , e);
        }


    }
    // ----------* NEGATIVE TEST CASES *------------

    @Test(description = "Verify that user is not able to login with invalid username",priority = 1)
    public void loginWithInvalidUsername() {
        try {

            String errorMsg = loginPage.loginWithCredentials(prop.getProperty("invalidUsername"), prop.getProperty("password"));
            Assert.assertEquals(errorMsg, AppConstants.LOGIN_ERROR_MESSAGE, "Error message does not match");

        } catch (Exception e) {
            Log.error("Exception occurred during invalidLogin: " , e);
            Assert.fail("Test failed due to exception: " , e);
        }
    }

    @Test(description = "Verify that user is not able to login with invalid password",priority = 2)
    public void loginWithInvalidPassword() {
        try {

            String errorMsg = loginPage.loginWithCredentials(prop.getProperty("username"), prop.getProperty("invalidPassword"));
            Assert.assertEquals(errorMsg, AppConstants.LOGIN_ERROR_MESSAGE, "Error message does not match");
        } catch (Exception e) {
            Log.error("Exception occurred during invalidPasswordLogin: " , e);
            Assert.fail("Test failed due to exception: " , e);


        }
    }
    @Test(description = "Verify that user is not able to login with invalid username and invalid password",priority = 3)
    public void loginWithInvalidCredentials() {
        try {

            String errorMsg = loginPage.loginWithCredentials(prop.getProperty("invalidUsername"), prop.getProperty("invalidPassword"));
            Assert.assertEquals(errorMsg, AppConstants.INVALID_CREDENTIALS_ERROR_MESSAGE, "Error message does not match");


            Assert.assertEquals(errorMsg, AppConstants.INVALID_CREDENTIALS_ERROR_MESSAGE, "Error message does not match");

        } catch (Exception e) {
            Log.error("Exception occurred during invalidLogin: " , e);
            Assert.fail("Test failed due to exception: " , e);
        }
    }


    @Test(description = "Verify that user is not able to login with blank username and password",priority = 4)
    public void loginWithBlankCredentials() {
        try {

            String errorMsg = loginPage.loginWithCredentials("", "");
            Assert.assertEquals(errorMsg, AppConstants.USERNAME_REQUIRED_ERROR_MESSAGE, "Error message does not match");
        } catch (Exception e) {
            Log.error("Exception occurred during blankCredentialsLogin: " , e);
            Assert.fail("Test failed due to exception: " , e);
        }
    }
    @Test(description = "Verify that user is not able to login with blank username only", priority = 5)
    public void loginWithBlankUsernameOnly() {
        try {
            loginPage.enterUserName("");
            loginPage.enterPassword(prop.getProperty("password"));
            loginPage.clickLoginButton();
            String errorMsg = loginPage.getErrorMessage();

            Assert.assertEquals(errorMsg, AppConstants.USERNAME_REQUIRED_ERROR_MESSAGE, "Error message does not match");
        } catch (Exception e) {
            Log.error("Exception occurred during loginWithBlankUsernameOnly", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify that user is not able to login with blank password only", priority = 6)
    public void loginWithBlankPasswordOnly() {
        try {
            loginPage.enterUserName(prop.getProperty("username"));
            loginPage.enterPassword("");
            loginPage.clickLoginButton();
            String errorMsg = loginPage.getErrorMessage();

            Assert.assertEquals(errorMsg, AppConstants.PASSWORD_REQUIRED_ERROR_MESSAGE, "Error message does not match");
        } catch (Exception e) {
            Log.error("Exception occurred during loginWithBlankPasswordOnly", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify that locked out user is not able to login",priority = 7)
    public void lockedOutUserLogin() {
        try {

            String errorMsg = loginPage.loginWithCredentials(prop.getProperty("lockedOutUsername"), prop.getProperty("password"));
            Assert.assertEquals(errorMsg, AppConstants.LOCKED_OUT_USER_ERROR_MESSAGE, "Error message does not match");
        } catch (Exception e) {
            Log.error("Exception occurred during lockedOutUserLogin: " , e);
            Assert.fail("Test failed due to exception: " , e);
        }
    }






}
