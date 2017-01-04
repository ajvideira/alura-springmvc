package br.com.ajvideira.cursos.alura.springmvc.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import br.com.ajvideira.cursos.alura.springmvc.model.Usuario;

public class SuccessLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		Usuario usuario = (Usuario)authentication.getPrincipal();
		System.out.println("usuario: " + usuario.getEmail());
		System.out.println("Locale: " + usuario.getLocale());
		Locale locale = new Locale(usuario.getLocale().split("_")[0], usuario.getLocale().split("_")[1]);
        
        localeResolver = new CookieLocaleResolver();
        localeResolver.setLocale(request, response, locale);
		
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, localeResolver);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
