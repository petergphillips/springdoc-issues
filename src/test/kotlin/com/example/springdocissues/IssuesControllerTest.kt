package com.example.springdocissues

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
import org.springframework.test.web.reactive.server.WebTestClient
import tools.jackson.databind.json.JsonMapper
import java.io.FileWriter

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class IssuesControllerTest(
  @param:Autowired val webTestClient: WebTestClient,
) {
  @Test
  fun `parameter should be required`() {
    webTestClient.get().uri("/")
      .exchange()
      .expectStatus()
      .isBadRequest
  }

  @Test
  fun `greet endpoint parameter should be marked as required in api docs`() {
    webTestClient.get().uri("/v3/api-docs")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .consumeWith(System.out::println)
      .jsonPath("paths./.get.parameters[0].required").isEqualTo("true")
  .consumeWith {
    JsonMapper().let { mapper ->
      mapper.writerWithDefaultPrettyPrinter().writeValue(
        FileWriter("old.json"),
        mapper.readTree(it.responseBody!!)
      )
    }
  }
  }

  @Test
  fun `Greeting name parameter should be marked as required in api docs`() {
    webTestClient.get().uri("/v3/api-docs")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .consumeWith(System.out::println)
      .jsonPath("components.schemas.Greeting.required").isEqualTo("name")
  }
}
