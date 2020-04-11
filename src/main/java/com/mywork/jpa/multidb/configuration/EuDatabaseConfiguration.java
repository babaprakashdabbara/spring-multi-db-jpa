package com.mywork.jpa.multidb.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mywork.jpa.multidb.repo",
        entityManagerFactoryRef = "euEntityManager",
        transactionManagerRef = "euTransactionManager")
public class EuDatabaseConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "eu.datasource")
  DataSource euDataSource() {
    return new HikariDataSource();
  }

  @Bean(name = "euEntityManager")
  @Primary
  LocalContainerEntityManagerFactoryBean euEntityManager() {
    final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    localContainerEntityManagerFactoryBean.setDataSource(euDataSource());
    localContainerEntityManagerFactoryBean.setPackagesToScan("com.mywork.jpa.multidb.repo");
    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
    return localContainerEntityManagerFactoryBean;
  }

  @Bean(name = "euTransactionManager")
  @Primary
  PlatformTransactionManager euTransactionManager() {
    final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(euEntityManager().getObject());
    return jpaTransactionManager;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    properties.setProperty("connection.release_mode", "auto");
    return properties;
  }
}
