package com.diet.app.models;

import com.diet.app.enums.Unit;
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
    private Double size;
    private Unit unit;
}
