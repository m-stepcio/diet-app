package com.diet.app.dto;

import com.diet.app.enums.Unit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NutritionDto {
    private String name;
    private String producer;
    private Double size;
    private Unit unit;
    private MacroInfo macroInfo;
    private MicroInfo microInfo;
}
