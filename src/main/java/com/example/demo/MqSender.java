package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MqMessage message, String exchange, String routingKey) {
        rabbitTemplate.convertAndSend(exchange, routingKey, JSONObject.toJSONString(message));
        System.out.println("遞送MQ_message - 主題為:" + exchange + " - 路由至:" + routingKey);
    }
}
