package com.epam.spring.hometask.service.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class CinemaDiscountService implements DiscountService {

  public CinemaDiscountService() {}

  // ToDo method implementation
  @Override
  public byte getDiscount(
      @Nullable User user,
      @Nonnull Event event,
      @Nonnull LocalDateTime airDateTime,
      long numberOfTickets) {
    return 0;
  }
}
