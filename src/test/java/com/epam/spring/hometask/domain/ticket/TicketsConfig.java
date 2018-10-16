package com.epam.spring.hometask.domain.ticket;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.dates.DatesConfig;
import com.epam.spring.hometask.domain.event.EventsConfig;
import com.epam.spring.hometask.domain.user.UsersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Configuration
@Import({UsersConfig.class, EventsConfig.class, DatesConfig.class})
public class TicketsConfig {

  @Autowired
  @Qualifier("userOne")
  User userOne;

  @Autowired
  @Qualifier("userOne")
  User userTwo;

  @Autowired
  @Qualifier("testEventOne")
  Event eventOne;

  @Autowired
  @Qualifier("testEventTwo")
  Event eventTwo;

  @Autowired
  @Qualifier("dateTimeNow")
  LocalDateTime now;

  @Bean(name = "testTicketOne")
  @Scope("prototype")
  public Ticket getTestTicketOne() {
    return new Ticket(userOne, eventOne, now, 10);
  }

  @Bean(name = "testTicketTwo")
  @Scope("prototype")
  public Ticket getTestTicketTwo() {
    return new Ticket(userOne, eventTwo, now, 33);
  }

  @Bean(name = "testTicket1")
  @Scope("prototype")
  public Ticket getTestTicket1() {
    return new Ticket(null, eventTwo, now, 1);
  }

  @Bean(name = "testTicket2")
  @Scope("prototype")
  public Ticket getTestTicket2() {
    return new Ticket(userTwo, eventTwo, now, 2);
  }

  @Bean(name = "testTicket3")
  @Scope("prototype")
  public Ticket getTestTicket3() {
    return new Ticket(userOne, eventTwo, now, 3);
  }

  @Bean(name = "tickets")
  public Set<Ticket> getTickets() {
    Set<Ticket> tickets = new TreeSet<>();
    tickets.add(getTestTicket1());
    tickets.add(getTestTicket2());
    tickets.add(getTestTicket3());
    return tickets;
  }
}
