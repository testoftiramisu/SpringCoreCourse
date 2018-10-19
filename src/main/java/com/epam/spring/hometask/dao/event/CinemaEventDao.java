package com.epam.spring.hometask.dao.event;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CinemaEventDao implements EventDao {
  private Map<Long, Event> events;
  private IdGeneratorService idGeneratorService;

  public CinemaEventDao(IdGeneratorService idGeneratorService) {
    events = new HashMap<>();
    this.idGeneratorService = idGeneratorService;
  }

  /**
   * Stores all {@link Event} from provided collection.
   *
   * @param events Collection of {@link Event}
   */
  public void setEvents(Collection<Event> events) {
    events.forEach(this::save);
  }

  /** Returns all {@link Event} from storage. */
  @Override
  public Collection<Event> getAll() {
    return ImmutableSet.copyOf(events.values());
  }

  /**
   * Returns {@link Event} by id from storage if found, returns null otherwise.
   *
   * @param id id of the object
   */
  @Override
  public Event getById(Long id) {
    if (events.containsKey(id)) {
      return events.get(id);
    }
    return null;
  }

  /**
   * Returns id of {@code Event} saved in storage.
   *
   * @param event that needs to be saved in storage
   */
  @Override
  public Long save(Event event) {
    Long id = idGeneratorService.getId();
    events.put(id, event);
    return id;
  }

  /**
   * Removes {@link Event} from storage, if present.
   *
   * @param event for removing
   */
  @Override
  public void remove(Event event) {
    for (Long id : events.keySet()) {
      Event currentEvent = events.get(id);
      if (currentEvent.equals(event)) {
        events.remove(id);
        break;
      }
    }
  }
}
