package com.epam.spring.hometask.dao.auditorium;

import com.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;
import java.util.Set;

public interface AuditoriumDao {

  /**
   * Adds all {@link Auditorium} from provided Collection to the storage.
   *
   * @param auditoriums collection of {@link Auditorium} that needs to be added to storage
   */
  void setAuditoriums(Collection<Auditorium> auditoriums);

  /**
   * Adds given {@link Auditorium} to the storage.
   *
   * @param auditorium which needs to be added to storage
   */
  void addAuditorium(Auditorium auditorium);

  /**
   * Returns {@code Auditorium} from the storage.
   *
   * @param name of auditorium that used as search criteria
   */
  Auditorium findByName(String name);

  /** Returns set of all auditoriums. */
  Set<Auditorium> getAll();

  /** Remove {@link Auditorium} from storage. */
  void remove(Auditorium auditorium);
}
