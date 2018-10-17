package com.epam.spring.hometask.service.booking;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaBookingServiceTest {
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaBookingServiceTestConfig.class);
  private final LocalDateTime now = context.getBean("dateTimeNow", LocalDateTime.class);
  private final Event testEventTwo = context.getBean("testEventTwo", Event.class);
  private final Auditorium auditorium = context.getBean("multikino", Auditorium.class);
  private final Set<Ticket> tickets = context.getBean("tickets", TreeSet.class);

  private BookingService service;
  private Set<Long> seats;

  @Before
  public void setUp() throws Exception {
    seats = new HashSet<>();
    NavigableSet<LocalDateTime> airDates =
        new TreeSet<>(Arrays.asList(now, now.plusDays(1), now.plusDays(2)));
    testEventTwo.setAirDates(airDates);
    testEventTwo.assignAuditorium(now, auditorium);
    service = context.getBean(BookingService.class);
  }

  @Test
  public void shouldCalculateTicketsPriceForThreeCommonTickets() {
    seats.addAll(Arrays.asList(1L, 2L, 3L));

    assertThat(service.getTicketsPrice(testEventTwo, now, null, seats))
        .isEqualTo(testEventTwo.getBasePrice() * seats.size());
  }

  @Test
  public void shouldCalculateTicketsPriceForTwoVipTickets() {
    seats.addAll(auditorium.getVipSeats());
    assertThat(service.getTicketsPrice(testEventTwo, now, null, seats))
        .isEqualTo(testEventTwo.getBasePrice() * service.getVipSeatsCoefficient() * seats.size());
  }

  @Test
  public void shouldBookTickets() {
    service.bookTickets(tickets);
    assertThat(service.getPurchasedTicketsForEvent(testEventTwo, now))
        .containsExactlyInAnyOrderElementsOf(tickets);
  }
}
