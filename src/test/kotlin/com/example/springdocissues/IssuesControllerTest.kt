package com.example.springdocissues

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
import org.springframework.test.web.reactive.server.WebTestClient
import tools.jackson.databind.json.JsonMapper

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class IssuesControllerTest(
  @param:Autowired val webTestClient: WebTestClient,
) {
  @Test
  fun `Greeting anyName parameter should not just have a type of null`() {
    webTestClient.get().uri("/v3/api-docs")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .consumeWith {
        JsonMapper().let { mapper ->
          mapper.writerWithDefaultPrettyPrinter().writeValue(
            System.out, mapper.readTree(it.responseBody!!)
          )
        }
      }
      .jsonPath("components.schemas.Greeting.properties.objectName.description")
      .isEqualTo("The object of the person to greet")
  }

  @Test
  fun `Greeting stringName parameter should have types of string and null`() {
    webTestClient.get().uri("/v3/api-docs")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .jsonPath("components.schemas.Greeting.properties.stringName.description")
      .isEqualTo("The name of the person to greet")
  }
}
