package com.diet.app.models;

import com.diet.app.entity.Nutrition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NutritionBasicInfo {
    private Long id;
    private String name;
    private String producent;
    private Double kcal;
    private Double protein;
    private Double fat;
    private Double carbs;

    public static NutritionBasicInfo fromNutrition(Nutrition nutrition){
        return NutritionBasicInfo
                .builder()
                .id(nutrition.getId())
                .name(nutrition.getName())
                .producent(nutrition.getProducer())
                .protein(nutrition.getMacroInfo().getProtein())
                .carbs(nutrition.getMacroInfo().getCarbohydrates())
                .fat(nutrition.getMacroInfo().getFat())
                .kcal(nutrition.getMacroInfo().getKcal())
                .build();
    }
}
