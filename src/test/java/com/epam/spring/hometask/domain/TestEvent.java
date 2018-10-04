package com.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class TestEvent {
  private final String EVENT_NAME = "Test Event Name";
  private Event event;
  private Auditorium auditorium;
  private LocalDateTime nowTime;
  private LocalDate nowDate;

  @Before
  public void initEvent() {
    event = new Event();
    event.setBasePrice(1.1);
    event.setName(EVENT_NAME);
    event.setRating(EventRating.HIGH);

    nowTime = LocalDateTime.now();
    nowDate = LocalDate.now();

    event.addAirDateTime(nowTime);
    event.addAirDateTime(nowTime.plusDays(1));
    event.addAirDateTime(nowTime.plusDays(2));

    auditorium = new Auditorium("test", "10", Collections.emptySet());
  }

  @Test
  public void shouldAddAirDateTime() {
    assertThat(event.addAirDateTime(nowTime.plusDays(1))).isFalse();
    assertThat(event.addAirDateTime(nowTime.plusDays(10))).isTrue();
  }

  @Test
  public void shouldReturnName() {
    assertThat(event.getName()).isEqualTo(EVENT_NAME);
  }

  @Test
  public void shouldStoreAirDates() {
    NavigableSet<LocalDateTime> airDates = new TreeSet<>();
    airDates.add(nowTime.plusDays(5));
    airDates.add(nowTime.plusDays(10));

    event.setAirDates(airDates);

    assertThat(event.getAirDates()).containsAll(airDates);
  }

  @Test
  public void shouldReturnBasePrice() {
    assertThat(event.getBasePrice()).isEqualTo(1.1);
  }

  @Test
  public void shouldReturnRating() {
    assertThat(event.getRating()).isEqualTo(EventRating.HIGH);
  }

  @Test
  public void shouldAddAndRemoveAirDates() {
    int size = event.getAirDates().size();

    nowTime = nowTime.plusDays(5);
    event.addAirDateTime(nowTime);

    assertThat(event.getAirDates().size()).isEqualTo(size + 1);
    assertThat(event.getAirDates()).contains(nowTime);

    event.removeAirDateTime(nowTime);

    assertThat(event.getAirDates().size()).isEqualTo(size);
    assertThat(event.getAirDates()).doesNotContain(nowTime);
  }

  @Test
  public void checkAirDates() {
    assertThat(event.airsOnDate(nowDate)).isTrue();
    assertThat(event.airsOnDate(nowDate.plusDays(1))).isTrue();
    assertThat(event.airsOnDate(nowDate.plusDays(2))).isTrue();
    assertThat(event.airsOnDate(nowDate.plusDays(10))).isFalse();
  }

  @Test
  public void checkAirDate() {
    assertThat(event.airsOnDates(nowDate, nowDate.plusDays(10))).isTrue();
    assertThat(event.airsOnDates(nowDate.minusDays(10), nowDate.plusDays(10))).isTrue();
    assertThat(event.airsOnDates(nowDate.plusDays(1), nowDate.plusDays(1))).isTrue();
    assertThat(event.airsOnDates(nowDate.minusDays(10), nowDate.minusDays(5))).isFalse();
  }

  @Test
  public void checkAirTimes() {
    nowTime = nowTime.plusHours(4);
    event.addAirDateTime(nowTime);

    assertThat(event.airsOnDateTime(nowTime)).isTrue();

    nowTime = nowTime.plusHours(30);
    assertThat(event.airsOnDateTime(nowTime)).isFalse();
  }

  @Test
  public void shouldAddAndRemoveAuditoriums() {
    LocalDateTime time = event.getAirDates().first();
    assertThat(event.getAuditoriums()).isEmpty();

    event.assignAuditorium(time, auditorium);
    assertThat(event.getAuditoriums()).isNotEmpty();

    event.removeAuditoriumAssignment(time);
    assertThat(event.getAuditoriums()).isEmpty();
  }

  @Test
  public void shouldAddAndRemoveAuditoriumsWithAirDates() {
    nowTime = nowTime.plusDays(10);
    assertThat(event.getAuditoriums()).isEmpty();

    event.addAirDateTime(nowTime, auditorium);
    assertThat(event.getAuditoriums()).isNotEmpty();

    event.removeAirDateTime(nowTime);
    assertThat(event.getAuditoriums()).isEmpty();
  }

  @Test
  public void shouldNotAddAuditoriumWithoutCorrectDate() {
    nowTime = nowTime.plusDays(10);
    boolean result = event.assignAuditorium(nowTime, auditorium);

    assertThat(result).isFalse();
    assertThat(event.getAuditoriums()).isEmpty();

    result = event.removeAirDateTime(nowTime);
    assertThat(result).isFalse();
    assertThat(event.getAuditoriums()).isEmpty();
  }
}
