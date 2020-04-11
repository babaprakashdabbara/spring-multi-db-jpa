package com.mywork.jpa.multidb.repo;

import com.mywork.jpa.multidb.repo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserServiceRepository extends CrudRepository<User, Long> {}