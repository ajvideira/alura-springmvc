package br.com.ajvideira.cursos.alura.springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.ajvideira.cursos.alura.springmvc.dao.UsuarioDAO;
import br.com.ajvideira.cursos.alura.springmvc.handler.SuccessLoginHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/inicializar-app").permitAll()
			.antMatchers("/carrinho").permitAll()
			.antMatchers("/produtos/detalhe/**").permitAll()			
			.antMatchers("/resources/**").permitAll()
			.antMatchers(HttpMethod.POST, "/produtos/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/produtos/**").hasRole("ADMIN")
			.antMatchers("/produtos/**").permitAll()
			.antMatchers("**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").defaultSuccessUrl("/", true).successHandler(new SuccessLoginHandler()).permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDAO).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
