package com.qa.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.qa.saucedemo.logger.Log;

public class HomePage {
    ExtentTest logger;
    Page page;
    private String title = "//span[@class='title']";
    private String menu = "//button[@id='react-burger-menu-btn']";
    private String filter = "//select[@class='product_sort_container']";
    private String cart = "//a[@class='shopping_cart_link']";
    private String allItems = "//a[@id='inventory_sidebar_link']";
    private String about = "//a[@id='about_sidebar_link']";
    private String logout = "#logout_sidebar_link";
    private String resetState = "//a[@id='reset_sidebar_link']";
    private String crossButton = "//button[@id='react-burger-cross-btn']";


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
}

