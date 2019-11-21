package com.github.rafaelsilvestri.webflux.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * A business rules class to handle person entity.
 *
 * Use Callable and Schedulers.elastic to defer execution, and it creates a dedicated thread to wait
 * for the blocking resource without tying up some other resource.
 *
 * See: https://projectreactor.io/docs/core/release/reference/#faq.wrap-blocking
 *
 * @author Rafael Silvestri
 */
@Service
public class PersonService {

  private static final Logger log = LoggerFactory.getLogger(PersonService.class);

  private PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Uses a default repository method to save an entity
   */
  //@Transactional
  public Mono<Person> save(Person person) {
    return Mono.fromCallable(() -> personRepository.save(person))
        .subscribeOn(Schedulers.elastic());
  }

  /**
   * This method uses a custom repository to save an entity directly through the {@link
   * org.springframework.jdbc.core.JdbcTemplate}
   */
  /*@Transactional
  public Mono<Person> saveCustom(Person person) {
    return Mono.fromCallable(() -> personRepository.customSave(person))
        .subscribeOn(Schedulers.elastic());
  }*/

  /**
   * This method saves only one entry keeping data inconsistent. To ensure the context is
   * transactional we need to add @Transactional annotation
   */
  @Transactional
  public Mono<Person> saveNoTransaction(Person person) {
    // save a person
    Mono.fromCallable(() -> personRepository.save(person))
        .subscribeOn(Schedulers.elastic());

    // save another one and force an error to see the inconsistency happens
    return Mono.fromCallable(() -> personRepository.save(person))
        .subscribeOn(Schedulers.elastic())
        .doOnSuccess(p -> {
          log.info("doOnSuccess for {}", p);
          if (p.getId() != null) {
            throw new RuntimeException("This is an expected exception!!!!!!");
          }
        });
  }

}
