package com.epam.spring.hometask.service.discount.strategy;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.EventRating;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.dates.DatesConfig;
import com.epam.spring.hometask.properties.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Configuration
@Import({PropertiesConfig.class, DatesConfig.class})
public class BirthdayStrategyTestConfig {

  @Autowired
  @Qualifier("dateTimeNow")
  LocalDateTime now;

  @Value("${birthday.discount}")
  private String birthdayDiscount;

  @Value("${birthday.period}")
  private String birthdayPeriod;

  @Bean(name = "birthdayStrategy")
  public BirthdayStrategy getBirthdayStrategy() {
    return new BirthdayStrategy(Byte.parseByte(birthdayDiscount), Byte.parseByte(birthdayPeriod));
  }

  @Bean(name = "birthdayUser")
  public User getBirthdayUser() {
    return new User("BirthdayName", "BirthdayLastName", "testBirthdayUser@email.com");
  }

  @Bean(name = "birthdayEvent")
  public Event getBirthdayEvent() {
    Event birthdayEvent = new Event();
    birthdayEvent.setName("Birthday event");
    birthdayEvent.setRating(EventRating.MID);
    birthdayEvent.setBasePrice(10.0);
    return birthdayEvent;
  }

  @Bean(name = "ticket1")
  @Scope("prototype")
  public Ticket getTicket1() {
    return new Ticket(getBirthdayUser(), getBirthdayEvent(), now, 1);
  }

  @Bean(name = "ticket2")
  @Scope("prototype")
  public Ticket getTicket2() {
    return new Ticket(getBirthdayUser(), getBirthdayEvent(), now, 2);
  }

  @Bean(name = "ticket3")
  @Scope("prototype")
  public Ticket getTicket3() {
    return new Ticket(getBirthdayUser(), getBirthdayEvent(), now, 3);
  }

  @Bean(name = "birthdayTickets")
  public Set<Ticket> getTickets() {
    Set<Ticket> tickets = new TreeSet<>();
    tickets.add(getTicket1());
    tickets.add(getTicket2());
    tickets.add(getTicket3());
    return tickets;
  }
}
