package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "/test")
public class DemoApplication {

    @Autowired
    private MqSender mqSender;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/get")
    public String test() {
        MqMessage message = new MqMessage();
        message.setContent("test");
        message.setContent("Hi I am here!");
        mqSender.sendMessage(message,"test_topic", "test");
        return "Hello world!";
    }

}
