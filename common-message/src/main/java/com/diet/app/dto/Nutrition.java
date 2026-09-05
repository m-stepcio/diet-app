package com.diet.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Nutrition{
    private String name;
    private String producer;
    private MacroInfo macroInfo;
    private MicroInfo microInfo;
}
