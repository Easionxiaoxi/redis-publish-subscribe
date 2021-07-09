package com.xyz.redispubsub.enums;

/**
 * redis 发布订阅主题枚举
 */
public enum RedisTopicEnums {
    /**
     * redis主题名称定义 需要与发布者一致
     */
    TOPIC_LOGIN("topic:login", "用户登入通知"),
    ;

    /**
     * 主题名称
     */
    private String topic;
    /**
     * 描述
     */
    private String description;

    RedisTopicEnums(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
