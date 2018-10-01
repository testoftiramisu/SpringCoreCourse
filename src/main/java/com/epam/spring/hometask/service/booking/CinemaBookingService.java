package com.epam.spring.hometask.service.booking;

import com.epam.spring.hometask.dao.Events;
import com.epam.spring.hometask.dao.Tickets;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

// ToDo methods implementations.
public class CinemaBookingService implements BookingService {
  private Tickets tickets;
  private Events events;

  public CinemaBookingService(Tickets tickets, Events events) {
    this.tickets = tickets;
    this.events = events;
  }

  @Override
  public double getTicketsPrice(
      @Nonnull Event event,
      @Nonnull LocalDateTime dateTime,
      @Nullable User user,
      @Nonnull Set<Long> seats) {
    return 0;
  }

  @Override
  public void bookTickets(@Nonnull Set<Ticket> tickets) {}

  @Nonnull
  @Override
  public Set<Ticket> getPurchasedTicketsForEvent(
      @Nonnull Event event, @Nonnull LocalDateTime dateTime) {
    return null;
  }
}
