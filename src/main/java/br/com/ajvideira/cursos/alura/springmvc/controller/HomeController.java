package br.com.ajvideira.cursos.alura.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import br.com.ajvideira.cursos.alura.springmvc.dao.ProdutoDAO;
import br.com.ajvideira.cursos.alura.springmvc.model.Produto;

@Controller
@RequestScope
public class HomeController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping("/")
	public ModelAndView index() {
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}

}
