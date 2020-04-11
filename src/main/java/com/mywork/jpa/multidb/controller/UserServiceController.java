package com.mywork.jpa.multidb.controller;

import com.mywork.jpa.multidb.controller.dto.DtoUser;
import com.mywork.jpa.multidb.repo.domain.User;
import com.mywork.jpa.multidb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceController {

  public UserServiceController(UserService userService) {
    this.userService = userService;
  }

  private final UserService userService;

  @PostMapping(path = "/saveuser")
  public ResponseEntity<Long> saveUser(@RequestBody DtoUser user) {
    final User savedUser = userService.saveUser(user);
    return new ResponseEntity<>(savedUser.getId(), HttpStatus.CREATED);
  }

  @GetMapping(path = "/{countrycode}/{userid}")
  public ResponseEntity<DtoUser> findUser(@PathVariable Long userid, @PathVariable String countrycode) {
    final DtoUser user = userService.findUser(userid, countrycode);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
