package com.epam.spring.hometask.service.user;

import com.epam.spring.hometask.dao.Users;
import com.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class CinemaUserService implements UserService {
  private Users users;

  public CinemaUserService(Users users) {
    this.users = users;
  }

  @Nullable
  @Override
  public User getUserByEmail(@Nonnull String email) {
    return null;
  }

  @Override
  public User save(@Nonnull User user) {
    if (users.save(user)) {
      return user;
    }
    return null;
  }

  @Override
  public void remove(@Nonnull User user) {
    users.remove(user);
  }

  @Override
  public User getById(@Nonnull Long id) {
    return users.getById(id);
  }

  @Nonnull
  @Override
  public Collection<User> getAll() {
    return users.getAll();
  }
}
