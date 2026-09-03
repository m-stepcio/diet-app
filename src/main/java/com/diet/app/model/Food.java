package com.diet.app.model;

import com.diet.app.enums.Category;
import jakarta.persistence.Entity;

@Entity
public class Food {
    private long id;
    private String name;
    private Category category;
    private MakroInfo makroInfo;
    private MikroInfo mikroInfo;
}
