package com.core.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtil {

    private DataProviderUtil() {
    }

    private static List<Map<String, String>> list = new ArrayList<>();

    @DataProvider 
    public static Object[] getData(Method m) {
        String testname = m.getName();
        if (list.isEmpty()) {
            list = JsonUtil.readJson();
        }

        List<Map<String, String>> runnableList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("Testname").equalsIgnoreCase(testname) &&
                    list.get(i).get("Execute").equalsIgnoreCase("Yes")) {
                runnableList.add(list.get(i));
            }
        }
        return runnableList.toArray();
    }
}
