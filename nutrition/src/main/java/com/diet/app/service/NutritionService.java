package com.diet.app.service;

import com.diet.app.dto.NutritionDto;
import com.diet.app.entity.Nutrition;
import com.diet.app.repository.NutritionRepository;
import org.springframework.stereotype.Service;

@Service
public class NutritionService {
    private final NutritionRepository nutritionRepository;

    public NutritionService(NutritionRepository nutritionRepository) {
        this.nutritionRepository = nutritionRepository;
    }

    public void uploadNewNutrition(NutritionDto nutritionDto){
        nutritionRepository.save(parseToNutrition(nutritionDto));
    }

    public Nutrition parseToNutrition(NutritionDto nutritionDto){
        Nutrition nutrition = new Nutrition();
        nutrition.setName(nutritionDto.getName());
        nutrition.setProducer(nutritionDto.getProducer());
        return nutrition;
    }

}
