package com.mywork.jpa.multidb.service;

import com.mywork.jpa.multidb.configuration.JPAFactory;
import com.mywork.jpa.multidb.configuration.Localityconfiguration;
import com.mywork.jpa.multidb.controller.dto.DtoUser;
import com.mywork.jpa.multidb.repo.UserServiceRepository;
import com.mywork.jpa.multidb.repo.domain.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {

  private UserServiceRepository userServiceRepository;
  private final JPAFactory jpaFactory;
  private final Localityconfiguration localityconfiguration;

  public UserServiceImpl(UserServiceRepository userServiceRepository,
                         JPAFactory jpaFactory,
                         Localityconfiguration localityconfiguration) {
    this.userServiceRepository = userServiceRepository;
    this.jpaFactory = jpaFactory;
    this.localityconfiguration = localityconfiguration;
  }

  @Override
  public User saveUser(DtoUser dtoUser) {
    return userServiceRepository.save(createUser(dtoUser));
  }

  @Override
  public DtoUser findUser(Long id, String countryCode) {
    setUserServiceRepository(countryCode);
    final Optional<User> user = userServiceRepository.findById(id);
    return user.map(value -> new DtoUser(value.getFirstName(), value.getLastName(), value.getCountry(), value.getCompany(), value.getRole())).orElse(null);
  }

  private User createUser(DtoUser dtoUser) {
    User user = new User();
    user.setCompany(dtoUser.getCompany());
    user.setCountry(dtoUser.getCountry());
    user.setFirstName(dtoUser.getFirstName());
    user.setLastName(dtoUser.getLastName());
    user.setRole(dtoUser.getRole());
    return user;
  }

  private void setUserServiceRepository(String country) {
    final String locality = localityconfiguration.localityOfCountry(country);
    userServiceRepository = jpaFactory.getJpaFactory(locality).getRepository(UserServiceRepository.class);
  }
}
