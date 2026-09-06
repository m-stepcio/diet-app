package com.diet.app.mapping.resolver;

import java.util.HashMap;
import java.util.Map;

public class FieldName {
    Map<String, String[]> fieldsMap;
    Map<String, Integer> indexes;

    public FieldName() {
        fieldsMap = new HashMap<>();
        indexes = new HashMap<>();
    }

    public FieldName(Map.Entry<String, String>... entrys){
       fieldsMap = new HashMap<>();
        for (Map.Entry<String, String> entry : entrys) {
            fieldsMap.put(entry.getKey(), entry.getValue().split("\\."));
        }
        indexes = new HashMap<>();
    }

    public void add(String inputField, String outputName){
        fieldsMap.put(outputName, inputField.split("\\."));
    }

    public void add(Map<String, String> map){
        for(String input : map.keySet()){
            add(map.get(input), input);
        }
    }

    public boolean hasNextPart(String outputField){
        if(indexes.containsKey(outputField)){
            return indexes.get(outputField) < fieldsMap.get(outputField).length;
        }
        return false;
    }

    public String getInputField(String outputField){
        if(fieldsMap.isEmpty()){
            return outputField;
        }

        if(!fieldsMap.containsKey(outputField)){
            return null;
        }

        if(!indexes.containsKey(outputField)){
            indexes.put(outputField, 0);
        }

        if(indexes.get(outputField) >= fieldsMap.get(outputField).length){
            return null;
        }

        String value = fieldsMap.get(outputField)[indexes.get(outputField)];
        indexes.put(outputField, indexes.get(outputField)+1);
        return value;
    }
}
