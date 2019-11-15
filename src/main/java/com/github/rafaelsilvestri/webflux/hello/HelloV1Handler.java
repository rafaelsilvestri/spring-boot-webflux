package com.github.rafaelsilvestri.webflux.hello;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloV1Handler {

  public Mono<ServerResponse> get(ServerRequest serverRequest) {

    final Mono<String> result = Mono.just("Hello World!!!");

    return ok().contentType(MediaType.APPLICATION_JSON)
        .body(result, String.class);
  }
}
