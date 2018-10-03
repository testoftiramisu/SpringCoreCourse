package com.epam.spring.hometask.dao.ticket;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CinemaTicketDao implements TicketDao {
  private Map<Long, Ticket> tickets;
  private IdGeneratorService idGeneratorService;

  public CinemaTicketDao(IdGeneratorService idGeneratorService) {
    this.idGeneratorService = idGeneratorService;
    tickets = new HashMap<>();
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

  @Override
  public Ticket getById(Long id) {
    if (tickets.containsKey(id)) {
      return tickets.get(id);
    }
    return null;
  }

  @Override
  public Long save(Ticket ticket) {
    Long id = idGeneratorService.generateId();

    while (tickets.containsKey(id)) {
      id = idGeneratorService.generateId();
    }

    tickets.put(id, ticket);
    return id;
  }

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
