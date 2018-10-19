package com.epam.spring.hometask.service.id;

import org.springframework.stereotype.Component;

import java.util.Random;

/** Generates random {@link Long} value in range from {@code BASE} to {@code SEED}. */
@Component
public class RandomIdGeneratorService implements IdGeneratorService {
  private static final long BASE = 100_000L;
  private Long id;

  /** Returns randomly generated id. */
  @Override
  public Long getId() {
    Random random = new Random();
    return Math.abs(random.nextLong()) + BASE;
  }
}
