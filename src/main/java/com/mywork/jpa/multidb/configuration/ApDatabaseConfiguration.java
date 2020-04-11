package com.mywork.jpa.multidb.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "apEntityManager",
        transactionManagerRef = "apTransactionManager")
public class ApDatabaseConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "ap.datasource")
  DataSource apDataSource() {
    return new HikariDataSource();
  }

  @Bean(name = "apEntityManager")
  LocalContainerEntityManagerFactoryBean apEntityManager() {
    final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    localContainerEntityManagerFactoryBean.setDataSource(apDataSource());
    localContainerEntityManagerFactoryBean.setPackagesToScan("com.mywork.jpa.multidb.repo");
    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
    return localContainerEntityManagerFactoryBean;
  }

  @Bean(name = "apTransactionManager")
  PlatformTransactionManager apTransactionManager() {
    final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(apEntityManager().getObject());
    return jpaTransactionManager;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    properties.setProperty("connection.release_mode", "auto");
    return properties;
  }
}
