package com.epam.spring.hometask.service.auditorium;

import com.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public interface AuditoriumService {

  /** Returns set of all auditoriums from the system. */
  public @Nonnull Set<Auditorium> getAll();

  /**
   * Returns auditorium found by the given name or returns null.
   *
   * @param name Name of the auditorium
   */
  public @Nullable Auditorium getByName(@Nonnull String name);
}
