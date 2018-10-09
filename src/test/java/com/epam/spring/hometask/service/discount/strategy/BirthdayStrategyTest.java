package com.epam.spring.hometask.service.discount.strategy;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.TreeSet;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BirthdayStrategyTest {

  private final ClassPathXmlApplicationContext context =
      new ClassPathXmlApplicationContext(
          "spring/service/discount/strategy/birthday-strategy-test.xml");
  private BirthdayStrategy birthdayStrategy;
  private User user;
  private Event event;
  private TreeSet<Ticket> tickets;
  private LocalDateTime now = context.getBean("dateTimeNow", LocalDateTime.class);

  @Before
  public void setUp() throws Exception {
    birthdayStrategy = context.getBean(BirthdayStrategy.class);
    user = context.getBean(User.class);
    event = context.getBean(Event.class);
    tickets = context.getBean("tickets", TreeSet.class);
  }

  @Test
  public void calculateDiscount() {
    event.addAirDateTime(now.plusDays(1));
    user.setTickets(tickets);
    user.setBirthday(now.toLocalDate().plusDays(3));

    assertThat(birthdayStrategy.calculateDiscount(user, event, now.plusDays(1), 5)).isEqualTo((byte)5);
  }
}
