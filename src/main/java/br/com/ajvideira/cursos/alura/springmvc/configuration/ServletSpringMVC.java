package br.com.ajvideira.cursos.alura.springmvc.configuration;

import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionTrackingMode;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		HashSet<SessionTrackingMode> set = new HashSet<SessionTrackingMode>();
        set.add(SessionTrackingMode.COOKIE);
        servletContext.setSessionTrackingModes(set);
        //servletContext.setInitParameter("spring.profiles.active", "dev");
        if (System.getProperty("spring.profiles.active") == null) {
        	servletContext.setInitParameter("spring.profiles.active", "dev");
        }
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "3128");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "3128");
		
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class, JPAProductionConfiguration.class, SecurityConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encondingFilter = new CharacterEncodingFilter();
		encondingFilter.setEncoding("UTF-8");
		
		OpenEntityManagerInViewFilter entityManagerFilter = new OpenEntityManagerInViewFilter();
		
		return new Filter[]{encondingFilter, entityManagerFilter};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
}
