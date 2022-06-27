package com.example.demo;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MqReceive {
    @RabbitListener(queues = MqConfig.APPLICATION_QUEUE_NAME)
    public void consumeMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        //消息確認
        channel.basicAck(tag,false);
        //消息拒絕 單條
        channel.basicReject(tag,false);
        //多個消費拒絕 第三個參數 true代表拒絕後 放回rabbit發送對列 false 直接銷毀
        channel.basicNack(tag,false,true);
        System.out.println("消費MQ訊息 - 主題: - 訊息體:" + message + "," + tag + "," + channel);
    }
}
