package com.core.constants;

import com.core.base.PropertyLoader;

public final class CoreConstants {
    private CoreConstants() {
    }

    private static final int EXPLICITWAIT = 10;
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/main/java/"+packageName();
    //private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
    private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/resources/config.properties";
    private static final String JSONFILEPATH = System.getProperty("user.dir") + "/src/test/resources/testdata.json";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static String extentReportFilepath = "";

    //this method will fetch the package name, split it till the name of project and return, it's needed to get the project name at runtime,
    //so that all child project can use it without having to change the config file path.
    private static String packageName(){
        Class<CoreConstants> clazz = CoreConstants.class;
        Package pkg = clazz.getPackage();
        String pkgName = pkg.getName();
        pkgName = pkgName.substring(0,pkgName.indexOf(".",pkgName.indexOf(".")+1)).replace(".","/");
        System.out.println(pkgName);
        return pkgName;
    }

    private static String createReportFilePath() {
        if (PropertyLoader.getProp("overridereports").equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "index.html";
        }
        return EXTENTREPORTFOLDERPATH + "index.html";
    }

    public static String getExtentReportFilepath() {
        if (extentReportFilepath.isEmpty()) {
            return createReportFilePath();
        }
        return extentReportFilepath;
    }

    public static int getExplicitwait() {
        return EXPLICITWAIT;
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getJsonFilePath() {
        return JSONFILEPATH;
    }
}

