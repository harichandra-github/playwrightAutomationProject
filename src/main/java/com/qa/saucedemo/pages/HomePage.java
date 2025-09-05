package com.qa.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.qa.saucedemo.logger.Log;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    ExtentTest logger;
    Page page;
    private final String title = "//span[@class='title']";
    private final String menu = "//button[@id='react-burger-menu-btn']";
    private final String filter = "//select[@data-test='product-sort-container']";
    private final String cart = "//a[@class='shopping_cart_link']";
    private final String allItems = "//a[@id='inventory_sidebar_link']";
    private final String about = "//a[@id='about_sidebar_link']";
    private final String logout = "#logout_sidebar_link";
    private final String resetState = "//a[@id='reset_sidebar_link']";
    private final String crossButton = "//button[@id='react-burger-cross-btn']";
    private final String productNames = "//div[@data-test='inventory-item-name']";
    private final String productPrices = "//div[@data-test='inventory-item-price']";


    public HomePage(Page page, ExtentTest logger) {
        this.page = page;
        this.logger = logger;
    }

    public void clickMenu() {
        try {
            Log.info("Going to click the Menu");
            page.click(menu);
            Log.info("Successfully clicked on Menu");
            logger.info("Successfully clicked on Menu");
        } catch (RuntimeException e) {
            Log.error("Failed to click on Menu: " + e.getMessage());
        }
    }

    public void selectFilter(String filterName) {
        try {
            Log.info("Going to select filter option");
            page.locator(filter).selectOption(filterName);
            Log.info("selected " + filterName + " filter");
            logger.info("selected " + filterName + " filter");
        } catch (RuntimeException e) {
            Log.error("Failed to select filter option: " + e.getMessage());
        }
    }

    public void clickShoppingCart() {
        System.out.println("Going to click the shopping cart");
        page.click(cart);
        System.out.println("Successfully clicked on cart");
    }

    public void clickCrossButton() {
        try {
            Log.info("Going to click the cross buttton");
            page.click(crossButton);
            Log.info("Successfully clicked on cross button");
            logger.info("Successfully clicked on cross button");
        } catch (RuntimeException e) {
            Log.error("Failed to click on cross button: " + e.getMessage());
        }
    }


    public String getPageTitle() {
        String pageTitle = null;
        try {
            Log.info("Going to get the page title");
            pageTitle = page.title();
            Log.info("page title is : " + pageTitle);
            logger.info("page title is : " + pageTitle);
            return pageTitle;
        } catch (RuntimeException e) {
            Log.error("Failed to get page title: " + e.getMessage());
        }
        return pageTitle;


    }

    public String getPageURL() {
        String pageURL = null;
        try {
            pageURL = page.url();
            Log.info("page url is : " + pageURL);
            logger.info("page url is : " + pageURL);
            return pageURL;

        } catch (RuntimeException e) {
            Log.error("Failed to get page URL: " + e.getMessage());
        }
        return pageURL;

    }
    public List<String> getAllProductNames() {
        try {
            Log.info("Getting product names from the home page");
            List<String> products = page.locator(productNames).allTextContents();
            Log.info("Product names retrieved: " + products);
            logger.info("Product names retrieved: " + products);
            return products;
        } catch (RuntimeException e) {
            Log.error("Failed to get product names: " + e.getMessage());
            return null;
        }
    }

    public List<Double> getAllProductPrices() {
        try {
            Log.info("Getting product prices from the home page");
            List<String> priceStrings = page.locator(productPrices).allTextContents();
            List<Double> prices = new ArrayList<>();

            for (String price : priceStrings) {
                prices.add(Double.parseDouble(price.replace("$", "").trim()));
            }

            Log.info("Product prices retrieved: " + prices);
            logger.info("Product prices retrieved: " + prices);
            return prices;
        } catch (RuntimeException e) {
            Log.error("Failed to get product prices: " + e.getMessage());
            return null;
        }
    }

    public void logoutFromApp() {
        try {
            Log.info("Going to logout from the application");
            clickMenu();
            page.click(logout);
            Log.info("Successfully logged out from the application");
            logger.info("Successfully logged out from the application");
        } catch (RuntimeException e) {
            Log.error("Failed to logout from the application: " + e.getMessage());
        }
    }


}

