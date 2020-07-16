package com.atxzy.tensquare.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "lingzundaren")
public class Customer1 {

    @RabbitHandler
    public void getMSG(String msg){
        System.out.println("直接模式消费了消息==>"+msg);

    }

}
