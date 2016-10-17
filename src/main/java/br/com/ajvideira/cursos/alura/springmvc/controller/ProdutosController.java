package br.com.ajvideira.cursos.alura.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ajvideira.cursos.alura.springmvc.dao.ProdutoDAO;
import br.com.ajvideira.cursos.alura.springmvc.infra.FileSaver;
import br.com.ajvideira.cursos.alura.springmvc.model.Produto;
import br.com.ajvideira.cursos.alura.springmvc.model.TipoPreco;
import br.com.ajvideira.cursos.alura.springmvc.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult results, RedirectAttributes redirectAttributes) {
		
		System.out.println(sumario.getOriginalFilename());
		
		if (results.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		
		produtoDAO.salvar(produto);
		
		redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
		
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id){
	    ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
	    Produto produto = produtoDAO.find(id);
	    modelAndView.addObject("produto", produto);
	    return modelAndView;
	}
	
}
