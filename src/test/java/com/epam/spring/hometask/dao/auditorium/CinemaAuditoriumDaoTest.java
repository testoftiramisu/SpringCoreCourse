package com.epam.spring.hometask.dao.auditorium;

import com.epam.spring.hometask.domain.Auditorium;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaAuditoriumDaoTest {
  private static final String MULTIKINO = "Multikino";
  private static final String IMAX = "IMAX";
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaAuditoriumDaoTestConfig.class);
  private AuditoriumDao auditoriumDao;
  private Auditorium newAuditorium;

  @Before
  public void setUp() {
    auditoriumDao = context.getBean(CinemaAuditoriumDao.class);
    newAuditorium = context.getBean("multikino", Auditorium.class);
  }

  @Test
  public void checkContext() {
    assertThat(auditoriumDao).isNotNull();
  }

  @Test
  public void shouldReturnAllValues() {
    assertThat(auditoriumDao.getAll()).isNotEmpty();
  }

  @Test
  public void shouldAddAuditorium() {
    assertThat(auditoriumDao.findByName(MULTIKINO)).isNull();

    auditoriumDao.addAuditorium(newAuditorium);

    assertThat(auditoriumDao.findByName(MULTIKINO)).isNotNull();
  }

  @Test
  public void shouldSetAuditoriums() {
    assertThat(auditoriumDao.findByName(MULTIKINO)).isNull();

    auditoriumDao.setAuditoriums(Collections.singletonList(newAuditorium));

    assertThat(auditoriumDao.findByName(MULTIKINO)).isNotNull();
  }

  @Test
  public void shouldFindByName() {
    assertThat(auditoriumDao.findByName(IMAX)).isNotNull();
  }
}
