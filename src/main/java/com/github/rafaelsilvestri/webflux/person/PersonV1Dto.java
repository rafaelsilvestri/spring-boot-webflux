package com.github.rafaelsilvestri.webflux.person;

import java.util.UUID;

/**
 * A simple data-transfer-object
 *
 * @author Rafael Silvestri
 */
public class PersonV1Dto {

  private UUID id;
  private String firstName;
  private String lastName;

  public PersonV1Dto() {
  }

  public PersonV1Dto(UUID id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
