package com.epam.spring.hometask.service.booking;

import com.epam.spring.hometask.dao.ticket.CinemaTicketDaoTestConfig;
import com.epam.spring.hometask.dao.ticket.TicketDao;
import com.epam.spring.hometask.domain.auditorium.AuditoriumsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CinemaTicketDaoTestConfig.class, AuditoriumsConfig.class})
public class CinemaBookingServiceTestConfig {

  @Autowired
  @Qualifier("ticketTestDao")
  TicketDao ticketsDao;

  @Value("${highRateCoefficient}")
  private String highRateCoefficient;

  @Value("${vipSeatsCoefficient}")
  private String vipSeatsCoefficient;

  @Bean(name = "eventService")
  public BookingService getBookingService() {
    BookingService bookingService = new CinemaBookingService(ticketsDao);
    bookingService.setHighRateCoefficient(Double.parseDouble(highRateCoefficient));
    bookingService.setVipSeatsCoefficient(Double.parseDouble(vipSeatsCoefficient));
    return bookingService;
  }
}
