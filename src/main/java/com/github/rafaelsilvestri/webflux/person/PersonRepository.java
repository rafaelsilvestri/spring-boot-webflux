package com.github.rafaelsilvestri.webflux.person;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, UUID>, PersonCustomRepository {

  @Query("SELECT * FROM person WHERE lastname = :lastname")
  List<Person> findByLastname(String lastname, Pageable pageable);

}
