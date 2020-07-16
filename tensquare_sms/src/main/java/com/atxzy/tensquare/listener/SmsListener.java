package com.atxzy.tensquare.listener;

import com.aliyuncs.exceptions.ClientException;
import com.atxzy.tensquare.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.sql.SQLOutput;
import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    /**
     * 发送短信
     */
    @RabbitHandler
    public void sendSms(Map<String,String> map){
        String mobile = map.get("mobile");
        String smscode = map.get("code");
        System.out.println(template_code + "===" + sign_name);
        System.out.println("手机号："+map.get("mobile"));
        System.out.println("验证码："+map.get("code"));
        try {
            smsUtil.sendSms(mobile,template_code,sign_name,"{\"smscode\":\""+smscode+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
