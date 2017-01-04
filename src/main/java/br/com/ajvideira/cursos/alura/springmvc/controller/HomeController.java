package br.com.ajvideira.cursos.alura.springmvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import br.com.ajvideira.cursos.alura.springmvc.dao.ProdutoDAO;
import br.com.ajvideira.cursos.alura.springmvc.dao.UsuarioDAO;
import br.com.ajvideira.cursos.alura.springmvc.model.Produto;
import br.com.ajvideira.cursos.alura.springmvc.model.Role;
import br.com.ajvideira.cursos.alura.springmvc.model.Usuario;

@Controller
@RequestScope
public class HomeController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		//LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        //localeResolver.setLocale(request, response, Locale.US);
		
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping("/inicializar-app")
	public ModelAndView inicializarApp(HttpServletRequest request, HttpServletResponse response) {
		
		Usuario usuario = new Usuario(); 
	    usuario.setNome("Administrador");
	    usuario.setEmail("admin@email.com");
	    usuario.setLocale("pt_BR");
	    usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
	    usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		
	    usuarioDAO.gravar(usuario);
	    
		return index(request, response);
	}

}
