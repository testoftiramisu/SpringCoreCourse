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
  private Map<String, Integer> accessedByNameCounter;
  private Map<String, Integer> priceQueriedCounter;
  private Map<String, Integer> ticketsBookedCounter;

  public CounterAspect() {
    accessedByNameCounter = new HashMap<>();
    priceQueriedCounter = new HashMap<>();
    ticketsBookedCounter = new HashMap<>();
  }

  public Map<String, Integer> getAccessedByNameCounter() {
    return accessedByNameCounter;
  }

  public Map<String, Integer> getPriceQueriedCounter() {
    return priceQueriedCounter;
  }

  public Map<String, Integer> getTicketsBookedCounter() {
    return ticketsBookedCounter;
  }

  @Pointcut("execution(* com.epam.spring.hometask.service.event.CinemaEventService+.getByName(..))")
  private void accessedByName() {}

  @Before("accessedByName() && args(name)")
  public void countEventGetByName(String name) {
    accessedByNameCounter.putIfAbsent(name, 0);
    accessedByNameCounter.put(name, accessedByNameCounter.get(name) + 1);
  }

  @Pointcut("execution(* com.epam.spring.hometask.domain.Event+.getBasePrice(..))")
  private void queryBasePrice() {}

  @Before("queryBasePrice()")
  public void priceQueried(JoinPoint joinPoint) {
    String name = ((Event) joinPoint.getThis()).getName();
    priceQueriedCounter.putIfAbsent(name, 0);
    priceQueriedCounter.put(name, priceQueriedCounter.get(name) + 1);
  }

  @Pointcut(
      "execution(* com.epam.spring.hometask.service.booking.CinemaBookingService+.bookTickets(..))")
  private void bookTickets() {}

  @Before("bookTickets() && args(tickets)")
  public void countBookTickets(Set<Ticket> tickets) {
    for (Ticket ticket : tickets) {
      String name = ticket.getEvent().getName();
      ticketsBookedCounter.putIfAbsent(name, 0);
      ticketsBookedCounter.put(name, ticketsBookedCounter.get(name) + 1);
    }
  }
}
