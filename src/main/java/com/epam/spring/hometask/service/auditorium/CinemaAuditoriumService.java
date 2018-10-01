package com.epam.spring.hometask.service.auditorium;

import com.epam.spring.hometask.dao.Auditoriums;
import com.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class CinemaAuditoriumService implements AuditoriumService {

  private Auditoriums auditoriums = new Auditoriums();

  public CinemaAuditoriumService(Auditoriums auditoriums) {
    this.auditoriums = auditoriums;
  }

  @Nonnull
  @Override
  public Set<Auditorium> getAll() {
    return auditoriums.getAll();
  }

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
