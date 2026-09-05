package com.diet.app.dto;

import com.diet.app.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nutrition{
    private String name;
    private Category category;
    private MacroInfo macroInfo;
    private MicroInfo microInfo;
}
