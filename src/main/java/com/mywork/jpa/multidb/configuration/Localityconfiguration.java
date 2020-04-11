package com.mywork.jpa.multidb.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "locality")
public class Localityconfiguration {

  private Map<String, String> mappings = new HashMap<>();

  public Map<String, String> getMappings() {
    return mappings;
  }

  public String localityOfCountry(String countryCode) {
    return mappings.get(countryCode);
  }
}
