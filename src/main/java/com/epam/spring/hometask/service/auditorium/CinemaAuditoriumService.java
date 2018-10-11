package com.epam.spring.hometask.service.auditorium;

import com.epam.spring.hometask.dao.auditorium.AuditoriumDao;
import com.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class CinemaAuditoriumService implements AuditoriumService {

  private AuditoriumDao auditoriums;

  public CinemaAuditoriumService(AuditoriumDao auditoriums) {
    this.auditoriums = auditoriums;
  }

  /** Returns all auditoriums. */
  @Nonnull
  @Override
  public Set<Auditorium> getAll() {
    return auditoriums.getAll();
  }

  /**
   * Returns auditorium found by the given name or returns null.
   *
   * @param name Name of the auditorium
   */
  @Nullable
  @Override
  public Auditorium getByName(@Nonnull String name) {
    for (Auditorium auditorium : auditoriums.getAll()) {
      String currentName = auditorium.getName();
      if (currentName.equals(name)) {
        return auditorium;
      }
    }
    return null;
  }
}
