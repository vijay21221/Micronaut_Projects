package com.example

import io.lettuce.core.pubsub.RedisPubSubAdapter
import io.lettuce.core.pubsub.RedisPubSubListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RedisLettuceMessageListener extends RedisPubSubAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLettuceMessageListener)
    @Override
    void message(Object channel, Object message) {
        LOGGER.info("channel : $channel, message : $message")
    }

    @Override
    void subscribed(Object channel, long count) {
        LOGGER.info("This service is subscribed to channel : $channel and count : $count")
    }

}
