package com.example

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.api.sync.RedisListCommands
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

@CompileStatic
class Application {
    static ApplicationContext applicationContext
    private static Logger LOGGER = LoggerFactory.getLogger(Application)

    static void main(String[] args) {
        applicationContext = Micronaut.run(Application, args)
        initializeRedisMessageListener()
    }

    static void registerRedisMessageListener(){
        RedisClient redisClient = applicationContext.getBean(RedisClient)
        StatefulRedisPubSubConnection connection = redisClient.connectPubSub()
        connection.addListener(new RedisMessageListener())
        RedisPubSubCommands sync = connection.sync()
        sync.subscribe("test")
    }

    static void initializeRedisMessageListener(){
        Thread.start {
            RedisClient redisClient = applicationContext.getBean(RedisClient)
            StatefulRedisConnection connection = redisClient.connect()
            RedisCommands redisCommands = connection.sync()
            LOGGER.info("started redis message listener")
            while (true){
                try{
                    def message = redisCommands.brpoplpush(0,"appfactory", "appfactory_backup")
                    LOGGER.info("message : $message consumed successfully")
                    redisCommands.del("appfactory_backup")
                    LOGGER.info("Deleted backup message from appfactory_backup")
                }catch(e){
                    LOGGER.error(e.getMessage())
                }

            }

        }
    }
}
