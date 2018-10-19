package com.epam.spring.hometask.service.user;

import com.epam.spring.hometask.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaUserServiceTest {

  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(CinemaUserServiceTestConfig.class);
  private UserService service;
  private User userTwo;
  private User userOne;

  @Before
  public void setUp() {
    service = context.getBean(UserService.class);
    userTwo = context.getBean("userTwo", User.class);
    userOne = context.getBean("userOne", User.class);
    service.save(userOne);
  }

  @After
  public void tearDown() {
    service.remove(userOne);
  }

  @Test
  public void getUserByEmail() {
    assertThat(service.getUserByEmail(userOne.getEmail())).isEqualTo(userOne);
  }

  @Test
  public void save() {
    service.save(userTwo);

    assertThat(service.getUserByEmail(userTwo.getEmail())).isEqualTo(userTwo);
  }

  @Test
  public void remove() {
    service.save(userTwo);
    service.remove(userTwo);

    assertThat(service.getAll()).doesNotContain(userTwo);
  }

  @Test
  public void getById() {
    Long id = service.save(userTwo).getId();

    assertThat(service.getById(id)).isEqualTo(userTwo);
  }

  @Test
  public void getAll() {
    assertThat(service.getAll()).isNotEmpty();
  }
}
