package com.epam.spring.hometask.service.event;

import com.epam.spring.hometask.dao.event.CinemaEventDao;
import com.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class CinemaEventService implements EventService {
  private CinemaEventDao events;

  public CinemaEventService(CinemaEventDao events) {
    this.events = events;
  }

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

  @Override
  public Event save(@Nonnull Event event) {
    Long id = events.save(event);
    return event.setId(id);
  }

  @Override
  public void remove(@Nonnull Event event) {
    events.remove(event);
  }

  @Override
  public Event getById(@Nonnull Long id) {
    return events.getById(id);
  }

  @Nonnull
  @Override
  public Collection<Event> getAll() {
    return events.getAll();
  }
}
