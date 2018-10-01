package com.epam.spring.hometask.service.booking;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {

  /**
   * Returns total price when purchase all selected seats for particular event.
   *
   * @param event event to get base ticket price, vip seats and other information
   * @param dateTime date and time of event air
   * @param user user that buys ticket (could be needed to calculate discount, could be null)
   * @param seats set of seat numbers that user wants to buy
   */
  public double getTicketsPrice(
      @Nonnull Event event,
      @Nonnull LocalDateTime dateTime,
      @Nullable User user,
      @Nonnull Set<Long> seats);

  /**
   * Books tickets in internal system. If user is not <code>null</code> in a ticket then booked
   * tickets are saved with it.
   *
   * @param tickets Set of tickets
   */
  public void bookTickets(@Nonnull Set<Ticket> tickets);

  /**
   * Returns set of all purchased tickets for event on specific air date and time.
   *
   * @param event Event to get tickets for
   * @param dateTime Date and time of airing of event
   */
  public @Nonnull Set<Ticket> getPurchasedTicketsForEvent(
      @Nonnull Event event, @Nonnull LocalDateTime dateTime);
}
