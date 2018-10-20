package com.epam.spring.hometask.domain;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Auditorium {

  private String name;
  private long numberOfSeats;
  private Set<Long> vipSeats;

  public Auditorium() {}

  public Auditorium(String name, String numberOfSeats, Set<Long> vipSeats) {
    this.name = name;
    this.numberOfSeats = Long.parseLong(numberOfSeats);
    this.vipSeats = vipSeats;
  }

  /**
   * Counts how many vip seats are there in supplied <code>seats</code>
   *
   * @param seats Seats to process
   * @return number of vip seats in request
   */
  public long countVipSeats(Collection<Long> seats) {
    return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
  }

  /** Returns auditoriums name. */
  public String getName() {
    return name;
  }

  /** Sets auditoriums name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Returns number of seats in auditorium. */
  public long getNumberOfSeats() {
    return numberOfSeats;
  }

  /** Sets number of seats in auditorium. */
  public void setNumberOfSeats(long numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  /** Returns all seats in auditorium. */
  public Set<Long> getAllSeats() {
    return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
  }

  /** Returns all Vip seats in auditorium. */
  public Set<Long> getVipSeats() {
    return vipSeats;
  }

  /** Sets Vip seats for auditorium. */
  public void setVipSeats(Set<Long> vipSeats) {
    this.vipSeats = vipSeats;
  }

  /** Returns hashcode. */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Auditorium other = (Auditorium) obj;
    if (name == null) {
      return other.name == null;
    } else {
      return name.equals(other.name);
    }
  }
}
