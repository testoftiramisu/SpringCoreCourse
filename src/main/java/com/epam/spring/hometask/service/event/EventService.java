package com.epam.spring.hometask.service.event;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.AbstractDomainObjectService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface EventService extends AbstractDomainObjectService<Event> {

  /**
   * Returns founded event by name or null.
   *
   * @param name Name of the event
   */
  public @Nullable Event getByName(@Nonnull String name);

  /**
   * Returns set of events, that air on specified date range.
   *
   * @param from Start date
   * @param to End date inclusive
   */
  // public @Nonnull Set<Event> getForDateRange(@Nonnull LocalDate from,
  // @Nonnull LocalDate to);

  /**
   * Returns events from 'now' till the the specified date time.
   *
   * @param to End date time inclusive
   */
  // public @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);

}
