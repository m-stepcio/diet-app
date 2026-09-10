package com.diet.app.entity;

import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.MicroInfo;
import com.diet.app.enums.Unit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String producer;
    private Unit unit;
    private Double size;
    @Embedded
    private MacroInfoEmbeddable macroInfo;
    @Embedded
    private MicroInfoEmbeddable microInfo;


}
