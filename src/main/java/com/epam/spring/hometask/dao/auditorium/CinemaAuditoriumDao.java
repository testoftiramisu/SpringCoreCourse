package com.epam.spring.hometask.dao.auditorium;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CinemaAuditoriumDao implements AuditoriumDao {
  private Map<Long, Auditorium> auditoriums;
  private IdGeneratorService idGeneratorService;

  public CinemaAuditoriumDao(IdGeneratorService idGeneratorService) {
    auditoriums = new HashMap<>();
    this.idGeneratorService = idGeneratorService;
  }

  /** Returns immutable set of all auditoriums. */
  @Override
  public Set<Auditorium> getAll() {
    return ImmutableSet.copyOf(auditoriums.values());
  }

  /**
   * Adds given {@link Auditorium} to the storage and assign it an autogenerated id.
   *
   * <p>If generated id is already present in the storage, generates new one.
   *
   * @param auditorium which needs to be added to storage
   */
  @Override
  public void addAuditorium(Auditorium auditorium) {
    Long id = idGeneratorService.generateId();

    while (auditoriums.containsKey(id)) {
      id = idGeneratorService.generateId();
    }

    auditoriums.put(id, auditorium);
  }

  /**
   * Adds all {@link Auditorium} from provided Collection to the storage.
   *
   * @param auditoriums collection of {@link Auditorium} that needs to be added to storage
   */
  @Override
  public void setAuditoriums(Collection<Auditorium> auditoriums) {
    auditoriums.forEach(this::addAuditorium);
  }

  /**
   * Returns {@code Auditorium} if auditorium with provided name is found in the storage .
   *
   * <p>Returns null otherwise.
   *
   * @param name of auditorium that used as search criteria
   */
  @Override
  public Auditorium findByName(String name) {
    return getAll()
        .stream()
        .filter(auditorium -> auditorium.getName().equals(name))
        .findAny()
        .orElse(null);
  }
}