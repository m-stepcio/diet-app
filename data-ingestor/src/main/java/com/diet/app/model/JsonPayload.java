package com.diet.app.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class JsonPayload implements Payload{
    private final JsonArray jsonArray;
    private final Set<String> keySet;

    public JsonPayload(JsonArray input) {
        jsonArray = input;
        keySet = readKeySet(input);
    }



    @Override
    public long getSize() {
        return jsonArray.size();
    }

    @Override
    public Set<String> getKeySet() {
        return keySet;
    }

    @Override
    public Stream<PayloadObject> stream() {
        return null;
    }

    @Override
    public Iterator<PayloadObject> getIterator() {
        return null;
    }

    @Override
    public PayloadObject getFirst() {
        return null;
    }

    private Set<String> readKeySet(JsonArray jsonArray){
        if(jsonArray.isEmpty()){
            return Collections.emptySet();
        }
        return jsonArray.get(0).getAsJsonObject().keySet();
    }
}
