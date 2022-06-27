package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    public static final String APPLICATION_QUEUE_NAME = "RyTest";

    @Bean
    Queue queueRySchedule() {
        return new Queue(APPLICATION_QUEUE_NAME, false);
    }

    @Bean
    public Binding binding(Queue queue) {
        return BindingBuilder.bind(queue)
                .to(new TopicExchange("test_topic"))
                .with("test");
    }
}
