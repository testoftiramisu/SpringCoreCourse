package com.epam.spring.hometask.dao.ticket;

import com.epam.spring.hometask.domain.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaTicketDaoTest {

  private final ClassPathXmlApplicationContext context =
      new ClassPathXmlApplicationContext("spring/dao/ticket-dao-test.xml");
  private TicketDao ticketDao;
  private Ticket ticketTwo;

  @Before
  public void setUp() {
    ticketDao = context.getBean(TicketDao.class);
    ticketTwo = context.getBean("testTickedTwo", Ticket.class);
  }

  @Test
  public void shouldReturnAllTickets() {
    assertThat(ticketDao.getAll()).isNotEmpty();
  }

  @Test
  public void shouldSaveTickedAndGetItById() {
    Long id = ticketDao.save(ticketTwo);

    assertThat(ticketDao.getById(id)).isEqualTo(ticketTwo);
  }

  @Test
  public void shouldSaveTicked() {
    ticketDao.save(ticketTwo);

    assertThat(ticketDao.getAll()).contains(ticketTwo);
  }

  @Test
  public void shouldRemoveTicket() {
    assertThat(ticketDao.getAll()).doesNotContain(ticketTwo);

    ticketDao.save(ticketTwo);
    assertThat(ticketDao.getAll()).contains(ticketTwo);

    ticketDao.remove(ticketTwo);
    assertThat(ticketDao.getAll()).doesNotContain(ticketTwo);
  }
}
