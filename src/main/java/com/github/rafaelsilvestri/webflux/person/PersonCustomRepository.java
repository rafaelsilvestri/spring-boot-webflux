package com.github.rafaelsilvestri.webflux.person;

/**
 * Just a simple example on how to customize Spring Data Repositories
 *
 * @author Rafael Silvestri
 */
public interface PersonCustomRepository {

  Person customSave(Person person);
}
