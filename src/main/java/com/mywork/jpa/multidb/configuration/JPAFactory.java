package com.mywork.jpa.multidb.configuration;

import com.mywork.jpa.multidb.errorhandling.UnsupportedLocalityException;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

public class JPAFactory {

  private EntityManager euEntityManager;
  private EntityManager apEntityManager;

  public JPAFactory(EntityManager euEntityManager, EntityManager apEntityManager) {
    this.euEntityManager = euEntityManager;
    this.apEntityManager = apEntityManager;
  }

  public JpaRepositoryFactory getJpaFactory(String locality) {
    EntityManager entityManager = getEntityManager(locality);
    return new JpaRepositoryFactory(entityManager);
  }

  private EntityManager getEntityManager(String locality) {
    if ("EU".equals(locality)) {
      return euEntityManager;
    }

    if ("AP".equals(locality)) {
      return apEntityManager;
    }

    throw new UnsupportedLocalityException("Unsupported locality exception");

  }
}
