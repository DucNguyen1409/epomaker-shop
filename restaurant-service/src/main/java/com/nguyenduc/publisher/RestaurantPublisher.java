package com.nguyenduc.publisher;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantPublisher {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    public void publish() {

    }
}
