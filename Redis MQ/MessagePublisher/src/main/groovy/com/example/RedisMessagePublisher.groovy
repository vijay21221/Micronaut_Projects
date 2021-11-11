package com.example

import redis.clients.jedis.Jedis


class RedisMessagePublisher implements MessagePublisher {
    @Override
    void publishMessage(Object message) {
        Jedis jedis = new Jedis("localhost", 6379)
        long subscribers = jedis.publish("appfactory", message.toString())
        println(subscribers)
        jedis.close()
    }
}
