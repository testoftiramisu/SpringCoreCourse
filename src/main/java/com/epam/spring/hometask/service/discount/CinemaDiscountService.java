package com.epam.spring.hometask.service.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.discount.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class CinemaDiscountService implements DiscountService {

  private List<DiscountStrategy> discountStrategies;

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
    byte discount = 0;

    for (DiscountStrategy discountStrategy : discountStrategies) {
      byte currentDiscount =
          discountStrategy.calculateDiscount(user, event, airDateTime, numberOfTickets);

      if (currentDiscount > discount) {
        discount = currentDiscount;
      }
    }

    return discount;
  }

  public void setDiscountStrategies(List<DiscountStrategy> discountStrategies) {
    this.discountStrategies = discountStrategies;
  }
}
