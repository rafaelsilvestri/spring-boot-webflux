package com.github.rafaelsilvestri.webflux.person;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Person router config (functional style). The supported versions could be handled by this class
 *
 * @author Rafael Silvestri
 */
@Configuration
public class PersonRouter {

  @Value("${server.servlet.context-path}")
  private String contextPath;

  /**
   * Routes to version 1
   */
  @Bean
  public RouterFunction<ServerResponse> personV1Route(PersonV1Handler handler) {
    // look at nested, path (for context, version, accept, produces, etc)
    return nest(path(contextPath),
        nest(accept(APPLICATION_JSON),
            route(POST("/v1/person"), handler::post)));
  }

  /**
   * Routes to version 2
   */
  @Bean
  public RouterFunction<ServerResponse> personV2Route(PersonV1Handler handler) {
    // look at nested, path (for context, version, accept, produces, etc)
    return nest(path(contextPath),
        nest(accept(APPLICATION_JSON),
            route(POST("/v2/person"), handler::post)));
  }

}
