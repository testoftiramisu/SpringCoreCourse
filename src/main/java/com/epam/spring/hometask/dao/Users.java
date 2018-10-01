package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.User;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Users {

  private static Map<Long, User> users;
  private Long baseId;

  public Users() {
    users = new HashMap<>();
    baseId = 10000L;
  }

  /** Returns all {@code User} from storage. */
  public Collection<User> getAll() {
    return ImmutableSet.copyOf(users.values());
  }

  /**
   * Returns {@code User} by id from storage if found, returns null otherwise.
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
   * Returns given {@code User} from storage, if present.
   *
   * @param user for removing
   */
  public void remove(User user) {
    for (Long id : users.keySet()) {
      User currentUser = users.get(id);
      if (currentUser.equals(user)) {
        users.remove(id);
      }
    }
  }

  /**
   * Returns true when {@code User} saved in storage, returns false otherwise.
   *
   * @param user for saving in storage
   */
  public boolean save(User user) {
    try {
      users.put(baseId + 1, user);
      return true;
    } catch (NullPointerException ex) {
      return false;
    }
  }
}
