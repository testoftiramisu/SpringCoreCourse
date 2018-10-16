package com.epam.spring.hometask.service;

import com.epam.spring.hometask.dao.DaoConfig;
import com.epam.spring.hometask.dao.auditorium.AuditoriumDao;
import com.epam.spring.hometask.dao.event.EventDao;
import com.epam.spring.hometask.dao.ticket.TicketDao;
import com.epam.spring.hometask.dao.user.UserDao;
import com.epam.spring.hometask.service.auditorium.AuditoriumService;
import com.epam.spring.hometask.service.auditorium.CinemaAuditoriumService;
import com.epam.spring.hometask.service.booking.BookingService;
import com.epam.spring.hometask.service.booking.CinemaBookingService;
import com.epam.spring.hometask.service.discount.CinemaDiscountService;
import com.epam.spring.hometask.service.discount.DiscountService;
import com.epam.spring.hometask.service.event.CinemaEventService;
import com.epam.spring.hometask.service.event.EventService;
import com.epam.spring.hometask.service.user.CinemaUserService;
import com.epam.spring.hometask.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
@ComponentScan("com.epam.spring.hometask.service")
public class ServiceConfig {

  @Autowired
  @Qualifier("userDao")
  UserDao userDao;

  @Autowired
  @Qualifier("eventDao")
  EventDao eventDao;

  @Autowired
  @Qualifier("auditoriumDao")
  AuditoriumDao auditoriumDao;

  @Autowired
  @Qualifier("ticketDao")
  TicketDao ticketDao;

  @Bean
  public AuditoriumService getAuditoriumService() {
    return new CinemaAuditoriumService(auditoriumDao);
  }

  @Bean
  public UserService getUserService() {
    return new CinemaUserService(userDao);
  }

  @Bean
  public EventService getEventService() {
    return new CinemaEventService(eventDao);
  }

  @Bean
  public BookingService produceBookingService() {
    return new CinemaBookingService(ticketDao);
  }

  @Bean
  public DiscountService getDiscountService() {
    return new CinemaDiscountService();
  }
}
