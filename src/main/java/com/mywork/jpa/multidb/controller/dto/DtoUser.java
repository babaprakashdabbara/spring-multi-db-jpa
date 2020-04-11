package com.mywork.jpa.multidb.controller.dto;

public class DtoUser {

  private String firstName;

  private String lastName;

  private String country;

  private String company;

  private String role;

  public DtoUser() {
  }

  public DtoUser(String firstName, String lastName, String country, String company, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.company = company;
    this.role = role;
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
