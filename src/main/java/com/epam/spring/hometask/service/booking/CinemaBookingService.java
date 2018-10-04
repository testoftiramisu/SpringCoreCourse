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
  private double vipSeatsCoefficient;
  private double highRateCoefficient;
  private TicketDao tickets;

  public CinemaBookingService(TicketDao tickets, EventDao events) {
    this.tickets = tickets;
  }

  /** Returns coefficient for Vip seats price. */
  public double getVipSeatsCoefficient() {
    return vipSeatsCoefficient;
  }

  /** Sets coefficient for Vip seats price. */
  public void setVipSeatsCoefficient(double vipSeatsCoefficient) {
    this.vipSeatsCoefficient = vipSeatsCoefficient;
  }

  /** Returns coefficient for High rate price. */
  public double getHighRateCoefficient() {
    return highRateCoefficient;
  }

  /** Sets coefficient for High rate price. */
  public void setHighRateCoefficient(double highRateCoefficient) {
    this.highRateCoefficient = highRateCoefficient;
  }

  /**
   * Returns total price when purchase all selected seats for particular event.
   *
   * @param event event to get base ticket price, vip seats and other information
   * @param dateTime date and time of event air
   * @param user user that buys ticket (could be needed to calculate discount, could be null)
   * @param seats set of seat numbers that user wants to buy
   */
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

  /**
   * Books tickets in internal system. If user is not <code>null</code> in a ticket then booked
   * tickets are saved with it.
   *
   * @param tickets Set of tickets
   */
  @Override
  public void bookTickets(@Nonnull Set<Ticket> tickets) {
    for (Ticket ticket : tickets) {
      User user = ticket.getUser();
      if (user != null) {
        user.getTickets().add(ticket);
      }
      this.tickets.save(ticket);
    }
  }

  /**
   * Returns set of all purchased tickets for event on specific air date and time.
   *
   * @param event Event to get tickets for
   * @param dateTime Date and time of airing of event
   */
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
