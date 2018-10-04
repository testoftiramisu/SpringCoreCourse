package com.epam.spring.hometask.domain;

public class DomainObject {

  private Long id;

  /** Returns id. */
  public Long getId() {
    return id;
  }

  /**
   * Returns object with set id.
   *
   * @param id that needs to be set for object
   */
  public <T extends DomainObject> T setId(Long id) {
    this.id = id;
    return (T) this;
  }
}
