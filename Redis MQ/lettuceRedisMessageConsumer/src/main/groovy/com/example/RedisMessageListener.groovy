package com.example

import io.lettuce.core.pubsub.RedisPubSubAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

class RedisMessageListener extends RedisPubSubAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageListener)
    @Override
    void message(Object channel, Object message) {
        LOGGER.info("channel : $channel, message : $message")
    }

    @Override
    void message(Object pattern, Object channel, Object message) {

    }

    @Override
    void subscribed(Object channel, long count) {
        LOGGER.info("This service subscribed to channel appfactory")
    }

    @Override
    void psubscribed(Object pattern, long count) {

    }

    @Override
    void unsubscribed(Object channel, long count) {

    }

    @Override
    void punsubscribed(Object pattern, long count) {

    }
}
