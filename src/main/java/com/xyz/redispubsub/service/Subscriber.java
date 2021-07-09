package com.xyz.redispubsub.service;

import org.springframework.data.redis.connection.MessageListener;

/**
 * 消息订阅者服务接口
 */
public interface Subscriber extends MessageListener {
    /**
     * 订阅者类型
     *
     * @return String 当前类名
     */
    default String getType() {
        return this.getClass().getSimpleName();
    }

    /**
     * 订阅主题
     *
     * @return String
     */
    String getTopic();
}
