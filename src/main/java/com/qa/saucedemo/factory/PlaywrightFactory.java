package com.qa.saucedemo.factory;

import com.microsoft.playwright.*;
import com.qa.saucedemo.exceptions.BrowserNotFoundException;
import com.qa.saucedemo.logger.Log;

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
    OptionsManager optionsManager;

    /**
     * This method is used to initiliaze browser
     */
    public Page initBrowser(Properties prop) {
        this.prop = prop;
        String browserName = prop.getProperty("browser");
        Log.info("Browser name is: " + browserName);

        playwright = Playwright.create();
        optionsManager = new OptionsManager(prop); // Initialize OptionsManager with properties

        switch (browserName.toLowerCase().trim()) {
            case "chrome":
                Log.info("Running tests on Local - Chrome");
                browser = playwright.chromium().launch(optionsManager.getChromeOptions());
                break;

            case "firefox":
                Log.info("Running tests on Local - Firefox");
                browser = playwright.firefox().launch(optionsManager.getFirefoxOptions());
                break;

            case "edge":
                Log.info("Running tests on Local - Edge");
                browser = playwright.chromium().launch(optionsManager.getEdgeOptions());
                break;

            case "safari":
                Log.info("Running tests on Local - Safari");
                browser = playwright.webkit().launch(optionsManager.getSafariOptions());
                break;

            default:
                Log.error("Please pass the right browser name: " + browserName);
                throw new BrowserNotFoundException("====BROWSER NOT FOUND=====");
        }

        // Create a new browser context and page
        page = browser.newContext().newPage();
        Log.info("Browser setup complete and Browser launched successfully");
        page.context().setDefaultTimeout(30000); // Set default timeout for actions
        page.navigate(prop.getProperty("url1")); // Navigate to the URL from properties

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }



}
