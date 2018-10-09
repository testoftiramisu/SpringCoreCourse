package com.epam.spring.hometask.service.discount.strategy;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public interface DiscountStrategy {

  /**
   * Returns discount value based on rules defined by Strategy implementation.
   *
   * @param user User that buys tickets, could be <code>null</code>
   * @param event event that tickets are bought for
   * @param dateTime Date and time of aired event for which to calculate discount strategy
   * @param numberOfTickets number of tickets that user buys
   */
  byte calculateDiscount(
      @Nullable User user,
      @Nonnull Event event,
      @Nonnull LocalDateTime dateTime,
      long numberOfTickets);
}
