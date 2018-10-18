package com.epam.spring.hometask.domain;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public class User extends DomainObject {

  private String firstName;
  private String lastName;
  private String email;
  private LocalDate birthday;
  private NavigableSet<Ticket> tickets = new TreeSet<>();

  public User() {}

  public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  /** Returns {@link User} first name. */
  public String getFirstName() {
    return firstName;
  }

  /** Sets {@link User} first name. */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /** Returns {@link User} birthday date. */
  public LocalDate getBirthday() {
    return birthday;
  }

  /** Sets {@link User} birthday date. */
  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  /** Returns {@link User} last name. */
  public String getLastName() {
    return lastName;
  }

  /** Sets {@link User} last name. */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /** Returns {@link User} email. */
  public String getEmail() {
    return email;
  }

  /** Sets {@link User} email. */
  public void setEmail(String email) {
    this.email = email;
  }

  /** Returns {@link User} tickets. */
  public NavigableSet<Ticket> getTickets() {
    return tickets;
  }

  /** Sets {@link User} ticket. */
  public void setTickets(NavigableSet<Ticket> tickets) {
    this.tickets = tickets;
  }

  /** Returns {@link User} hashcode. */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email);
  }

  /** Compare {@link User} with other object. */
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
    User other = (User) obj;
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (firstName == null) {
      if (other.firstName != null) {
        return false;
      }
    } else if (!firstName.equals(other.firstName)) {
      return false;
    }
    if (lastName == null) {
      return other.lastName == null;
    } else {
      return lastName.equals(other.lastName);
    }
  }
}
