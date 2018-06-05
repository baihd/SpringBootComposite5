package com.composite.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloTwo")
public class HelloTwoReceiver2 {

    @RabbitHandler
    public void process(String helloTwo) {
        System.out.println("Receiver 2:" + helloTwo);
    }
}