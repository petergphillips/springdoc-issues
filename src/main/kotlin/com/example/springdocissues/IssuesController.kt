package com.example.springdocissues

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IssuesController {
  @GetMapping("/")
  fun greet(greeting: Greeting): String = greeting.name?.toString() ?: "Hello World"
}

data class Greeting(val name: Any? = null)
