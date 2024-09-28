package com.idas.springbootinit.bizmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.idas.springbootinit.bizmq.BiMqConstant.BI_EXCHANGE_NAME;
import static com.idas.springbootinit.bizmq.BiMqConstant.BI_ROUTING_KEY;

@Component
public class MessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(BI_EXCHANGE_NAME, BI_ROUTING_KEY, message);
    }
}
