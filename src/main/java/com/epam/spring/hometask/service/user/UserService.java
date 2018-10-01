package com.epam.spring.hometask.service.user;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.AbstractDomainObjectService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserService extends AbstractDomainObjectService<User> {

  /**
   * Returns user by email or null.
   *
   * @param email Email of the user
   */
  public @Nullable User getUserByEmail(@Nonnull String email);
}
