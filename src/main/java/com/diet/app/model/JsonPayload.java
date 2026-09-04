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

        if(jsonArray.isEmpty()){
            keySet = Collections.emptySet();
        } else {
            keySet = jsonArray.get(0).getAsJsonObject().keySet();
        }
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
    public Stream<JsonElement> stream() {
        return Stream.of(jsonArray);
    }

    @Override
    public Iterator<PayloadObject> getIterator() {
        return null;
    }

    @Override
    public PayloadObject getFirst() {
        return null;
    }
}
