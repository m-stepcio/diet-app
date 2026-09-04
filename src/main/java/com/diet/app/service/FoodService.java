package com.diet.app.service;

import com.diet.app.dto.SchemaMetadata;
import com.diet.app.model.Payload;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private MappingResolver mappingResolver;

    public void process(Payload payload, SchemaMetadata metadata){
    }
}
