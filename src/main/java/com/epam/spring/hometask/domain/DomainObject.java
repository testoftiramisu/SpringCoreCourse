package com.epam.spring.hometask.domain;

public class DomainObject {

  private Long id;

  public Long getId() {
    return id;
  }

  public <T extends DomainObject> T setId(Long id) {
    this.id = id;
    return (T) this;
  }
}
