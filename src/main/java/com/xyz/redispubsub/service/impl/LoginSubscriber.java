package com.xyz.redispubsub.service.impl;

import com.xyz.redispubsub.enums.RedisTopicEnums;
import com.xyz.redispubsub.service.Subscriber;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登入消息订阅
 */
@Service
public class LoginSubscriber implements Subscriber {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 订阅主题
     *
     * @return String
     */
    @Override
    public String getTopic() {
        return RedisTopicEnums.TOPIC_LOGIN.getTopic();
    }

    /**
     * 消费消息
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(String.format("欢迎%s回家",message.toString()));
    }
}
