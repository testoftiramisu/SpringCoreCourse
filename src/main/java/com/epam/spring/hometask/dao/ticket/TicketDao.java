package com.epam.spring.hometask.dao.ticket;

import com.epam.spring.hometask.domain.Ticket;

import java.util.Collection;

public interface TicketDao {

  /** Returns all {@link Ticket} objects. */
  Collection<Ticket> getAll();

  /**
   * Returns an {@link Ticket} object by id.
   *
   * @param id id of the object
   */
  Ticket getById(Long id);

  /**
   * Saves an {@link Ticket}.
   *
   * @param ticket that needs to be saved
   * @return id of saved {@link Ticket}
   */
  Long save(Ticket ticket);

  /**
   * Returns given {@link Ticket}
   *
   * @param ticket for removing
   */
  void remove(Ticket ticket);
}
