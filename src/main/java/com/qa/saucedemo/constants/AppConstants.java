package com.qa.saucedemo.constants;

public class AppConstants {
    public static final String CONFIG_FILE_PATH="src/test/resources/config/config.properties";


    final public static String TITLE = "Swag Labs";
    final public static String URL = "https://www.saucedemo.com/inventory.html";
    final public static String HOMEPAGE_URL = "https://www.saucedemo.com/inventory.html";
    final public static String HOMEPAGE_TITLE = "Swag Labs";
    public static final String LOGIN_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    public static final String LOCKED_OUT_USER_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
    public static final String USERNAME_REQUIRED_ERROR_MESSAGE = "Epic sadface: Username is required";
    public static final String PASSWORD_REQUIRED_ERROR_MESSAGE = "Epic sadface: Password is required";
    public static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";


    public static final class FilterOptions {
        public static final String NAME_ASCENDING = "Name (A to Z)";
        public static final String NAME_DESCENDING = "Name (Z to A)";
        public static final String PRICE_ASCENDING = "Price (low to high)";
        public static final String PRICE_DESCENDING = "Price (high to low)";
    }
}