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
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(AppConfig.class);
  private App app;
  private AuditoriumService auditoriumService;
  private BookingService bookingService;
  private DiscountService discountService;
  private EventService eventService;
  private UserService userService;

  @Before
  public void setUp() {
    app = context.getBean(App.class);
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
  public void checkAuditoriumServiceContext() {
    auditoriumService = context.getBean(AuditoriumService.class);

    assertThat(auditoriumService).isNotNull();
  }

  @Test
  public void checkBookingServiceContext() {
    bookingService = context.getBean(BookingService.class);

    assertThat(bookingService).isNotNull();
  }

  @Test
  public void checkDiscountServiceContext() {
    discountService = context.getBean(DiscountService.class);

    assertThat(discountService).isNotNull();
  }

  @Test
  public void checkEventServiceContext() {
    eventService = context.getBean(EventService.class);

    assertThat(eventService).isNotNull();
  }

  @Test
  public void checkUserServiceContext() {
    userService = context.getBean(UserService.class);

    assertThat(userService).isNotNull();
  }
}
