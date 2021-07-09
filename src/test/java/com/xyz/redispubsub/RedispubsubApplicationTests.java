package com.xyz.redispubsub;

import com.xyz.redispubsub.enums.RedisTopicEnums;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedispubsubApplicationTests {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 建立发布者，通过频道发布消息
     */
    @Test
    void contextLoads() {
        redisTemplate.convertAndSend(RedisTopicEnums.TOPIC_LOGIN.getTopic(), "小明");
    }

}
