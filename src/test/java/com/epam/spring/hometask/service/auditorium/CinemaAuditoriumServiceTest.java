package com.epam.spring.hometask.service.auditorium;

import com.epam.spring.hometask.domain.Auditorium;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaAuditoriumServiceTest {

  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaAuditoriumServiceTestConfig.class);
  private final Auditorium auditorium = context.getBean("imax", Auditorium.class);
  private AuditoriumService service;

  @Before
  public void setUp() throws Exception {
    service = context.getBean(AuditoriumService.class);
  }

  @Test
  public void shouldReturnAllAuditoriums() {
    assertThat(service.getAll()).isNotEmpty();
  }

  @Test
  public void shouldReturnAuditoriumByName() {
    assertThat(service.getByName("IMAX")).isEqualTo(auditorium);
  }
}
