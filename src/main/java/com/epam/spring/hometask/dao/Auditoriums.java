package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Auditorium;
import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Auditoriums {

  private static Map<Long, Auditorium> auditoriums;

  public Auditoriums() {
    auditoriums = new HashMap<>();
  }

  public Set<Auditorium> getAll() {
    return ImmutableSet.copyOf(auditoriums.values());
  }
}
