package com.example.finaltask.util;

import com.example.finaltask.exception.InvalidSearchFormatException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchUtil {
    private static final int FIELD = 0;
    private static final int VALUE = 1;

    public static Map<String, String> buildEntityProperties(String search) {
        if (search == null || search.isEmpty()) {
            return null;
        }
        String[] fieldAndValue = search.split(",");
        Map<String, String> fieldKeyAndValueMap = new HashMap<>();
        Arrays.stream(fieldAndValue).forEach(s -> {
            if (s.split("=").length != 2) {
                throw new InvalidSearchFormatException("Invalid search format.");
            }
            String fields = s.split("=")[FIELD];
            String values = s.split("=")[VALUE];
            fieldKeyAndValueMap.put(fields, values);
        });
        return fieldKeyAndValueMap;
    }
}