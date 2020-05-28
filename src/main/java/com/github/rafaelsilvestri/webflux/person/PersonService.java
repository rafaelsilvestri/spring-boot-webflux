package com.github.rafaelsilvestri.webflux.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A business rules class to handle person entity.
 * <p>
 * DO NOT USE reactive streams when working with relational database
 *
 * @author Rafael Silvestri
 */
@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Uses a default repository method to save an entity
     */
    @Transactional(timeout = 10)
    public Person save(Person person) {
        return personRepository.save(person);
    }

    /**
     * This method uses a custom repository to save an entity directly through the {@link
     * org.springframework.jdbc.core.JdbcTemplate}
     */
    @Transactional
    public Person saveCustom(Person person) {
        return personRepository.customSave(person);
    }

    /**
     * This method saves only one entry keeping data inconsistent. To ensure the context is
     * transactional we need to add @Transactional annotation
     */
    @Transactional
    public Person saveNoTransaction(Person person) {
        // save a person
        personRepository.save(person);

        // save another one and force an error to see the inconsistency happens
        Person p = personRepository.save(person);
        if (p.getId() != null) {
            throw new RuntimeException("This is an expected exception!!!!!!");
        }

        return p;
    }

    /**
     * Returns all entries
     *
     * @param page          page number (zero-based).
     * @param size          number of items to be returned.
     * @param sortBy        sort fields comma-separated
     * @param sortDirection sort direction (asc,desc)
     * @return paged items.
     */
    public Page<Person> findAll(int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.Direction.fromString(sortDirection), sortBy.split(","));
        return personRepository.findAll(pageable);
    }
}
