package com.epam.spring.hometask.dao.event;

import com.epam.spring.hometask.domain.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaEventDaoTest {
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaEventDaoTestConfig.class);
  private EventDao eventDao;
  private Event eventOne;
  private Event eventTwo;

  @Before
  public void setUp() {
    eventDao = context.getBean(EventDao.class);
    eventOne = context.getBean("testEventOne", Event.class);
    eventTwo = context.getBean("testEventTwo", Event.class);
    eventDao.save(eventOne);
  }

  @After
  public void tearDown() {
    eventDao.remove(eventOne);
  }

  @Test
  public void shouldReturnAllEvents() {
    assertThat(eventDao.getAll()).isNotEmpty();
  }

  @Test
  public void shouldSaveEventAndGetItById() {
    Long id = eventDao.save(eventTwo);

    assertThat(eventDao.getById(id)).isEqualTo(eventTwo);
    eventDao.remove(eventTwo);
  }

  @Test
  public void shouldSaveEvent() {
    eventDao.save(eventTwo);

    assertThat(eventDao.getAll()).contains(eventTwo);
    eventDao.remove(eventTwo);
  }

  @Test
  public void shouldRemoveEvent() {
    eventDao.save(eventTwo);
    eventDao.remove(eventTwo);

    assertThat(eventDao.getAll()).doesNotContain(eventTwo);
  }
}
