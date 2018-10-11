package com.epam.spring.hometask.service.id;

import org.springframework.stereotype.Component;

@Component
public interface IdGeneratorService {

  /** Returns generated id as Long. */
  Long generateId();
}
