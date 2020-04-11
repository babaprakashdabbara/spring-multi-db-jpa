package com.mywork.jpa.multidb.repo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String firstName;

  private String lastName;

  private String country;

  private String company;

  private String role;

  public User() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCountry() {
    return country;
  }

  public String getCompany() {
    return company;
  }

  public String getRole() {
    return role;
  }
}
