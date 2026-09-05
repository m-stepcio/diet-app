package com.diet.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nutrition{
    private String name;
    private MacroInfo macroInfo;
    private MicroInfo microInfo;
}
