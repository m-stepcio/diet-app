package com.diet.app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MacroInfo{
    private double kcal;
    private double protein;
    private double fat;
    private double carbohydrates;
}
