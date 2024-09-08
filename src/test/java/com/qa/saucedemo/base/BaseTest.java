package com.qa.saucedemo.base;

import com.microsoft.playwright.Page;
import com.qa.saucedemo.factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory playwrightFactory;
    protected Page page;
    Properties prop;

    @BeforeTest
    public void setup(){
        playwrightFactory=new PlaywrightFactory();
        prop= playwrightFactory.init_prop();
        page=playwrightFactory.initBrowser(prop.getProperty("browser").trim());
        System.out.println("Browser launched");
    }
    @AfterTest
    public void tearDown(){
        page.context().browser().close();
        System.out.println("Browser closed");

    }
}
