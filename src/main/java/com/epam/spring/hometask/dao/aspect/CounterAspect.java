package com.epam.spring.hometask.dao.aspect;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class CounterAspect {
  private Map<String, Integer> eventsAccessedByName;
  private Map<String, Integer> eventsPriceQueried;
  private Map<String, Integer> eventsTicketsBooked;

  public CounterAspect() {
    eventsAccessedByName = new HashMap<>();
    eventsPriceQueried = new HashMap<>();
    eventsTicketsBooked = new HashMap<>();
  }

  /** Returns how many times each {@link Event} was accessed by name. */
  public Map<String, Integer> getEventsAccessedByName() {
    return eventsAccessedByName;
  }

  /** Returns how many times each {@link Event} price was queried. */
  public Map<String, Integer> getEventsPriceQueried() {
    return eventsPriceQueried;
  }

  /** Returns how many tickets were booked for {@link Event}. */
  public Map<String, Integer> getEventsTicketsBooked() {
    return eventsTicketsBooked;
  }

  @Pointcut("execution(* com.epam.spring.hometask.service.event.EventService+.getByName(..))")
  private void accessedByName() {}

  @Before("accessedByName() && args(name)")
  public void countEventAccessedByName(String name) {
    eventsAccessedByName.putIfAbsent(name, 0);
    eventsAccessedByName.put(name, eventsAccessedByName.get(name) + 1);
  }

  @Pointcut("execution(* com.epam.spring.hometask.domain.Event+.getBasePrice(..))")
  private void queryBasePrice() {}

  @Before("queryBasePrice()")
  public void countPriceQueried(JoinPoint joinPoint) {
    String name = ((Event) joinPoint.getThis()).getName();
    eventsPriceQueried.putIfAbsent(name, 0);
    eventsPriceQueried.put(name, eventsPriceQueried.get(name) + 1);
  }

  @Pointcut("execution(* com.epam.spring.hometask.service.booking.BookingService+.bookTickets(..))")
  private void bookTickets() {}

  @Before("bookTickets() && args(tickets)")
  public void countBookedTickets(Set<Ticket> tickets) {
    for (Ticket ticket : tickets) {
      String name = ticket.getEvent().getName();
      eventsTicketsBooked.putIfAbsent(name, 0);
      eventsTicketsBooked.put(name, eventsTicketsBooked.get(name) + 1);
    }
  }
}
