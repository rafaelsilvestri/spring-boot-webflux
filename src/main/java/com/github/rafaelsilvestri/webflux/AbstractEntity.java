package com.github.rafaelsilvestri.webflux;

import org.springframework.data.domain.Persistable;

/**
 * @author Rafael Silvestri
 */
public abstract class AbstractEntity<ID> implements Persistable<ID> {

  public abstract ID getId();

  public abstract void setId(ID id);

  @Override
  public boolean isNew() {
    if (getId() instanceof String) {
      return getId() == null || ((String) getId()).trim().length() == 0;
    }
    return getId() == null;
  }

  @Override
  public int hashCode() {
    int prime = 37;
    return prime * ((getId() == null) ? 0 : getId().hashCode());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }

    AbstractEntity<ID> other = (AbstractEntity<ID>) obj;
    if (getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!getId().equals(other.getId())) {
      return false;
    }

    return true;
  }
}
