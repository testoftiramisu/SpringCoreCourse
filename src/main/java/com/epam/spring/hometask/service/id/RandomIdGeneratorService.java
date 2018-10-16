package com.epam.spring.hometask.service.id;

import org.springframework.stereotype.Component;

import java.util.Random;

/** Generates random {@link Long} value in range from {@code BASE} to {@code SEED}. */
@Component
public class RandomIdGeneratorService implements IdGeneratorService {
  private static final long BASE = 100_000L;
  private static final long SEED = 100_000L;

  private final Random random = new Random(SEED);

  /** Returns randomly generated id. */
  @Override
  public Long generateId() {
    return random.nextLong() + BASE;
  }
}
