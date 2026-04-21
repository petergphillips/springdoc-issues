package com.example.springdocissues

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IssuesController {
  @GetMapping("/")
  fun greet(greeting: Greeting): String = greeting.objectName?.toString() ?: "Hello World"
}

data class Greeting(
  @field:Schema(description = "The name of the person to greet")
  val stringName: String? = null,
  @field:Schema(description = "The object of the person to greet")
  val objectName: SomeObject? = null,
)
data class SomeObject(val someProperty: String)
