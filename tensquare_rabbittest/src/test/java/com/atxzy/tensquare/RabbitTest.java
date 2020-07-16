package com.atxzy.tensquare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@SpringBootTest(classes = RabbitApplication.class)
@RunWith(SpringRunner.class)
public class RabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired


    @Test
    public void sendMSG1(){
        rabbitTemplate.convertAndSend("lingzundaren","直接模式测试");
    }


    /**
     * 分裂模式
     */
    @Test
    public void sendMSG2(){
        rabbitTemplate.convertAndSend("heiyu","","分裂模式");
    }


}
