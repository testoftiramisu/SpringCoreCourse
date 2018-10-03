package com.epam.spring.hometask.dao.event;

import com.epam.spring.hometask.domain.Event;

import java.util.Collection;

public interface EventDao {

  /** Returns all {@link Event} objects. */
  Collection<Event> getAll();

  /**
   * Returns an {@link Event} by id.
   *
   * @param id id of the object
   */
  Event getById(Long id);

  /**
   * Saves an {@link Event}.
   *
   * @param event that needs to be saved
   * @return id of saved {@link Event}
   */
  Long save(Event event);

  /**
   * Returns given {@link Event}
   *
   * @param event for removing
   */
  void remove(Event event);
}
