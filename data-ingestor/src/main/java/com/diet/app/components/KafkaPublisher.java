package com.diet.app.components;

import com.diet.app.dto.NutritionDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {
    private final static String NUTRITION_TOPIC = "nutrition";
    private final KafkaTemplate<String, NutritionDto> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String, NutritionDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(NutritionDto message) {
        kafkaTemplate.send(NUTRITION_TOPIC, message);
    }
}
