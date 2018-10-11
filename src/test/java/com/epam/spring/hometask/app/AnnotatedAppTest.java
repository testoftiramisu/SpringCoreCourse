package com.epam.spring.hometask.app;

import com.epam.spring.hometask.service.auditorium.AuditoriumService;
import com.epam.spring.hometask.service.booking.BookingService;
import com.epam.spring.hometask.service.discount.DiscountService;
import com.epam.spring.hometask.service.event.EventService;
import com.epam.spring.hometask.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnotatedAppTest {

  private final ClassPathXmlApplicationContext context =
      new ClassPathXmlApplicationContext("spring/app/spring.xml");
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
  public void checkContext() {
    auditoriumService = context.getBean(AuditoriumService.class);
    assertThat(auditoriumService).isNotNull();

    bookingService = context.getBean(BookingService.class);
    assertThat(bookingService).isNotNull();

    discountService = context.getBean(DiscountService.class);
    assertThat(discountService).isNotNull();

    eventService = context.getBean(EventService.class);
    assertThat(eventService).isNotNull();

    userService = context.getBean(UserService.class);
    assertThat(userService).isNotNull();
  }
}
