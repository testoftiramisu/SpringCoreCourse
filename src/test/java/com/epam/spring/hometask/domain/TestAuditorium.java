package com.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestAuditorium {
  private Auditorium auditorium;

  @Before
  public void setUp() throws Exception {
    auditorium = new Auditorium("test", "10", Stream.of(1L, 2L, 3L).collect(Collectors.toSet()));
  }

  @Test
  public void shouldCountVipSeats() {
    assertEquals(0, auditorium.countVipSeats(Arrays.asList(10L, 20L, 30L)));
    assertEquals(1, auditorium.countVipSeats(Arrays.asList(10L, 2L, 30L)));
    assertEquals(2, auditorium.countVipSeats(Arrays.asList(10L, 2L, 3L, 4L, 5L, 6L)));
  }

  @Test
  public void shouldReturnAllSeats() {
    assertEquals(10, auditorium.getAllSeats().size());
  }
}
