package com.epam.spring.hometask.app;

import com.epam.spring.hometask.service.ServiceConfig;
import com.epam.spring.hometask.service.auditorium.AuditoriumService;
import com.epam.spring.hometask.service.booking.BookingService;
import com.epam.spring.hometask.service.discount.DiscountService;
import com.epam.spring.hometask.service.event.EventService;
import com.epam.spring.hometask.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.epam.spring.hometask"})
@Import(ServiceConfig.class)
public class AppConfig {

  @Bean
  public App getApp(
      AuditoriumService auditoriumService,
      BookingService bookingService,
      DiscountService discountService,
      EventService eventService,
      UserService userService) {
    return new App(auditoriumService, bookingService, discountService, eventService, userService);
  }
}
