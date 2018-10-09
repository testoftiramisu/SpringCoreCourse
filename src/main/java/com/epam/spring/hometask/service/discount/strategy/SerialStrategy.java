package com.epam.spring.hometask.service.discount.strategy;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class SerialStrategy implements DiscountStrategy {
  private int ticketsForDiscount;
  private byte baseDiscount;

  public SerialStrategy(int ticketsForDiscount, byte baseDiscount) {
    this.ticketsForDiscount = ticketsForDiscount;
    this.baseDiscount = baseDiscount;
  }

  @Override
  public byte calculateDiscount(
      @Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime date, long ticketsAmount) {
    int previousTickets = 0;
    double discountForTicket = 0;
    double cumulativePrice = 0;

    if (user != null) {
      previousTickets = user.getTickets().size();
    }

    for (int tickets = previousTickets; tickets <= previousTickets + ticketsAmount; tickets++) {
      cumulativePrice += event.getBasePrice();
      if (tickets % ticketsForDiscount == 0) {
        discountForTicket += (event.getBasePrice() / 100) * baseDiscount;
      }
    }

    return (byte) (discountForTicket / (cumulativePrice / 100));
  }

  /** Returns the base discount for serial strategy. */
  public double getDiscount() {
    return baseDiscount;
  }

  /** Set the base discount for serial strategy. */
  public void setDiscount(byte discount) {
    this.baseDiscount = discount;
  }

  /** Returns the amount of tickets required for getting a discount. */
  public int getTicketsForDiscount() {
    return ticketsForDiscount;
  }

  /** Sets the amount of tickets required for getting a discount. */
  public void setTicketsForDiscount(int ticketNumberToGetDiscount) {
    this.ticketsForDiscount = ticketNumberToGetDiscount;
  }
}
