package com.github.rafaelsilvestri.webflux.person;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * A functional style of a "Person handler"
 *
 * @author Rafael Silvestri
 */
@Component
public class PersonV1Handler {

  private static final Logger log = LoggerFactory.getLogger(PersonV1Handler.class);

  private PersonService personService;
  private PersonV1Converter personConverter;

  public PersonV1Handler(PersonService personService, PersonV1Converter personConverter) {
    this.personService = personService;
    this.personConverter = personConverter;
  }

  /**
   * if transactional query param is false, run in an experiment to proof inconsistency, otherwise
   * run in a transactional context
   */
  public Mono<ServerResponse> post(ServerRequest serverRequest) {
    boolean isTransactional = serverRequest.queryParam("transactional")
        .map(Boolean::new)
        .orElse(Boolean.TRUE);

    return serverRequest.bodyToMono(PersonV1Dto.class)
        .map(dto -> personConverter.toEntity(dto))
        .flatMap(isTransactional ? personService::save : personService::saveNoTransaction)
        .map(entity -> personConverter.toDto(entity))
        .flatMap(person -> ok().body(Mono.just(person), PersonV1Dto.class))
        .doOnError(ex -> log.error("Oops, something went wrong!!!", ex))
        .doOnSuccess(e -> log.info("Success!!! {}", e.statusCode()));
  }

  /**
   * Returns all entries
   */
  public Mono<ServerResponse> get(ServerRequest serverRequest) {
    throw new IllegalStateException("Not implemented yet");
  }

}
