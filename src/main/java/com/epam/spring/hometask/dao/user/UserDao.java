package com.epam.spring.hometask.dao.user;

import com.epam.spring.hometask.domain.User;

import java.util.Collection;

public interface UserDao {

  /** Returns from the storage all instances of {@link User}. */
  Collection<User> getAll();

  /** Returns from the storage {@link User} by id. */
  User getById(Long id);

  /**
   * Remove given {@link User} from the storage.
   *
   * @param user for removing
   */
  void remove(User user);

  /**
   * Saves {@link User} in the storage.
   *
   * @param user for saving in the storage
   */
  Long save(User user);
}
