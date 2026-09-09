package com.diet.app.consumer;

import com.diet.app.dto.NutritionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NutritionMessageConsumer {
    private final static String NUTRITION_MESSAGE = "nutrition";
    private final static String CONsuMER_GROUP_ID = "nutrition-app";
    @KafkaListener(topics = NUTRITION_MESSAGE, groupId = CONsuMER_GROUP_ID)
    public void listen(NutritionDto nutritionDto){
        log.info("Recive new message {}", nutritionDto);
    }
}
