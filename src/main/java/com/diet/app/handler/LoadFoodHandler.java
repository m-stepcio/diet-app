package com.diet.app.handler;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.service.FoodService;
import com.diet.app.service.PayloadResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoadFoodHandler {

    private final FoodService foodService;
    private final PayloadResolver payloadResolver;

    public void process(LoadFoodDataSchema loadFoodDataSchema){
        loadFoodDataSchema.getSchemaVersion();
    }

    private void validateFormat(String format){

    }

}
