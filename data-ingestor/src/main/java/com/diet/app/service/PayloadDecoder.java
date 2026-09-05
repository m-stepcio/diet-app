package com.diet.app.service;

import com.diet.app.model.Payload;

public interface PayloadDecoder {
    Payload decode(String input);
}
