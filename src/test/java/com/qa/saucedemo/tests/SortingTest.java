package com.qa.saucedemo.tests;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.pages.HomePage;
import com.qa.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    // BeforeClass to ensure login is done once before all tests in this class
    @BeforeMethod
    public void setUpPages() {
        //logger = extent.createTest(getClass().getSimpleName() + " Setup");
        loginPage = new LoginPage(page, logger);
        homePage = new HomePage(page, logger);
        loginPage.loginToApp(prop.getProperty("username"), prop.getProperty("password"));
        logger.pass("Login successful");
    }
    @AfterMethod
    public void tearDown() {
        homePage.logoutFromApp();
        logger.pass("Logout successful");
    }



    @Test(description = "Verify that products are sorted by Name (A to Z)")
    public void sortProductsByNameAToZ() {
        homePage.selectFilter(AppConstants.FilterOptions.NAME_ASCENDING);
        List<String> productNames = homePage.getAllProductNames();
        List<String> sortedNames = new ArrayList<>(productNames);
        Collections.sort(sortedNames);
        Assert.assertFalse(productNames.isEmpty(), "Product list is empty after sorting.");
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted A to Z");
    }

    @Test(description = "Verify that products are sorted by Name (Z to A)")
    public void sortProductsByNameZToA() {
        homePage.selectFilter(AppConstants.FilterOptions.NAME_DESCENDING);
        List<String> productNames = homePage.getAllProductNames();
        List<String> sortedNames = new ArrayList<>(productNames);
        sortedNames.sort(Collections.reverseOrder());
        Assert.assertFalse(productNames.isEmpty(), "Product list is empty after sorting.");
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted Z to A");
    }

    @Test(description = "Verify that products are sorted by Price (Low to High)")
    public void sortProductsByPriceLowToHigh() {
        homePage.selectFilter(AppConstants.FilterOptions.PRICE_ASCENDING);
        List<Double> productPrices = homePage.getAllProductPrices();
        List<Double> sortedPrices = new ArrayList<>(productPrices);
        Collections.sort(sortedPrices);
        Assert.assertFalse(sortedPrices.isEmpty(), "Products price list is empty after sorting.");
        Assert.assertEquals(productPrices, sortedPrices, "Products are not sorted by price low to high");
    }

    @Test(description = "Verify that products are sorted by Price (High to Low)")
    public void sortProductsByPriceHighToLow() {
        homePage.selectFilter(AppConstants.FilterOptions.PRICE_DESCENDING);
        List<Double> productPrices = homePage.getAllProductPrices();
        List<Double> sortedPrices = new ArrayList<>(productPrices);
        sortedPrices.sort(Collections.reverseOrder());
        Assert.assertFalse(sortedPrices.isEmpty(), "Products price list is empty after sorting.");
        Assert.assertEquals(productPrices, sortedPrices, "Products are not sorted by price high to low");
    }



}
