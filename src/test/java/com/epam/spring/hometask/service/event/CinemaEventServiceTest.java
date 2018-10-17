package com.epam.spring.hometask.service.event;

import com.epam.spring.hometask.domain.Event;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaEventServiceTest {

  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaEventServiceTestConfig.class);
  private final Event testEventOne = context.getBean("testEventOne", Event.class);
  private final Event testEventTwo = context.getBean("testEventTwo", Event.class);
  private EventService service;

  @Before
  public void setUp() throws Exception {
    service = context.getBean(EventService.class);
  }

  @Test
  public void shouldReturnEventByName() {
    assertThat(service.getByName("Test Event One")).isEqualTo(testEventOne);
  }

  @Test
  public void shouldSaveEvent() {
    service.save(testEventTwo);

    assertThat(service.getByName("Test Event Two")).isEqualTo(testEventTwo);
  }

  @Test
  public void shouldRemoveEvent() {
    service.remove(testEventOne);
    assertThat(service.getAll()).doesNotContain(testEventOne);
  }

  @Test
  public void shouldReturnEventById() {
    Long id = service.save(testEventTwo).getId();

    assertThat(service.getById(id)).isEqualTo(testEventTwo);
  }

  @Test
  public void shouldReturnAllEvents() {
    assertThat(service.getAll()).isNotEmpty();
  }
}
