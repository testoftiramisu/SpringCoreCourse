package com.epam.spring.hometask.dao.ticket;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CinemaTicketDao implements TicketDao {
  private Map<Long, Ticket> tickets = new HashMap<>();
  private IdGeneratorService idGeneratorService;

  public CinemaTicketDao(IdGeneratorService idGeneratorService) {
    this.idGeneratorService = idGeneratorService;
  }

  /**
   * Stores all {@link Ticket} from provided collection.
   *
   * @param tickets Collection of {@link Ticket}
   */
  public void setTickets(Collection<Ticket> tickets) {
    tickets.forEach(this::save);
  }

  /** Returns all {@link Ticket} objects. */
  @Override
  public Collection<Ticket> getAll() {
    return ImmutableSet.copyOf(tickets.values());
  }

  /**
   * Returns an {@link Ticket} object by id.
   *
   * @param id id of the object
   */
  @Override
  public Ticket getById(Long id) {
    if (tickets.containsKey(id)) {
      return tickets.get(id);
    }
    return null;
  }

  /**
   * Saves an {@link Ticket}.
   *
   * @param ticket that needs to be saved
   * @return id of saved {@link Ticket}
   */
  @Override
  public Long save(Ticket ticket) {
    Long id = idGeneratorService.getId();
    tickets.put(id, ticket);
    return id;
  }

  /**
   * Returns given {@link Ticket}
   *
   * @param ticket for removing
   */
  @Override
  public void remove(Ticket ticket) {
    for (Long id : tickets.keySet()) {
      Ticket currentTicket = tickets.get(id);
      if (currentTicket.equals(ticket)) {
        tickets.remove(id);
        break;
      }
    }
  }
}
