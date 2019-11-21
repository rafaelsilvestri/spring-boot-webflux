package com.github.rafaelsilvestri.webflux;

import java.util.UUID;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration
@EnableJdbcRepositories
@PropertySources({@PropertySource("classpath:application.properties"),
    @PropertySource(value = "file:${config.file}", ignoreResourceNotFound = true)})
public class PersistenceConfig extends AbstractJdbcConfiguration {

  @Value("${dbHost}")
  private String dbHost;
  @Value("${dbPort}")
  private String dbPort;
  @Value("${dbUser}")
  private String dbUser;
  @Value("${dbPwd}")
  private String dbPwd;
  @Value("${dbName}")
  private String dbName;

  @Bean
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName("org.postgresql.Driver");
    ds.setUrl(String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName));
    ds.setUsername(dbUser);
    ds.setPassword(dbPwd);
    return ds;
  }

  /**
   * used internally to submit SQL statements to the database.
   */
  @Bean
  NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * support for transactions that span more than a single statement
   */
  @Bean
  PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  ApplicationListener<BeforeSaveEvent> beforeSaveEventListener() {
    return event -> {
      if (event.getEntity() instanceof AbstractEntity) {
        AbstractEntity entity = (AbstractEntity) event.getEntity();
        if (entity.getId() == null) {
          entity.setId(UUID.randomUUID());
        }
      }
    };
  }
}
