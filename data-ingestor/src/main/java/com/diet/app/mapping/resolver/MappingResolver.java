package com.diet.app.mapping.resolver;

import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.Nutrition;
import com.diet.app.exceptions.MissingRequiredFieldException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static java.util.Objects.isNull;

public class MappingResolver {

    public Nutrition mapTo(JsonObject jsonObject) {
        return Nutrition.builder()
                .name(required(jsonObject,"name").getAsString())
                .macroInfo(MacroInfo.builder()
                        .fat(required(jsonObject, "fat").getAsDouble())
                        .protein(required(jsonObject, "protein").getAsDouble())
                        .kcal(required(jsonObject, "kcal").getAsDouble())
                        .carbohydrates(required(jsonObject, "carbohydrates").getAsDouble())
                        .build())
                .build();
    }

    private JsonElement required(JsonObject jsonObject, String field){
        JsonElement value = jsonObject.get(field);
        if(isNull(value)){
            throw new MissingRequiredFieldException(field);
        }
        return value;
    }
}
