package com.diet.app.dto;

import com.diet.app.enums.Unit;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutritionDto {
    private String name;
    private String producer;
    private Double size;
    private Unit unit;
    private MacroInfo macroInfo;
    private MicroInfo microInfo;
}
