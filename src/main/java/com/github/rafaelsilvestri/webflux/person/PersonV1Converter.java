package com.github.rafaelsilvestri.webflux.person;

import org.springframework.stereotype.Component;

/**
 * Simple Data Converter
 *
 * @author Rafael Silvestri
 */
@Component
public class PersonV1Converter {

  public Person toEntity(PersonV1Dto dto) {
    Person entity = new Person();
    entity.setId(dto.getId());
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    return entity;
  }

  public PersonV1Dto toDto(Person entity) {
    PersonV1Dto dto = new PersonV1Dto();
    dto.setId(entity.getId());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    return dto;
  }

}
