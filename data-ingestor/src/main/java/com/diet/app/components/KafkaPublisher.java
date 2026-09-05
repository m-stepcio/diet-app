package com.diet.app.components;

import com.diet.app.dto.Nutrition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {
    private final static String NUTRITION_TOPIC = "nutrition";
    private final KafkaTemplate<String, Nutrition> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String, Nutrition> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Nutrition message) {
        kafkaTemplate.send(NUTRITION_TOPIC, message);
    }
}
