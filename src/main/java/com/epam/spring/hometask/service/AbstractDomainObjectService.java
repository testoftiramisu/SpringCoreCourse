package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import java.util.Collection;

/** @param <T> DomainObject subclass */
public interface AbstractDomainObjectService<T extends DomainObject> {

  /**
   * Saves new object to storage or updates existing one.
   *
   * @param object Object to save
   * @return saved object with assigned id
   */
  public T save(@Nonnull T object);

  /**
   * Removes object from storage.
   *
   * @param object Object to remove
   */
  public void remove(@Nonnull T object);

  /**
   * Gets object by id from storage.
   *
   * @param id id of the object
   * @return Found object or <code>null</code>
   */
  public T getById(@Nonnull Long id);

  /**
   * Gets all objects from storage.
   *
   * @return collection of objects
   */
  public @Nonnull Collection<T> getAll();
}
