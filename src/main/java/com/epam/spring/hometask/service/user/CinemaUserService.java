package com.epam.spring.hometask.service.user;

import com.epam.spring.hometask.dao.user.UserDao;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class CinemaUserService implements UserService {
  private UserDao users;

  public CinemaUserService(UserDao users) {
    this.users = users;
  }

  /**
   * Returns user by email or null.
   *
   * @param email Email of the user
   */
  @Nullable
  @Override
  public User getUserByEmail(@Nonnull String email) {
    for (User user : users.getAll()) {
      if (user.getEmail().equals(email)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Saves {@link User} in the storage.
   *
   * @return saved user with id
   */
  @Override
  public User save(@Nonnull User user) {
    Long id = users.save(user);
    return user.setId(id);
  }

  /** Removes {@link User} from the storage. */
  @Override
  public void remove(@Nonnull User user) {
    users.remove(user);
  }

  /** Returns user by id. */
  @Override
  public User getById(@Nonnull Long id) {
    return users.getById(id);
  }

  /** Returns all users from the storage. */
  @Nonnull
  @Override
  public Collection<User> getAll() {
    return users.getAll();
  }
}
