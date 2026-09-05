package com.diet.app.mapping.resolver;

import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.MicroInfo;
import com.diet.app.dto.Nutrition;
import com.diet.app.exceptions.MissingRequiredFieldException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static java.util.Objects.isNull;

public class MappingResolver {

    public Nutrition mapTo(JsonObject jsonObject) {
        return Nutrition.builder()
                .name(required(jsonObject, "name").getAsString())
                .producer(optionalString(jsonObject, "producer"))
                .macroInfo(MacroInfo.builder()
                        .fat(required(jsonObject, "fat").getAsDouble())
                        .protein(required(jsonObject, "protein").getAsDouble())
                        .kcal(required(jsonObject, "kcal").getAsDouble())
                        .carbohydrates(required(jsonObject, "carbohydrates").getAsDouble())
                        .build())
                .microInfo(MicroInfo.builder()
                        .folat(optionalDouble(jsonObject, "folat"))
                        .calcium(optionalDouble(jsonObject, "calcium"))
                        .iron(optionalDouble(jsonObject, "iron"))
                        .magnesium(optionalDouble(jsonObject, "magnesium"))
                        .potassium(optionalDouble(jsonObject, "potassium"))
                        .sodium(optionalDouble(jsonObject, "sodium"))
                        .zinc(optionalDouble(jsonObject, "zinc"))
                        .selenium(optionalDouble(jsonObject, "selenium"))
                        .iodine(optionalDouble(jsonObject, "iodine"))
                        .phosphorus(optionalDouble(jsonObject, "phosphorus"))
                        .copper(optionalDouble(jsonObject, "copper"))
                        .manganese(optionalDouble(jsonObject, "manganese"))
                        .build()).build();

    }

    private JsonElement required(JsonObject jsonObject, String field) {
        JsonElement value = jsonObject.get(field);
        if (isNull(value) || value.isJsonNull()) {
            throw new MissingRequiredFieldException(field);
        }
        return value;
    }

    private String optionalString(JsonObject jsonObject, String field) {
        JsonElement value = jsonObject.get(field);
        if (isNull(value) || value.isJsonNull()) {
            return null;
        }
        return value.getAsString();
    }

    private Double optionalDouble(JsonObject jsonObject, String field) {
        JsonElement value = jsonObject.get(field);
        if (isNull(value) || value.isJsonNull()) {
            return null;
        }
        return value.getAsDouble();
    }
}
