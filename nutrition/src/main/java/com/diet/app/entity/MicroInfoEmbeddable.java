package com.diet.app.entity;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class MicroInfoEmbeddable {
    private Double folat;
    private Double calcium;
    private Double iron;
    private Double magnesium;
    private Double potassium;
    private Double sodium;
    private Double zinc;
    private Double selenium;
    private Double iodine;
    private Double phosphorus;
    private Double copper;
    private Double manganese;
}
