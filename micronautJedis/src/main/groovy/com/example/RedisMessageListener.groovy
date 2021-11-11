package com.example

import redis.clients.jedis.JedisPoolConfig

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedisMessageListener {

    @Inject
    JedisPoolConfig jedisPoolConfig



}
