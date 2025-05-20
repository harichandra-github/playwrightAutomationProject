package com.qa.saucedemo.factory;

import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.qa.saucedemo.logger.Log;

import java.util.Arrays;
import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private  LaunchOptions launchOptions;

    public OptionsManager(Properties prop) {
        this.prop = prop;
        launchOptions = new LaunchOptions();
    }

    public LaunchOptions getChromeOptions() {
        launchOptions.setChannel("chrome");

        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless"))
                || Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isHeadless) {
            Log.info("====Running tests in headless mode for Chrome=====");
            launchOptions.setHeadless(true);
            launchOptions.setArgs(Arrays.asList("--headless=new"));
        } else {
            launchOptions.setHeadless(false);
        }

        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            Log.info("====Running tests in incognito mode=====");
            // Use browser context for incognito
        }

        return launchOptions;
    }

    public LaunchOptions getFirefoxOptions() {

        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless"))
                || Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isHeadless) {
            Log.info("====Running tests in headless mode for Firefox=====");
            launchOptions.setHeadless(true);
        } else {
            launchOptions.setHeadless(false);
        }

        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            Log.info("====Running tests in incognito mode=====");
            // Use browser context for incognito
        }

        return launchOptions;
    }

    public LaunchOptions getEdgeOptions() {
        launchOptions.setChannel("msedge");

        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless"))
                || Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isHeadless) {
            Log.info("====Running tests in headless mode for Edge=====");
            launchOptions.setHeadless(true);
            launchOptions.setArgs(Arrays.asList("--headless=new"));
        } else {
            launchOptions.setHeadless(false);
        }

        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            Log.info("====Running tests in incognito mode=====");
            // Use browser context for incognito
        }

        return launchOptions;
    }

    public LaunchOptions getSafariOptions() {

        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless"))
                || Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isHeadless) {
            Log.info("====Running tests in headless mode for Safari=====");
            launchOptions.setHeadless(true);
        } else {
            launchOptions.setHeadless(false);
        }

        return launchOptions;
    }
}
