package com.core.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.core.base.PropertyLoader;
import com.core.utilities.CoreUtils;


public final class ExtentLogger {

    private ExtentLogger() {
    }

    /**
     * Logs pass event in the extent report
     */
    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    /**
     * Logs fail event in the extent report
     */
    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    /**
     * Logs skip event in the extent report
     */
    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    /**
     * Logs pass event in the extent report based on user input in property file
     */
    public static void pass(String message, boolean isScreenshotNeeded) {
        if (PropertyLoader.getProp("passedscreenshot").equalsIgnoreCase("yes")
                && isScreenshotNeeded) {
            ExtentManager.getExtentTest()
                    .pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(CoreUtils.getBase64Image()).build());
        } else {
            pass(message);
        }
    }

    /**
     * Logs fail event in the extent report based on user input in property file
     */
    public static void fail(String message, boolean isScreenshotNeeded) {
        if (PropertyLoader.getProp("failedscreenshot").equalsIgnoreCase("yes")
                && isScreenshotNeeded) {
            ExtentManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(CoreUtils.getBase64Image()).build());
        } else {
            fail(message);
        }
    }

    /**
     * Logs skip event in the extent report based on user input in property file
     */
    public static void skip(String message, boolean isScreenshotNeeded) {
        if (PropertyLoader.getProp("skippedscreenshot").equalsIgnoreCase("yes")
                && isScreenshotNeeded) {
            ExtentManager.getExtentTest().skip(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(CoreUtils.getBase64Image()).build());
        } else {
            skip(message);
        }
    }


}
