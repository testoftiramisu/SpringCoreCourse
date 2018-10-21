package com.epam.spring.hometask.dao.aspect;

import com.epam.spring.hometask.dao.config.DataSourceConfig;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

@Aspect
@Component
public class CounterAspect {

  private static final String UPDATE =
      "UPDATE statistics SET counter = counter + 1 WHERE event_name = ? AND target = ?";
  private static final String INSERT =
      "INSERT INTO statistics (event_name, target, counter) VALUES (?, ?, 1)";
  private static final String SELECT =
      "SELECT counter FROM statistics WHERE event_name = ? AND target = ?";
  private static final String ACCESSED_BY_NAME = "eventsAccessedByName";
  private static final String PRICE_QUERIED = "eventsPriceQueried";
  private static final String TICKETS_BOOKED = "eventsTicketsBooked";
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(DataSourceConfig.class);
  private JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);

  public CounterAspect() {}

  @Pointcut("execution(* com.epam.spring.hometask.service.event.EventService+.getByName(..))")
  private void accessedByName() {}

  @Before("accessedByName() && args(name)")
  public void countEventAccessedByName(String name) {
    updateCounter(ACCESSED_BY_NAME, name);
  }

  @Pointcut("execution(* com.epam.spring.hometask.domain.Event+.getBasePrice(..))")
  private void queryBasePrice() {}

  @Before("queryBasePrice()")
  public void countPriceQueried(JoinPoint joinPoint) {
    String name = ((Event) joinPoint.getThis()).getName();
    updateCounter(PRICE_QUERIED, name);
  }

  @Pointcut("execution(* com.epam.spring.hometask.service.booking.BookingService+.bookTickets(..))")
  private void bookTickets() {}

  @Before("bookTickets() && args(tickets)")
  public void countBookedTickets(Set<Ticket> tickets) {
    final Ticket[] ticketArray = tickets.toArray(new Ticket[0]);

    jdbcTemplate.batchUpdate(
        UPDATE,
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement statement, int i) throws SQLException {
            statement.setString(1, TICKETS_BOOKED);
            statement.setString(2, ticketArray[i].getEvent().getName());
          }

          @Override
          public int getBatchSize() {
            return tickets.size();
          }
        });
  }

  private void updateCounter(String eventName, String targetName) {
    try {
      jdbcTemplate.queryForObject(SELECT, new Object[] {eventName, targetName}, Long.class);
      jdbcTemplate.update(UPDATE, eventName, targetName);

    } catch (DataAccessException e) {
      jdbcTemplate.update(INSERT, eventName, targetName);
    }
  }
}
