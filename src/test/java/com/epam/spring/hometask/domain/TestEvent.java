package com.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class TestEvent {
  private Event event;
  private Auditorium auditorium;

  @Before
  public void initEvent() {
    event = new Event();
    event.setBasePrice(1.1);
    event.setName("aaa");
    event.setRating(EventRating.HIGH);

    LocalDateTime now = LocalDateTime.now();

    event.addAirDateTime(now);
    event.addAirDateTime(now.plusDays(1));
    event.addAirDateTime(now.plusDays(2));

    auditorium = new Auditorium("test", "10", Collections.emptySet());
  }

  @Test
  public void testAddRemoveAirDates() {
    int size = event.getAirDates().size();

    LocalDateTime date = LocalDateTime.now().plusDays(5);

    event.addAirDateTime(date);

    assertThat(event.getAirDates().size()).isEqualTo(size + 1);
    assertThat(event.getAirDates()).contains(date);

    event.removeAirDateTime(date);

    assertThat(event.getAirDates().size()).isEqualTo(size);
    assertThat(event.getAirDates()).doesNotContain(date);
  }

  @Test
  public void testCheckAirDates() {
    LocalDate now = LocalDate.now();

    assertThat(event.airsOnDates(now, now.plusDays(10))).isTrue();
    assertThat(event.airsOnDates(now.minusDays(10), now.plusDays(10))).isTrue();
    assertThat(event.airsOnDates(now.plusDays(1), now.plusDays(1))).isTrue();
    assertThat(event.airsOnDates(now.minusDays(10), now.minusDays(5))).isFalse();
  }

  @Test
  public void testCheckAirTimes() {
    LocalDateTime time = LocalDateTime.now().plusHours(4);
    event.addAirDateTime(time);

    assertThat(event.airsOnDateTime(time)).isTrue();

    time = time.plusHours(30);
    assertThat(event.airsOnDateTime(time)).isFalse();
  }

  @Test
  public void testAddRemoveAuditoriums() {
    LocalDateTime time = event.getAirDates().first();
    assertThat(event.getAuditoriums()).isEmpty();

    event.assignAuditorium(time, auditorium);
    assertThat(event.getAuditoriums()).isNotEmpty();

    event.removeAuditoriumAssignment(time);
    assertThat(event.getAuditoriums()).isEmpty();
  }

  @Test
  public void testAddRemoveAuditoriumsWithAirDates() {
    LocalDateTime time = LocalDateTime.now().plusDays(10);
    assertThat(event.getAuditoriums()).isEmpty();

    event.addAirDateTime(time, auditorium);
    assertThat(event.getAuditoriums()).isNotEmpty();

    event.removeAirDateTime(time);
    assertThat(event.getAuditoriums()).isEmpty();
  }

  @Test
  public void testNotAddAuditoriumWithoutCorrectDate() {
    LocalDateTime time = LocalDateTime.now().plusDays(10);
    boolean result = event.assignAuditorium(time, auditorium);

    assertThat(result).isFalse();
    assertThat(event.getAuditoriums()).isEmpty();

    result = event.removeAirDateTime(time);
    assertThat(result).isFalse();
    assertThat(event.getAuditoriums()).isEmpty();
  }
}
