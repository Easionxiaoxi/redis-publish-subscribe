package com.xyz.redispubsub.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyz.redispubsub.service.Subscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * redis配置类
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * redis模板序列化配置
     * @return RedisTemplate<String, Object>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // fastjson序列化器
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // string key 序列化
        redisTemplate.setStringSerializer(new StringRedisSerializer());
        // string key 序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash key 序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // hash value 序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }

    /**
     * redis发布订阅监听配置,添加订阅者
     * @return RedisTemplate<String, Object>
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,List<Subscriber> subscriberList) {
        //创建一个消息监听容器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //设置连接工厂
        container.setConnectionFactory(connectionFactory);
        if (!CollectionUtils.isEmpty(subscriberList)) {
            for (Subscriber subscriber : subscriberList) {
                container.addMessageListener(subscriber, new PatternTopic(subscriber.getTopic()));
            }
        }
        return container;
    }

}
