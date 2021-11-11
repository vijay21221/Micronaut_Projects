package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller
class MessageSenderController {

    @Post("/api/sendMessage")
    def sendMessage(@Body Map body){
        MessagePublisher publisher = new RedisMessagePublisher()
        publisher.publishMessage(new ObjectMapper().writeValueAsString(body))
        println('message published')
        return "message_published_successfully"
    }
}
