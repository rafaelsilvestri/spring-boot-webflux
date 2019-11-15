package com.github.rafaelsilvestri.webflux.hello;

import static com.github.rafaelsilvestri.webflux.WebfluxApplication.CONTEXT_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloV1Router {

  @Bean
  public RouterFunction<ServerResponse> route(HelloV1Handler handler) {

    return RouterFunctions
        .route(GET(CONTEXT_PATH + "/v1/hello").and(accept(
            APPLICATION_JSON)), handler::get);
  }
}
