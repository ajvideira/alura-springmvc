package br.com.ajvideira.cursos.alura.springmvc.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Map<String, String> additionalProperties) {
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setDataSource(dataSource);
		factory.setJpaPropertyMap(additionalProperties);
		factory.setPackagesToScan("br.com.ajvideira.cursos.alura.springmvc.model");
		
		return factory;
	}

	@Bean
	@Profile(value={"test", "dev"})
	private Map<String, String> additionalProperties() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.hbm2ddl.auto", "update");
		return map;
	}

	@Bean
	@Profile("dev")
	private DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("postgres");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/alura_springmvc");
		dataSource.setDriverClassName("org.postgresql.Driver");
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}
