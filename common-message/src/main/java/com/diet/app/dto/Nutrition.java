package com.diet.app.dto;

import com.diet.app.enums.Category;

public record Nutrition(long id,
                        String name,
                        Category category,
                        MacroInfo macroInfo,
                        MicroInfo microInfo){
}
