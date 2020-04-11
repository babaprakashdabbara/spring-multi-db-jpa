package com.mywork.jpa.multidb.configuration;

import com.mywork.jpa.multidb.repo.UserServiceRepository;
import com.mywork.jpa.multidb.service.UserService;
import com.mywork.jpa.multidb.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

  @Bean
  public UserService userService(UserServiceRepository userServiceRepository,
                                 JPAFactory jpaFactory,
                                 Localityconfiguration localityconfiguration) {
    return new UserServiceImpl(userServiceRepository, jpaFactory, localityconfiguration);
  }
}
