package com.diet.app.controller;

import com.diet.app.models.NutritionBasicInfo;
import com.diet.app.service.NutritionService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutrition/product")
public class NutritionController {
    private final NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @GetMapping("/{id}")
    public NutritionBasicInfo getProductNutrition(@PathVariable("id") int id){
        return nutritionService.getProductBeId(id);
    }
}
