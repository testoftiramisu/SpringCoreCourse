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

  @Nullable
  @Override
  public User getUserByEmail(@Nonnull String email) {
    return null;
  }

  @Override
  public User save(@Nonnull User user) {
    Long id = users.save(user);
    return user.setId(id);
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
