package com.diet.app.handler;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.model.Payload;
import com.diet.app.service.PayloadResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoadFoodHandler {

    private final PayloadResolver payloadResolver;

    public LoadFoodHandler(PayloadResolver payloadResolver) {
        this.payloadResolver = payloadResolver;
    }

    public void process(LoadFoodDataSchema loadFoodDataSchema) throws Exception {
        Payload payload = payloadResolver.resolve(loadFoodDataSchema);

    }
}
