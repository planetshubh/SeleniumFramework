package com.core.listeners;

import com.core.base.CoreBase;
import com.core.reports.ExtentLogger;
import com.core.reports.ExtentManager;
import com.core.reports.ExtentReport;
import com.core.utilities.CoreUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener extends CoreBase implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test execution started." + context.getName());
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test execution End." + context.getName());
        ExtentReport.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started to run - " + result.getName());
        ExtentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed -" + result.getName());
        ExtentLogger.pass(result.getMethod().getMethodName() + " is passed.", true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed, screenshot captured -" + result.getName());
        ExtentLogger.fail(result.getMethod().getMethodName() + " is failed.", true);
        CoreUtils coreUtils = new CoreUtils(getDriver());
        coreUtils.captureScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped - " + result.getName());
        ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /*
         *Will be implemented if needed in future.
         **/
    }
}