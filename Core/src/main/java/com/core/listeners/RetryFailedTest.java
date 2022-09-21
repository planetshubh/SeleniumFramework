package com.core.listeners;

import com.core.base.PropertyLoader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {

    int count = 0;
    int retries = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean value = false;
        if (PropertyLoader.getProp("retryfailedtc").equalsIgnoreCase("yes")) {
            value = count < retries;
            count++;
        }
        return value;
    }
}
