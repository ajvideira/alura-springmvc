package br.com.ajvideira.cursos.alura.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		System.out.println("Entrando no home.index");
		
		return "home";
	}

}
