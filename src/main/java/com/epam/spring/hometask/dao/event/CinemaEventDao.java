package com.epam.spring.hometask.dao.event;

import com.epam.spring.hometask.dao.config.DataSourceConfig;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;

public class CinemaEventDao implements EventDao {
  private static final String GET_ALL = "SELECT * FROM EVENTS";
  private static final String GET_BY_ID = "SELECT * FROM EVENTS WHERE id = ?";
  private static final String SAVE =
      "INSERT INTO EVENTS (id, name, base_price) VALUES (?, ?, ?)";
  private static final String UPDATE =
      "UPDATE EVENTS SET name = ?, base_price = ? WHERE id = ?";
  private static final String REMOVE = "DELETE FROM EVENTS WHERE id = ?";

  private final ApplicationContext context =
      new AnnotationConfigApplicationContext(DataSourceConfig.class);
  private IdGeneratorService idGeneratorService;
  private JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);

  public CinemaEventDao(IdGeneratorService idGeneratorService) {
    this.idGeneratorService = idGeneratorService;
  }

  /**
   * Stores all {@link Event} from provided collection.
   *
   * @param events Collection of {@link Event}
   */
  public void setEvents(Collection<Event> events) {
    events.forEach(this::save);
  }

  /** Returns all {@link Event} from storage. */
  @Override
  public Collection<Event> getAll() {
    return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Event.class));
  }

  /**
   * Returns {@link Event} by id from storage if found, returns null otherwise.
   *
   * @param id id of the object
   */
  @Override
  public Event getById(Long id) {
    return jdbcTemplate.queryForObject(
        GET_BY_ID, new Object[] {id}, new BeanPropertyRowMapper<>(Event.class));
  }

  /**
   * Returns id of {@code Event} saved in storage.
   *
   * @param event that needs to be saved in storage
   */
  @Override
  public Long save(Event event) {
    Long id = event.getId();

    if (id != null) {
      jdbcTemplate.update(UPDATE, event.getName(), event.getBasePrice(), id);
    } else {
      id = idGeneratorService.getId();
      event.setId(id);
      jdbcTemplate.update(SAVE, id, event.getName(), event.getBasePrice());
    }

    return id;
  }

  /**
   * Removes {@link Event} from storage, if present.
   *
   * @param event for removing
   */
  @Override
  public void remove(Event event) {
    jdbcTemplate.update(REMOVE, event.getId());
  }
}
