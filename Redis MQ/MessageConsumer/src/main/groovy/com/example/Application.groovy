package com.example

import io.lettuce.core.RedisClient
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
    private static final Logger LOGGER = LoggerFactory.getLogger(Application)
    static ApplicationContext applicationContext = null

    static void main(String[] args) {
        applicationContext = Micronaut.run(Application, args)
        LOGGER.info("Application started")
        initializeRedisSubscriber()
        LOGGER.info("Main thread done")
    }


    static void initializeRedisSubscriber(){
//        Thread.start {
//            LOGGER.info("Initializing redis subscriber")
//            Jedis jedis = null
//            try{
//                jedis = new Jedis()
//                jedis.subscribe(new RedisMessageListener(), "appfactory")
//            }catch(e){
//                println(e.getMessage())
//            }finally{
//                if(jedis!= null){
//                    jedis.close()
//                    LOGGER.info("jedis connection is closed")
//                }
//            }
//        }

        RedisClient redisClient = RedisClient.create("redis://localhost")
        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub()
        connection.addListener(new RedisLettuceMessageListener())
        RedisPubSubCommands sync = connection.sync()
        sync.subscribe("appfactory")

    }
}
