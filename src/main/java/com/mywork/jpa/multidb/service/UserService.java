package com.mywork.jpa.multidb.service;

import com.mywork.jpa.multidb.controller.dto.DtoUser;
import com.mywork.jpa.multidb.repo.domain.User;

public interface UserService {

  User saveUser(DtoUser dtoUser);

  DtoUser findUser(Long id, String countryCode);

}
