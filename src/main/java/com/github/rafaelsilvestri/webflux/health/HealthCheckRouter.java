package com.github.rafaelsilvestri.webflux.health;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Health Check Router
 *
 * @author Rafael Silvestri
 */
@Configuration
public class HealthCheckRouter {

  /**
   * Health Check Route to version 1
   */
  @Bean
  public RouterFunction<ServerResponse> healthCheckV1Router(HealthCheckV1Handler handler) {
    return nest(accept(APPLICATION_JSON, APPLICATION_XML),
        route(GET("/v1/statuscheck"), handler::get)
            .andRoute(GET("/v1/resourcecheck"), handler::get));
  }
}
