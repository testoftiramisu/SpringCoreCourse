package com.epam.spring.hometask.dao.event;

import com.epam.spring.hometask.domain.Event;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaEventDaoTest {
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaEventDaoTestConfig.class);
  private EventDao eventDao;
  private Event event;

  @Before
  public void setUp() {
    eventDao = context.getBean(EventDao.class);
    event = context.getBean("testEventTwo", Event.class);
  }

  @Test
  public void shouldReturnAllEvents() {
    assertThat(eventDao.getAll()).isNotEmpty();
  }

  @Test
  public void shouldSaveEventAndGetItById() {
    Long id = eventDao.save(event);

    assertThat(eventDao.getById(id)).isEqualTo(event);
  }

  @Test
  public void shouldSaveEvent() {
    eventDao.save(event);

    assertThat(eventDao.getAll()).contains(event);
  }

  @Test
  public void shouldRemoveEvent() {
    eventDao.save(event);

    eventDao.remove(event);

    assertThat(eventDao.getAll()).doesNotContain(event);
  }
}
