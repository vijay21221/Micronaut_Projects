package com.example

import io.lettuce.core.RedisClient
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedisConnector {

    @Inject
    RedisClient redisClient

    def registerRedisMessageListener(){
        StatefulRedisPubSubConnection pubSubConnection = redisClient.connectPubSub()
        pubSubConnection.addListener(new RedisMessageListener())
        RedisPubSubCommands sync = pubSubConnection.sync()
        sync.subscribe("test")
    }
}
