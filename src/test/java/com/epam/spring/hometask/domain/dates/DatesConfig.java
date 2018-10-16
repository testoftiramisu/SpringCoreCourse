package com.epam.spring.hometask.domain.dates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DatesConfig {

  @Bean(name = "dateTimeNow")
  public LocalDateTime getDateTimeNow() {
    return LocalDateTime.now();
  }
}
