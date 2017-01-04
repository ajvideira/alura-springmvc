package br.com.ajvideira.cursos.alura.springmvc.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {

	@Bean
	@Profile("test")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("postgres");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/alura_springmvc_test");
		dataSource.setDriverClassName("org.postgresql.Driver");
		
		return dataSource;
	}
	
}
