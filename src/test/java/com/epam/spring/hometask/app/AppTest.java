package com.epam.spring.hometask.app;

import com.epam.spring.hometask.service.auditorium.AuditoriumService;
import com.epam.spring.hometask.service.booking.BookingService;
import com.epam.spring.hometask.service.discount.DiscountService;
import com.epam.spring.hometask.service.event.EventService;
import com.epam.spring.hometask.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

  private final ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
  private App app;
  private AuditoriumService auditoriumService;
  private BookingService bookingService;
  private DiscountService discountService;
  private EventService eventService;
  private UserService userService;

  @Before
  public void setUp() {
    app = ctx.getBean(App.class);
  }

  @Test
  public void getAuditoriumService() {
    auditoriumService = app.getAuditoriumService();
    assertThat(auditoriumService).isNotNull();
  }

  @Test
  public void getBookingService() {
    bookingService = app.getBookingService();
    assertThat(bookingService).isNotNull();
  }

  @Test
  public void getDiscountService() {
    discountService = app.getDiscountService();
    assertThat(discountService).isNotNull();
  }

  @Test
  public void getEventService() {
    eventService = app.getEventService();
    assertThat(eventService).isNotNull();
  }

  @Test
  public void getUserService() {
    userService = app.getUserService();
    assertThat(userService).isNotNull();
  }

  @Test
  public void checkAnnotatedContext() {
    auditoriumService = ctx.getBean(AuditoriumService.class);
    bookingService = ctx.getBean(BookingService.class);
    discountService = ctx.getBean(DiscountService.class);
    eventService = ctx.getBean(EventService.class);
    userService = ctx.getBean(UserService.class);

    assertThat(auditoriumService).isNotNull();
    assertThat(bookingService).isNotNull();
    assertThat(discountService).isNotNull();
    assertThat(eventService).isNotNull();
    assertThat(userService).isNotNull();
  }
}
