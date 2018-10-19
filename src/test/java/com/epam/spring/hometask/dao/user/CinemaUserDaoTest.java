package com.epam.spring.hometask.dao.user;

import com.epam.spring.hometask.domain.User;
import org.junit.After;
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
  private User userOne;

  @Before
  public void setUp() throws Exception {
    userDao = context.getBean(UserDao.class);
    userTwo = context.getBean("userTwo", User.class);
    userOne = context.getBean("userOne", User.class);
    userDao.save(userOne);
  }

  @After
  public void tearDown() {
    userDao.remove(userOne);
  }

  @Test
  public void shouldReturnAllUsers() {
    assertThat(userDao.getAll()).isNotEmpty();
  }

  @Test
  public void shouldSaveUserAndGetItById() {
    Long id = userDao.save(userTwo);

    assertThat(userDao.getById(id)).isEqualTo(userTwo);
    userDao.remove(userTwo);
  }

  @Test
  public void shouldSaveUser() {
    userDao.save(userTwo);

    assertThat(userDao.getAll()).contains(userTwo);
    userDao.remove(userTwo);
  }

  @Test
  public void shouldRemoveUser() {
    userDao.save(userTwo);

    userDao.remove(userTwo);

    assertThat(userDao.getAll()).doesNotContain(userTwo);
  }
}
