package com.github.rafaelsilvestri.webflux.health;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Health Check handler
 *
 * @author Rafael Silvestri
 */
@Component
public class HealthCheckV1Handler {

  /**
   * Returns OK if this method is reachable
   */
  public Mono<ServerResponse> get(ServerRequest serverRequest) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_XML)
        .body(BodyInserters.fromValue(new Status("OK")));
  }
}
