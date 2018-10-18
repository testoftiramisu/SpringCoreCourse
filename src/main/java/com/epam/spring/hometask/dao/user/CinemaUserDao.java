package com.epam.spring.hometask.dao.user;

import com.epam.spring.hometask.dao.config.DataSourceConfig;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;

public class CinemaUserDao implements UserDao {

  private static final String GET_ALL = "SELECT * FROM USERS";
  private static final String GET_BY_ID = "SELECT * FROM USERS WHERE id = ?";
  private static final String SAVE =
      "INSERT INTO USERS (id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
  private static final String UPDATE =
      "UPDATE USERS SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
  private static final String REMOVE = "DELETE FROM USERS WHERE id = ?";
  private static final String GET_BY_EMAIL = "SELECT * FROM USERS WHERE email = ?";

  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(DataSourceConfig.class);

  private JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);

  private IdGeneratorService idGeneratorService;

  public CinemaUserDao(IdGeneratorService idGeneratorService) {
    this.idGeneratorService = idGeneratorService;
  }

  /** Returns users. */
  public Collection<User> getUsers() {
    return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(User.class));
  }

  /** Saves all users from provided {@link Collection}. */
  public void setUsers(Collection<User> users) {
    for (User user : users) {
      this.save(user);
    }
  }

  /** Returns all {@link User} from the storage. */
  public Collection<User> getAll() {
    return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(User.class));
  }

  /**
   * Returns {@link User} by id from the storage if found, returns null otherwise.
   *
   * @param id of the user
   */
  public User getById(Long id) {
    return jdbcTemplate.queryForObject(
        GET_BY_ID, new Object[] {id}, new BeanPropertyRowMapper<>(User.class));
  }

  /**
   * Removes given {@code User} from the storage.
   *
   * @param user for removing
   */
  public void remove(User user) {
    jdbcTemplate.update(REMOVE, user.getId());
  }

  /**
   * Returns id of {@code User} saved in the storage.
   *
   * @param user for saving in the storage
   */
  public Long save(User user) {
    Long id = user.getId();

    if (id != null) {
      jdbcTemplate.update(UPDATE, user.getFirstName(), user.getLastName(), user.getEmail(), id);
    } else {
      id = idGeneratorService.generateId();
      user.setId(id);
      jdbcTemplate.update(SAVE, id, user.getFirstName(), user.getLastName(), user.getEmail());
    }

    return id;
  }

  /**
   * Returns {@code User} from storage by email.
   *
   * @param email of the user to search for
   */
  public User getByEmail(String email) {
    return jdbcTemplate.queryForObject(
        GET_BY_EMAIL, new Object[] {email}, new BeanPropertyRowMapper<>(User.class));
  }
}
