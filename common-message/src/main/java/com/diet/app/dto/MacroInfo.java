package com.diet.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MacroInfo{
    private double kcal;
    private double protein;
    private double fat;
    private double carbohydrates;
}
