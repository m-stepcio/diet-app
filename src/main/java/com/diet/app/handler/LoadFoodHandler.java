package com.diet.app.handler;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoadFoodHandler {

    private final FoodService foodService;
    private

    public void process(LoadFoodDataSchema loadFoodDataSchema){

    }

}
