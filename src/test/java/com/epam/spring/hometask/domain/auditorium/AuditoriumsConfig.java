package com.epam.spring.hometask.domain.auditorium;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.properties.PropertiesConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@Import(PropertiesConfig.class)
public class AuditoriumsConfig {

  @Value("${imax.name}")
  private String imaxName;

  @Value("${imax.numberOfSeats}")
  private String imaxNumberOfSeats;

  @Value("${imax.vipSeats}")
  private String imaxVipSeats;

  @Value("${multikino.name}")
  private String multikinoName;

  @Value("${multikino.numberOfSeats}")
  private String multikinoNumberOfSeats;

  @Value("${multikino.vipSeats}")
  private String multikinoVipSeats;

  @Bean(name = "imax")
  @Scope("prototype")
  public Auditorium getImax() {
    Set<Long> vipSeats =
        StringUtils.commaDelimitedListToSet(imaxVipSeats)
            .stream()
            .map(Long::parseLong)
            .collect(Collectors.toSet());

    return new Auditorium(imaxName, imaxNumberOfSeats, vipSeats);
  }

  @Bean(name = "multikino")
  @Scope("prototype")
  public Auditorium getMultikino() {
    Set<Long> vipSeats =
        StringUtils.commaDelimitedListToSet(multikinoVipSeats)
            .stream()
            .map(Long::parseLong)
            .collect(Collectors.toSet());

    return new Auditorium(multikinoName, multikinoNumberOfSeats, vipSeats);
  }
}
