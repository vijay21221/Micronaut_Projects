package com.example

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisListCommands

import javax.inject.Inject

class RedisPublishMessage {

    @Inject
    RedisClient redisClient

    void publishMessage(String listName, String message){
        StatefulRedisConnection connection = redisClient.connect()
        RedisListCommands sync = connection.sync()
        sync.lpush(listName, message)
        println("Message is published to redis successfully!!!")
    }
}
