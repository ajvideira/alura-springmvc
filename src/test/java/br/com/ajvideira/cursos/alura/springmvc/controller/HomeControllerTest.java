package br.com.ajvideira.cursos.alura.springmvc.controller;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.ajvideira.cursos.alura.springmvc.configuration.AppWebConfiguration;
import br.com.ajvideira.cursos.alura.springmvc.configuration.DataSourceConfigurationTest;
import br.com.ajvideira.cursos.alura.springmvc.configuration.JPAConfiguration;
import br.com.ajvideira.cursos.alura.springmvc.configuration.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void shouldReturnToHome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/home.jsp"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"));
	}
	
	@Test
	public void shouldOnlyAdminUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
			.with(SecurityMockMvcRequestPostProcessors
						.user("usuario@email.com").password("senha").roles("USUARIO")))
			.andExpect(MockMvcResultMatchers.status().is(403));
	}
	
}
