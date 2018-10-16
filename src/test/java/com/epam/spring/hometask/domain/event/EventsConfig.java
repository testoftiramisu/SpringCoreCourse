package com.epam.spring.hometask.domain.event;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.EventRating;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EventsConfig {

  @Bean(name = "testEventOne")
  @Scope("prototype")
  public Event getTestEventOne() {
    Event event = new Event();
    event.setName("Test Event One");
    event.setBasePrice(8);
    event.setRating(EventRating.HIGH);
    return event;
  }

  @Bean(name = "testEventTwo")
  @Scope("prototype")
  public Event getTestEventTwo() {
    Event event = new Event();
    event.setName("Test Event Two");
    event.setBasePrice(10);
    event.setRating(EventRating.MID);
    return event;
  }
}
