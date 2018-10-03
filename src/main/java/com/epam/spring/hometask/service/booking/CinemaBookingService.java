package com.epam.spring.hometask.service.booking;

import com.epam.spring.hometask.dao.event.EventDao;
import com.epam.spring.hometask.dao.ticket.TicketDao;
import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.EventRating;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.google.common.collect.ImmutableSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public class CinemaBookingService implements BookingService {
  private final double vipSeatsCoefficient = 2D;
  private final double highRateCoefficient = 1.2D;
  private TicketDao tickets;

  public CinemaBookingService(TicketDao tickets, EventDao events) {
    this.tickets = tickets;
  }

  @Override
  public double getTicketsPrice(
      @Nonnull Event event,
      @Nonnull LocalDateTime dateTime,
      @Nullable User user,
      @Nonnull Set<Long> seats) {

    Auditorium auditorium = event.getAuditoriums().get(dateTime);
    double ticketsPrice = 0;
    double basePrice = event.getBasePrice();

    if (event.getRating().equals(EventRating.HIGH)) {
      basePrice *= highRateCoefficient;
    }

    for (Long seat : seats) {
      ticketsPrice +=
          auditorium.getVipSeats().contains(seat) ? basePrice * vipSeatsCoefficient : basePrice;
    }

    return ticketsPrice;
  }

  @Override
  public void bookTickets(@Nonnull Set<Ticket> tickets) {
    for (Ticket ticket : tickets) {
      User user = ticket.getUser();
      if (user.getId() != null) {
        user.getTickets().add(ticket);
      }
      this.tickets.save(ticket);
    }
  }

  @Nonnull
  @Override
  public Set<Ticket> getPurchasedTicketsForEvent(
      @Nonnull Event event, @Nonnull LocalDateTime dateTime) {
    ImmutableSet.Builder<Ticket> purchasedTickets = ImmutableSet.builder();

    for (Ticket ticket : tickets.getAll()) {
      if (ticket.getEvent().equals(event) && ticket.getDateTime().equals(dateTime)) {
        purchasedTickets.add(ticket);
      }
    }
    return purchasedTickets.build();
  }
}
