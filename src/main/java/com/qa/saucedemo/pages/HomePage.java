package com.qa.saucedemo.pages;

import com.microsoft.playwright.Page;

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
        System.out.println("Going to click the Menu");
        page.click(menu);
        System.out.println("Successfully clicked on Menu");
    }

    public void selectFilter(String filterName){
        System.out.println("Going to select filter option");
        page.locator(filter).selectOption(filterName);
        System.out.println("selected "+filterName+" filter");

    }

    public void clickShoppingCart(){
        System.out.println("Going to click the shopping cart");
        page.click(cart);
        System.out.println("Successfully clicked on cart");
    }
    public void clickCrossButton(){
        System.out.println("Going to click the cross buttton");
        page.click(crossButton);
        System.out.println("Successfully clicked on cross button");
    }


    public String getPageTitle(){
        String pagetitle=page.title();

        System.out.println("page title is : "+pagetitle);
        return pagetitle;

    }
    public String getPageURL(){
        String PageURL=page.url();
        System.out.println("page url is : "+PageURL);
        return PageURL;

    }
}
