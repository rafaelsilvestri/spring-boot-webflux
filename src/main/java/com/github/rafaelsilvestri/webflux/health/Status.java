package com.github.rafaelsilvestri.webflux.health;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rafael Silvestri
 */
@XmlRootElement(name = "status")
public class Status {

  private String result;

  public Status() {
  }

  public Status(String result) {
    this.result = result;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
