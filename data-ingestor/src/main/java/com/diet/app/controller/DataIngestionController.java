package com.diet.app.controller;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.service.LoadNutritionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class DataIngestionController {

    private final LoadNutritionHandler loadNutritionHandler;

    public DataIngestionController(LoadNutritionHandler loadNutritionHandler) {
        this.loadNutritionHandler = loadNutritionHandler;
    }

    @PostMapping("/ingestion/load")
    public ResponseEntity<Void> loadData(@RequestBody LoadFoodDataSchema loadFoodDataSchema){
        try {
            loadNutritionHandler.process(loadFoodDataSchema);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }
}
