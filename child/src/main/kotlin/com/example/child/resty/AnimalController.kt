package com.example.child.resty

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AnimalController {
    @RequestMapping(value=["/feed"], method = [RequestMethod.GET])
    fun feedCuteAnimal(): String {
        return """{"message": "No more hungry."}"""
    }
}
