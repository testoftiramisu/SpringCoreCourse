package com.epam.spring.hometask.dao.user;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CinemaUserDao implements UserDao {

  private Map<Long, User> users;
  private IdGeneratorService idGeneratorService;

  public CinemaUserDao(IdGeneratorService idGeneratorService) {
    users = new HashMap<>();
    this.idGeneratorService = idGeneratorService;
  }

  /** Returns users. */
  public Map<Long, User> getUsers() {
    return users;
  }

  /** Sets users. */
  public void setUsers(Collection<User> users) {
    for (User user : users) {
      this.save(user);
    }
  }

  /** Returns all {@link User} from the storage. */
  public Collection<User> getAll() {
    return ImmutableSet.copyOf(users.values());
  }

  /**
   * Returns {@link User} by id from the storage if found, returns null otherwise.
   *
   * @param id of the user
   */
  public User getById(Long id) {
    if (users.containsKey(id)) {
      return users.get(id);
    }
    return null;
  }

  /**
   * Removes given {@code User} from the storage.
   *
   * @param user for removing
   */
  public void remove(User user) {
    for (Long id : users.keySet()) {
      User currentUser = users.get(id);
      if (currentUser.equals(user)) {
        users.remove(id);
        break;
      }
    }
  }

  /**
   * Returns id of {@code User} saved in the storage.
   *
   * @param user for saving in the storage
   */
  public Long save(User user) {
    Long id = idGeneratorService.generateId();

    while (users.containsKey(id)) {
      id = idGeneratorService.generateId();
    }

    users.put(id, user);
    return id;
  }
}
