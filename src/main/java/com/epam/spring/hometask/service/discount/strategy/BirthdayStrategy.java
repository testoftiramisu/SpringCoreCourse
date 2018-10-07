package com.epam.spring.hometask.service.discount.strategy;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

// ToDo: unit tests for birthday strategy.
public class BirthdayStrategy implements DiscountStrategy {
  private double discount;
  private byte allowedDaysDifference;

  @Override
  public double calculateDiscount(
      @Nullable User user,
      @Nonnull Event event,
      @Nonnull LocalDateTime dateTime,
      long numberOfTickets) {
    int dayOfBirthday = user.getBirthday().getDayOfYear();
    int dayOfEvent = dateTime.getDayOfYear();

    int daysDifference = Math.abs(dayOfBirthday - dayOfEvent);

    if (dayOfBirthday >= dayOfEvent && daysDifference <= allowedDaysDifference) {
      return discount;
    }

    return 0;
  }

  /** Returns discount for birthday strategy. */
  public double getDiscount() {
    return discount;
  }

  /** Sets discount for birthday strategy. */
  public void setDiscount(double discount) {
    this.discount = discount;
  }

  /** Returns allowed days of difference for birthday strategy. */
  public byte getAllowedDaysDifference() {
    return allowedDaysDifference;
  }

  /** Sets allowed days of difference for birthday strategy. */
  public void setAllowedDaysDifference(byte allowedDaysDifference) {
    this.allowedDaysDifference = allowedDaysDifference;
  }
}
