package com.epam.spring.hometask.dao.user;

import com.epam.spring.hometask.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaUserDaoTest {
  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaUserDaoTestConfig.class);
  private UserDao userDao;
  private User userTwo;

  @Before
  public void setUp() throws Exception {
    userDao = context.getBean(UserDao.class);
    userTwo = context.getBean("userTwo", User.class);
  }

  @Test
  public void shouldReturnAllUsers() {
    assertThat(userDao.getAll()).isNotEmpty();
  }

  @Test
  public void shouldSaveEventAndGetItById() {
    Long id = userDao.save(userTwo);

    assertThat(userDao.getById(id)).isEqualTo(userTwo);
  }

  @Test
  public void shouldSaveEvent() {
    userDao.save(userTwo);

    assertThat(userDao.getAll()).contains(userTwo);
  }

  @Test
  public void shouldRemoveEvent() {
    userDao.save(userTwo);

    userDao.remove(userTwo);

    assertThat(userDao.getAll()).doesNotContain(userTwo);
  }
}
