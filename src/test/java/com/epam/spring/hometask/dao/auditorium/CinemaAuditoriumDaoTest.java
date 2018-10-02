package com.epam.spring.hometask.dao.auditorium;

import com.epam.spring.hometask.domain.Auditorium;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaAuditoriumDaoTest {
  private static final String MULTIKINO = "Multikino";
  private static final String IMAX = "IMAX";
  private ClassPathXmlApplicationContext context =
      new ClassPathXmlApplicationContext("auditorium-dao-test.xml");
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
  public void getAll() {
    assertThat(auditoriumDao.getAll()).isNotEmpty();
  }

  @Test
  public void addAuditorium() {
    assertThat(auditoriumDao.findByName(MULTIKINO)).isNull();

    auditoriumDao.addAuditorium(newAuditorium);

    assertThat(auditoriumDao.findByName(MULTIKINO)).isNotNull();
  }

  @Test
  public void setAuditoriums() {
    assertThat(auditoriumDao.findByName(MULTIKINO)).isNull();

    auditoriumDao.setAuditoriums(Collections.singletonList(newAuditorium));

    assertThat(auditoriumDao.findByName(MULTIKINO)).isNotNull();
  }

  @Test
  public void findByName() {
    assertThat(auditoriumDao.findByName(IMAX)).isNotNull();
  }
}
