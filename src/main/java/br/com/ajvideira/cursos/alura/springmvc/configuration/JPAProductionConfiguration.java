package br.com.ajvideira.cursos.alura.springmvc.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("prod")
public class JPAProductionConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	private Map<String, String> additionalProperties() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.hbm2ddl.auto", "update");
		return map;
	}

	@Bean
	private DataSource dataSource() throws URISyntaxException {
		
		URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://"+dbUrl.getHost()+":"+dbUrl.getPort()+dbUrl.getPath());
	    dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
	    dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);
		dataSource.setDriverClassName("org.postgresql.Driver");
		return dataSource;
	}
	
}
