package com.core.utilities;

import com.core.constants.CoreConstants;
import com.core.exceptions.CoreException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class JsonUtil {

    private JsonUtil(){}

    public static List<Map<String,String>> readJson(){
        List<Map<String, String>> list = new ArrayList<>();
        try {
            HashMap<String, Object> map = new ObjectMapper()
                    .readValue(new File(CoreConstants.getJsonFilePath()),
                            new TypeReference<HashMap<String, Object>>() {
                            });

            HashMap<String, String> map1;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                map1 = (HashMap<String, String>) entry.getValue();
                list.add(map1);
                //list.add((HashMap<String, String>) entry.getValue());
            }
        } catch(JsonMappingException e1){
            throw new CoreException("---- Problem occured is parsing data from Json file. ----");
        } catch (IOException e) {
            throw new CoreException("---- Problem occured while trying to read JSON file.-----");
        }
        return list;
    }
}
