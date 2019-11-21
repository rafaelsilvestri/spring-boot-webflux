package com.github.rafaelsilvestri.webflux.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.rafaelsilvestri.webflux.PersistenceConfig;
import com.github.rafaelsilvestri.webflux.WebfluxApplication;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebfluxApplication.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@Transactional
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