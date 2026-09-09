package com.diet.app.entity;

import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.MicroInfo;
import com.diet.app.enums.Unit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

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
    private MacroInfo macroInfo;
    private MicroInfo microInfo;


}
