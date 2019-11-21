package com.github.rafaelsilvestri.webflux.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HelloV1Router.class, HelloV1Handler.class})
@WebFluxTest
class HelloV1HandlerTest {

  @Autowired
  private ApplicationContext context;

  @Value("${server.servlet.context-path}")
  private String contextPath;

  private WebTestClient webTestClient;

  @BeforeEach
  public void setUp() {
    webTestClient = WebTestClient.bindToApplicationContext(context).build();
  }

  @Test
  public void testGetRoute() {
    webTestClient.get()
        .uri(contextPath + "/v1/hello")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .value(response ->
            Assertions.assertThat(response).isEqualTo("Hello World!!!")
        );
  }

}