package com.diet.app.service;

import com.diet.app.model.JsonPayload;
import com.diet.app.model.Payload;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class JsonDecoder implements PayloadDecoder{

    private final Gson gson = new Gson();

    @Override
    public Payload decode(String input) {
        return new JsonPayload(gson.fromJson(input, JsonArray.class));
    }
}
