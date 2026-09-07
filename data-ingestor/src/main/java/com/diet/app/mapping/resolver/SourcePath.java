package com.diet.app.mapping.resolver;

import java.util.HashMap;
import java.util.Map;

public class SourcePath {
    Map<String, String[]> fieldsMap;

    public SourcePath() {
        fieldsMap = new HashMap<>();
    }

    public SourcePath(Map.Entry<String, String>... entrys){
       fieldsMap = new HashMap<>();
        for (Map.Entry<String, String> entry : entrys) {
            fieldsMap.put(entry.getKey(), entry.getValue().split("\\."));
        }
    }

    public void add(String inputField, String outputName){
        fieldsMap.put(outputName, inputField.split("\\."));
    }

    public void add(Map<String, String> map){
        for(String input : map.keySet()){
            add(map.get(input), input);
        }
    }

    public boolean isMappingUsed(){
        return !fieldsMap.isEmpty();
    }

    public boolean hasMapping(String outputField){
        return fieldsMap.containsKey(outputField);
    }

    public String[] getInputPath(String outputField){
        if(fieldsMap.isEmpty()){
            return null;
        }

        if(!fieldsMap.containsKey(outputField)){
            return null;
        }

        return fieldsMap.get(outputField);
    }
}
