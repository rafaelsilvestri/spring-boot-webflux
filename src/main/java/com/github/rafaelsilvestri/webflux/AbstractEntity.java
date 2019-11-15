package com.github.rafaelsilvestri.webflux;

import org.springframework.data.domain.Persistable;

/**
 * Abstract Entity
 *
 * @param <T> the ID type
 * @author Rafael Silvestri
 */
public abstract class AbstractEntity<T> implements Persistable<T> {

  public abstract void setId(T id);

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

    AbstractEntity<T> other = (AbstractEntity<T>) obj;
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
