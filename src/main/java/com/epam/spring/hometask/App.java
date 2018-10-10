package com.epam.spring.hometask;

import com.epam.spring.hometask.service.auditorium.AuditoriumService;
import com.epam.spring.hometask.service.booking.BookingService;
import com.epam.spring.hometask.service.discount.DiscountService;
import com.epam.spring.hometask.service.event.EventService;
import com.epam.spring.hometask.service.user.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  private AuditoriumService auditoriumService;
  private BookingService bookingService;
  private DiscountService discountService;
  private EventService eventService;
  private UserService userService;

  public App(
      AuditoriumService auditoriumService,
      BookingService bookingService,
      DiscountService discountService,
      EventService eventService,
      UserService userService) {
    this.auditoriumService = auditoriumService;
    this.bookingService = bookingService;
    this.discountService = discountService;
    this.eventService = eventService;
    this.userService = userService;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    App app = context.getBean(App.class);

    context.close();
  }

  public AuditoriumService getAuditoriumService() {
    return auditoriumService;
  }

  public BookingService getBookingService() {
    return bookingService;
  }

  public DiscountService getDiscountService() {
    return discountService;
  }

  public EventService getEventService() {
    return eventService;
  }

  public UserService getUserService() {
    return userService;
  }
}
