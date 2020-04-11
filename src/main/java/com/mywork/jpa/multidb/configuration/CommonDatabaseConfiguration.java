package com.mywork.jpa.multidb.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;

@Configuration
public class CommonDatabaseConfiguration {

  @Bean(name = "jpaFactory")
  public JPAFactory jpaFactory(@Qualifier("euEntityManager") EntityManager euEntityManager,
                               @Qualifier("apEntityManager") EntityManager apEntityManager) {
    return new JPAFactory(euEntityManager, apEntityManager);
  }

  @Bean(name = "chainedTransactionManager")
  public ChainedTransactionManager chainedTransactionManager(@Qualifier("euTransactionManager") PlatformTransactionManager euTransactionManager,
                                                             @Qualifier("apTransactionManager") PlatformTransactionManager apTransactionManager) {
    return new ChainedTransactionManager(euTransactionManager, apTransactionManager);
  }
}
