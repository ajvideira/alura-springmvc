package br.com.ajvideira.cursos.alura.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import br.com.ajvideira.cursos.alura.springmvc.dao.ProdutoDAO;
import br.com.ajvideira.cursos.alura.springmvc.model.CarrinhoCompras;
import br.com.ajvideira.cursos.alura.springmvc.model.CarrinhoItem;
import br.com.ajvideira.cursos.alura.springmvc.model.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@RequestScope
public class CarrinhoController {

	@Autowired
	ProdutoDAO produtoDAO;
	
	@Autowired
	CarrinhoCompras carrinhoCompras;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
		
		carrinhoCompras.add(criarItem(produtoId, tipoPreco));
		
		return modelAndView;
	}

	private CarrinhoItem criarItem(Integer produtoId, TipoPreco tipoPreco) {
		
		CarrinhoItem item = new CarrinhoItem();
		item.setProduto(produtoDAO.find(produtoId));
		item.setTipo(tipoPreco);
		
		return item;
	}
	
}
