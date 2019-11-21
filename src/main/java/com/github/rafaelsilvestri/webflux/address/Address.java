package com.github.rafaelsilvestri.webflux.address;

import com.github.rafaelsilvestri.webflux.AbstractEntity;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Address entity
 *
 * @author Rafael Silvestri
 */
@Table("address")
public class Address extends AbstractEntity<UUID> {

  @Id
  private UUID id;
  @Column("street_name")
  private String street;
  private String city;
  private String state;
  @Column("zip_code")
  private String zipCode;

  @Override
  public boolean isNew() {
    return id == null;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
