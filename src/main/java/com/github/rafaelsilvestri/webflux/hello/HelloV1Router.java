package com.github.rafaelsilvestri.webflux.hello;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Hello World router
 *
 * @author Rafael Silvestri
 */
@Configuration
public class HelloV1Router {

  @Bean
  public RouterFunction<ServerResponse> helloRoute(HelloV1Handler handler) {
    return nest(accept(APPLICATION_JSON),
        route(GET("/v1/hello"), handler::get));
  }
}
