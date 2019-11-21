package com.github.rafaelsilvestri.webflux.person;

import com.github.rafaelsilvestri.webflux.AbstractEntity;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class
 *
 * @author Rafael Silvestri
 */
@Table("person")
public class Person extends AbstractEntity<UUID> {

  @Id
  private UUID id;

  @Column("first_name")
  private String firstName;

  @Column("last_name")
  private String lastName;

  @Column("address_id")
  private UUID addressId;

  public Person() {
  }

  public Person(UUID id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
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

  public UUID getAddressId() {
    return addressId;
  }

  public void setAddressId(UUID addressId) {
    this.addressId = addressId;
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", addressId=" + addressId +
        '}';
  }
}
