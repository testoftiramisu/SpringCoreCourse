package com.epam.spring.hometask.service.event;

import com.epam.spring.hometask.dao.event.EventDao;
import com.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class CinemaEventService implements EventService {
  private EventDao events;

  public CinemaEventService(EventDao events) {
    this.events = events;
  }

  /**
   * Returns founded event by name or null.
   *
   * @param name Name of the event
   */
  @Nullable
  @Override
  public Event getByName(@Nonnull String name) {
    for (Event event : events.getAll()) {
      String currentName = event.getName();
      if (currentName.equals(name)) {
        return event;
      }
    }
    return null;
  }

  /**
   * Saves given {@link Event} in storage.
   *
   * @return id from the storage.
   */
  @Override
  public Event save(@Nonnull Event event) {
    Long id = events.save(event);
    return event.setId(id);
  }

  /** Removes given {@link Event} from the storage. */
  @Override
  public void remove(@Nonnull Event event) {
    events.remove(event);
  }

  /** Returns {@link Event} from the storage by given id. */
  @Override
  public Event getById(@Nonnull Long id) {
    return events.getById(id);
  }

  /** Removes all {@link Event} from the storage. */
  @Nonnull
  @Override
  public Collection<Event> getAll() {
    return events.getAll();
  }
}
