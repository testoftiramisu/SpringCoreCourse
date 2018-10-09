package com.epam.spring.hometask.service.discount.strategy;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class BirthdayStrategy implements DiscountStrategy {
  private byte discount;
  private byte discountPeriod;

  public BirthdayStrategy(byte discount, byte discountPeriod) {
    this.discount = discount;
    this.discountPeriod = discountPeriod;
  }

  @Override
  public byte calculateDiscount(
      @Nullable User user,
      @Nonnull Event event,
      @Nonnull LocalDateTime dateTime,
      long numberOfTickets) {
    if (user == null || user.getBirthday() == null) {
      return 0;
    }

    int currentDifference = user.getBirthday().getDayOfYear() - dateTime.getDayOfYear();

    if (currentDifference > 0 && currentDifference <= discountPeriod) {
      return discount;
    }

    return 0;
  }

  /** Returns discount for birthday strategy. */
  public double getDiscount() {
    return discount;
  }

  /** Sets discount for birthday strategy. */
  public void setDiscount(byte discount) {
    this.discount = discount;
  }

  /** Returns allowed days of difference for birthday strategy. */
  public byte getDiscountPeriod() {
    return discountPeriod;
  }

  /** Sets allowed days of difference for birthday strategy. */
  public void setDiscountPeriod(byte discountPeriod) {
    this.discountPeriod = discountPeriod;
  }
}
