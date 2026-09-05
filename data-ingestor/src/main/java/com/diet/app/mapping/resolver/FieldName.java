package com.diet.app.mapping.resolver;

import java.util.HashMap;
import java.util.Map;

public class FieldName {
    Map<String, String> fieldsMap;

    public FieldName() {
        fieldsMap = new HashMap<>();
    }

    public FieldName(Map<String, String> map){
        fieldsMap = map;
    }

    public FieldName(Map.Entry<String, String>... entrys){
       fieldsMap = new HashMap<>();
        for (Map.Entry<String, String> entry : entrys) {
            fieldsMap.put(entry.getKey(), entry.getValue());
        }
    }

    public void add(String inputField, String outputName){
        fieldsMap.put(outputName, inputField);
    }

    public void add(Map<String, String> map){
        fieldsMap.putAll(map);
    }

    public String getInputField(String outputField){
        if(fieldsMap.isEmpty()){
            return outputField;
        }
        return fieldsMap.get(outputField);
    }
}
