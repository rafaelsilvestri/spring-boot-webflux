package com.github.rafaelsilvestri.webflux.person;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Custom implementation of Spring Data Repository that uses JdbcTemplate to perform database
 * operations.
 *
 * @author Rafael Silvestri
 */
public class PersonCustomRepositoryImpl implements PersonCustomRepository {

  private static final Logger log = LoggerFactory.getLogger(PersonCustomRepositoryImpl.class);

  private JdbcTemplate jdbcTemplate;

  public PersonCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Person customSave(Person person) {
    log.info("Invoking a custom save method for {}", person);
    if (person.isNew()) {
      person.setId(UUID.randomUUID());
    }

    jdbcTemplate
        .update("INSERT INTO person(id,first_name, last_name) VALUES (?,?,?)", person.getId(),
            person.getFirstName(), person.getLastName());

    return person;
  }
}
