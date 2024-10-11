package com.qa.saucedemo.listeners;

import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.utilities.CommonFunctions;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


import java.io.IOException;

public class RetryFailed implements IRetryAnalyzer {




    int counter=1;
    int maxRetryCount=Integer.parseInt(CommonFunctions.loadTestConfig().getProperty("retryCount"));

    public RetryFailed() throws IOException {
    }

    @Override
    public boolean retry(ITestResult result) {
        if (counter <= maxRetryCount) {
            Log.info("Retrying " + result.getName() +  " again and the retry count is " + (counter++));

            return true;
        }

        return false;
    }
}
