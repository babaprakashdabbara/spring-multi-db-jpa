package com.mywork.jpa.multidb.errorhandling;

public class UnsupportedLocalityException extends RuntimeException {
  public UnsupportedLocalityException(String message) {
    super(message);
  }
}
