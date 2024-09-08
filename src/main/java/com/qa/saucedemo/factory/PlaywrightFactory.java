package com.qa.saucedemo.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    /**
     * This method is used to initiliaze browser
     * @param browserName
     * @return
     */
    public Page initBrowser(String browserName){
        System.out.println("Browser name is :"+ browserName);
        playwright=Playwright.create();
        switch (browserName.toLowerCase()){
            case "chromium":
                browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            case "firefox":
                browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari":
                browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                System.out.println("Please pass the correct browser name: "+browserName);
                break;
        }

        browserContext=browser.newContext();
        page= browserContext.newPage();

        page.navigate(prop.getProperty("url"));

        return page;


    }

    /**
     * This method is used to initialize properties from config file
     *
     */

    public Properties init_prop() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }



}
