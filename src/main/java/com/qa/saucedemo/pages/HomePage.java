package com.qa.saucedemo.pages;

import com.microsoft.playwright.Page;
import com.qa.saucedemo.logger.Log;

public class HomePage {
    Page page;
    private String title="//span[@class='title']";
    private String menu="//button[@id='react-burger-menu-btn']";
    private String filter="//select[@class='product_sort_container']";
    private String cart="//a[@class='shopping_cart_link']";
    private String allItems="//a[@id='inventory_sidebar_link']";
    private String about="//a[@id='about_sidebar_link']";
    private String logout="#logout_sidebar_link";
    private String resetState="//a[@id='reset_sidebar_link']";
    private String crossButton="//button[@id='react-burger-cross-btn']";



    public HomePage(Page page){
        this.page=page;
    }

    public void clickMenu(){
        Log.info("Going to click the Menu");
        page.click(menu);
        Log.info("Successfully clicked on Menu");
    }

    public void selectFilter(String filterName){
        Log.info("Going to select filter option");
        page.locator(filter).selectOption(filterName);
        Log.info("selected "+filterName+" filter");

    }

    public void clickShoppingCart(){
        Log.info("Going to click the shopping cart");
        page.click(cart);
        Log.info("Successfully clicked on cart");
    }
    public void clickCrossButton(){
        Log.info("Going to click the cross buttton");
        page.click(crossButton);
        Log.info("Successfully clicked on cross button");
    }


    public String getPageTitle(){
        String pagetitle=page.title();

        Log.info("page title is : "+pagetitle);
        return pagetitle;

    }
    public String getPageURL(){
        String PageURL=page.url();
        Log.info("page url is : "+PageURL);
        return PageURL;

    }
}
