package com.tr.ksbg.graphql.resolver

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.LocalDate
import java.util.UUID

@Controller
class HelloWorldResolver {

    @QueryMapping(name = "helloWorld")
    fun getHelloWorld(): String {
        return "Hello world!!!"
    }

    @QueryMapping(name = "greet")
    fun greet(@Argument name: String): String {
        return "Hello $name"
    }

    @QueryMapping
    fun getRandomNumbers(): List<Int> {
        return listOf(1, 2, 3)
    }

    @QueryMapping(name = "getEvent")
    fun getEvent(): Event {
        return Event(
            id = UUID.randomUUID(),
            eventType = "Testing"
        )
    }

    @QueryMapping(name = "test")
    fun test(@Argument date: LocalDate): String {
        return "date: $date"
    }
}

data class Event(
    private val id: UUID,
    private val eventType: String
)
