package com.epam.spring.hometask.service.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

// ToDo: implement DiscountStrategy
public class CinemaDiscountService implements DiscountService {

  /**
   * Returns discount value from 0 to 100, based on rules for user that buys tickets for event on
   * the specific date and time.
   *
   * @param user User that buys tickets, could be null
   * @param event Event that tickets are bought for
   * @param airDateTime The date and time event will be aired
   * @param numberOfTickets Number of tickets that user buys
   */
  @Override
  public byte getDiscount(
      @Nullable User user,
      @Nonnull Event event,
      @Nonnull LocalDateTime airDateTime,
      long numberOfTickets) {
    return 0;
  }
}
