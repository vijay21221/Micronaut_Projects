package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject

@Controller
class AppController {

    @Inject
    RedisConnector redisConnector

    @Get("/api/registerListener")
    def startRedisConnection(){
        redisConnector.registerRedisMessageListener()
        return true
    }

    @Get("/api/publishMessage")
    def publishMessage(){
        String key = "appfactory"
        String message = "vijay" + new Random().nextLong()
        new RedisPublishMessage().publishMessage(key, message)
        return true
    }
}
