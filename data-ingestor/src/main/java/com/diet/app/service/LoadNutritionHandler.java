package com.diet.app.service;

import com.diet.app.components.KafkaPublisher;
import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.dto.Nutrition;
import com.diet.app.mapping.resolver.MappingResolver;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@Slf4j
public class LoadNutritionHandler {
    private final KafkaPublisher kafkaPublisher;
    private final Gson gson;

    public LoadNutritionHandler(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
        this.gson = new Gson();

    }

    public void process(LoadFoodDataSchema loadFoodDataSchema) {
        log.info("Starting process of request from {}, type: {},  size: {}",
                loadFoodDataSchema.getSource(),
                loadFoodDataSchema.getDataType(),
                loadFoodDataSchema.getSize());
        JsonElement payload = gson.fromJson(loadFoodDataSchema.getPayload(), JsonElement.class);
        JsonArray jsonArray = payload.getAsJsonArray();
        Iterator<JsonElement> iterator = jsonArray.iterator();
        MappingResolver mappingResolver = new MappingResolver();
        while (iterator.hasNext()) {
            Nutrition nutrition = mappingResolver.mapTo(iterator.next().getAsJsonObject());
            kafkaPublisher.publish(nutrition);
        }
    }
}
