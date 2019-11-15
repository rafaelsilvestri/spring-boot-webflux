package com.github.rafaelsilvestri.webflux.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.rafaelsilvestri.webflux.PersistenceConfig;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration(classes = PersistenceConfig.class)
public class PersonRepositoryTest {

  @Autowired
  PersonRepository personRepository;

  @Test
  public void createPerson() {
    Person person = new Person();
    person.setFirstName("Rafael");
    person.setLastName("Silvestri");

    Person persisted = personRepository.save(person);
    assertNotNull(persisted.getId());

    persisted.setLastName("Cechinel Silvestri");
    personRepository.save(persisted);

    Optional<Person> reloaded = personRepository.findById(persisted.getId());
    assertTrue(reloaded.isPresent());

    assertEquals("Cechinel Silvestri", reloaded.get().getLastName());
  }

}