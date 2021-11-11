//package com.example
//
//import groovy.transform.CompileStatic
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import redis.clients.jedis.JedisPubSub
//
//import javax.inject.Singleton
//
//@Singleton
//@CompileStatic
//class RedisMessageListener extends JedisPubSub{
//    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageListener)
//
//    @Override
//    public void onMessage(String channel, String message) {
//        LOGGER.info("channel : $channel, message : $message")
//    }
//
//    @Override
//    public void onSubscribe(String channel, int subscribedChannels) {
//        LOGGER.info("This service is subscribed to the channel : $channel")
//    }
//}
