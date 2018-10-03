package com.epam.spring.hometask.service.booking;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// ToDo: tests implementations
public class CinemaBookingServiceTest {

  private final ClassPathXmlApplicationContext context =
      new ClassPathXmlApplicationContext("booking-service-test.xml");
  private BookingService service;

  @Test
  public void getTicketsPrice() {}

  @Test
  public void bookTickets() {}

  @Test
  public void getPurchasedTicketsForEvent() {}
}
