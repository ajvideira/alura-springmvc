package br.com.ajvideira.cursos.alura.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		carrinhoCompras.add(criarItem(produtoId, tipoPreco));
		
		return modelAndView;
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(Integer produtoId, TipoPreco tipoPreco, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		carrinhoCompras.remove(criarItem(produtoId, tipoPreco));
		
		redirectAttributes.addFlashAttribute("mensagem", "Produto removido com sucesso!");
		redirectAttributes.addFlashAttribute("tipoMensagem", "success");
		
		return modelAndView;
	}
	
	@RequestMapping("/update-amount")
	public ModelAndView updateAmount(Integer produtoId, TipoPreco tipoPreco, Integer quantidade, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		carrinhoCompras.updateAmount(criarItem(produtoId, tipoPreco), quantidade);
		
		redirectAttributes.addFlashAttribute("mensagem", "Quantidade atualizada com sucesso!");
		redirectAttributes.addFlashAttribute("tipoMensagem", "success");
		
		return modelAndView;
	}

	private CarrinhoItem criarItem(Integer produtoId, TipoPreco tipoPreco) {
		
		CarrinhoItem item = new CarrinhoItem();
		item.setProduto(produtoDAO.find(produtoId));
		item.setTipoPreco(tipoPreco);
		
		return item;
	}
	
}
