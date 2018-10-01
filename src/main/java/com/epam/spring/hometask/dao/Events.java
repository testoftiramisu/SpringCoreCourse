package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Event;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Events {
  private static Map<Long, Event> events;
  private Long baseId;

  public Events() {
    events = new HashMap<>();
    baseId = 10000L;
  }

  /** Returns all {@code Event} from storage. */
  public Collection<Event> getAll() {
    return ImmutableSet.copyOf(events.values());
  }

  /**
   * Returns {@code Event} by id from storage if found, returns null otherwise.
   *
   * @param id id of the object
   */
  public Event getById(Long id) {
    if (events.containsKey(id)) {
      return events.get(id);
    }
    return null;
  }

  /**
   * Returns true when {@code Event} saved in storage, returns false otherwise.
   *
   * @param event that needs to be saved in storage
   */
  public boolean save(Event event) {
    try {
      events.put(baseId + 1, event);
      return true;
    } catch (NullPointerException ex) {
      return false;
    }
  }

  /**
   * Returns given Event from storage, if present.
   *
   * @param event for removing
   */
  public void remove(Event event) {
    for (Long id : events.keySet()) {
      Event currentEvent = events.get(id);
      if (currentEvent.equals(event)) {
        events.remove(id);
      }
    }
  }
}
