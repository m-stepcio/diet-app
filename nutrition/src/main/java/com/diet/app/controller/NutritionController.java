package com.diet.app.controller;

import com.diet.app.dto.PageResponse;
import com.diet.app.enums.QueryOperator;
import com.diet.app.models.NutritionBasicInfo;
import com.diet.app.service.NutritionService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutrition/product")
public class NutritionController {
    private final NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @GetMapping("/nutrition/{id}")
    public NutritionBasicInfo getProductNutrition(@PathVariable("id") int id,
                                                  @RequestParam("size") double size,
                                                  @RequestParam("unit") String unit){
        return nutritionService.getProductById(id, size, unit);
    }

    @GetMapping("/nutrition")
    public PageResponse getNutrition(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "kcalOp", defaultValue = "EQ") QueryOperator kcalOp,
            @RequestParam(name = "kcal", required = false) Double kcal,
            @RequestParam(name = "proteinOp", defaultValue = "EQ") QueryOperator proteinOp,
            @RequestParam(name = "protein", required = false) Double protein,
            @RequestParam(name = "fatOp", defaultValue = "EQ") QueryOperator fatOp,
            @RequestParam(name = "fat", required = false) Double fat,
            @RequestParam(name = "carbohydratesOp", defaultValue = "EQ") QueryOperator carbohydratesOp,
            @RequestParam(name = "carbohydrates", required = false) Double carbohydrates,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size){
        nutritionService.getProducts(name, kcalOp, kcal, proteinOp, protein,
                fatOp, fat, carbohydratesOp, carbohydrates, page, size);
    }

}
